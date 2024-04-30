package Afg2RuntimeEnviornment;

import java.util.HashMap;
import java.util.Map;

public class RunEnvironment {
    private class Status{
        private int id;
        private String name;
        private String zustand;
        public Status(int id,String name,String zustand){
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
    }
    private HashMap<Status,RunComponent> threadHashMap=null;
    private int laufendeNummer = 1;
    public RunEnvironment(){
        threadHashMap = new HashMap<>();
    }
    public void deployComponent(String pathToJar,String name){
        RunComponent intrc=new RunComponent();
        intrc.deployComponentWithPath(pathToJar);
        threadHashMap.put(new Status(laufendeNummer++,name,"notRunning"),intrc);
    }
    private Map.Entry<Status,RunComponent> iterateOverHashMap(int id){
        for(Map.Entry<Status,RunComponent> entry:threadHashMap.entrySet()){
            if(entry.getKey().id==id){
                return entry;
            }
            if(id==-1){
                System.out.println(entry.getKey().id+"\n"+entry.getKey().name+"\n"+entry.getKey().zustand);
            }
        }
        return null;
    }
    public void startComponent(int id){
          Map.Entry<Status,RunComponent> intsrc= iterateOverHashMap(id);
          intsrc.getKey().setZustand("Running");
          intsrc.getValue().start();
    }
    public void stopComponent(int id){
        Map.Entry<Status,RunComponent> intsrc= iterateOverHashMap(id);
        intsrc.getKey().setZustand("notRunning");
        intsrc.getValue().stop();
    }
    public void getStatus(){
        iterateOverHashMap(-1);
    }

    public static void main(String[] args) {

        String pathToJar = "C:/Users/lukas/IdeaProjects/OOKASS24/out/artifacts/OOKAAbgabeLukasLeschUeb1_jar/OOKAAbgabeLukasLeschUeb1.jar";
        RunEnvironment rv = new RunEnvironment();
        rv.deployComponent(pathToJar,"Thread1");
        rv.deployComponent(pathToJar,"Thread2");
        rv.deployComponent(pathToJar,"Thread3");
        rv.getStatus();
        rv.startComponent(1);
        rv.startComponent(2);
        rv.startComponent(3);
        rv.getStatus();
        rv.stopComponent(1);
        rv.stopComponent(2);
        rv.stopComponent(3);
        rv.getStatus();

    }

}
