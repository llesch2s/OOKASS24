package org.hbrs.ooka.Buchungssystem.Caching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cache implements Caching{

    private static Cache cache;
    private HashMap<String,List<String>> internalStorage;
    public Cache(){
        internalStorage = new HashMap<>();
    }


    @Override
    public void cacheResult(String key, List<String> value) {
      internalStorage.put(key,value);
    }

    @Override
    public List<String> getResult(String key) {
       try{
        return internalStorage.remove(key);
       } catch(NullPointerException n){
          System.out.println("Kein Hotel mit Schlüssel: "+key+" vorhanden");
       }
       // scheinbarer zugriff ohne nullpointer
       return new ArrayList<String>();
    }
}
