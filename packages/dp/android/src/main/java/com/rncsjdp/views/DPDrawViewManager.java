package com.rncsjdp.views;

import android.graphics.Color;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bytedance.sdk.dp.DPSdk;
import com.bytedance.sdk.dp.DPWidgetDrawParams;
import com.bytedance.sdk.dp.IDPWidget;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.rncsjdp.utils.LogUtils;

import java.util.Map;

public class DPDrawViewManager extends ViewGroupManager<FrameLayout> {
  public static final String REACT_CLASS = "DPDrawViewManager";
  public static final String TAG = LogUtils.createLogTag(REACT_CLASS);

  ReactApplicationContext reactContext;
  public final int COMMAND_CREATE = 1;
  private IDPWidget mWidget;

  // 宽度
  private int mWidth;
  // 高度
  private int mHeight;
  // 进度条样式
  private Integer mProgressBarStyle;
  // 混流内容
  private Integer mDrawContentType;
  // 广告偏移量（距离底部）
  private Integer mAdOffset;
  // 否隐藏返回按钮
  private Boolean mHideClose = false;
  // 否可以显示新手引导动画
  private Boolean mShowGuide = true;
  // 推荐频道名称
  private String mCustomCategory;
  // 小视频外流底部标题文案、进度条、评论按钮底部偏移
  private Integer mBottomOffset;
  // 标题栏距离顶部间距
  private Integer mTitleTopMargin;
  // 标题栏距离左间距
  private Integer mTitleLeftMargin;
  // 标题栏距离右间距
  private Integer mTitleRightMargin;
  // 沉浸式小视频频道
  private Integer mDrawChannelType;
  // 是否隐藏关注功能
  private Boolean mHideFollow;
  // 是否隐藏频道名称
  private Boolean mHideChannelName;
  // 是否支持下拉刷新
  private Boolean mEnableRefresh;
  // 视频场景
  private String mScene;


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

  @ReactProp(name = "adOffset")
  public void setAdOffset(FrameLayout view, int adOffset) {
    this.mAdOffset = adOffset;
  }

  @ReactProp(name = "hideClose")
  public void setHideClose(FrameLayout view, boolean hideClose) {
    this.mHideClose = hideClose;
  }

  @ReactProp(name = "showGuide")
  public void setShowGuide(FrameLayout view, boolean showGuide) {
    this.mShowGuide = showGuide;
  }

  @ReactProp(name = "customCategory")
  public void setCustomCategory(FrameLayout view, String customCategory) {
    this.mCustomCategory = customCategory;
  }

  @ReactProp(name = "bottomOffset")
  public void setBottomOffset(FrameLayout view, int bottomOffset) {
    this.mBottomOffset = bottomOffset;
  }

  @ReactProp(name = "titleTopMargin")
  public void setTitleTopMargin(FrameLayout view, int titleTopMargin) {
    this.mTitleTopMargin = titleTopMargin;
  }

  @ReactProp(name = "titleLeftMargin")
  public void setTitleLeftMargin(FrameLayout view, int titleLeftMargin) {
    this.mTitleLeftMargin = titleLeftMargin;
  }

  @ReactProp(name = "titleRightMargin")
  public void setTitleRightMargin(FrameLayout view, int titleRightMargin) {
    this.mTitleRightMargin = titleRightMargin;
  }

  @ReactProp(name = "drawChannelType")
  public void setDrawChannelType(FrameLayout view, int drawChannelType) {
    this.mDrawChannelType = drawChannelType;
  }

  @ReactProp(name = "hideFollow")
  public void setHideFollow(FrameLayout view, boolean hideFollow) {
    this.mHideFollow = hideFollow;
  }

  @ReactProp(name = "hideChannelName")
  public void setHideChannelName(FrameLayout view, boolean hideChannelName) {
    this.mHideChannelName = hideChannelName;
  }

  @ReactProp(name = "enableRefresh")
  public void setEnableRefresh(FrameLayout view, boolean enableRefresh) {
    this.mEnableRefresh = enableRefresh;
  }

  @ReactProp(name = "scene")
  public void setScene(FrameLayout view, String scene) {
    this.mScene = scene;
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
    DPWidgetDrawParams params = DPWidgetDrawParams
      .obtain();

    if (mProgressBarStyle != null) {
      params.progressBarStyle(mProgressBarStyle);
    }
    if (mDrawContentType != null) {
      params.drawContentType(mDrawContentType);
    }
    if (mAdOffset != null) {
      params.adOffset(mAdOffset);
    }
    if (mHideClose != null) {
      params.hideClose(mHideClose, null);
    }
    if (mShowGuide != null) {
      params.showGuide(mShowGuide);
    }
    if (mCustomCategory != null) {
      params.customCategory(mCustomCategory);
    }
    if (mBottomOffset != null) {
      params.bottomOffset(mBottomOffset);
    }
    if (mTitleTopMargin != null) {
      params.titleTopMargin(mTitleTopMargin);
    }
    if (mTitleLeftMargin != null) {
      params.titleLeftMargin(mTitleLeftMargin);
    }
    if (mTitleRightMargin != null) {
      params.titleRightMargin(mTitleRightMargin);
    }
    if (mDrawChannelType != null) {
      params.drawChannelType(mDrawChannelType);
    }
    if (mHideFollow != null) {
      params.hideFollow(mHideFollow);
    }
    if (mHideChannelName != null) {
      params.hideChannelName(mHideChannelName);
    }
    if (mEnableRefresh != null) {
      params.enableRefresh(mEnableRefresh);
    }
    if (mScene != null) {
      params.scene(mScene);
    }
    Log.d(TAG, "params: " + params);
    return params;
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
