package com.ericsson.colorful;

class DontCatchRuntimeExceptionTest {
    String method(String str) {
      try {
        return str.trim();
      } catch(RuntimeException e) {
        return "";
      }
    }
}
