package com.yijian.staff.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/4 16:51:14
 */
@Entity
public class SearchKey {

    private String key;
    private String roleId;
    @Generated(hash = 2001363141)
    public SearchKey(String key, String roleId) {
        this.key = key;
        this.roleId = roleId;
    }
    @Generated(hash = 11165861)
    public SearchKey() {
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getRoleId() {
        return this.roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
