import * as React from 'react';

import { StyleSheet, View, Button, type EmitterSubscription } from 'react-native';
import { addEventListener, loadRewardAd, AdEvent, SplashAdEvent } from '@rn-csj/ad';
import { useEffect } from 'react';

export default function App() {
  useEffect(() => {
    const subscriptions: EmitterSubscription[] = [];

    subscriptions.push(addEventListener(AdEvent.AD_START_SUCCESS, () => {
      console.log('广告初始化成功');
    }));
    subscriptions.push(addEventListener(SplashAdEvent.ON_SPLASH_LOAD_SUCCESS, () => {
      console.log('ON_SPLASH_LOAD_SUCCESS');
    }));
    subscriptions.push(addEventListener(SplashAdEvent.ON_SPLASH_LOAD_FAIL, () => {
      console.log('ON_SPLASH_LOAD_FAIL');
    }));
    subscriptions.push(addEventListener(SplashAdEvent.ON_SPLASH_RENDER_SUCCESS, () => {
      console.log('ON_SPLASH_RENDER_SUCCESS');
    }));
    subscriptions.push(addEventListener(SplashAdEvent.ON_SPLASH_RENDER_FAIL, () => {
      console.log('ON_SPLASH_RENDER_FAIL');
    }));

    return () => {
      subscriptions.forEach(sub => sub.remove())
    }
  }, [])

  return (
    <View style={styles.container}>
      <Button title={'加载激励视频'} onPress={() => loadRewardAd('901121593')} />
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
