package Afg2RuntimeEnviornment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class RunComponent implements Runnable{

    private Object componentToBeRun = null;
    private Thread internalThread;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private BlockingQueue<QueueObject> linkedBlockingQueue = new LinkedBlockingQueue<>();
    public int getLinkedBlockingQueueSize() {
        return linkedBlockingQueue.size();
    }
    public void start(){
        internalThread = new Thread(this);
        internalThread.start();
    }

    public void stop() {
        try {
            linkedBlockingQueue.add(new QueueObject("Stop",new Object[]{}));
           componentToBeRun.getClass().getMethod("Stop").invoke(componentToBeRun);

        }catch (NoSuchMethodException name){

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        running.set(false);
    }
    public void runComponentMethod(String name,Object[] parameters){
                linkedBlockingQueue.add(new QueueObject(name,parameters));
    }
    public void setLogger(Logger logger) throws NoSuchMethodException {
            String className=logger.getClass().getName();
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
    private Class<?>[] generateClasses(Object[] parameters){
        Class<?>[] classes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            classes[i] = parameters[i].getClass();
        }
        return classes;
    }
    @Override
    public void run() {
        try {
            System.out.println("starting hotel");
            componentToBeRun.getClass().getMethod("start").invoke(componentToBeRun);
        }catch (NoSuchMethodException name){

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        running.set(true);


        while (running.get()) {
                 try {
                 QueueObject queueObject = linkedBlockingQueue.poll();
                 if (queueObject.getMethodName().equals("Stop")) {
                     stop();
                     continue;
                 }
                 if (queueObject.getParameters().length == 0) {
                     componentToBeRun.getClass().getMethod(queueObject.getMethodName()).invoke(componentToBeRun);
                 } else {
                     Object[] parameters = queueObject.getParameters();
                     Class<?>[] classes = generateClasses(parameters);
                     componentToBeRun.getClass().getMethod(queueObject.getMethodName(), classes).invoke(componentToBeRun, parameters);
                 }

                 System.out.println("Anzahl elemente in Queue:" + linkedBlockingQueue.size());
             }catch(NullPointerException n){

             } catch(InvocationTargetException e){
                 throw new RuntimeException(e);
             } catch(IllegalAccessException e){
                 throw new RuntimeException(e);
             } catch(NoSuchMethodException e){
                 throw new RuntimeException(e);
             } /*catch(InterruptedException e){
                 throw new RuntimeException(e);
             }*/
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
