package com.rncsjdp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.rncsjdp.views.DPDrawViewManager;
import com.rncsjdp.views.DPGridViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DpPackage implements ReactPackage {
  @NonNull
  @Override
  public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();
    modules.add(new DpModule(reactContext));
    return modules;
  }

  @NonNull
  @Override
  public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
    List<ViewManager> managers = new ArrayList<>();
    managers.add(new DPGridViewManager(reactContext));
    managers.add(new DPDrawViewManager(reactContext));
    return managers;
  }
}
