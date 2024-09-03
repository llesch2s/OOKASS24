package Afg2RuntimeEnviornment;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancer {




    private HashMap<RunEnvironment.Status,RunComponent> localThreadHashMap=null;
    private RunEnvironment runEnvironment=null;
    private RunComponent leastOccupiedrunComponent=null;
    private RunEnvironment.Status leastOccupiedrunStatus=null;
    public LoadBalancer(RunEnvironment runEnvironment) {
        this.runEnvironment=runEnvironment;
    }
    public void runComponentMethod(String pathToJar,String methodname,Object[] parameters){
        localThreadHashMap=this.runEnvironment.getThreadHashMap();

        try{
        for(Map.Entry<RunEnvironment.Status,RunComponent> entry:localThreadHashMap.entrySet()){
            RunComponent potentialLeastOccupiedrunComponent=entry.getValue();
            RunEnvironment.Status potentialStatus=entry.getKey();
            System.out.println("Komponente mit id:"+potentialStatus.getId()+"Anzahl elemente in queue"+potentialLeastOccupiedrunComponent.getLinkedBlockingQueueSize());
            if(leastOccupiedrunComponent==null&&pathToJar.equals(entry.getKey().getPathToJar())){
                leastOccupiedrunComponent=potentialLeastOccupiedrunComponent;
                leastOccupiedrunStatus=potentialStatus;
            }else if(pathToJar.equals(entry.getKey().getPathToJar())&&leastOccupiedrunComponent.getLinkedBlockingQueueSize()>potentialLeastOccupiedrunComponent.getLinkedBlockingQueueSize()){
                leastOccupiedrunComponent=potentialLeastOccupiedrunComponent;
                leastOccupiedrunStatus=potentialStatus;
            }
        }
            System.out.println("Komponente mit id:"+leastOccupiedrunStatus.getId()+"Ausgewählt");
            leastOccupiedrunComponent.runComponentMethod(methodname,parameters);
        }catch (NullPointerException ne){
            System.out.println("Keine Komponente mit path: "+ pathToJar+" vorhanden");

        }
    }

}
