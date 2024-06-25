package com.rncsjdp.views;

import android.graphics.Color;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bytedance.sdk.dp.DPPageState;
import com.bytedance.sdk.dp.DPSdk;
import com.bytedance.sdk.dp.DPWidgetDrawParams;
import com.bytedance.sdk.dp.IDPAdListener;
import com.bytedance.sdk.dp.IDPDrawListener;
import com.bytedance.sdk.dp.IDPWidget;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.rncsjdp.utils.LogUtils;

import java.util.List;
import java.util.Map;

public class DPDrawViewManager extends ViewGroupManager<FrameLayout> {
  public static final String REACT_CLASS = "DPDrawViewManager";
  public static final String TAG = LogUtils.createLogTag(REACT_CLASS);

  ReactApplicationContext reactContext;
  public final int COMMAND_CREATE = 1;
  private int mWidth;
  private int mHeight;
  private IDPWidget mWidget;
  private int mProgressBarStyle;
  private int mDrawContentType;

  @NonNull
  @Override
  public String getName() {
    return REACT_CLASS;
  }

  public DPDrawViewManager(ReactApplicationContext reactContext) {
    this.reactContext = reactContext;
  }

  @ReactProp(name = "progressBarStyle")
  public void setProgressBarStyle(FrameLayout view, int progressBarStyle) {
    this.mProgressBarStyle = progressBarStyle;
  }

  @ReactProp(name = "drawContentType")
  public void setDrawContentType(FrameLayout view, int drawContentType) {
    this.mDrawContentType = drawContentType;
  }


  @ReactPropGroup(names = {"width", "height"}, customType = "Style")
  public void setStyle(FrameLayout view, int index, Integer value) {
    if (index == 0) {
      mWidth = value;
    }

    if (index == 1) {
      mHeight = value;
    }
  }

  @Override
  public void onDropViewInstance(@NonNull FrameLayout view) {
    super.onDropViewInstance(view);
    if (mWidget != null) {
      Log.d(TAG, "销毁组件");
      mWidget.getFragment().setUserVisibleHint(false);
      mWidget.destroy();
      mWidget = null;
    }
  }

  @NonNull
  @Override
  public FrameLayout createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new FrameLayout(reactContext);
  }

  @NonNull
  @Override
  public Map<String, Integer> getCommandsMap() {
    return MapBuilder.of("create", COMMAND_CREATE);
  }

  @Override
  public void receiveCommand(
    @NonNull FrameLayout root,
    String commandId,
    ReadableArray args
  ) {
    super.receiveCommand(root, commandId, args);

    int commandIdInt = Integer.parseInt(commandId);

    if (commandIdInt == COMMAND_CREATE) {
      Log.d(TAG, "创建组件");
      assert args != null;
      int reactNativeViewId = args.getInt(0);
      root.setBackgroundColor(Color.parseColor("#FFFFFF"));
      createFragment(root, reactNativeViewId);
    }
  }

  public void createFragment(FrameLayout root, int reactNativeViewId) {
    ViewGroup parentView = root.findViewById(reactNativeViewId);
    setupLayout(parentView);

    mWidget = DPSdk.factory().createDraw(buildParams(reactNativeViewId));

    FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();
    assert activity != null;
    activity.getSupportFragmentManager().beginTransaction()
      .replace(reactNativeViewId, mWidget.getFragment(), String.valueOf(reactNativeViewId))
      .commit();
  }

  private DPWidgetDrawParams buildParams(int reactNativeViewId) {
    return DPWidgetDrawParams
      .obtain()
      .progressBarStyle(this.mProgressBarStyle)
      .drawChannelType(DPWidgetDrawParams.DRAW_CHANNEL_TYPE_RECOMMEND)
      .drawContentType(this.mDrawContentType)
      .hideClose(true, null)
      .hideChannelName(true)
      .hideChannelName(true);
  }

  public void setupLayout(ViewGroup view) {
    Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
      @Override
      public void doFrame(long frameTimeNanos) {
        manuallyLayoutChildren(view);
        view.getViewTreeObserver().dispatchOnGlobalLayout();
        Choreographer.getInstance().postFrameCallback(this);
      }
    });
  }

  /**
   * Layout all children properly
   */
  public void manuallyLayoutChildren(ViewGroup view) {
    view.measure(
      View.MeasureSpec.makeMeasureSpec(mWidth, View.MeasureSpec.EXACTLY),
      View.MeasureSpec.makeMeasureSpec(mHeight, View.MeasureSpec.EXACTLY));

    view.layout(0, 0, mWidth, mHeight);
  }
}
