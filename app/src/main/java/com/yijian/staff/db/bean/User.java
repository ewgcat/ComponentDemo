package com.yijian.staff.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 14:31:37
 */
@Entity
public class User {
    public int age;
    public String name;
    @Generated(hash = 1080248539)
    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
