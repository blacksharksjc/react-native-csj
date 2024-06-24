package com.rncsjad.constant;

public enum ErrorCode {
  AD_REWARD_VIDEO_FAIL(20002, "激励视频广告视频播放失败"),
  ;

  private final int code;
  private final String message;

  private ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getStringCode() {
    return String.valueOf(code);
  }

  public String getMessage() {
    return message;
  }
}
