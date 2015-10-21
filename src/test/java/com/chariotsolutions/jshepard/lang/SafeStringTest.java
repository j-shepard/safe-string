package com.chariotsolutions.jshepard.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class SafeStringTest {
  // Unicode 0x10400 is d801 dc00 in UTF-16.
  private String testString = new StringBuilder()
      .append("a")
      .appendCodePoint(0x10400)
      .append("cd")
      .toString();
  private SafeString testSafeString = SafeString.of(testString);

  private static final int VALID_LENGTH = 4;

  @Test
  public void testStringLengthFails() {
    assertNotEquals(VALID_LENGTH, testString.length());
  }

  @Test
  public void testStringSubstringFails() {
    String newString = testString.substring(2, 4);
    assertNotEquals("cd", newString);
  }

  @Test
  public void testSafeStringLength() {
    assertEquals(VALID_LENGTH, testSafeString.length());
  }

  @Test
  public void testSafeStringSubstring() {
    SafeString newSafeString = testSafeString.substring(2, 4);
    assertEquals("cd", newSafeString.get());
  }

  @Test
  public void testSafeStringCharAt() {
    assertEquals('a', testSafeString.characterAt(0));
    assertEquals(0x10400, testSafeString.characterAt(1));
    assertEquals('c', testSafeString.characterAt(2));
    assertEquals('d', testSafeString.characterAt(3));
  }
}