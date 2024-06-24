import { AppRegistry } from 'react-native';
import App from './src/App';
import {init, loadSplashScreen} from "@rn-csj/ad";

init('5001121', 'APP测试媒体').then(() => loadSplashScreen('801121648'));

AppRegistry.registerComponent('main', () => App);
