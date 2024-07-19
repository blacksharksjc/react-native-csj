package com.rncsjdp.listeners;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bytedance.sdk.dp.DPPageState;
import com.bytedance.sdk.dp.IDPDrawListener;
import com.bytedance.sdk.dp.IDPQuizHandler;
import com.facebook.react.bridge.ReactApplicationContext;
import com.rncsjdp.utils.EventHelper;

import java.util.List;
import java.util.Map;

public class CsjDPDrawListener extends IDPDrawListener {
  private final int viewTagId;
  private final EventHelper eventHelper;

  public CsjDPDrawListener(int viewTagId, ReactApplicationContext reactContext) {
    this.viewTagId = viewTagId;
    this.eventHelper  = new EventHelper(reactContext);
  }

  @Override
  public void onDPRefreshFinish() {
    eventHelper.receiveEvent(viewTagId, "onDPRefreshFinish", null);
    super.onDPRefreshFinish();
  }

  @Override
  public void onDPListDataChange(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPListDataChange", null);
    super.onDPListDataChange(map);
  }

  @Override
  public void onDPSeekTo(int i, long l) {
    eventHelper.receiveEvent(viewTagId, "onDPSeekTo", null);
    super.onDPSeekTo(i, l);
  }

  @Override
  public void onDPPageChange(int i, Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPPageChange", null);
    super.onDPPageChange(i, map);
  }

  @Override
  public void onDPVideoPlay(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPVideoPlay", null);
    super.onDPVideoPlay(map);
  }

  @Override
  public void onDPVideoPause(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPVideoPause", null);
    super.onDPVideoPause(map);
  }

  @Override
  public void onDPVideoContinue(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPVideoContinue", null);
    super.onDPVideoContinue(map);
  }

  @Override
  public void onDPVideoCompletion(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPVideoCompletion", null);
    super.onDPVideoCompletion(map);
  }

  @Override
  public void onDPVideoOver(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPVideoOver", null);
    super.onDPVideoOver(map);
  }

  @Override
  public void onDPClose() {
    eventHelper.receiveEvent(viewTagId, "onDPClose", null);
    super.onDPClose();
  }

  @Override
  public void onDPReportResult(boolean b, Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPReportResult", null);
    super.onDPReportResult(b, map);
  }

  @Override
  public void onDPRequestStart(@Nullable Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPRequestStart", null);
    super.onDPRequestStart(map);
  }

  @Override
  public void onDPRequestFail(int i, String s, @Nullable Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPRequestFail", null);
    super.onDPRequestFail(i, s, map);
  }

  @Override
  public void onDPRequestSuccess(List<Map<String, Object>> list) {
    eventHelper.receiveEvent(viewTagId, "onDPRequestSuccess", null);
    super.onDPRequestSuccess(list);
  }

  @Override
  public void onDPClickAvatar(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPClickAvatar", null);
    super.onDPClickAvatar(map);
  }

  @Override
  public void onDPClickAuthorName(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPClickAuthorName", null);
    super.onDPClickAuthorName(map);
  }

  @Override
  public void onDPClickComment(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPClickComment", null);
    super.onDPClickComment(map);
  }

  @Override
  public void onDPClickLike(boolean b, Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPClickLike", null);
    super.onDPClickLike(b, map);
  }

  @Override
  public void onDPClickShare(Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPClickShare", null);
    super.onDPClickShare(map);
  }

  @Override
  public void onDPPageStateChanged(DPPageState dpPageState) {
    eventHelper.receiveEvent(viewTagId, "onDPPageStateChanged", null);
    super.onDPPageStateChanged(dpPageState);
  }

  @Override
  public View onCreateQuizView(ViewGroup viewGroup) {
    return super.onCreateQuizView(viewGroup);
  }

  @Override
  public void onQuizBindData(View view, List<String> list, int i, int i1, IDPQuizHandler idpQuizHandler, Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onQuizBindData", null);
    super.onQuizBindData(view, list, i, i1, idpQuizHandler, map);
  }

  @Override
  public void onChannelTabChange(int i) {
    eventHelper.receiveEvent(viewTagId, "onChannelTabChange", null);
    super.onChannelTabChange(i);
  }

  @Override
  public void onDurationChange(long l) {
    eventHelper.receiveEvent(viewTagId, "onDurationChange", null);
    super.onDurationChange(l);
  }
}
