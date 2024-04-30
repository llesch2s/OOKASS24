package Afg2RuntimeEnviornment;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class RunComponent implements Runnable{

    private Class componentToBeRun = null;
    private Thread internalThread;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public void start(){
        internalThread = new Thread(this);
        internalThread.start();

    }

    public void stop() {
        try {
            componentToBeRun.getMethod("stop",null);

        }catch (NoSuchMethodException nsme){

        }

        running.set(false);
    }
    @Override
    public void run() {
         running.set(true);
         try {
             componentToBeRun.getMethod("start",null);

         }catch (NullPointerException n){
             System.out.println("No Component deployed");
         } catch (NoSuchMethodException e) {
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
            componentToBeRun = cl.loadClass(value);

            //componentToBeRun = (MainClass) c.newInstance();

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
       // } catch (InstantiationException ex) {
       //     throw new RuntimeException(ex);
       // } catch (IllegalAccessException ex) {
       //     throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}
