package com.twilio.type;

import java.util.HashMap;
import java.util.Map;

public class RegionEndpoints {
  private static Map<String, String> regionMap = new HashMap<>();
  static {
    regionMap.put("au1", "sydney");
    regionMap.put("br1", "sao-paulo");
    regionMap.put("ie1", "dublin");
    regionMap.put("de1", "frankfurt");
    regionMap.put("jp1", "tokyo");
    regionMap.put("jp2", "osaka");
    regionMap.put("sg1", "singapore");
    regionMap.put("us1", "ashburn");
    regionMap.put("us2", "umatilla");
  }
  public static Map<String, String> getRegions() {
    return regionMap;
  }
}
