import React, { useEffect } from 'react';
import {
  Dimensions,
  InteractionManager,
  PixelRatio,
  requireNativeComponent,
} from 'react-native';
import { useNative } from '../hooks';
import { DPGridViewProps } from 'src/types';

const DPGridViewManager =
  requireNativeComponent<DPGridViewProps>('DPGridViewManager');

const { width: windowWidth, height: windowHeight } = Dimensions.get('window');
export const DPGridView: React.FC<DPGridViewProps> = ({ style, ...props }) => {
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
      {...props}
    />
  );
};

export default DPGridView;
