import * as React from 'react';

import { Dimensions, StyleSheet, View } from 'react-native';
import { useEffect } from 'react';
import { init as initAdSdk } from '@react-native-csj/ad';
import { init as initDjxSdk } from '@react-native-csj/djx';
import {
  DPDrawView,
  DrawChannelType,
  DrawContentType,
  init as initDpSDK,
} from '@react-native-csj/dp';

const { width, height } = Dimensions.get('window');
export default function App() {
  const [ready, setReady] = React.useState(false);

  useEffect(() => {
    initAdSdk({
      appId: '5434881',
      appName: 'djxsdk_demo',
    })
      .then(() => {
        return initDjxSdk({
          debug: true,
          settingFileName: 'SDK_Setting.json',
        });
      })
      .then(() => {
        return initDpSDK({
          debug: true,
          settingFileName: 'SDK_Setting.json',
        });
      })
      .then(() => {
        setReady(true);
      });
  }, []);

  if (!ready) {
    return null;
  }

  return (
    <View style={styles.container}>
      <DPDrawView
        style={{ width, height }}
        hideClose
        hideChannelName
        hideFollow
        drawChannelType={DrawChannelType.Recommend}
        drawContentType={DrawContentType.All}
        onDPVideoPlay={(data) => {
          console.log('onDPVideoPlay', data.nativeEvent);
        }}
        onDPRefreshFinish={() => {
          console.log('onDPRefreshFinish');
        }}
        onDPPageChange={data => {
          console.log('onDPPageChange', data.nativeEvent);
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
