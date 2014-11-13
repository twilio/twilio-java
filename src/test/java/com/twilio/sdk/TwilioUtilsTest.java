package com.twilio.sdk;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test TwilioUtils - currently only the secureCompare method.
 */
public class TwilioUtilsTest {
  // number of loops for inner timing loop per sample
  // needs to be big enough so that measurement granularity isn't a problem, but not too slow
  public static final int LOOP_COUNT = 1000;

  // number of samples to collect
  // needs to be sufficiently large that we will avoid JIT and GC differences
  private static final int SAMPLE_COUNT = 20;

  // length of strings to compare
  private static final int COMPARE_LENGTH = 1024;

  // prevent compiler optimizations
  private static volatile boolean result;

  @Test
  public void testSecureCompareEquals() {
    assertTrue(TwilioUtils.secureCompare("", ""));
    assertTrue(TwilioUtils.secureCompare("a", "a"));
    assertTrue(TwilioUtils.secureCompare("abcdefghijklmnopq", "abcdefghijklmnopq"));
  }

  @Test
  public void testSecureCompareNotEquals() {
    assertFalse(TwilioUtils.secureCompare("a", "b"));
    assertFalse(TwilioUtils.secureCompare("abcdefghijklmnopq", "Abcdefghijklmnopq"));
  }

  @Test
  public void testSecureCompareDifferentLength() {
    assertFalse(TwilioUtils.secureCompare("a", ""));
    assertFalse(TwilioUtils.secureCompare("abcdefghijklmnopq", "abcde"));
    assertFalse(TwilioUtils.secureCompare("abcde", "abcdefghijklmnopq"));
  }

  @Test
  public void testSecureCompareNull() {
    assertFalse(TwilioUtils.secureCompare(null, ""));
    assertFalse(TwilioUtils.secureCompare("abcdefghijklmnopq", null));
  }

  @Test
  public void testSecureCompareTiming() {
    char[] ch = new char[COMPARE_LENGTH];
    Arrays.fill(ch, 'a');
    String FULL_MATCH = String.valueOf(ch);
    ch[COMPARE_LENGTH - 1] = 'b';
    String LONG_FAIL = String.valueOf(ch);
    ch[0] = 'b';
    String SHORT_FAIL = String.valueOf(ch);

    // now for the real test
    long shortFailTime = getBestTime(FULL_MATCH, SHORT_FAIL);
    long longFailTime = getBestTime(FULL_MATCH, LONG_FAIL);

    // allow +-20% of the larger value
    double deltaPercent = 100.0 * (shortFailTime - longFailTime) / Math.max(shortFailTime, longFailTime);
    assertEquals(0, deltaPercent, 20.0);
  }

  private static long getBestTime(String a, String b) {
    // this is inherently a bit flakey, so use the best time to minimize flakiness
    long bestTime = Long.MAX_VALUE;
    for (int loop = 0; loop < SAMPLE_COUNT; ++loop) {
      long start = System.nanoTime();
      for (int i = 0; i < LOOP_COUNT; ++i) {
        result = TwilioUtils.secureCompare(a, b);
      }
      long result = System.nanoTime() - start;
      if (result < bestTime) {
        bestTime = result;
      }
    }
    return bestTime;
  }
}
