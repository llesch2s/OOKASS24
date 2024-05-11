package Afg2RuntimeEnviornment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunEnvironment {
    private class Status{
        private int id;
        private String name;
        private String zustand;
        private String pathToJar;
        public Status(int id,String name,String zustand,String pathToJar){
            this.id=id;
            this.name=name;
            this.zustand=zustand;
        }
        public int getId(){
            return id;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public void setZustand(String zustand){
            this.zustand=zustand;
        }
        public String getZustand(){
            return zustand;
        }
        public void setPathToJar(String pathToJar){
         this.pathToJar = pathToJar;
        }
        public String getPathToJar(){
            return pathToJar;
        }
        public String toString(){
            return id+":"+name+":"+zustand+":"+pathToJar;
        }
    }
    private HashMap<Status,RunComponent> threadHashMap=null;
    private int laufendeNummer = 1;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private String[] components;
    public RunEnvironment(){

    }
    public void deployComponent(String pathToJar,String name){
        if(running.get()) {
            RunComponent intrc = new RunComponent();
            intrc.deployComponentWithPath(pathToJar);
            int id = laufendeNummer++;
            Status newStatus = new Status(id, name, "notRunning",pathToJar);
            threadHashMap.put(newStatus, intrc);

        }else{
            throw new RuntimeException("Laufzeitumgegbung nicht gestartet");
        }
    }
    public void unDeployComponent(int id){
            Map.Entry<Status, RunComponent> intsrc = iterateOverHashMap(id);
            if(intsrc==null){
                throw new NullPointerException("Element mit id:"+id+" existiert nicht!");
            }
            Object ret = threadHashMap.remove(intsrc.getKey());

    }
    private Map.Entry<Status,RunComponent> iterateOverHashMap(int id){
        components = new String[threadHashMap.size()];
        int i = 0;
        if(threadHashMap.isEmpty()){
            System.out.println("Keine Components vorhanden");
            return null;
        }
        for(Map.Entry<Status,RunComponent> entry:threadHashMap.entrySet()){
            if(entry.getKey().id==id){
                return entry;
            }
            if(id==-1){
                System.out.println(entry.getKey().id+"\n"+entry.getKey().name+"\n"+entry.getKey().zustand);
                components [i] = entry.getKey().id+" - "+entry.getKey().name+" - "+ entry.getKey().zustand;
                i++;
            }
        }
        return null;
    }
    public void startComponent(int id){
          Map.Entry<Status,RunComponent> intsrc= iterateOverHashMap(id);
          if(intsrc==null){
              throw new NullPointerException("Element mit id:"+id+" existiert nicht!");
          }
          intsrc.getKey().setZustand("Running");
          intsrc.getValue().start();

    }
    public void stopComponent(int id){
        Map.Entry<Status,RunComponent> intsrc= iterateOverHashMap(id);
        if(intsrc==null){
            throw new NullPointerException("Element mit id:"+id+" existiert nicht!");
        }
        intsrc.getKey().setZustand("notRunning");
        intsrc.getValue().stop();
    }
    public void injectLoggerIntoComponent(int id)  {
        Map.Entry<Status,RunComponent> intsrc= iterateOverHashMap(id);
        if(intsrc==null){
            throw new NullPointerException("Element mit id:"+id+" existiert nicht!");
        }
        try {
            intsrc.getValue().setLogger(new Logger());

        }catch (NoSuchMethodException nsme){
            System.out.println("Methode logger existiert nicht");
        }
    }
    public void sendLogger(int id){
        Map.Entry<Status,RunComponent> intsrc= iterateOverHashMap(id);
        if(intsrc==null){
            throw new NullPointerException("Element mit id:"+id+" existiert nicht!");
        }
        try {
            intsrc.getValue().sendlogger();
        }catch (NoSuchMethodException nsme){
            System.out.println("Methode logger existiert nicht");
        }
    }
    public String[] getStatus(){
        iterateOverHashMap(-1);
        return components;
    }

    public void startEnviornment(){
        running.set(true);
        threadHashMap = new HashMap<>();
    }
    public void stopEnviornment(){
       threadHashMap.clear();
       running.set(false);
    }

    public String environmentStatus(){ return running.toString(); }

    public static void main(String[] args) {

        String pathToJar = System.getProperty("user.home") + "/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";
        RunEnvironment rv = new RunEnvironment();
        rv.startEnviornment();
        rv.deployComponent(pathToJar,"Thread1");
        rv.deployComponent(pathToJar,"Thread2");
        rv.deployComponent(pathToJar,"Thread3");
        rv.getStatus();
        rv.startComponent(1);
        rv.startComponent(2);
        rv.startComponent(3);
        rv.getStatus();
        rv.injectLoggerIntoComponent(1);
        rv.injectLoggerIntoComponent(2);
        rv.injectLoggerIntoComponent(3);
        rv.sendLogger(1);
        rv.sendLogger(2);
        rv.sendLogger(3);
        rv.stopComponent(1);
        rv.stopComponent(2);
        rv.stopComponent(3);
        rv.getStatus();
        rv.unDeployComponent(1);
        rv.unDeployComponent(2);
        rv.unDeployComponent(3);
        rv.getStatus();
        rv.stopEnviornment();
    }

}
