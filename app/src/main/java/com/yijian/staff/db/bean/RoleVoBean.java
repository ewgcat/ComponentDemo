package com.yijian.staff.db.bean;

import com.yijian.staff.util.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/6/27 11:39:25
 */

@Entity
public  class RoleVoBean {
    /**
     * classification (integer, optional): 岗位分类：【0 无分类 1：教练 2：会籍(目前新角色暂时分教练和会籍)】 ,
     roleCode (string, optional): 角色编码 ,
     roleId (string, optional): 角色id ,
     roleName (string, optional): 角色名称
     */

    private int classification;
    private String roleCode;
    private String roleId;
    private String roleName;


    public RoleVoBean(JSONObject jsonObject) {
        this.classification = JsonUtil.getInt(jsonObject, "classification");
        this.roleCode = JsonUtil.getString(jsonObject, "roleCode");
        this.roleId = JsonUtil.getString(jsonObject, "roleId");
        this.roleName = JsonUtil.getString(jsonObject, "roleName");
    }


    @Generated(hash = 1607978983)
    public RoleVoBean(int classification, String roleCode, String roleId,
            String roleName) {
        this.classification = classification;
        this.roleCode = roleCode;
        this.roleId = roleId;
        this.roleName = roleName;
    }


    @Generated(hash = 1692024802)
    public RoleVoBean() {
    }


    public int getClassification() {
        return this.classification;
    }


    public void setClassification(int classification) {
        this.classification = classification;
    }


    public String getRoleCode() {
        return this.roleCode;
    }


    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }


    public String getRoleId() {
        return this.roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getRoleName() {
        return this.roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
   
}
