package org.hbrs.ooka.Buchungssystem;


import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxy;
import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxyImplementation;
import org.hbrs.ooka.Buchungssystem.DB.DBAccess;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;

import java.util.List;

public class MainClass {

    public static  void main(String[] args){
         HotelRetrievalProxy hotelRetrieval = new HotelRetrievalProxyImplementation();
         hotelRetrieval.openSession();
         List<Hotel> listHotelRetrievalResults = hotelRetrieval.getHotelByName("*");
         for(Hotel h:listHotelRetrievalResults){
             System.out.println("Id:"+h.getId());
             System.out.println("Name:"+h.getName());
             System.out.println("Location:"+h.getLocation());
         }
         hotelRetrieval.closeSession();

    }
}
