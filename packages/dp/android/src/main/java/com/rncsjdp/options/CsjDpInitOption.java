package com.rncsjdp.options;

import com.bytedance.sdk.dp.DPSdkConfig;
import com.facebook.react.bridge.ReadableMap;

public class CsjDpInitOption {
  // 是否开启debug
  public Boolean debug = false;
  // 文章详情列表字体大小样式
  public Boolean largeFontStyle = false;

  public CsjDpInitOption(ReadableMap map)  {
    if (map.hasKey("debug")) {
      this.debug = map.getBoolean("debug");
    }
    if (map.hasKey("largeFontStyle")) {
      this.largeFontStyle = map.getBoolean("largeFontStyle");
    }
  }
}
