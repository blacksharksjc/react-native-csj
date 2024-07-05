package com.rncsjad.options;

import com.facebook.react.bridge.ReadableMap;

public class CsjAdInitOption {
  // 穿山甲媒体平台注册的应用ID
  public String appId;
  // 应用名称
  public String appName;
  // 默认使用SurfaceView播放视频广告,当有SurfaceView冲突的场景，可以使用TextureView
  public Boolean useTextureView;
  // 落地页主题
  public Integer titleBarTheme;
  // 允许直接下载的网络状态集合,没有设置的网络下点击下载apk会有二次确认弹窗，弹窗中会披露应用信息
  public Integer directDownloadNetworkType;
  // 是否允许sdk展示通知栏提示,若设置为false则会导致通知栏不显示下载进度
  public Boolean allowShowNotify;
  // 是否开启debug
  public Boolean debug;

  public CsjAdInitOption(ReadableMap map)  {
    this.appId = map.getString("appId");
    this.appName = map.getString("appName");
    this.useTextureView = map.getBoolean("useTextureView");
    this.titleBarTheme = map.getInt("titleBarTheme");
    this.directDownloadNetworkType = map.getInt("directDownloadNetworkType");
    this.allowShowNotify = map.getBoolean("allowShowNotify");
    this.debug = map.getBoolean("debug");
  }
}
