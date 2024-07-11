package com.rncsjad.options;

import com.facebook.react.bridge.ReadableMap;

public class CsjPrivacyOption {
  /**
   * 是否允许SDK主动使用地理位置信息
   */
  public Boolean isCanUseLocation;
  /**
   * 是否允许SDK主动使用手机硬件参数
   */
  public Boolean isCanUsePhoneState;
  /**
   * 是否允许SDK主动使用ACCESS_WIFI_STATE权限
   */
  public Boolean isCanUseWifiState;
  /**
   * 是否允许SDK主动使用WRITE_EXTERNAL_STORAGE权限
   */
  public Boolean isCanUseWriteExternal;
  /**
   * 是否允许SDK主动获取设备上应用安装列表的采集权限
   */
  public Boolean alist;
  /**
   * 是否允许SDK主动获取ANDROID_ID
   */
  public Boolean isCanUseAndroidId;
  /**
   * 是否允许SDK在申明和授权了的情况下使用录音权限
   */
  public Boolean isCanUsePermissionRecordAudio;

  public CsjPrivacyOption(ReadableMap map) {
    if (map.hasKey("isCanUseLocation")) {
      this.isCanUseLocation = map.getBoolean("isCanUseLocation");
    }
    if (map.hasKey("isCanUsePhoneState")) {
      this.isCanUsePhoneState = map.getBoolean("isCanUsePhoneState");
    }
    if (map.hasKey("isCanUseWifiState")) {
      this.isCanUseWifiState = map.getBoolean("isCanUseWifiState");
    }
    if (map.hasKey("isCanUseWriteExternal")) {
      this.isCanUseWriteExternal = map.getBoolean("isCanUseWriteExternal");
    }
    if (map.hasKey("alist")) {
      this.alist = map.getBoolean("alist");
    }
    if (map.hasKey("isCanUseAndroidId")) {
      this.isCanUseAndroidId = map.getBoolean("isCanUseAndroidId");
    }
    if (map.hasKey("isCanUsePermissionRecordAudio")) {
      this.isCanUsePermissionRecordAudio = map.getBoolean("isCanUsePermissionRecordAudio");
    }
  }
}
