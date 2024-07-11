# @rn-csj/ad

穿山甲广告SDK React Native插件

## 📦 安装

### 安装依赖包

#### npm

```sh

npm install @rn-csj/ad

```
#### yarn

```shell

yarn add @rn-csj/ad

```

#### Android
`android/build.gradle`中添加如下内容：

```gradle

allprojects {
  repositories {
      // ...
      maven {url "https://artifact.bytedance.com/repository/pangle"}
      // ...
  }
}

```


## 🔨使用

### SDK初始化

```ts
import { init } from '@rn-csj/ad';

init('your_app_id', 'your_app_name');

```

### 开屏广告

```ts
import { loadSplashScreen } from '@rn-csj/ad';

loadSplashScreen('your_ad_code');

```

### 模版渲染激励视频广告

```ts
import { loadRewardAd } from '@rn-csj/ad';

loadRewardAd('your_ad_code');

```


## 💡插件支持情况

### 广告

| 功能           | 是否支持 |
|--------------|------|
| 开屏广告         | ✓    |
| 模版渲染激励视频广告   | ✓    |
| 新模版渲染插屏广告    | ×    |
| 模板渲染信息流广告    | ×    |
| 模板渲染Banner广告 | ×    |
| 模板渲染Draw广告   | ×    |
| 模版渲染全屏视频广告   | ×    |
| 模板渲染插屏广告     | ×    |
| 自渲染信息流广告     | ×    |
| 自渲染Banner广告  | ×    |
| 自渲染Draw广告    | ×    |
| 自渲染贴片广告      | ×    |
| 自渲染插屏广告      | ×    |


## SDK版本信息

* 广告SDK: `com.pangle.cn:ads-sdk-pro:5.6.1.5`

## 许可协议

MIT
