package com.rncsjdjx.views;

import android.graphics.Color;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bytedance.sdk.djx.DJXSdk;
import com.bytedance.sdk.djx.IDJXWidget;
import com.bytedance.sdk.djx.interfaces.listener.IDJXDramaHomeListener;
import com.bytedance.sdk.djx.interfaces.listener.IDJXDramaUnlockListener;
import com.bytedance.sdk.djx.model.DJXDrama;
import com.bytedance.sdk.djx.model.DJXDramaDetailConfig;
import com.bytedance.sdk.djx.model.DJXDramaUnlockAdMode;
import com.bytedance.sdk.djx.params.DJXWidgetDramaHomeParams;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.bytedance.sdk.djx.model.DJXDramaUnlockInfo;
import com.bytedance.sdk.djx.model.DJXDramaUnlockMethod;
import com.rncsjdjx.utils.LogUtils;

import java.util.Map;

public class DJXDrawHomeViewManager extends ViewGroupManager<FrameLayout> {
  public static final String REACT_CLASS = "DJXDrawHomeViewManager";
  public static final String TAG = LogUtils.createLogTag(REACT_CLASS);

  ReactApplicationContext mContext;
  public final int COMMAND_CREATE = 1;
  private int mWidth;
  private int mHeight;
  private IDJXWidget mWidget;
  private int mFreeEpisode = 10;
  private int mUnlockEpisode = 5;

  @NonNull
  @Override
  public String getName() {
    return REACT_CLASS;
  }

  public DJXDrawHomeViewManager(ReactApplicationContext context) {
    this.mContext = context;
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

  @ReactProp(name = "freeEpisode")
  public void setFreeEpisode(FrameLayout view, int freeEpisode) {
    if (freeEpisode > 0) {
      this.mFreeEpisode = freeEpisode;
    }
  }

  @ReactProp(name = "unlockEpisode")
  public void setExtendEpisode(FrameLayout view, int unlockEpisode) {
    if (unlockEpisode > 0) {
      this.mUnlockEpisode = unlockEpisode;
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

    mWidget = DJXSdk.factory().createDramaHome(buildParams());

    FragmentActivity activity = (FragmentActivity) mContext.getCurrentActivity();
    assert activity != null;
    activity.getSupportFragmentManager()
      .beginTransaction()
      .replace(reactNativeViewId, mWidget.getFragment(), String.valueOf(reactNativeViewId))
      .commit();
  }

  private DJXWidgetDramaHomeParams buildParams() {
    DJXDramaDetailConfig dramaDetailConfig = DJXDramaDetailConfig
      .obtain(DJXDramaUnlockAdMode.MODE_COMMON, mFreeEpisode, new IDJXDramaUnlockListener() {
        @Override
        public void unlockFlowStart(@NonNull DJXDrama djxDrama, @NonNull UnlockCallback unlockCallback, @Nullable Map<String, ?> map) {
          Log.d(TAG, "unlockFlowStart: " + djxDrama);
          DJXDramaUnlockInfo info = new DJXDramaUnlockInfo(djxDrama.id, mUnlockEpisode, DJXDramaUnlockMethod.METHOD_AD, false, null, false);
          unlockCallback.onConfirm(info);
        }

        @Override
        public void unlockFlowEnd(@NonNull DJXDrama djxDrama, @Nullable UnlockErrorStatus unlockErrorStatus, @Nullable Map<String, ?> map) {
          Log.d(TAG, "unlockFlowEnd: " + djxDrama);
        }

        @Override
        public void showCustomAd(@NonNull DJXDrama djxDrama, @NonNull CustomAdCallback customAdCallback) {
          Log.d(TAG, "showCustomAd: " + djxDrama);
        }
      })
      .hideTopInfo(false)
      .hideBottomInfo(false);
    return DJXWidgetDramaHomeParams
      .obtain(dramaDetailConfig)
      .showBackBtn(false)
      .showPageTitle(false)
      .listener(new IDJXDramaHomeListener() {
        @Override
        public void onItemClick(DJXDrama djxDrama, @Nullable Map<String, Object> map) {
          Log.d(TAG, String.valueOf(djxDrama));
          super.onItemClick(djxDrama, map);
        }
      });
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
