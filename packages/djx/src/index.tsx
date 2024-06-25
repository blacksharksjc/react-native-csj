import Native from './native';

/**
 * 初始化广告引擎
 * @returns
 */
export function init(): Promise<void> {
  return Native.init();
}
