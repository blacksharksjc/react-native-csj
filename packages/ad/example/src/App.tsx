import * as React from 'react';

import { StyleSheet, View, Button, type EmitterSubscription } from 'react-native';
import { addEventListener, loadRewardAd, AdEvent, SplashAdEvent, RewardAdEvent } from '@rn-csj/ad';
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

    subscriptions.push(addEventListener(RewardAdEvent.ON_ERROR, () => {
      console.log('ON_ERROR');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_REWARD_VIDEO_AD_LOAD, () => {
      console.log('ON_REWARD_VIDEO_AD_LOAD');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_REWARD_VIDEO_CACHED, () => {
      console.log('ON_REWARD_VIDEO_CACHED');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_AD_SHOW, () => {
      console.log('ON_AD_SHOW');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_AD_VIDEO_BAR_CLICK, () => {
      console.log('ON_AD_VIDEO_BAR_CLICK');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_AD_CLOSE, () => {
      console.log('ON_AD_CLOSE');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_AD_CLOSE, () => {
      console.log('ON_AD_CLOSE');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_VIDEO_COMPLETE, () => {
      console.log('ON_VIDEO_COMPLETE');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_VIDEO_ERROR, () => {
      console.log('ON_VIDEO_ERROR');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_SKIPPED_VIDEO, () => {
      console.log('ON_SKIPPED_VIDEO');
    }));
    subscriptions.push(addEventListener(RewardAdEvent.ON_REWARD_ARRIVED, () => {
      console.log('ON_REWARD_ARRIVED');
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
