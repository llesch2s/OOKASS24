package org.hbrs.ooka.Buchungssystem.Control;

import org.hbrs.ooka.Buchungssystem.Caching.Cache;
import org.hbrs.ooka.Buchungssystem.DB.DBAccess;
import org.hbrs.ooka.Buchungssystem.Model.Hotel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class HotelRetrieval implements HotelSearch{
         private  DBAccess acc;
         private Cache localCacheInstance;
    public HotelRetrieval(){
        acc = new DBAccess();
        localCacheInstance = Cache.getCache();

    }
    @Override
    public void openSession() {
        acc.openConnection();
    }

    @Override
    public List<Hotel> getHotelByName(String name) {
        Logger.generateLogging("Buchungssystem","getHotelByName",name);
        String key = "1";
        LinkedList<Hotel> hotelList = new LinkedList<Hotel>();
        localCacheInstance.cacheResult(key, acc.getObjects(DBAccess.HOTEL, name));
        List<String> result = localCacheInstance.getResult(key);
        Iterator<String> iter = result.iterator();

        while (iter.hasNext() ){
                hotelList.add(new Hotel(iter.next(),iter.next(),iter.next()));
        }
        return hotelList;
    }

    @Override
    public void closeSession() {
        acc.closeConnection();

    }
}
