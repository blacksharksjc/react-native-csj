import { AppRegistry } from 'react-native';
import App from './src/App';
import { init, loadSplashScreen } from '@rn-csj/ad';

init({
  appId: '5001121',
  appName: 'APP测试媒体',
}).then(() => loadSplashScreen({
  code: '801121648',
}));

AppRegistry.registerComponent('main', () => App);
