package org.hbrs.ooka.Buchungssystem.Control;

import org.hbrs.ooka.Buchungssystem.Model.Hotel;

import java.util.List;

public class HotelRetrievalProxyImplementation implements HotelRetrievalProxy{
    private HotelRetrieval hotelRetrieval= null;
    public HotelRetrievalProxyImplementation(){
        hotelRetrieval = new HotelRetrieval();
    }
    @Override
    public void openSession() {
          hotelRetrieval.openSession();
    }

    @Override
    public List<Hotel> getHotelByName(String name) {
        return hotelRetrieval.getHotelByName(name);
    }

    @Override
    public void closeSession() {
      hotelRetrieval.closeSession();
    }
}
