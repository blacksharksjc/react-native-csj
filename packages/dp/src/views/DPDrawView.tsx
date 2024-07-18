import React, { useEffect } from 'react';
import {
  Dimensions,
  InteractionManager,
  PixelRatio,
  requireNativeComponent,
} from 'react-native';
import { useNative } from '../hooks';
import { DPDrawViewProps } from 'src/types';

const DPDrawViewManager: any = requireNativeComponent('DPDrawViewManager');
const { width: windowWidth, height: windowHeight } = Dimensions.get('window');
export const DPDrawView: React.FC<DPDrawViewProps> = ({
  style,
  ...props
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
      {...props}
    />
  );
};

export default DPDrawView;
