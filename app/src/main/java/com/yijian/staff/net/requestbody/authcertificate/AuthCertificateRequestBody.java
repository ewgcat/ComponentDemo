package com.yijian.staff.net.requestbody.authcertificate;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/17 14:16:34
 */
public class AuthCertificateRequestBody {
    private List<AuthBean> authList;
    private List<CertBean> certList;

    public AuthCertificateRequestBody(List<AuthBean> authList, List<CertBean> certList) {
        this.authList = authList;
        this.certList = certList;
    }






}
