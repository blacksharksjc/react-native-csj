import * as React from 'react';

import {StyleSheet, View, Button} from 'react-native';
import { addEventListener, loadRewardAd } from '@rn-csj/ad';
import { useEffect } from 'react';
import { AdEvent } from '../../src/types';

export default function App() {
  useEffect(() => {
    const subscription = addEventListener(AdEvent.AD_START_SUCCESS, () => {
      console.log('广告初始化成功');
    })

    return () => {
      subscription.remove();
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
