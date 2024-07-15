import Native from './native';
import type { CsjDpSDKInitOption } from './types';
export * from './views';

/**
 * 初始化广告引擎
 * @returns
 */
export function init(option: CsjDpSDKInitOption): Promise<void> {
  return Native.init(option);
}
