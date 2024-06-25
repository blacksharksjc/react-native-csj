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
import com.bytedance.sdk.dp.DPWidgetGridParams;
import com.bytedance.sdk.dp.IDPAdListener;
import com.bytedance.sdk.dp.IDPGridListener;
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

public class DPGridViewManager extends ViewGroupManager<FrameLayout> {
  public static final String REACT_CLASS = "DPGridViewManager";
  public static final String TAG = LogUtils.createLogTag(REACT_CLASS);

  ReactApplicationContext mContext;
  public final int COMMAND_CREATE = 1;
  private int mWidth;
  private int mHeight;
  private IDPWidget mWidget;

  private String mType = "1"; // 1-grid, 2-double feed
  private Integer mCardStyle = DPWidgetGridParams.CARD_STAGGERED_STYLE;

  @NonNull
  @Override
  public String getName() {
    return REACT_CLASS;
  }

  public DPGridViewManager(ReactApplicationContext reactContext) {
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

  @ReactProp(name = "type")
  public void setType(FrameLayout view, String type) {
    this.mType = type;
  }

  @ReactProp(name = "cardStyle")
  public void setCardStyle(FrameLayout view, int cardStyle) {
    if (cardStyle != DPWidgetGridParams.CARD_STAGGERED_STYLE && cardStyle != DPWidgetGridParams.CARD_NORMAL_STYLE) {
      Log.e(TAG, "cardStyle参数错误, " + cardStyle);
      return;
    }
    this.mCardStyle = cardStyle;
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

    if (this.mType.equals("1")) {
      mWidget = DPSdk.factory().createGrid(buildParams());
    } else {
      mWidget = DPSdk.factory().createDoubleFeed(buildParams());
    }

    FragmentActivity activity = (FragmentActivity) mContext.getCurrentActivity();
    assert activity != null;
    activity.getSupportFragmentManager().beginTransaction()
      .replace(reactNativeViewId, mWidget.getFragment(), String.valueOf(reactNativeViewId))
      .commit();
  }

  private DPWidgetGridParams buildParams() {
    return DPWidgetGridParams.obtain().cardStyle(mCardStyle);
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
