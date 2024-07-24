import { NativeSyntheticEvent } from 'react-native';

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
  All = 3,
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

export type DPDrawViewProps = IDPDrawListener & {
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
};

export type IDPDrawListener = {
  /**
   * 下拉刷新事件
   */
  onDPRefreshFinish?: () => void;
  /**
   * 列表数据变化回调
   * @returns
   */
  onDPListDataChange?: (
    event?: NativeSyntheticEvent<DPListDataChangeEvent>
  ) => void;
  /**
   * 视频播放时回调
   */
  onDPVideoPlay?: (event: NativeSyntheticEvent<DPVideoPlayEvent>) => void;
  /**
   * 视频播放时回调
   */
  onDPVideoPause?: (event: NativeSyntheticEvent<DPVideoPauseEvent>) => void;
  /**
   *
   * @param event
   * @returns
   */
  onDPPageChange?: (event: NativeSyntheticEvent<DPPageChangeEvent>) => void;
  /**
   * 用户拖动进度条松手时回调
   * @returns
   */
  onDPSeekTo?: (event: NativeSyntheticEvent<DPSeekToEvent>) => void;
  /**
   * 视频继续播放时回调
   * @param event
   * @returns
   */
  onDPVideoContinue?: (event: NativeSyntheticEvent<DPSeekToEvent>) => void;
  /**
   * 视频播放完成时回调
   * @param event
   * @returns
   */
  onDPVideoCompletion?: (
    event: NativeSyntheticEvent<DPVideoCompletionEvent>
  ) => void;
  /**
   * 视频播放结束时回调
   * @param event
   * @returns
   */
  onDPVideoOver?: (event: NativeSyntheticEvent<DPVideoOverEvent>) => void;
  /**
   * 界面关闭时回调
   * @returns
   */
  onDPClose?: () => void;
  /**
   * 举报结果回调
   * @returns
   */
  onDPReportResult?: (event: NativeSyntheticEvent<DPReportResultEvent>) => void;
  /**
   * 开始请求回调
   * @returns
   */
  onDPRequestStart?: () => void;
  /**
   * 请求失败回调
   * @param event
   * @returns
   */
  onDPRequestFail?: (event: NativeSyntheticEvent<DPRequestFailEvent>) => void;
  /**
   * 请求成功回调
   * @param event
   * @returns
   */
  onDPRequestSuccess?: (
    event: NativeSyntheticEvent<DPRequestSuccessEvent>
  ) => void;
  /**
   * 点击作者头像时回调
   * @param event
   * @returns
   */
  onDPClickAvatar?: (event: NativeSyntheticEvent<DPClickEvent>) => void;
  /**
   * 点击作者昵称时回调
   * @param event
   * @returns
   */
  onDPClickAuthorName?: (event: NativeSyntheticEvent<DPClickEvent>) => void;
  /**
   * 点击评论时回调
   * @param event
   * @returns
   */
  onDPClickComment?: (event: NativeSyntheticEvent<DPClickEvent>) => void;
  /**
   * 点赞时回调
   * @param event
   * @returns
   */
  onDPClickLike?: (event: NativeSyntheticEvent<DPClickEvent>) => void;
  /**
   * 点赞时回调
   * @param event
   * @returns
   */
  onDPClickShare?: (event: NativeSyntheticEvent<DPClickShareEvent>) => void;
  /**
   * 页面状态回调
   * @param event
   * @returns
   */
  onDPPageStateChanged?: (event: NativeSyntheticEvent<DPPageStateChangedEvent>) => void;
};

export interface DPPageStateChangedEvent {
  dpPageState: number;
}

export interface DPClickShareEvent {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 标题
   */
  title: string;
  /**
   * 作者昵称
   */
  authorName: string;
  /**
   * 发布时间
   */
  publishTime: number;
}

export interface DPClickEvent {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 来源
   */
  categoryName: string;
}

export type DPRequestSuccessEvent = Array<DPRequestSuccessItem>;
export interface DPRequestSuccessItem {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 来源
   */
  categoryName: string;
  /**
   * 扩展字段
   */
  extra: string;
  /**
   * 标题
   */
  title: string;
  /**
   * 视频时长
   */
  videoDuration: number;
  /**
   * 文件大小
   */
  videoSize: number;
  /**
   * 一级类别
   */
  category: number;
  /**
   * 作者昵称
   */
  authorName: string;
  /**
   * 是否置顶
   */
  isStick: boolean;
}

export interface DPRequestFailEvent {
  /**
   * 错误码
   */
  code: number;
  /**
   * 错误信息
   */
  message: string;
  /**
   * 请求id
   */
  reqId?: string;
}

export interface DPReportResultEvent {
  /**
   * 是否举报成功
   */
  isSucceed: boolean;
  /**
   * 新闻id
   */
  groupId: number;
}

export interface DPVideoOverEvent {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 播放百分比0-100
   */
  percent: number;
  /**
   * 视频总时长
   */
  duration: number;
  /**
   * 来源
   */
  categoryName: string;
  /**
   * 扩展字段
   */
  extra: string;
}

export interface DPVideoCompletionEvent {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 来源
   */
  categoryName: string;
  /**
   * 扩展字段
   */
  extra: string;
  /**
   * 标题
   */
  title: string;
  /**
   * 视频时长
   */
  videoDuration: number;
  /**
   * 文件大小
   */
  videoSize: number;
  /**
   * 一级类别
   */
  category: number;
  /**
   * 作者昵称
   */
  authorName: string;
  /**
   * 是否置顶
   */
  isStick: boolean;
}

export interface DPVideoContinueEvent {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 来源
   */
  categoryName: string;
  /**
   * 扩展字段
   */
  extra: string;
}

export interface DPSeekToEvent {
  /**
   * 视频位置
   */
  position: number;
  /**
   * 毫秒
   */
  time: number;
}

export interface DPListDataChangeEvent {
  /**
   * 数据源加密字符串
   */
  data: string;
  /**
   * 1刷新2追加
   */
  type: string;
}

export interface DPPageChangeEvent {
  /**
   * 页面索引值
   */
  position: number;
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 扩展字段
   */
  extra: string;
}

export interface DPVideoPauseEvent {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 来源
   */
  categoryName: string;
  /**
   * 扩展字段
   */
  extra: string;
  /**
   * 视频宽度
   */
  videoWidth: number;
  /**
   * 视频长度
   */
  videoHeight: number;
}

export interface DPVideoPlayEvent {
  /**
   * 新闻id
   */
  groupId: number;
  /**
   * 来源
   */
  categoryName: string;
  /**
   * 扩展字段
   */
  extra: string;
  /**
   * 视频宽度
   */
  videoWidth: number;
  /**
   * 视频长度
   */
  videoHeight: number;
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
