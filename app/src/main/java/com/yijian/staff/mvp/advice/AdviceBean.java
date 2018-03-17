package com.yijian.staff.mvp.advice;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 14:42:53
 */
public class AdviceBean {
    private String content;
    private String name;
    private String time;

    public AdviceBean(String content, String name, String time) {
        this.content = content;
        this.name = name;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
