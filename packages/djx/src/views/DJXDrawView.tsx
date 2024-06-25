import React, { useEffect } from 'react';

import {
  Dimensions,
  InteractionManager,
  PixelRatio,
  requireNativeComponent,
} from 'react-native';
import { useNative } from '../hooks';

export interface DJXDrawViewProps {
  style?: {
    width: number;
    height: number;
  };
}

const DJXDrawViewManager =
  requireNativeComponent<DJXDrawViewProps>('DJXDrawViewManager');

const { width: windowWidth, height: windowHeight } = Dimensions.get('window');
export const DJXDrawView: React.FC<DJXDrawViewProps> = ({
  style,
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
    <DJXDrawViewManager
      ref={nativeCompRef}
      style={{
        width: PixelRatio.getPixelSizeForLayoutSize(width),
        height: PixelRatio.getPixelSizeForLayoutSize(height),
      }}
      {...props}
    />
  );
};
