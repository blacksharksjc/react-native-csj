package com.rncsjdp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.rncsjdp.config.DPHolder;
import com.rncsjdp.utils.LogUtils;

@ReactModule(name = DpModule.NAME)
public class DpModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Dp";
  public static final String TAG = LogUtils.createLogTag(NAME);

  public DpModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void init(Promise promise) {
    DPHolder.init(this.getReactApplicationContext(), promise);
  }
}
