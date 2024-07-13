package com.rncsjad.options;

import com.facebook.react.bridge.ReadableMap;

public class CsjLoadSplashAdOption {
  public String code;
  public int timeout = 3500;

  public CsjLoadSplashAdOption(ReadableMap map) {
    this.code = map.getString("code");
    if (map.hasKey("timeout")) {
      this.timeout = map.getInt("timeout");
    }
  }
}
