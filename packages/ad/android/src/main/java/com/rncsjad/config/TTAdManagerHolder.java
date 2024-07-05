package com.rncsjad.config;

import static com.rncsjad.constant.AdInitEvent.AD_START_SUCCESS;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.rncsjad.constant.AdInitEvent;
import com.rncsjad.options.CsjAdInitOption;
import com.rncsjad.utils.LogUtils;

public class TTAdManagerHolder {
  private static final String TAG = LogUtils.createLogTag("TTAdManagerHolder");
  private static boolean sInit;
  private static boolean sStart;

  public static void init(final ReactContext context, CsjAdInitOption option, Promise promise) {
    if (sInit) {
      Log.d(TAG, "SDK已初始化");
      promise.resolve(null);
      return;
    }
    TTAdSdk.init(context, buildConfig(option));
    sInit = true;
    Log.d(TAG, "SDK初始化完成");

    if (sStart) {
      Log.d(TAG, "SDK已启动");
      promise.resolve(null);
      return;
    }
    TTAdSdk.start(new TTAdSdk.Callback() {
      @Override
      public void success() {
        Log.d(TAG, "SDK启动成功");

        sendEvent(context, AD_START_SUCCESS.name(), Arguments.createMap());

        sStart = true;
        promise.resolve(null);
      }

      @Override
      public void fail(int i, String s) {
        sStart = false;
        Log.e(TAG, "SDK启动失败");
        promise.reject(String.valueOf(i), s);
      }
    });
  }

  private static void sendEvent(ReactContext reactContext,
                                String eventName,
                                @Nullable WritableMap params) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
  }

  private static TTAdConfig buildConfig(CsjAdInitOption option) {
    return new TTAdConfig.Builder()
      .appId(option.appId)
      .appName(option.appName)
      .debug(option.debug)
      .titleBarTheme(option.titleBarTheme)
      .directDownloadNetworkType(option.directDownloadNetworkType)
      .allowShowNotify(option.allowShowNotify)
      .useTextureView(option.debug)
      .build();
  }
}
