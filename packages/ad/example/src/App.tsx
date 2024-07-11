import * as React from 'react';

import { StyleSheet, View, Button, type EmitterSubscription } from 'react-native';
import { addEventListener, loadRewardAd, updatePrivacy } from '@rn-csj/ad';
import { useEffect } from 'react';

export default function App() {
  useEffect(() => {
    const subscriptions: EmitterSubscription[] = [];

    subscriptions.push(addEventListener('AdSDK.onStartSuccess', () => {
      console.log('RewardAd.onRewardArrived');
    }));

    setTimeout(async () => {
      console.log('更新隐私策略');
      await updatePrivacy({
        isCanUseAndroidId: false
      });
    }, 5000);


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
