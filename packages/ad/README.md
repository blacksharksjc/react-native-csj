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
