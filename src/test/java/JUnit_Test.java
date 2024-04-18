import org.hbrs.ooka.Buchungssystem.Control.HotelRetrieval;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JUnit_Test {
    @Test
    public void testGetAllHotels(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("*");
        hotels.closeSession();
        assertEquals("Nicht alle Hotels gefunden!", 11, hotelList.size());
    }

    @Test
    public void testGetHotelsNamedHotel(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Hotel");
        hotels.closeSession();
        assertEquals("Nicht alle Hotels gefunden!", 6, hotelList.size());
    }

    @Test
    public void testGetHotelByName(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertEquals("Hotel nicht gefunden!", 1, hotelList.size());
    }

    @Test
    public void testGetHotelByName_ID(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertEquals("Hotel nicht gefunden!", "2", hotelList.get(0).getId());
    }

    @Test
    public void testGetHotelByName_Name(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertEquals("Hotel nicht gefunden!", "Maritim", hotelList.get(0).getName());
    }

    @Test
    public void testGetHotelByName_Location(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertEquals("Hotel nicht gefunden!", "Bonn", hotelList.get(0).getLocation());
    }

}
