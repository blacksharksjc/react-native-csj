package com.rncsjad.config;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.facebook.react.bridge.Promise;
import com.rncsjad.options.CsjAdInitOption;
import com.rncsjad.utils.LogUtils;

public class TTAdManagerHolder {
  private static final String TAG = LogUtils.createLogTag("TTAdManagerHolder");
  private static boolean sInit;
  private static boolean sStart;

  public static void init(final Context context, CsjAdInitOption option, Promise promise) {
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
