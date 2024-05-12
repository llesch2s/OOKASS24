package Afg2RuntimeEnviornment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class RunComponent implements Runnable{

    private Object componentToBeRun = null;
    private Thread internalThread;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public void start(){
        internalThread = new Thread(this);
        internalThread.start();

    }

    public void stop() {
        try {
           componentToBeRun.getClass().getMethod("Stop").invoke(componentToBeRun);

        }catch (NoSuchMethodException name){

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        running.set(false);
    }
    public void setLogger(Logger logger) throws NoSuchMethodException {


            try {
                componentToBeRun.getClass().getMethod("setMylog", Logger.class).invoke(componentToBeRun,logger);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e){
                throw new NoSuchMethodException("Methode setLogger existiert nicht!!!");
            }


    }
    public void sendlogger() throws NoSuchMethodException {
        try {
            componentToBeRun.getClass().getMethod("sendMyLog").invoke(componentToBeRun);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e){
            System.out.println("Methode sendlogger existiert nicht!!!");
        } catch (NullPointerException n){
            System.out.println("Kein Logger gesetzt");
        }
    }
    @Override
    public void run() {
         running.set(true);
         try {
             componentToBeRun.getClass().getMethod("start").invoke(componentToBeRun);
         }catch (NullPointerException n){
             System.out.println("No Component deployed");
         } catch (NoSuchMethodException e) {
             throw new RuntimeException(e);
         } catch (InvocationTargetException e) {
             throw new RuntimeException(e);
         } catch (IllegalAccessException e) {
             throw new RuntimeException(e);
         }
        while(running.get()){

         }
    }
    public void deployComponentWithPath(String pathToJar){

        JarFile jarFile = null;
        URL[] urls = new URL[0];
        try {
            jarFile = new JarFile(pathToJar);
        } catch (java.io.IOException jie) {
            System.out.println("IoException");
        }
        Enumeration<JarEntry> e = jarFile.entries();
        try {
            urls = new URL[]{new URL("jar:file:" + pathToJar + "!/")};
        } catch (java.net.MalformedURLException jnmue) {
            System.out.println("MalformedURLException");
        }
        URLClassLoader cl = null;
        try {
            cl = URLClassLoader.newInstance(urls);
        }catch (NullPointerException nullPointerException){

        }

        try {
            URL url = cl.findResource("META-INF/MANIFEST.MF");
            Manifest manifest = new Manifest(url.openStream());
            Attributes attr = manifest.getMainAttributes();
            String value = attr.getValue("Main-Class");
            System.out.println(value);
            componentToBeRun = cl.loadClass(value).newInstance();

        } catch (ClassNotFoundException | IOException ex) {
            throw new RuntimeException(ex);
        } catch (NullPointerException npe){
               System.out.println("Kein Manifast MF vorhanden");
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }



}
