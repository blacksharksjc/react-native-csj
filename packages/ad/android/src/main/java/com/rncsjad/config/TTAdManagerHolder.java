package com.rncsjad.config;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.facebook.react.bridge.Promise;
import com.rncsjad.utils.LogUtils;

public class TTAdManagerHolder {
  private static final String TAG = LogUtils.createLogTag("TTAdManagerHolder");
  private static boolean sInit;
  private static boolean sStart;

  public static void init(final Context context, String appId, String appName, Promise promise) {
    boolean isFinished = init(context, appId, appName, new TTAdSdk.Callback() {
      @Override
      public void success() {
        promise.resolve(null);
      }

      @Override
      public void fail(int i, String s) {
        promise.reject(String.valueOf(i), s);
      }
    });

    if (isFinished) {
      promise.resolve(null);
    }
  }

  public static boolean init(final Context context, String appId, String appName, TTAdSdk.Callback callback) {
    if (sInit) {
      Log.d(TAG, "SDK已初始化");
      return true;
    }
    TTAdSdk.init(context, buildConfig(appId, appName));
    sInit = true;
    Log.d(TAG, "SDK初始化完成");

    if (sStart) {
      Log.d(TAG, "SDK已启动");
      return true;
    }
    TTAdSdk.start(new TTAdSdk.Callback() {
      @Override
      public void success() {
        Log.d(TAG, "SDK启动成功");

        sStart = true;
        callback.success();
      }

      @Override
      public void fail(int i, String s) {
        sStart = false;
        Log.e(TAG, "SDK启动失败");
        callback.fail(i, s);
      }
    });
    return false;
  }

  private static TTAdConfig buildConfig(String appId, String appName) {
    return new TTAdConfig.Builder()
      .appId(appId)
      .appName(appName)
      .debug(true)
      .useTextureView(true)
      .build();
  }
}
