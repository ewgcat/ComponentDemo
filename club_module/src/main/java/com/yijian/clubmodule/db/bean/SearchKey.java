package com.yijian.clubmodule.db.bean;

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
    private Long id;
    private String key;
    private String userId;
    @Generated(hash = 1890037094)
    public SearchKey(Long id, String key, String userId) {
        this.id = id;
        this.key = key;
        this.userId = userId;
    }
    @Generated(hash = 11165861)
    public SearchKey() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
