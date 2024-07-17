package com.rncsjdp;

import static com.rncsjdp.constant.ErrorCode.DP_SDK_INIT_FAIL;

import android.util.Log;

import com.bytedance.sdk.dp.DPSdk;
import com.bytedance.sdk.dp.DPSdkConfig;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.rncsjdp.options.CsjDpInitOption;
import com.rncsjdp.utils.EventHelper;
import com.rncsjdp.utils.LogUtils;

public class DpSDKHolder {
  private static final String TAG = LogUtils.createLogTag("DPHolder");

  private final ReactApplicationContext mContext;
  private final EventHelper mEventHelper;

  public DpSDKHolder(ReactApplicationContext reactContext) {
    this.mContext = reactContext;
    this.mEventHelper = new EventHelper(reactContext, "DpSDK");
  }

  public void init(CsjDpInitOption option, Promise promise) {
    DPSdk.init(mContext, "SDK_Setting.json", buildConfig(option));
    DPSdk.start(new DPSdk.StartListener() {
      @Override
      public void onStartComplete(boolean isSuccess, String message) {
        if (isSuccess) {
          Log.d(TAG, "初始化成功");
          promise.resolve(null);
        } else {
          Log.e(TAG, "初始化失败：" + message);
          promise.reject(DP_SDK_INIT_FAIL.getStringCode(), message);
        }

        WritableMap params = Arguments.createMap();
        params.putBoolean("isSuccess", isSuccess);
        params.putString("message", message);
        mEventHelper.sendEvent("onStartComplete", params);
      }
    });
  }

  private DPSdkConfig buildConfig(CsjDpInitOption option) {
    return new DPSdkConfig
      .Builder()
      .debug(option.debug)
      .fontStyle(option.largeFontStyle ? DPSdkConfig.ArticleDetailListTextStyle.FONT_XL : DPSdkConfig.ArticleDetailListTextStyle.FONT_NORMAL)
      .build();
  }
}
