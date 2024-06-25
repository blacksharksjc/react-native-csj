import React, { useEffect } from 'react';
import {
  Dimensions,
  InteractionManager,
  PixelRatio,
  requireNativeComponent,
} from 'react-native';
import { useNative } from '../hooks';

export enum DrawContentType {
  Video = 1,
  Live = 2,
  All = 3,
}

export enum ProgressBarStyle {
  Light = 1,
  Dark = 2,
}

export interface DPDrawViewProps {
  drawContentType?: DrawContentType;
  style?: {
    width: number;
    height: number;
  };
  progressBarStyle?: ProgressBarStyle;
}

const DPDrawViewManager: any = requireNativeComponent('DPDrawViewManager');

const { width: windowWidth, height: windowHeight } = Dimensions.get('window');
export const DPDrawView: React.FC<DPDrawViewProps> = ({
  drawContentType = DrawContentType.Video,
  style,
  progressBarStyle = ProgressBarStyle.Light,
}) => {
  const { width = windowWidth, height = windowHeight } = style ?? {};

  const { nativeCompRef, dispatchCommand } = useNative('DPDrawViewManager');

  useEffect(() => {
    InteractionManager.runAfterInteractions(() => {
      dispatchCommand('create');
    });
  }, [dispatchCommand, nativeCompRef]);

  return (
    <DPDrawViewManager
      ref={nativeCompRef}
      style={{
        width: PixelRatio.getPixelSizeForLayoutSize(width),
        height: PixelRatio.getPixelSizeForLayoutSize(height),
      }}
      drawContentType={drawContentType}
      progressBarStyle={progressBarStyle}
    />
  );
};
