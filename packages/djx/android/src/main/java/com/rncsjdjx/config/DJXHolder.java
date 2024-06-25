package com.rncsjdjx.config;

import static com.rncsjdjx.constant.ErrorCode.DJX_SDK_INIT_FAIL;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.djx.DJXSdk;
import com.bytedance.sdk.djx.DJXSdkConfig;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.facebook.react.bridge.Promise;
import com.rncsjdjx.utils.LogUtils;

public class DJXHolder {
  private static final String TAG = LogUtils.createLogTag("DJXHolder");

  public static void init(Context context, Promise promise) {
    Log.d(TAG, "开始初始化");
    DJXSdk.init(context, "SDK_Setting.json",buildConfig());
    DJXSdk.start(new DJXSdk.StartListener() {
      @Override
      public void onStartComplete(boolean b, String s) {
        if (b) {
          Log.d(TAG, "初始化成功");
          promise.resolve(null);
        } else {
          Log.e(TAG, "初始化失败, " + s);
          promise.reject(DJX_SDK_INIT_FAIL.getStringCode(), s);
        }
        Log.d(TAG, "结束初始化");
      }
    });
  }

  private static DJXSdkConfig buildConfig() {
    return new DJXSdkConfig.Builder().debug(true).build();
  }
}
