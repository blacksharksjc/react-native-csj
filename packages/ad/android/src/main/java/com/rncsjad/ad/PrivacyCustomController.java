package com.rncsjad.ad;

import androidx.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTLocation;

public class PrivacyCustomController {
  private boolean isCanUseLocation = true;
  private boolean isCanUsePhoneState = true;
  private boolean isCanUseWifiState = true;
  private boolean isCanUseWriteExternal = true;
  private boolean alist = true;
  private boolean isCanUseAndroidId = true;
  private boolean isCanUsePermissionRecordAudio = true;

  public void setCanUseLocation(boolean canUseLocation) {
    isCanUseLocation = canUseLocation;
  }

  public void setCanUsePhoneState(boolean canUsePhoneState) {
    isCanUsePhoneState = canUsePhoneState;
  }

  public void setCanUseWifiState(boolean canUseWifiState) {
    isCanUseWifiState = canUseWifiState;
  }

  public void setCanUseWriteExternal(boolean canUseWriteExternal) {
    isCanUseWriteExternal = canUseWriteExternal;
  }

  public void setAlist(boolean alist) {
    this.alist = alist;
  }

  public void setCanUseAndroidId(boolean canUseAndroidId) {
    isCanUseAndroidId = canUseAndroidId;
  }

  public void setCanUsePermissionRecordAudio(boolean canUsePermissionRecordAudio) {
    isCanUsePermissionRecordAudio = canUsePermissionRecordAudio;
  }

  public boolean isCanUseLocation() {
    return isCanUseLocation;
  }

  public boolean isCanUsePhoneState() {
    return isCanUsePhoneState;
  }

  public boolean isCanUseWifiState() {
    return isCanUseWifiState;
  }

  public boolean isCanUseWriteExternal() {
    return isCanUseWriteExternal;
  }

  public boolean isAlist() {
    return alist;
  }

  public boolean isCanUseAndroidId() {
    return isCanUseAndroidId;
  }

  public boolean isCanUsePermissionRecordAudio() {
    return isCanUsePermissionRecordAudio;
  }

  @NonNull
  @Override
  public String toString() {
    return "PrivacyCustomController{" +
      "isCanUseLocation=" + isCanUseLocation +
      ", isCanUsePhoneState=" + isCanUsePhoneState +
      ", isCanUseWifiState=" + isCanUseWifiState +
      ", isCanUseWriteExternal=" + isCanUseWriteExternal +
      ", alist=" + alist +
      ", isCanUseAndroidId=" + isCanUseAndroidId +
      ", isCanUsePermissionRecordAudio=" + isCanUsePermissionRecordAudio +
      '}';
  }
}
