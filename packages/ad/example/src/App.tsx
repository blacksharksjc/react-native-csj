import * as React from 'react';

import {StyleSheet, View, Button} from 'react-native';
import {loadRewardAd} from "@rn-csj/ad";

export default function App() {

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
