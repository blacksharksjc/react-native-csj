# @rn-csj/dp

穿山甲小视频SDK React Native插件

## 📦 安装

### 安装依赖包

#### npm

```sh

npm install @rn-csj/dp

```

#### yarn

```shell

yarn add @rn-csj/dp

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
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

<!-- application为示例节点，请将里面的provider添加至自己的application中 -->
<application>
  <provider
    android:name="com.bytedance.sdk.dp.act.DPProvider"
    android:authorities="${applicationId}.BDDPProvider"
    android:exported="false" />
</application>
```

</details>

## 🔨使用

### SDK初始化

#### 调用方式

`init(options: CsjDpSDKInitOption) => Promise<void>`

#### 示例

```ts
import { init } from '@rn-csj/dp';

init(options);

```

#### CsjDpSDKInitOption

| 属性            | 类型    | 描述          | 是否必输 | 默认值 | 示例               | 说明 |
| --------------- | ------- | ------------- | -------- | ------ | ------------------ | ---- |
| settingFileName | string  | 配置文件名称  | Y        | -      | 'SDK_Setting.json' | -    |
| debug           | boolean | 是否开启debug | N        | false  | -                  | -    |

### 沉浸式小视频

#### 示例

```tsx

import { FDPDrawView, DrawContentType, ProgressBarStyle } from '@rn-csj/dp';

export function App() {
  return (
    <FDPDrawView
      drawContentType={DrawContentType.Video}
      progressBarStyle={ProgressBarStyle.Light}
      style={{ width: 100, height: 100 }}
    />
  )
}

```

#### API

| 属性             | 类型                                  | 描述             | 是否必输 | 默认值 | 示例                      | 说明                              |
| ---------------- | ------------------------------------- | ---------------- | -------- | ------ | ------------------------- | --------------------------------- |
| style            | object                                | 样式             | Y        | -      | {width: 100, height: 100} | 仅支持width和height，并且两者必输 |
| drawContentType  | [DrawContentType](#DrawContentType)   | 混流内容         | N        | Video  | -                         | -                                 |
| progressBarStyle | [ProgressBarStyle](#ProgressBarStyle) | 播放器进度条样式 | N        | Light  | -                         | -                                 |

##### DrawContentType

| 枚举值 | 说明             |
| ------ | ---------------- |
| Video  | 小视频+广告      |
| Live   | 直播+广告        |
| All    | 直播+小视频+广告 |

##### ProgressBarStyle

| 枚举值 | 说明 |
| ------ | ---- |
| Light  | 浅色 |
| Dark   | 深色 |

### 宫格小视频

#### 示例

```tsx

import { DPGridView, DPGridViewType } from '@rn-csj/dp';

export function App() {
  return (
    <DPGridView
      style={{ width: 100, height: 100 }}
      type={DPGridViewType.DoubleFeed}
      cardStyle={CardStyle.NormalStyle}
    />
  )
}

```

#### API

| 属性      | 类型                              | 描述         | 是否必输 | 默认值         | 示例                      | 说明                              |
| --------- | --------------------------------- | ------------ | -------- | -------------- | ------------------------- | --------------------------------- |
| style     | object                            | 样式         | Y        | -              | {width: 100, height: 100} | 仅支持width和height，并且两者必输 |
| type      | [DPGridViewType](#DPGridViewType) | 类型         | N        | DoubleFeed     | -                         | -                                 |
| cardStyle | [CardStyle](#CardStyle)           | 宫格卡片样式 | N        | StaggeredStyle | -                         | -                                 |

##### DPGridViewType

| 枚举值     | 说明     |
| ---------- | -------- |
| Grid       | 宫格     |
| DoubleFeed | 双Feed流 |

##### CardStyle

| 枚举值         | 说明       |
| -------------- | ---------- |
| NormalStyle    | 普通样式   |
| StaggeredStyle | 瀑布流样式 |

## 💡插件支持情况

| 功能                            | 是否支持 |
| ------------------------------- | -------- |
| 沉浸式小视频                    | ✓        |
| 宫格小视频                      | ✓        |
| 小视频卡片                      | ×        |
| 小视频单卡片                    | ×        |
| 个人主页                        | ×        |
| 热门个性化组件_气泡组件         | ×        |
| 热门个性化推荐组件_文字链组件   | ×        |
| 热门个性化推荐组件_站内Push组件 | ×        |
| 热门个性化推荐组件_Banner组件   | ×        |

## SDK版本信息

* 广告SDK: `com.pangle.cn:ads-sdk-pro:5.6.1.5`

## 许可协议

MIT
