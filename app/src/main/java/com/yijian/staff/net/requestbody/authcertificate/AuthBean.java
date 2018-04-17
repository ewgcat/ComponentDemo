package com.yijian.staff.net.requestbody.authcertificate;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/17 16:37:24
 */
public class AuthBean {
    public AuthBean(String authInfo) {
        this.authInfo = authInfo;
    }

    /**
     * id : 6184e94aab6843b38b13a7cb0e8b9c44
     * coachId : string
     * authInfo : 1
     * remark : null
     * createTime : 1523934980000
     */

    private String authInfo;

    @Override
    public String toString() {
        return "AuthListBean{" +
                "authInfo='" + authInfo + '\'' +
                '}';
    }

    public String getAuthInfo() {
        return authInfo;
    }
}
