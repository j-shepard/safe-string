package com.chariotsolutions.jshepard.lang;

import java.util.Objects;
import java.util.stream.IntStream;

public class SafeString {
  private final String data;
  private final int length;

  private SafeString(String source) {
    data = Objects.requireNonNull(source).intern();
    length = data.codePointCount(0, data.length());
  }

  public String get() {
    return data;
  }

  public int length() {
    return length;
  }

  public int characterAt(int index) {
    return data.codePointAt(getRealIndex(index));
  }

  public IntStream characters() {
    return data.codePoints();
  }

  public SafeString substring(int startIndex, int endIndex) {
    int codePointStartIndex = getRealIndex(startIndex);
    int codePointEndIndex = getRealIndex(endIndex);
    String newData = data.substring(codePointStartIndex, codePointEndIndex);
    return new SafeString(newData);
  }

  private int getRealIndex(int index) {
    return data.offsetByCodePoints(0, index);
  }

  public static SafeString of(String source) {
    return new SafeString(source);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (!(object instanceof SafeString)) {
      return false;
    }
    SafeString other = (SafeString) object;
    return data == other.data;
  }

  @Override
  public int hashCode() {
    return data.hashCode();
  }

  @Override
  public String toString() {
    return data;
  }
}
