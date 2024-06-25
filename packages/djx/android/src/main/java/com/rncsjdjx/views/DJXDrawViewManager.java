package com.rncsjdjx.views;

import android.graphics.Color;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bytedance.sdk.djx.DJXSdk;
import com.bytedance.sdk.djx.IDJXWidget;
import com.bytedance.sdk.djx.interfaces.listener.IDJXAdListener;
import com.bytedance.sdk.djx.interfaces.listener.IDJXDramaUnlockListener;
import com.bytedance.sdk.djx.interfaces.listener.IDJXDrawListener;
import com.bytedance.sdk.djx.model.DJXDrama;
import com.bytedance.sdk.djx.model.DJXDramaDetailConfig;
import com.bytedance.sdk.djx.model.DJXDramaUnlockAdMode;
import com.bytedance.sdk.djx.params.DJXWidgetDrawParams;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.rncsjdjx.utils.LogUtils;

import java.util.Map;

public class DJXDrawViewManager extends ViewGroupManager<FrameLayout> {
  public static final String REACT_CLASS = "DJXDrawViewManager";
  public static final String TAG = LogUtils.createLogTag(REACT_CLASS);

  ReactApplicationContext mContext;
  public final int COMMAND_CREATE = 1;
  private int mWidth;
  private int mHeight;
  private IDJXWidget mWidget;

  @NonNull
  @Override
  public String getName() {
    return REACT_CLASS;
  }

  public DJXDrawViewManager(ReactApplicationContext reactContext) {
    this.mContext = reactContext;
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
    assert args != null;
    int reactNativeViewId = args.getInt(0);
    int commandIdInt = Integer.parseInt(commandId);

    if (commandIdInt == COMMAND_CREATE) {
      root.setBackgroundColor(Color.parseColor("#FFFFFF"));
      createFragment(root, reactNativeViewId);
    }
  }

  public void createFragment(FrameLayout root, int reactNativeViewId) {
    ViewGroup parentView = root.findViewById(reactNativeViewId);
    setupLayout(parentView);

    mWidget = DJXSdk.factory().createDraw(buildParams(reactNativeViewId));

    FragmentActivity activity = (FragmentActivity) mContext.getCurrentActivity();
    assert activity != null;
    activity.getSupportFragmentManager().beginTransaction()
      .replace(reactNativeViewId, mWidget.getFragment(), String.valueOf(reactNativeViewId))
      .commit();
  }

  private DJXWidgetDrawParams buildParams(int reactNativeViewId) {
    DJXDramaDetailConfig dramaDetailConfig = DJXDramaDetailConfig.obtain(DJXDramaUnlockAdMode.MODE_COMMON, 5, new IDJXDramaUnlockListener() {
      @Override
      public void unlockFlowStart(@NonNull DJXDrama djxDrama, @NonNull UnlockCallback unlockCallback, @Nullable Map<String, ?> map) {

      }

      @Override
      public void unlockFlowEnd(@NonNull DJXDrama djxDrama, @Nullable UnlockErrorStatus unlockErrorStatus, @Nullable Map<String, ?> map) {

      }

      @Override
      public void showCustomAd(@NonNull DJXDrama djxDrama, @NonNull CustomAdCallback customAdCallback) {

      }
    });
    return DJXWidgetDrawParams
      .obtain()
      .detailConfig(dramaDetailConfig)
      .drawContentType(DJXWidgetDrawParams.DRAW_CONTENT_TYPE_ONLY_DRAMA)
      .drawChannelType(DJXWidgetDrawParams.DRAW_CHANNEL_TYPE_RECOMMEND)
      .hideClose(true, null)
      .hideChannelName(true)
      .hideChannelName(true)
      .adListener(new IDJXAdListener() {})
      .listener(new IDJXDrawListener() {});
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
