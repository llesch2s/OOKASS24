package org.hbrs.ooka.Buchungssystem.Caching;

import java.util.List;

public interface Caching {
  public void  cacheResult( String key, List<String> value);
  public List<String> getResult(String key);
}
