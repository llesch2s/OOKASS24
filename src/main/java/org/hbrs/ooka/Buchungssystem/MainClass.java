package org.hbrs.ooka.Buchungssystem;


import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxy;
import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxyImplementation;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;

import java.util.List;

public class MainClass {
    //lokale variablen in Threads immer mit Threadlocal
    ThreadLocal<HotelRetrievalProxy> hotelRetrieval = new ThreadLocal<>();

    public void start(){
        hotelRetrieval.set(new HotelRetrievalProxyImplementation());
        hotelRetrieval.get().openSession();
        List<Hotel> listHotelRetrievalResults = hotelRetrieval.get().getHotelByName("*");
        for(Hotel h:listHotelRetrievalResults){
            System.out.println("Id:"+h.getId());
            System.out.println("Name:"+h.getName());
            System.out.println("Location:"+h.getLocation());
        }

    }

    public void stop(){
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
