package com.yijian.clubmodule.net.requestbody.savemenu;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/28 19:19:18
 */
public class MenuRequestBody {
    private List<MenuBean> dtos;

    public MenuRequestBody(List<MenuBean> dtos) {
        this.dtos = dtos;
    }
}
