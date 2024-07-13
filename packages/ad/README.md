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

#### 配置

<details>
<summary>Android</summary>
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

`android/app/src/main/AndroidManifest.xml`中添加如下内容：

```xml
<!--必要权限-->
<uses-permission android:name="android.permission.INTERNET"/>

  <!--必要权限，解决安全风险漏洞，发送和注册广播事件需要调用带有传递权限的接口-->
<permission android:name="${applicationId}.openadsdk.permission.TT_PANGOLIN"
            android:protectionLevel="signature"/>

<uses-permission android:name="${applicationId}.openadsdk.permission.TT_PANGOLIN"/>

  <!--可选权限-->
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
<uses-permission android:name="android.permission.GET_TASKS"/>

  <!--可选，穿山甲提供“获取地理位置权限”和“不给予地理位置权限，开发者传入地理位置参数”两种方式上报用户位置，两种方式均可不选，添加位置权限或参数将帮助投放定位广告-->
  <!--请注意：无论通过何种方式提供给穿山甲用户地理位置，均需向用户声明地理位置权限将应用于穿山甲广告投放，穿山甲不强制获取地理位置信息-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

  <!--建议添加“query_all_package”权限，穿山甲将通过此权限在Android R系统上判定广告对应的应用是否在用户的app上安装，避免投放错误的广告，以此提高用户的广告体验。若添加此权限，需要在您的用户隐私文档中声明！ -->
<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"/>

  <!-- application为示例节点，请将里面的provider添加至自己的application中 -->
<application>
<provider
  android:name="com.bytedance.sdk.openadsdk.TTFileProvider"
  android:authorities="${applicationId}.TTFileProvider"
  android:exported="false"
  android:grantUriPermissions="true">
  <meta-data
    android:name="android.support.FILE_PROVIDER_PATHS"
    android:resource="@xml/file_paths"/>
</provider>

<provider android:name="com.bytedance.sdk.openadsdk.multipro.TTMultiProvider"
          android:authorities="${applicationId}.TTMultiProvider" android:exported="false"/>
</application>
```

</details>

## 🔨使用

### SDK初始化

#### 调用方式

`init(options: CsjInitOption) => Promise<void>`

#### 示例

```ts
import { init } from '@rn-csj/ad';

init(options);

```

#### CsjInitOption

| 属性                        | 类型                              | 描述              | 是否必输 | 默认值                   | 示例                        | 说明                     |
|---------------------------|---------------------------------|-----------------|------|-----------------------|---------------------------|------------------------|
| appId                     | string                          | 应用ID            | Y    | -                     | {width: 100, height: 100} | 仅支持width和height，并且两者必输 |
| appName                   | string                          | 应用名称            | Y    | -                     | -                         | -                      |
| useTextureView            | boolean                         | 是否使用TextureView | N    | true                  | -                         | -                      |
| titleBarTheme             | [TitleBarTheme](#TitleBarTheme) | 落地页主题           | N    | TITLE_BAR_THEME_LIGHT | -                         | -                      |
| directDownloadNetworkType | [NetworkType](#NetworkType)     | 允许直接下载的网络状态集合   | N    | NETWORK_STATE_WIFI    | -                         | -                      |
| allowShowNotify           | boolean                         | 是否允许sdk展示通知栏提示  | N    | true                  | -                         | -                      |
| debug                     | boolean                         | 是否开启debug       | N    | true                  | -                         | -                      |
| supportMultiProcess       | boolean                         | 是否支持多进程         | N    | false                 | -                         | -                      |

##### TitleBarTheme

| 枚举值                          | 说明         |
|------------------------------|------------|
| TITLE_BAR_THEME_LIGHT        | 亮色主题       |
| TITLE_BAR_THEME_DARK         | 暗色主题       |
| TITLE_BAR_THEME_NO_TITLE_BAR | 无title bar |

##### NetworkType

| 枚举值                  | 说明   |
|----------------------|------|
| NETWORK_STATE_MOBILE | 移动网络 |
| NETWORK_STATE_2G     | 2G   |
| NETWORK_STATE_3G     | 3G   |
| NETWORK_STATE_WIFI   | Wifi |
| NETWORK_STATE_4G     | 4G   |
| NETWORK_STATE_5G     | 5G   |

### 开屏广告

> [!IMPORTANT]
> 不支持点睛动画

#### 调用方式

`loadSplashScreen(option: LoadSplashAdOption) => Promise<void>`

#### 示例

```ts
import { loadSplashScreen } from '@rn-csj/ad';

loadSplashScreen(option);

```

#### CsjLoadSplashAdOption

| 属性      | 类型     | 描述       | 是否必输 | 默认值 | 示例 | 说明 |
|---------|--------|----------|------|-----|----|----|
| code    | string | 广告位      | Y    | -   | -  | -  |
| timeout | string | 广告加载超时时间 | Y    | -   | -  | -  |

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
