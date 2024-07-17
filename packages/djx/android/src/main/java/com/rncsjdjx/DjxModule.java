package com.rncsjdjx;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.rncsjdjx.options.CsjDJXInitOption;

@ReactModule(name = DjxModule.NAME)
public class DjxModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Djx";
  private final DJXHolder djxHolder;

  public DjxModule(ReactApplicationContext reactContext) {
    super(reactContext);
    djxHolder = new DJXHolder(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  @ReactMethod
  public void init(ReadableMap map, Promise promise) {
    djxHolder.init(new CsjDJXInitOption(map), promise);
  }
}
