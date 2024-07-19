package com.rncsjdp.listeners;

import com.bytedance.sdk.dp.IDPAdListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.rncsjdp.utils.EventHelper;

import java.util.Map;

public class CsjDPAdListener extends IDPAdListener {
  private final int viewTagId;
  private final EventHelper eventHelper;

  public CsjDPAdListener(int viewTagId, ReactApplicationContext reactContext) {
    this.viewTagId = viewTagId;
    this.eventHelper  = new EventHelper(reactContext);
  }

  @Override
  public void onDPAdRequest(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdRequest", null);
    super.onDPAdRequest(map);
  }

  @Override
  public void onDPAdRequestSuccess(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdRequestSuccess", null);
    super.onDPAdRequestSuccess(map);
  }

  @Override
  public void onDPAdRequestFail(int i, String s, Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdRequestFail", null);
    super.onDPAdRequestFail(i, s, map);
  }

  @Override
  public void onDPAdFillFail(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdFillFail", null);
    super.onDPAdFillFail(map);
  }

  @Override
  public void onDPAdShow(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdShow", null);
    super.onDPAdShow(map);
  }

  @Override
  public void onDPAdPlayStart(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdPlayStart", null);
    super.onDPAdPlayStart(map);
  }

  @Override
  public void onDPAdPlayPause(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdPlayPause", null);
    super.onDPAdPlayPause(map);
  }

  @Override
  public void onDPAdPlayContinue(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdPlayContinue", null);
    super.onDPAdPlayContinue(map);
  }

  @Override
  public void onDPAdPlayComplete(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdPlayComplete", null);
    super.onDPAdPlayComplete(map);
  }

  @Override
  public void onDPAdClicked(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPAdClicked", null);
    super.onDPAdClicked(map);
  }

  @Override
  public void onRewardVerify(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onRewardVerify", null);
    super.onRewardVerify(map);
  }

  @Override
  public void onSkippedVideo(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onSkippedVideo", null);
    super.onSkippedVideo(map);
  }
}
