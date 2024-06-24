package com.rncsjad;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.rncsjad.ad.RewardAd;
import com.rncsjad.ad.SplashAd;
import com.rncsjad.config.TTAdManagerHolder;
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
  public void init(String appId, String appName, Promise promise) {
    Log.d(TAG, "appId: " + appId);
    Log.d(TAG, "appName: " + appName);
    TTAdManagerHolder.init(this.getReactApplicationContext(), appId, appName, promise);
  }

  @ReactMethod
  public void loadSplashScreen(String code, Promise promise) {
    Log.d(TAG, "loadSplashScreen code: " + code);
    SplashAd.loadSplashAd(code, Objects.requireNonNull(this.getCurrentActivity()), new TTAdSdk.Callback() {
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
  public void loadRewardAd(String code,Promise promise) {
    Log.d(TAG, "loadRewardAd code: " + code);
    RewardAd.loadRewardAd(code, Objects.requireNonNull(this.getCurrentActivity()), promise);
  }

  @ReactMethod
  public void requestPermissionIfNecessary() {
    TTAdSdk.getAdManager().requestPermissionIfNecessary(this.getReactApplicationContext());
  }

}
