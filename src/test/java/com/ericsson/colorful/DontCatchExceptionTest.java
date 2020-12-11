package com.ericsson.colorful;

class DontCatchExceptionTest {
    String method(String str) {
      try {
        return str.trim();
      } catch(Exception e) {
        return "";
      }
    }
}
