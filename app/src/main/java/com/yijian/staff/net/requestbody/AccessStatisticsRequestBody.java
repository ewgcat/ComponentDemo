package com.yijian.staff.net.requestbody;

import com.yijian.commonlib.prefs.SharePreferenceUtil;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/25 15:28:08
 */
public class AccessStatisticsRequestBody {


    /**
     * moduleCode : string
     * userId : string
     * version : string
     */

    private String moduleCode;
    private String userId;
    private String version="1.3";
    private String terminalVersion;

    public String getTerminalVersion() {
        return terminalVersion;
    }

    public void setTerminalVersion(String terminalVersion) {
        this.terminalVersion = terminalVersion;
    }

    public AccessStatisticsRequestBody(String moduleCode, String terminalVersion) {
        this.moduleCode = moduleCode;
        this.userId = SharePreferenceUtil.getUserId();
        this.terminalVersion = terminalVersion;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
