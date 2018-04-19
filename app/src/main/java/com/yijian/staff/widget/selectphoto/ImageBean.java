package com.yijian.staff.widget.selectphoto;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/18 11:13:06
 */
public class ImageBean {
    private String url;
    private int type; //0 未上传 1 已上传

    public ImageBean(String url, int type) {
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public int getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(int type) {
        this.type = type;
    }
}
