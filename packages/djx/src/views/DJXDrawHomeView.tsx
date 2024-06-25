import React, { useEffect } from 'react';
import {
  Dimensions,
  InteractionManager,
  PixelRatio,
  requireNativeComponent,
} from 'react-native';
import { useNative } from '../hooks';

export interface DJXDrawHomeViewProps {
  style?: {
    width: number;
    height: number;
  };
  freeEpisode?: number;
  unlockEpisode?: number;
}

const DJXDrawHomeViewManager = requireNativeComponent<DJXDrawHomeViewProps>(
  'DJXDrawHomeViewManager'
);

const { width: windowWidth, height: windowHeight } = Dimensions.get('window');
export const DJXDrawHomeView: React.FC<DJXDrawHomeViewProps> = ({
  style,
  ...props
}) => {
  const { width = windowWidth, height = windowHeight } = style ?? {};

  const { nativeCompRef, dispatchCommand } = useNative(
    'DJXDrawHomeViewManager'
  );
  useEffect(() => {
    InteractionManager.runAfterInteractions(() => {
      dispatchCommand('create');
    });
  }, [dispatchCommand, nativeCompRef]);

  return (
    <DJXDrawHomeViewManager
      ref={nativeCompRef}
      style={{
        width: PixelRatio.getPixelSizeForLayoutSize(width),
        height: PixelRatio.getPixelSizeForLayoutSize(height),
      }}
      {...props}
    />
  );
};
