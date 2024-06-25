import React, { useEffect } from 'react';
import {
  Dimensions,
  InteractionManager,
  PixelRatio,
  requireNativeComponent,
} from 'react-native';
import { useNative } from '../hooks';

export enum DPGridViewType {
  Grid = '1',
  DoubleFeed = '2',
}

export enum CardStyle {
  NormalStyle = 1,
  StaggeredStyle,
}

export interface DPGridViewProps {
  type?: DPGridViewType;
  style?: {
    width: number;
    height: number;
  };
  cardStyle?: CardStyle;
}

const DPGridViewManager =
  requireNativeComponent<DPGridViewProps>('DPGridViewManager');

const { width: windowWidth, height: windowHeight } = Dimensions.get('window');
export const DPGridView: React.FC<DPGridViewProps> = ({
  type = DPGridViewType.DoubleFeed,
  style,
  cardStyle = CardStyle.StaggeredStyle,
  ...props
}) => {
  const { width = windowWidth, height = windowHeight } = style ?? {};

  const { nativeCompRef, dispatchCommand } = useNative('DJXDrawViewManager');
  useEffect(() => {
    InteractionManager.runAfterInteractions(() => {
      dispatchCommand('create');
    });
  }, [dispatchCommand, nativeCompRef]);

  return (
    <DPGridViewManager
      ref={nativeCompRef}
      style={{
        width: PixelRatio.getPixelSizeForLayoutSize(width),
        height: PixelRatio.getPixelSizeForLayoutSize(height),
      }}
      type={type}
      cardStyle={cardStyle}
      {...props}
    />
  );
};
