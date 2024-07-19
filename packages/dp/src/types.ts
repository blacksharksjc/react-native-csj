/**
 * SDK初始化参数
 */
export interface CsjDpSDKInitOption {
  /**
   * 配置文件名称
   */
  settingFileName: string;
  /**
   * 是否开启debug模式
   */
  debug?: boolean;
  /**
   * 是否启用大字体
   */
  largeFontStyle?: boolean;
}

export enum DrawContentType {
  /**
   * 仅视频
   */
  Video = 1,
  /**
   * 仅直播
   */
  Live = 2,
  /**
   * 视频+直播
   */
  All = 3,
}

export enum DrawChannelType {
  /**
   * 推荐频道
   */
  Recommend = 1,
  /**
   * 关注频道
   */
  Follow = 2,
  /**
   * 推荐+关注
   */
  All = 3
}

export enum ProgressBarStyle {
  /**
   * 浅色样式
   */
  Light = 1,
  /**
   * 深色样式
   */
  Dark = 2,
}

export interface DPDrawViewProps {
  style?: {
    width: number;
    height: number;
  };
  /**
   * 混流内容
   */
  drawContentType?: DrawContentType;
  /**
   * 播放器进度条样式, 默认为浅色样式
   */
  progressBarStyle?: ProgressBarStyle;
  /**
   * 广告偏移量（距离底部）
   */
  adOffset?: number;
  /**
   * 否隐藏返回按钮
   */
  hideClose?: boolean;
  /**
   * 否可以显示新手引导动画
   */
  showGuide?: boolean;
  /**
   * 推荐频道名称
   */
  customCategory?: string;
  /**
   * 小视频外流底部标题文案、进度条、评论按钮底部偏移
   */
  bottomOffset?: number;
  /**
   * 标题栏距离顶部间距
   */
  titleTopMargin?: number;
  /**
   * 标题栏距离左间距
   */
  titleLeftMargin?: number;
  /**
   * 标题栏距离右间距
   */
  titleRightMargin?: number;
  /**
   * 沉浸式小视频频道
   */
  drawChannelType?: number;
  /**
   * 是否隐藏关注功能
   */
  hideFollow?: boolean;
  /**
   * 是否隐藏频道名称
   */
  hideChannelName?: boolean;
  /**
   * 是否支持下拉刷新
   */
  enableRefresh?: boolean;
  /**
   * 视频场景
   */
  scene?: string;
  /**
   * 下拉刷新事件
   */
  onDPRefreshFinish?: () => void;
  onDPAdRequest?: () => void;
  onDPVideoPlay?: () => void;
}

export enum DPGridViewType {
  /**
   * 宫格
   */
  Grid = '1',
  /**
   * 双Feed流
   */
  DoubleFeed = '2',
}

export enum CardStyle {
  NormalStyle = 1,
  StaggeredStyle,
}

export interface DPGridViewProps {
  style?: {
    width: number;
    height: number;
  };
  /**
   * GirdView类型
   */
  type?: DPGridViewType;
  /**
   * 卡片样式
   */
  cardStyle?: CardStyle;
}
