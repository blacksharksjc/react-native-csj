package com.rncsjdjx.options;

import com.facebook.react.bridge.ReadableMap;

public class CsjDJXInitOption {
  // 是否开启debug
  public Boolean debug = false;
  // 配置文件名称
  public String settingFileName;

  public CsjDJXInitOption(ReadableMap map) {
    this.settingFileName = map.getString("settingFileName");

    if (map.hasKey("debug")) {
      this.debug = map.getBoolean("debug");
    }
  }
}
