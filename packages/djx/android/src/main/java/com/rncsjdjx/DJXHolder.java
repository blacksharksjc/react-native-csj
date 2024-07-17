package com.rncsjdjx;

import static com.rncsjdjx.constant.ErrorCode.DJX_SDK_INIT_FAIL;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.djx.DJXSdk;
import com.bytedance.sdk.djx.DJXSdkConfig;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.rncsjdjx.options.CsjDJXInitOption;
import com.rncsjdjx.utils.EventHelper;
import com.rncsjdjx.utils.LogUtils;

public class DJXHolder {
  private static final String TAG = LogUtils.createLogTag("DJXHolder");
  private final ReactApplicationContext mContext;
  private final EventHelper mEventHelper;

  public DJXHolder(ReactApplicationContext reactContext) {
    this.mContext = reactContext;
    this.mEventHelper = new EventHelper(reactContext, "DjxSDK");
  }

  public void init(CsjDJXInitOption option, Promise promise) {
    Log.d(TAG, "开始初始化");
    DJXSdk.init(mContext, option.settingFileName, buildConfig());
    DJXSdk.start(new DJXSdk.StartListener() {
      @Override
      public void onStartComplete(boolean isSuccess, String message) {
        if (isSuccess) {
          Log.d(TAG, "初始化成功: " + message);
          promise.resolve(null);
        } else {
          Log.e(TAG, "初始化失败, " + message);
          promise.reject(DJX_SDK_INIT_FAIL.getStringCode(), message);
        }
        Log.d(TAG, "结束初始化");

        WritableMap params = Arguments.createMap();
        params.putBoolean("isSuccess", isSuccess);
        params.putString("message", message);
        mEventHelper.sendEvent("onStartComplete", params);
      }
    });
  }

  private DJXSdkConfig buildConfig() {
    return new DJXSdkConfig.Builder().debug(true).build();
  }
}
