package Afg2RuntimeEnviornment;

import java.text.SimpleDateFormat;

public class Logger {
    public void sendLog(String str){
        System.out.println("++++ LOG: Meldung aus Component: Prozess gestartet "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
    }
}
