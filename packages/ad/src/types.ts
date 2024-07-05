export interface CsjInitOption {
  /**
   * 应用ID
   */
  appId: string;
  /**
   * 应用名称
   */
  appName: string;
  /**
   * 默认使用SurfaceView播放视频广告,当有SurfaceView冲突的场景，可以使用TextureView
   */
  useTextureView?: boolean;
  /**
   * 落地页主题
   */
  titleBarTheme?: TitleBarTheme;
  /**
   * 允许直接下载的网络状态集合,没有设置的网络下点击下载apk会有二次确认弹窗，弹窗中会披露应用信息
   */
  directDownloadNetworkType?: NetworkType;
  /**
   * 是否允许sdk展示通知栏提示,若设置为false则会导致通知栏不显示下载进度
   */
  allowShowNotify?: boolean;
  /**
   * 是否开启debug
   */
  debug?: boolean;
}

export enum TitleBarTheme {
  TITLE_BAR_THEME_LIGHT = 0,
  TITLE_BAR_THEME_DARK= 1,
  TITLE_BAR_THEME_NO_TITLE_BAR = -1,
}

export enum NetworkType {
  NETWORK_STATE_MOBILE = 1,
  NETWORK_STATE_2G = 2,
  NETWORK_STATE_3G = 3,
  NETWORK_STATE_WIFI = 4,
  NETWORK_STATE_4G = 5,
  NETWORK_STATE_5G = 6,
}

export enum AdEvent {
  AD_START_SUCCESS = "AD_START_SUCCESS",
  AD_START_FAIL = "AD_START_FAIL",
}
