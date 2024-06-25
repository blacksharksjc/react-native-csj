import * as React from 'react';

import {Dimensions, StyleSheet, View} from 'react-native';
import {useEffect} from "react";
import {init as initAdSdk} from "@rn-csj/ad";
import {init as initDjxSdk} from "@rn-csj/djx";
import {DPDrawView, init as initDpSDK} from "@rn-csj/dp";

const { width, height } = Dimensions.get('window');
export default function App() {
  const [ready, setReady] = React.useState(false);

  useEffect(() => {
    initAdSdk('5434881', 'djxsdk_demo').then(initDjxSdk).then(initDpSDK).then(() => {setReady(true)});
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
