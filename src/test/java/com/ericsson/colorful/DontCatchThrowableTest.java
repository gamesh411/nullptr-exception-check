package com.ericsson.colorful;

class DontCatchThrowableTest {
    String method(String str) {
      try {
        return str.trim();
      } catch(Throwable t) {
        return "";
      }
    }
}
