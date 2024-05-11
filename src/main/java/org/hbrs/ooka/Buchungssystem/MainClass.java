package org.hbrs.ooka.Buchungssystem;


import Afg2RuntimeEnviornment.Inject;
import Afg2RuntimeEnviornment.Logger;
import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxy;
import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxyImplementation;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;

import java.util.List;

public class MainClass {
    //lokale variablen in Threads immer mit Threadlocal
    ThreadLocal<HotelRetrievalProxy> hotelRetrieval = new ThreadLocal<>();
    @Inject
    private ThreadLocal<Logger> mylog = new ThreadLocal<>();
    @Start
    public  void start(){
        hotelRetrieval.set(new HotelRetrievalProxyImplementation());
        hotelRetrieval.get().openSession();
        List<Hotel> listHotelRetrievalResults = hotelRetrieval.get().getHotelByName("*");
        for(Hotel h:listHotelRetrievalResults){
            System.out.println("Id:"+h.getId());
            System.out.println("Name:"+h.getName());
            System.out.println("Location:"+h.getLocation());
        }
    }
    public void setMylog(Logger logger){
        mylog.set(logger);
    }
    public void sendMyLog() throws NullPointerException {

        mylog.get().sendLog( "Meldung aus Component: Prozess gestartet" );

    }
    @Stop
    public  void stop(){
        hotelRetrieval.get().closeSession();
    }

    public static void main(String[] args){
        ThreadLocal<HotelRetrievalProxy> hotelRetrieval = new ThreadLocal<>();
        hotelRetrieval.set(new HotelRetrievalProxyImplementation());

        hotelRetrieval.get().openSession();
        List<Hotel> listHotelRetrievalResults = hotelRetrieval.get().getHotelByName("*");
        for(Hotel h:listHotelRetrievalResults){
            System.out.println("Id:"+h.getId());
            System.out.println("Name:"+h.getName());
            System.out.println("Location:"+h.getLocation());
        }
        hotelRetrieval.get().closeSession();

    }
}
