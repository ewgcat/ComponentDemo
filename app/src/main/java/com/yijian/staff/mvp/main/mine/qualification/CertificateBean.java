package com.yijian.staff.mvp.main.mine.qualification;

import com.yijian.staff.net.requestbody.authcertificate.AuthBean;
import com.yijian.staff.net.requestbody.authcertificate.CertBean;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/17 15:33:51
 */
public class CertificateBean {

    /**
     * authList : [{"authInfo":"1","remark":null,"createTime":1523934980000}],
     * certList : [{"certificate":"2"}]
     */


    private List<AuthBean> authList=new ArrayList<>();
    private List<CertBean> certList=new ArrayList<>();



    public CertificateBean(JSONObject jsonObject){

        try {
            if (jsonObject.has("authList")){
                JSONArray array1 = JsonUtil.getJsonArray(jsonObject, "authList");
                for (int i = 0; i < array1.length(); i++) {
                    JSONObject jsonObject1 = array1.getJSONObject(i);
                    String authInfo = jsonObject1.getString("authInfo");
                    AuthBean authBean = new AuthBean(authInfo);
                    authList.add(authBean);
                }
            }
            if (jsonObject.has("certList")){
                JSONArray array1 = JsonUtil.getJsonArray(jsonObject, "certList");
                for (int i = 0; i < array1.length(); i++) {
                    JSONObject jsonObject1 = array1.getJSONObject(i);
                    String certificate = jsonObject1.getString("certificate");
                    CertBean certBean = new CertBean(certificate);
                    certList.add(certBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public List<AuthBean> getAuthList() {
        return authList;
    }

    public List<CertBean> getCertList() {
        return certList;
    }
}
