package com.yijian.workspace.bean;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/21 16:58:04
 */
public class LoginRequestBody {
    private String username;
    private String password;

    public LoginRequestBody(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
