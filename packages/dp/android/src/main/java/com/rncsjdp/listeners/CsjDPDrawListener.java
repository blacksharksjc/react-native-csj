package com.rncsjdp.listeners;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bytedance.sdk.dp.DPPageState;
import com.bytedance.sdk.dp.IDPDrawListener;
import com.bytedance.sdk.dp.IDPQuizHandler;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.rncsjdp.utils.EventHelper;
import com.rncsjdp.utils.LogUtils;

import java.util.List;
import java.util.Map;

public class CsjDPDrawListener extends IDPDrawListener {
  private final int viewTagId;
  private final EventHelper eventHelper;
  private final String TAG = LogUtils.createLogTag("CsjDPDrawListener");

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
    WritableMap params = Arguments.createMap();
    params.putString("data", String.valueOf(map.get("data")));
    params.putString("type", String.valueOf(map.get("type")));
    eventHelper.receiveEvent(viewTagId, "onDPListDataChange", params);
    super.onDPListDataChange(map);
  }

  @Override
  public void onDPSeekTo(int position, long time) {
    WritableMap params = Arguments.createMap();
    params.putInt("position", position);
    params.putDouble("time", time);
    eventHelper.receiveEvent(viewTagId, "onDPSeekTo", params);
    super.onDPSeekTo(position, time);
  }

  @Override
  public void onDPPageChange(int position, Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putInt("position", position);
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("extra", String.valueOf(map.get("extra")));
    eventHelper.receiveEvent(viewTagId, "onDPPageChange", params);
    super.onDPPageChange(position, map);
  }

  @Override
  public void onDPVideoPlay(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    params.putString("extra", String.valueOf(map.get("extra")));
    params.putInt("videoWidth", Integer.parseInt(String.valueOf(map.get("video_width"))));
    params.putInt("videoHeight", Integer.parseInt(String.valueOf(map.get("video_height"))));
    eventHelper.receiveEvent(viewTagId, "onDPVideoPlay", params);
    super.onDPVideoPlay(map);
  }

  @Override
  public void onDPVideoPause(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    params.putString("extra", String.valueOf(map.get("extra")));
    params.putInt("videoWidth", Integer.parseInt(String.valueOf(map.get("video_width"))));
    params.putInt("videoHeight", Integer.parseInt(String.valueOf(map.get("video_height"))));
    eventHelper.receiveEvent(viewTagId, "onDPVideoPause", params);
    super.onDPVideoPause(map);
  }

  @Override
  public void onDPVideoContinue(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    params.putString("extra", String.valueOf(map.get("extra")));
    eventHelper.receiveEvent(viewTagId, "onDPVideoContinue", params);
    super.onDPVideoContinue(map);
  }

  @Override
  public void onDPVideoCompletion(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    params.putString("extra", String.valueOf(map.get("extra")));
    params.putString("title", String.valueOf(map.get("title")));
    params.putInt("videoDuration", Integer.parseInt(String.valueOf(map.get("video_duration"))));
    params.putDouble("videoSize", Long.parseLong(String.valueOf(map.get("video_size"))));
    params.putInt("category", Integer.parseInt(String.valueOf(map.get("category"))));
    params.putString("authorName", String.valueOf(map.get("author_name")));
    params.putBoolean("isStick", Boolean.parseBoolean(String.valueOf(map.get("is_stick"))));
    eventHelper.receiveEvent(viewTagId, "onDPVideoCompletion", params);
    super.onDPVideoCompletion(map);
  }

  @Override
  public void onDPVideoOver(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putInt("percent", Integer.parseInt(String.valueOf(map.get("percent"))));
    params.putDouble("duration", Long.parseLong(String.valueOf(map.get("duration"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    params.putString("extra", String.valueOf(map.get("extra")));
    eventHelper.receiveEvent(viewTagId, "onDPVideoOver", params);
    super.onDPVideoOver(map);
  }

  @Override
  public void onDPClose() {
    eventHelper.receiveEvent(viewTagId, "onDPClose", null);
    super.onDPClose();
  }

  @Override
  public void onDPReportResult(boolean isSucceed, Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putBoolean("isSucceed", isSucceed);
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    eventHelper.receiveEvent(viewTagId, "onDPReportResult", params);
    super.onDPReportResult(isSucceed, map);
  }

  @Override
  public void onDPRequestStart(@Nullable Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onDPRequestStart", null);
    super.onDPRequestStart(map);
  }

  @Override
  public void onDPRequestFail(int code, String message, @Nullable Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putInt("code", code);
    params.putString("message", message);
    if (map != null) {
      params.putString("reqId", String.valueOf(map.get("req_id")));
    }
    eventHelper.receiveEvent(viewTagId, "onDPRequestFail", params);
    super.onDPRequestFail(code, message, map);
  }

  @Override
  public void onDPRequestSuccess(List<Map<String, Object>> list) {
    Log.d(TAG, list.toString());
    WritableMap params = Arguments.createMap();
    WritableArray array = Arguments.createArray();
    for (int i = 0; i < list.size(); i++) {
      Map<String, Object> item = list.get(i);
      WritableMap data = Arguments.createMap();
      data.putDouble("groupId", Long.parseLong(String.valueOf(item.get("group_id"))));
      data.putString("categoryName", String.valueOf(item.get("category_name")));
      data.putString("extra", String.valueOf(item.get("extra")));
      data.putString("title", String.valueOf(item.get("title")));
      data.putInt("videoDuration", Integer.parseInt(String.valueOf(item.get("video_duration"))));
      data.putDouble("videoSize", Long.parseLong(String.valueOf(item.get("video_size"))));
      data.putInt("category", Integer.parseInt(String.valueOf(item.get("category"))));
      data.putString("authorName", String.valueOf(item.get("author_name")));
      data.putBoolean("isStick", Boolean.parseBoolean(String.valueOf(item.get("is_stick"))));
      // cover_list让穿山甲加密了，此处无法处理
      array.pushMap(data);
    }

    eventHelper.receiveEvent(viewTagId, "onDPRequestSuccess", params);
    super.onDPRequestSuccess(list);
  }

  @Override
  public void onDPClickAvatar(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    eventHelper.receiveEvent(viewTagId, "onDPClickAvatar", params);
    super.onDPClickAvatar(map);
  }

  @Override
  public void onDPClickAuthorName(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    eventHelper.receiveEvent(viewTagId, "onDPClickAuthorName", params);
    super.onDPClickAuthorName(map);
  }

  @Override
  public void onDPClickComment(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    eventHelper.receiveEvent(viewTagId, "onDPClickComment", params);
    super.onDPClickComment(map);
  }

  @Override
  public void onDPClickLike(boolean b, Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("categoryName", String.valueOf(map.get("category_name")));
    eventHelper.receiveEvent(viewTagId, "onDPClickLike", params);
    super.onDPClickLike(b, map);
  }

  @Override
  public void onDPClickShare(Map<String, Object> map) {
    WritableMap params = Arguments.createMap();
    params.putDouble("groupId", Long.parseLong(String.valueOf(map.get("group_id"))));
    params.putString("title", String.valueOf(map.get("title")));
    params.putString("authorName", String.valueOf(map.get("author_name")));
    params.putDouble("publishTime", Long.parseLong(String.valueOf(map.get("publish_time"))));
    // cover_list让穿山甲加密了，此处无法处理
    eventHelper.receiveEvent(viewTagId, "onDPClickShare", params);
    super.onDPClickShare(map);
  }

  @Override
  public void onDPPageStateChanged(DPPageState dpPageState) {
    WritableMap params = Arguments.createMap();
    params.putInt("dpPageState", dpPageState.ordinal());
    eventHelper.receiveEvent(viewTagId, "onDPPageStateChanged", params);
    super.onDPPageStateChanged(dpPageState);
  }

  @Override
  public View onCreateQuizView(ViewGroup viewGroup) {
    return super.onCreateQuizView(viewGroup);
  }

  @Override
  public void onQuizBindData(View view, List<String> list, int answer, int lastAnswer, IDPQuizHandler idpQuizHandler, Map<String, Object> map) {
    eventHelper.receiveEvent(viewTagId, "onQuizBindData", null);
    super.onQuizBindData(view, list, answer, lastAnswer, idpQuizHandler, map);
  }

  @Override
  public void onChannelTabChange(int channel) {
    WritableMap params = Arguments.createMap();
    params.putInt("channel", channel);
    eventHelper.receiveEvent(viewTagId, "onChannelTabChange", params);
    super.onChannelTabChange(channel);
  }

  @Override
  public void onDurationChange(long current) {
    WritableMap params = Arguments.createMap();
    params.putDouble("current", current);
    eventHelper.receiveEvent(viewTagId, "onDurationChange", params);
    super.onDurationChange(current);
  }
}
