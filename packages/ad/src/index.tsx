import Native from './native';
import { type CsjInitOption, NetworkType, TitleBarTheme } from './types';

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
    ...option,
  }
  return Native.init(optionWithDefault);
}

/**
 * 加载开屏广告
 * @returns
 */
export function loadSplashScreen(code: string): Promise<void> {
  return Native.loadSplashScreen(code);
}

/**
 * 加载激励视频
 * @returns
 */
export function loadRewardAd(code: string): Promise<number> {
  return Native.loadRewardAd(code);
}

/**
 * 请求必要权限
 * @returns
 */
export function requestPermissionIfNecessary(): void {
  return Native.requestPermissionIfNecessary();
}
