import Native from './native';
import { CsjDjxSDKInitOption } from './types';

/**
 * 初始化广告引擎
 * @returns
 */
export function init(option: CsjDjxSDKInitOption): Promise<void> {
  return Native.init(option);
}
