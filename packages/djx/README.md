# @rn-csj/djx

穿山甲短剧SDK React Native插件

## 📦 安装

### 安装依赖包

#### npm

```sh

npm install @rn-csj/djx

```

#### yarn

```shell

yarn add @rn-csj/djx

```

#### 配置

<details>
<summary>Android</summary>
`android/build.gradle`中添加如下内容：

```gradle

allprojects {
  repositories {
      // ...
      maven {url "https://artifact.bytedance.com/repository/Volcengine/"}
      maven {url "https://artifact.bytedance.com/repository/pangle"}
      // ...
  }
}

```

`android/app/src/main/AndroidManifest.xml`中添加如下内容：

```xml
<!-- 这四个权限最好都申请，有助于视频推荐和ecpm -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```

</details>

## 🔨使用

### SDK初始化

#### 调用方式

`init(options: CsjDjxSDKInitOption) => Promise<void>`

#### 示例

```ts
import { init } from '@rn-csj/dp';

init(options);

```

#### CsjDjxSDKInitOption

| 属性            | 类型    | 描述          | 是否必输 | 默认值 | 示例               | 说明 |
| --------------- | ------- | ------------- | -------- | ------ | ------------------ | ---- |
| settingFileName | string  | 配置文件名称  | Y        | -      | 'SDK_Setting.json' | -    |
| debug           | boolean | 是否开启debug | N        | false  | -                  | -    |


### 短剧聚合页

#### 示例

```tsx

import { DJXDrawHomeView } from '@rn-csj/djx';

export function App() {
  return (
    <DJXDrawHomeView
      style={{ width: 100, height: 100 }}
      freeEpisode={5}
      unlockEpisode={5}
    />
  )
}
```

#### API

| 属性            | 类型     | 描述       | 是否必输 | 默认值 | 示例                        | 说明                     |
|---------------|--------|----------|------|-----|---------------------------|------------------------|
| style         | object | 样式       | Y    | -   | {width: 100, height: 100} | 仅支持width和height，并且两者必输 |
| freeEpisode   | number | 免费集数     | N    | 5   | -                         | -                      |
| unlockEpisode | number | 看广告后解锁集数 | N    | 5   | -                         | -                      |

### 短剧滑滑流

```tsx

import { DJXDrawView } from '@rn-csj/djx';

export function App() {
  return (
    <DJXDrawView style={{ width: 100, height: 100 }} />
  )
}
```

#### API

| 属性    | 类型     | 描述 | 是否必输 | 默认值 | 示例                        | 说明                     |
|-------|--------|----|------|-----|---------------------------|------------------------|
| style | object | 样式 | Y    | -   | {width: 100, height: 100} | 仅支持width和height，并且两者必输 |

## 💡插件支持情况

| 功能                 | 是否支持 |
|--------------------|------|
| 沉浸式小视频             | ✓    |
| 宫格小视频              | ✓    |
| 小视频卡片              | ×    |
| 小视频单卡片             | ×    |
| 个人主页               | ×    |
| 热门个性化组件_气泡组件       | ×    |
| 热门个性化推荐组件_文字链组件    | ×    |
| 热门个性化推荐组件_站内Push组件 | ×    |
| 热门个性化推荐组件_Banner组件 | ×    |

## SDK版本信息

* 广告SDK: `com.pangle.cn:ads-sdk-pro:5.6.1.5`

## 许可协议

MIT
