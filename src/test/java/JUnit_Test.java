import org.hbrs.ooka.Buchungssystem.Control.HotelRetrieval;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class JUnit_Test {
    @Test
    public void testGetAllHotels(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("*");
        hotels.closeSession();
        assertTrue("Nicht alle Hotels gefunden!" ,hotelList.size() == 11);
    }

    @Test
    public void testGetHotelsNamedHotel(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Hotel");
        hotels.closeSession();
        assertTrue("Nicht alle Hotels gefunden!" ,hotelList.size() == 6);
    }

    @Test
    public void testGetHotelByName(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertTrue("Hotel nicht gefunden!" ,hotelList.size() == 1);
    }

    @Test
    public void testGetHotelByName_ID(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertTrue("Hotel nicht gefunden!" ,hotelList.get(0).getId().equals("2"));
    }

    @Test
    public void testGetHotelByName_Name(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertTrue("Hotel nicht gefunden!" ,hotelList.get(0).getName().equals("Maritim"));
    }

    @Test
    public void testGetHotelByName_Location(){
        HotelRetrieval hotels = new HotelRetrieval();
        hotels.openSession();
        List<Hotel> hotelList = hotels.getHotelByName("Maritim");
        hotels.closeSession();
        assertTrue("Hotel nicht gefunden!" ,hotelList.get(0).getLocation().equals("Bonn"));
    }

}
