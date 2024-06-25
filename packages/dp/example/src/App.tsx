import * as React from 'react';

import {Dimensions, StyleSheet, View} from 'react-native';
import {useEffect} from "react";
import {init as initAdSdk} from "@rn-csj/ad";
import {init as initDpSDK} from "@rn-csj/dp";
import {DPDrawView} from "../../src/views";

const { width, height } = Dimensions.get('window');
export default function App() {
  const [ready, setReady] = React.useState(false);

  useEffect(() => {
    initAdSdk('5001121', 'APP测试媒体').then(initDpSDK).then(() => {setReady(true)});
  }, []);

  if (!ready) {
    return null;
  }

  return (
    <View style={styles.container}>
      <DPDrawView style={{width, height}}/>
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
