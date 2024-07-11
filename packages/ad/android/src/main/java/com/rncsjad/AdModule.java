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
import com.rncsjad.ad.AdSDK;
import com.rncsjad.ad.RewardAd;
import com.rncsjad.ad.SplashAd;
import com.rncsjad.options.CsjAdInitOption;
import com.rncsjad.options.CsjPrivacyOption;
import com.rncsjad.utils.LogUtils;

@ReactModule(name = AdModule.NAME)
public class AdModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Ad";
  public static final String TAG = LogUtils.createLogTag(NAME);
  private final AdSDK adSDK;
  private final SplashAd splashAd;
  private final RewardAd rewardAd;

  public AdModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.adSDK = new AdSDK(reactContext);
    this.splashAd = new SplashAd(reactContext);
    this.rewardAd = new RewardAd(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void init(ReadableMap map, Promise promise) {
    CsjAdInitOption option = new CsjAdInitOption(map);
    adSDK.init(option, promise);
  }

  @ReactMethod
  public void updatePrivacy(ReadableMap options, Promise promise) {
    CsjPrivacyOption option = new CsjPrivacyOption(options);
    adSDK.updatePrivacy(option);
    promise.resolve(null);
  }

  @ReactMethod
  public void loadSplashScreen(String code, Promise promise) {
    Log.d(TAG, "loadSplashScreen code: " + code);
    splashAd.loadSplashAd(code, promise);
  }

  @ReactMethod
  public void loadRewardAd(String code, Promise promise) {
    Log.d(TAG, "loadRewardAd code: " + code);
    rewardAd.loadRewardAd(code, promise);
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
