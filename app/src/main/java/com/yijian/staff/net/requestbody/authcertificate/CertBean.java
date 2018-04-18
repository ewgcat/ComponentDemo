package com.yijian.staff.net.requestbody.authcertificate;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/17 16:36:21
 */

public  class CertBean  {
    public CertBean(String certificate) {
        this.certificate = certificate;
    }

    /**
     * id : 15a8b0f2659845418dc214814affc48d
     * coachId : string
     * certificate : 2
     * remark : null
     * createTime : 1523934980000
     */


    private String certificate;

    public String getCertificate() {
        return certificate;
    }


}
