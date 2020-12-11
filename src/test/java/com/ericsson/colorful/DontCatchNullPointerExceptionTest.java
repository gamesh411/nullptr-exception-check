package com.ericsson.colorful;

class DontCatchNullPointerExceptionTest {
    String method(String str) {
      try {
        return str.trim();
      } catch(NullPointerException e) {
        return "";
      }
    }
}
