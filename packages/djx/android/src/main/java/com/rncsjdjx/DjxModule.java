package com.rncsjdjx;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.rncsjdjx.config.DJXHolder;

@ReactModule(name = DjxModule.NAME)
public class DjxModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Djx";

  public DjxModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  @ReactMethod
  public void init(Promise promise) {
    DJXHolder.init(this.getReactApplicationContext(), promise);
  }
}
