package org.hbrs.ooka.Buchungssystem.Caching;

import java.util.List;

public class CacheProxyImplementation implements CacheProxy{

    Cache cache = null;
    public CacheProxyImplementation(){
        cache = Cache.getCache();
    }

    @Override
    public void cacheResult(String key, List<String> value) {
        cache.cacheResult(key,value);
    }

    @Override
    public List<String> getResult(String key) {
        return cache.getResult(key);
    }
}
