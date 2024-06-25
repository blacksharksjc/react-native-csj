package com.rncsjdjx.constant;

public enum ErrorCode {
  DJX_SDK_INIT_FAIL(99998, "短剧SDK初始化失败"),
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
