package org.hbrs.ooka.Buchungssystem;

import org.hbrs.ooka.Buchungssystem.Control.HotelRetrieval;
import org.hbrs.ooka.Buchungssystem.DB.DBAccess;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;

import java.util.List;

public class MainClass {

    public static  void main(String[] args){
         HotelRetrieval hotelRetrieval = new HotelRetrieval();
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
