package org.hbrs.ooka.Buchungssystem.Control;


import java.sql.Timestamp;

public class Logger {

    public Logger(){

    }

    public static void generateLogging(String system,String methode,String suchwort){
        System.out.println(new Timestamp(System.currentTimeMillis())+" Zugriff auf "+system+" über Methode "+methode+" Suchwort: "+suchwort);

    }
}
