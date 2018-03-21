package com.yijian.staff.db.bean;

import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 14:31:37
 */
@Entity
public class User {
    public int age;
    public int role;// 0 会籍客服 1 教练  2会籍总监 3教练总监 4操课教练 5行政  6店长
    public String name;
    @Generated(hash = 1956144629)
    public User(int age, int role, String name) {
        this.age = age;
        this.role = role;
        this.name = name;
    }
    @Generated(hash = 586692638)
    public User() {
    }

    public User(JSONObject jsonObject){
        this.age= JsonUtil.getInt(jsonObject,"age");
        this.role= JsonUtil.getInt(jsonObject,"role");
        this.name= JsonUtil.getString(jsonObject,"name");
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
    public int getRole() {
        return this.role;
    }
    public void setRole(int role) {
        this.role = role;
    }
}
