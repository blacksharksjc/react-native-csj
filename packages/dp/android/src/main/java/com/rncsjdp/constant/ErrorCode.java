package com.rncsjdp.constant;

public enum ErrorCode {
  DP_SDK_INIT_FAIL(99997, "短视频SDK初始化失败"),
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
