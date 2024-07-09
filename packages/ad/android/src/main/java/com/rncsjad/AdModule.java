package com.rncsjad;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.rncsjad.ad.RewardAd;
import com.rncsjad.ad.SplashAd;
import com.rncsjad.config.TTAdManagerHolder;
import com.rncsjad.options.CsjAdInitOption;
import com.rncsjad.utils.LogUtils;

import java.util.Objects;

@ReactModule(name = AdModule.NAME)
public class AdModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Ad";
  public static final String TAG = LogUtils.createLogTag(NAME);

  public AdModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  @ReactMethod
  public void init(ReadableMap options, Promise promise) {
    CsjAdInitOption option = new CsjAdInitOption(options);
    TTAdManagerHolder.init(this.getReactApplicationContext(), option, promise);
  }

  @ReactMethod
  public void loadSplashScreen(String code, Promise promise) {
    Log.d(TAG, "loadSplashScreen code: " + code);
    SplashAd.loadSplashAd(code, this.getReactApplicationContext(), new TTAdSdk.Callback() {
      @Override
      public void success() {
        promise.resolve(null);
      }

      @Override
      public void fail(int i, String s) {
        promise.reject(String.valueOf(i), s);
      }
    });
  }

  @ReactMethod
  public void loadRewardAd(String code, Promise promise) {
    Log.d(TAG, "loadRewardAd code: " + code);
    RewardAd.loadRewardAd(code, this.getReactApplicationContext(), promise);
  }

  @ReactMethod
  public void requestPermissionIfNecessary() {
    TTAdSdk.getAdManager().requestPermissionIfNecessary(this.getReactApplicationContext());
  }

  @ReactMethod
  public void addListener(String eventName) {
    // Keep: Required for RN built in Event Emitter Calls.
  }

  @ReactMethod
  public void removeListeners(double count) {
    // Keep: Required for RN built in Event Emitter Calls.
  }

}
