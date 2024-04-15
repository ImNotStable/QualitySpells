package com.imnotstable.qualityspells;

import lombok.Getter;

@Getter
public class CastPattern {
  
  private final char[] pattern;
  
  private CastPattern(char initial) {
    this.pattern = new char[3];
    this.pattern[0] = initial;
  }
  
  private CastPattern(char... pattern) {
    this.pattern = pattern;
  }
  
  public CastPattern then(char next) {
    if (pattern[1] == 0) {
      pattern[1] = next;
    } else if (pattern[2] == 0) {
      pattern[2] = next;
    }
    return this;
  }
  
  public int length() {
    return (int) new String(pattern).chars().filter(c -> c != 0).count();
  }
  
  public boolean matches(CastPattern pattern) {
    return matches(pattern.getPattern());
  }
  
  public boolean matches(char[] input) {
    for (int i = 0; i < pattern.length; i++) {
      if (pattern[i] != 0 && pattern[i] != input[i]) {
        return false;
      }
    }
    return true;
  }
  
  public boolean isComplete() {
    return pattern[2] != 0;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof CastPattern castPattern)
      return matches(castPattern.getPattern());
    return false;
  }
  
  @Override
  public String toString() {
    return pattern[0] + " - " + pattern[1] + " - " + pattern[2];
  }
  
  public static CastPattern create(char initial) {
    return new CastPattern(initial);
  }
  
  public static CastPattern create(char first, char second, char third) {
    return new CastPattern(first, second, third);
  }
  
}
