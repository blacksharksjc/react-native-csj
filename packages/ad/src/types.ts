import type { EventWithNamespace, NativeEventListenerGeneric } from '@rn-csj/shared';

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
  /**
   * 是否支持多进程
   */
  supportMultiProcess?: boolean;
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

export type AdSDKEvent = 'onStartSuccess' | 'onStartFail';
export type SplashAdEvent = 'onSplashLoadSuccess'
  | 'onSplashLoadFail'
  | 'onSplashRenderSuccess'
  | 'onSplashRenderFail'
  | 'onSplashAdShow'
  | 'onSplashAdClick' | 'onSplashAdClose';
export type RewardAdEvent = 'onError'
  | 'onRewardVideoAdLoad'
  | 'onRewardVideoCached'
  | 'onAdShow'
  | 'onAdVideoBarClick'
  | 'onVideoComplete'
  | 'onVideoError'
  | 'onRewardArrived'
  | 'onSkippedVideo';

export type AdNativeEvent = EventWithNamespace<'AdSDK', AdSDKEvent>
  | EventWithNamespace<'SplashAd', SplashAdEvent>
  | EventWithNamespace<'RewardAd', RewardAdEvent>;

export interface AdEventListeners {
  'AdSDK.onStartSuccess': () => void;
  'AdSDK.onStartFail': (event: ErrorEvent) => void;
  'SplashAd.onSplashLoadSuccess': () => void;
  'SplashAd.onSplashLoadFail': (event: ErrorEvent) => void;
  'SplashAd.onSplashRenderSuccess': () => void;
  'SplashAd.onSplashRenderFail': (event: ErrorEvent) => void;
  'SplashAd.onSplashAdShow': () => void;
  'SplashAd.onSplashAdClose': (event: {closeType: string}) => void;
  'RewardAd.onError': (event: ErrorEvent) => void;
  'RewardAd.onRewardVideoAdLoad': () => void;
  'RewardAd.onRewardVideoCached': () => void;
  'RewardAd.onAdShow': () => void;
  'RewardAd.onAdVideoBarClick': () => void;
  'RewardAd.onVideoComplete': () => void;
  'RewardAd.onVideoError': () => void;
  'RewardAd.onRewardArrived': (event: RewardArrivedEvent) => void;
  'RewardAd.onSkippedVideo': () => void;
  'SplashAd.onSplashAdClick': () => void;
}
export type NativeEventListener = NativeEventListenerGeneric<AdNativeEvent, AdEventListeners>;

export interface RewardArrivedEvent {
  /**
   * 此次奖励是否有效
   */
  isRewardValid: boolean;
  /**
   * 错误码
   */
  serverErrorCode: number;
  /**
   * 错误信息
   */
  serverErrorMessage: string;
  /**
   * 平台配置的奖励名称
   */
  rewardName: string;
  /**
   * 平台配置的奖励数量
   */
  rewardAmount: number;
  /**
   * 建议奖励百分比
   */
  rewardPropose: number;
  /**
   * 奖励次数
   */
  rewardCount: number;
}

export interface ErrorEvent {
  code: number;
  message: string;
}

export interface PrivacyOption {
  isCanUseLocation: boolean;
  isCanUsePhoneState: boolean;
  isCanUseWifiState: boolean;
  isCanUseWriteExternal: boolean;
  alist: boolean;
  isCanUseAndroidId: boolean;
  isCanUsePermissionRecordAudio: boolean;
}

export interface CsjLoadSplashAdOption {
  code: string;
  timeout?: number;
}
