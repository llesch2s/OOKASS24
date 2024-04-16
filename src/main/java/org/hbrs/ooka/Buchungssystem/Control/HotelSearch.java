package org.hbrs.ooka.Buchungssystem.Control;

import org.hbrs.ooka.Buchungssystem.Model.Hotel;

import java.util.List;

public interface HotelSearch {
    public void openSession();
    public List<Hotel> getHotelByName(String name);

    public void closeSession();
}
