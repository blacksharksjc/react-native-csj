package com.rncsjdp.utils;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventHelper {
  private final ReactApplicationContext mContext;
  private final List<String> namespaces = new ArrayList<String>();

  public EventHelper(ReactApplicationContext context, String...namespaces) {
    this.mContext = context;
    Collections.addAll(this.namespaces, namespaces);
  }

  private String buildName(String eventName) {
    String namespace = String.join(".", namespaces);
    return namespace + "." + eventName;
  }

  public void sendEvent(String eventName, @Nullable WritableMap params) {
    mContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(buildName(eventName), params);
  }
}
