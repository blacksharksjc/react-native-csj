package com.rncsjad.ad;

import static com.rncsjad.constant.ErrorCode.AD_REWARD_VIDEO_FAIL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdLoadType;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.rncsjad.utils.EventHelper;
import com.rncsjad.utils.LogUtils;

public class RewardAd {
  private static final String TAG = LogUtils.createLogTag("RewardAd");
  private final ReactApplicationContext mContext;
  private final EventHelper mEventHelper;

  public RewardAd(ReactApplicationContext reactContext) {
    this.mContext = reactContext;
    this.mEventHelper = new EventHelper(reactContext, "RewardAd");
  }

  public void loadRewardAd(String code, Promise promise) {
    TTAdManager ttAdManager = TTAdSdk.getAdManager();
    TTAdNative mTTAdNative = ttAdManager.createAdNative(mContext);
    AdLoadListener mAdLoadListener = new AdLoadListener(promise);
    mTTAdNative.loadRewardVideoAd(buildSlot(code, mContext.getCurrentActivity()), mAdLoadListener);
  }

  public AdSlot buildSlot(String code, final Activity activity) {
    return new AdSlot.Builder()
      .setCodeId(code)
      .setAdLoadType(TTAdLoadType.LOAD)
      .build();
  }

  private class AdLoadListener implements TTAdNative.RewardVideoAdListener {
    private TTRewardVideoAd mAd;
    private int count = 0;
    private Promise mPromise = null;

    private void onResolve() {
      if (mPromise != null) {
        mPromise.resolve(count);
        reset();
      }
    }

    public AdLoadListener(Promise promise) {
      mPromise = promise;
    }

    private void onReject(String code, String message) {
      mPromise.reject(code, message);
    }

    private void reset() {
      count = 0;
      mPromise = null;
    }

    @Override
    public void onError(int code, String message) {
      Log.e(TAG, "加载激励视频失败");
      onReject(String.valueOf(code), message);

      WritableMap params = Arguments.createMap();
      params.putInt("code", code);
      params.putString("message", message);
      mEventHelper.sendEvent("onError", params);
    }

    /**
     * 广告基础信息加载完成，此方法是回调后是广告可调用展示的最早时机
     */
    @Override
    public void onRewardVideoAdLoad(TTRewardVideoAd ttRewardVideoAd) {
      Log.d(TAG, "激励视频加载成功");
      handleAd(ttRewardVideoAd);
      mEventHelper.sendEvent("onRewardVideoAdLoad", null);
    }

    @Override
    public void onRewardVideoCached() {
      // 已废弃 请使用 onRewardVideoCached(TTRewardVideoAd ad) 方法
    }

    /**
     * 广告基础信息与素材缓存完成，此时调用广告展示流畅，是展示广告的最理想时机
     */
    @Override
    public void onRewardVideoCached(TTRewardVideoAd ttRewardVideoAd) {
      Log.d(TAG, "激励视频已缓存");
      handleAd(ttRewardVideoAd);
      mEventHelper.sendEvent("onRewardVideoCached", null);
    }

    private void handleAd(TTRewardVideoAd ad) {
      if (mAd != null) {
        return;
      }
      mAd = ad;
      //【必须】广告展示时的生命周期监听
      mAd.setRewardAdInteractionListener(new AdLifeListener());
      //【可选】再看一个展示时的生命状态监听
      PlayAgainAdLifeListener playAgainAdLifeListener = new PlayAgainAdLifeListener();
      mAd.setRewardPlayAgainInteractionListener(playAgainAdLifeListener);
      //【可选】再看一个入口与奖励显示控制器
      PlayAgainController playAgainController = new PlayAgainController();
      mAd.setRewardPlayAgainController(playAgainController);

      Log.d(TAG, "播放激励视频");
      mAd.showRewardVideoAd(mContext.getCurrentActivity());
    }

    private class AdLifeListener implements TTRewardVideoAd.RewardAdInteractionListener {
      @Override
      public void onAdShow() {
        // 广告展示
        Log.d(TAG, "AdLifeListener --> rewardVideoAd show");
        mEventHelper.sendEvent("onAdShow", null);
      }

      @Override
      public void onAdVideoBarClick() {
        // 广告中产生了点击行为
        Log.d(TAG, "AdLifeListener --> rewardVideoAd bar click");
        mEventHelper.sendEvent("onAdVideoBarClick", null);
      }

      @Override
      public void onAdClose() {
        // 广告整体关闭
        Log.d(TAG, "AdLifeListener --> rewardVideoAd close");
        onResolve();
        mEventHelper.sendEvent("onAdClose", null);
      }

      // 视频播放完成回调
      @Override
      public void onVideoComplete() {
        // 广告素材播放完成，例如视频未跳过，完整的播放了
        Log.d(TAG, "AdLifeListener --> rewardVideoAd complete");
        mEventHelper.sendEvent("onVideoComplete", null);
      }

      @Override
      public void onVideoError() {
        // 广告素材展示时出错
        Log.e(TAG, "AdLifeListener --> rewardVideoAd error");
        onReject(AD_REWARD_VIDEO_FAIL.getStringCode(), AD_REWARD_VIDEO_FAIL.getMessage());
        mEventHelper.sendEvent("onVideoError", null);
      }

      @Override
      public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName, int errorCode, String errorMsg) {
        // 已废弃 请使用 onRewardArrived(boolean isRewardValid, int rewardType, Bundle extraInfo)
      }

      @Override
      public void onRewardArrived(boolean isRewardValid, int rewardType, Bundle extraInfo) {
        Log.d(TAG, "AdLifeListener --> onRewardArrived: " + String.valueOf(isRewardValid) + "," + String.valueOf(rewardType));
        if (isRewardValid) {
          count += 1;
        }
        RewardBundleModel rewardBundleModel = new RewardBundleModel(extraInfo);
        WritableMap params = Arguments.createMap();
        params.putBoolean("isRewardValid", isRewardValid);
        params.putInt("serverErrorCode", rewardBundleModel.getServerErrorCode());
        params.putString("serverErrorMessage", rewardBundleModel.getServerErrorMsg());
        params.putString("rewardName", rewardBundleModel.getRewardName());
        params.putInt("rewardAmount", rewardBundleModel.getRewardAmount());
        params.putDouble("rewardPropose", rewardBundleModel.getRewardPropose());
        params.putDouble("rewardCount", count);
        mEventHelper.sendEvent("onRewardArrived", params);
      }

      @Override
      public void onSkippedVideo() {
        // 用户在观看素材时点击了跳过
        Log.d(TAG, "AdLifeListener --> rewardVideoAd has onSkippedVideo");
        mEventHelper.sendEvent("onSkippedVideo", null);
      }
    }

    private class PlayAgainController implements TTRewardVideoAd.RewardAdPlayAgainController {
      @Override
      public void getPlayAgainCondition(int nextPlayAgainCount, Callback callback) {
        Log.d(TAG, "PlayAgainController --> getPlayAgainCondition, " + nextPlayAgainCount);
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_PLAY_AGAIN_ALLOW, nextPlayAgainCount < 2);
        bundle.putString(KEY_PLAY_AGAIN_REWARD_NAME, "");
        bundle.putString(KEY_PLAY_AGAIN_REWARD_AMOUNT, "");
        callback.onConditionReturn(bundle);
      }
    }

    /**
     * 【可选】再看广告生命状态监听器
     */
    private class PlayAgainAdLifeListener implements TTRewardVideoAd.RewardAdInteractionListener {
      @Override
      public void onAdShow() {
        Log.d(TAG, "PlayAgainAdLifeListener --> onAdShow");
        mEventHelper.sendEvent("onAdShow", null);
      }

      @Override
      public void onAdVideoBarClick() {
        Log.d(TAG, "PlayAgainAdLifeListener --> onAdVideoBarClick");
        mEventHelper.sendEvent("onAdVideoBarClick", null);
      }

      @Override
      public void onAdClose() {
        // 再看广告不会调到这个回调
        Log.d(TAG, "PlayAgainAdLifeListener --> onAdClose");
        mEventHelper.sendEvent("onAdClose", null);
      }

      //视频播放完成回调
      @Override
      public void onVideoComplete() {
        Log.d(TAG, "PlayAgainAdLifeListener --> onVideoComplete");
        mEventHelper.sendEvent("onVideoComplete", null);
      }

      @Override
      public void onVideoError() {
        Log.d(TAG, "PlayAgainAdLifeListener --> onVideoError");
        mEventHelper.sendEvent("onVideoError", null);
      }

      @Override
      public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName, int errorCode, String errorMsg) {
        // 已废弃 请使用 onRewardArrived(boolean isRewardValid, int rewardType, Bundle extraInfo) 方法
      }

      @Override
      public void onRewardArrived(boolean isRewardValid, int rewardType, Bundle extraInfo) {
        Log.d(TAG, "PlayAgainAdLifeListener --> onRewardArrived, " + String.valueOf(isRewardValid) + ", " + String.valueOf(rewardType));
        if (isRewardValid) {
          count += 1;
        }
        RewardBundleModel rewardBundleModel = new RewardBundleModel(extraInfo);
        WritableMap params = Arguments.createMap();
        params.putBoolean("isRewardValid", isRewardValid);
        params.putInt("serverErrorCode", rewardBundleModel.getServerErrorCode());
        params.putString("serverErrorMessage", rewardBundleModel.getServerErrorMsg());
        params.putString("rewardName", rewardBundleModel.getRewardName());
        params.putInt("rewardAmount", rewardBundleModel.getRewardAmount());
        params.putDouble("rewardPropose", rewardBundleModel.getRewardPropose());
        params.putDouble("rewardCount", count);
        mEventHelper.sendEvent("onRewardArrived", params);
      }

      @Override
      public void onSkippedVideo() {
        Log.d(TAG, "PlayAgainAdLifeListener --> onSkippedVideo");
        mEventHelper.sendEvent("onSkippedVideo", Arguments.createMap());
      }
    }
  }
}
