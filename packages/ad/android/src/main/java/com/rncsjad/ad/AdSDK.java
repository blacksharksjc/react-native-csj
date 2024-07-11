package com.rncsjad.ad;
import android.util.Log;

import com.bytedance.sdk.openadsdk.LocationProvider;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.rncsjad.options.CsjAdInitOption;
import com.rncsjad.options.CsjPrivacyOption;
import com.rncsjad.utils.EventHelper;
import com.rncsjad.utils.LogUtils;

public class AdSDK {
  private static final String TAG = LogUtils.createLogTag("AdSDK");

  private final ReactApplicationContext mContext;
  private boolean mInit;
  private boolean mStart;
  private final EventHelper mEventHelper;
  private final PrivacyCustomController privacyCustomController;

  public AdSDK(ReactApplicationContext reactContext) {
    this.mContext = reactContext;
    this.mEventHelper = new EventHelper(reactContext, "AdSDK");
    this.privacyCustomController = new PrivacyCustomController();
  }

  public void init(CsjAdInitOption option, Promise promise) {
    if (mInit) {
      Log.d(TAG, "SDK已初始化");
      promise.resolve(null);
      return;
    }
    TTAdSdk.init(mContext, buildConfig(option));
    mInit = true;
    Log.d(TAG, "SDK初始化完成");

    if (mStart) {
      Log.d(TAG, "SDK已启动");
      promise.resolve(null);
      return;
    }
    TTAdSdk.start(new TTAdSdk.Callback() {
      @Override
      public void success() {
        Log.d(TAG, "SDK启动成功");

        mEventHelper.sendEvent("onStartSuccess", null);

        mStart = true;
        promise.resolve(null);
      }

      @Override
      public void fail(int code, String message) {
        mStart = false;
        Log.e(TAG, "SDK启动失败");

        WritableMap params = Arguments.createMap();
        params.putInt("code", code);
        params.putString("message", message);
        mEventHelper.sendEvent("onStartFail", params);

        promise.reject(String.valueOf(code), message);
      }
    });
  }

  public void updatePrivacy(CsjPrivacyOption option) {
    if (option.alist != null) {
      privacyCustomController.setAlist(option.alist);
    }
    if (option.isCanUseAndroidId != null) {
      privacyCustomController.setCanUseAndroidId(option.isCanUseAndroidId);
    }
    if (option.isCanUseLocation != null) {
      privacyCustomController.setCanUseLocation(option.isCanUseLocation);
    }
    if (option.isCanUsePermissionRecordAudio != null) {
      privacyCustomController.setCanUsePermissionRecordAudio(option.isCanUsePermissionRecordAudio);
    }
    if (option.isCanUsePhoneState != null) {
      privacyCustomController.setCanUsePhoneState(option.isCanUsePhoneState);
    }
    if (option.isCanUseWifiState != null) {
      privacyCustomController.setCanUseWifiState(option.isCanUseWifiState);
    }
    if (option.isCanUseWriteExternal != null) {
      privacyCustomController.setCanUseWriteExternal(option.isCanUseWriteExternal);
    }
    Log.d(TAG, "隐私策略已更新：" + privacyCustomController);
  }

  private TTAdConfig buildConfig(CsjAdInitOption option) {
    return new TTAdConfig.Builder()
      .appId(option.appId)
      .appName(option.appName)
      .debug(option.debug)
      .titleBarTheme(option.titleBarTheme)
      .directDownloadNetworkType(option.directDownloadNetworkType)
      .allowShowNotify(option.allowShowNotify)
      .useTextureView(option.debug)
      .supportMultiProcess(option.supportMultiProcess)
      .customController(new TTCustomController() {
        @Override
        public boolean isCanUseLocation() {
          return privacyCustomController.isCanUseLocation();
        }

        @Override
        public boolean alist() {
          return privacyCustomController.isAlist();
        }

        @Override
        public boolean isCanUsePhoneState() {
          return privacyCustomController.isCanUsePhoneState();
        }

        @Override
        public boolean isCanUseWifiState() {
          return privacyCustomController.isCanUseWifiState();
        }

        @Override
        public boolean isCanUseWriteExternal() {
          return privacyCustomController.isCanUseWriteExternal();
        }

        @Override
        public boolean isCanUseAndroidId() {
          return privacyCustomController.isCanUseAndroidId();
        }

        @Override
        public boolean isCanUsePermissionRecordAudio() {
          return privacyCustomController.isCanUsePermissionRecordAudio();
        }
      })
      .build();
  }
}
