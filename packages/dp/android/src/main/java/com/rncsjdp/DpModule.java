package com.rncsjdp;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.rncsjdp.options.CsjDpInitOption;
import com.rncsjdp.utils.LogUtils;

@ReactModule(name = DpModule.NAME)
public class DpModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Dp";
  public static final String TAG = LogUtils.createLogTag(NAME);
  private final DpSDKHolder dpSDKHolder;

  public DpModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.dpSDKHolder = new DpSDKHolder(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void init(ReadableMap map, Promise promise) {
    dpSDKHolder.init(new CsjDpInitOption(map), promise);
  }
}
