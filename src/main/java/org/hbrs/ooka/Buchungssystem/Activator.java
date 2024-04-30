package org.hbrs.ooka.Buchungssystem;


import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxy;
import org.hbrs.ooka.Buchungssystem.Control.HotelRetrievalProxyImplementation;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.List;

public class Activator implements BundleActivator {
    private HotelRetrievalProxy hotelRetrieval;

    public void start(BundleContext context) throws Exception {

        hotelRetrieval = new HotelRetrievalProxyImplementation();
        hotelRetrieval.openSession();
        List<Hotel> listHotelRetrievalResults = hotelRetrieval.getHotelByName("*");
        for(Hotel h:listHotelRetrievalResults){
            System.out.println("Id:"+h.getId());
            System.out.println("Name:"+h.getName());
            System.out.println("Location:"+h.getLocation());
        }
    }

    public void stop(BundleContext context) throws Exception {
        hotelRetrieval.closeSession();
    }
}
