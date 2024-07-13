import Native from './native';
import {
  type CsjInitOption,
  type Listeners, type CsjLoadSplashAdOption,
  type NativeEventListener,
  NetworkType, type PrivacyOption,
  TitleBarTheme,
} from './types';
import { NativeEventEmitter } from 'react-native';
export * from './types';

/**
 * 初始化广告引擎
 * @returns
 */
export function init(option: CsjInitOption): Promise<void> {
  const optionWithDefault = {
    debug: true,
    useTextureView: true,
    titleBarTheme: TitleBarTheme.TITLE_BAR_THEME_LIGHT,
    directDownloadNetworkType: NetworkType.NETWORK_STATE_WIFI,
    allowShowNotify: true,
    supportMultiProcess: false,
    ...option,
  }
  return Native.init(optionWithDefault);
}

/**
 * 加载开屏广告
 * @returns
 */
export function loadSplashScreen(option: CsjLoadSplashAdOption): Promise<void> {
  return Native.loadSplashScreen(option);
}

/**
 * 加载激励视频
 * @returns
 */
export function loadRewardAd(code: string): Promise<number> {
  return Native.loadRewardAd(code);
}

const eventEmitter = new NativeEventEmitter(Native);

/**
 * 添加事件监听
 * @param eventName
 * @param listener
 */
export function addEventListener<T extends keyof Listeners>(eventName: T, listener: NativeEventListener[T]) {
  return eventEmitter.addListener(eventName, listener);
}

/**
 * 更新隐私权限
 * @param option
 */
export function updatePrivacy(option: Partial<PrivacyOption>): Promise<void> {
  return Native.updatePrivacy(option)
}
