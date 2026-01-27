package com.twilio.type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides region-to-edge mappings for Twilio's regional processing.
 * <p>
 * This class is part of the transition away from direct edge configuration,
 * allowing clients to use region identifiers that are mapped to the appropriate edge endpoints.
 * It is intended to centralize and simplify region-to-edge lookups for Twilio services.
 */
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
    return Collections.unmodifiableMap(regionMap);
  }
}
