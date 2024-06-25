import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import {init as initAdSdk} from "@rn-csj/ad";
import {init as initDjxSdk} from "@rn-csj/djx";
import {useEffect} from "react";
import {DJXDrawHomeView} from "../../src/views";

export default function App() {
  const [ready, setReady] = React.useState(false);

  useEffect(() => {
    initAdSdk('5434881', 'djxsdk_demo').then(initDjxSdk).then(() => {setReady(true)});
  }, []);

  if (!ready) {
    return null;
  }

  return (
    <View style={styles.container}>
      <DJXDrawHomeView />
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
