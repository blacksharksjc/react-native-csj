package com.rncsjdjx.config;

import static com.rncsjdjx.constant.ErrorCode.DJX_SDK_INIT_FAIL;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.dp.DPSdk;
import com.bytedance.sdk.dp.DPSdkConfig;
import com.facebook.react.bridge.Promise;
import com.rncsjdjx.utils.LogUtils;

public class DPHolder {
  private static final String TAG = LogUtils.createLogTag("DPHolder");

  public static void init(Context context, Promise promise) {
    DPSdk.init(context, "SDK_Setting.json",buildConfig());
    DPSdk.start(new DPSdk.StartListener() {
      @Override
      public void onStartComplete(boolean b, String s) {
        if (b) {
          Log.d(TAG, "初始化成功");
          promise.resolve(null);
        } else {
          Log.e(TAG, "初始化失败：" + s);
          promise.reject(DJX_SDK_INIT_FAIL.getStringCode(), DJX_SDK_INIT_FAIL.getMessage());
        }
      }
    });
  }

  private static DPSdkConfig buildConfig() {
    return new DPSdkConfig.Builder().debug(true).build();
  }
}
