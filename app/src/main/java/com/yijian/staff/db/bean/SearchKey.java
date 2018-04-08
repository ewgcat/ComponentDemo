package com.yijian.staff.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/4 16:51:14
 */
@Entity
public class SearchKey {

    @Id(autoincrement = true)
    private Long searchId;

    private String key;
    private String roleId;
    @Generated(hash = 819399303)
    public SearchKey(Long searchId, String key, String roleId) {
        this.searchId = searchId;
        this.key = key;
        this.roleId = roleId;
    }
    @Generated(hash = 11165861)
    public SearchKey() {
    }
    public Long getSearchId() {
        return this.searchId;
    }
    public void setSearchId(Long searchId) {
        this.searchId = searchId;
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
