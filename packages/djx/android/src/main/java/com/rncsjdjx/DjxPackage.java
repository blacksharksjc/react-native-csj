package com.rncsjdjx;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.rncsjdjx.views.DJXDrawHomeViewManager;
import com.rncsjdjx.views.DJXDrawViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DjxPackage implements ReactPackage {
    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new DjxModule(reactContext));
        return modules;
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
      List<ViewManager> managers = new ArrayList<>();
      managers.add(new DJXDrawViewManager(reactContext));
      managers.add(new DJXDrawHomeViewManager(reactContext));
      return managers;
    }
}
