import Native from './native';

/**
 * 初始化广告引擎
 * @returns
 */
export function init(appId: string, appName: string): Promise<void> {
  return Native.init(appId, appName);
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
