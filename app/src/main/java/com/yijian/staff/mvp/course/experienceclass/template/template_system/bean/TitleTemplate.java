package com.yijian.staff.mvp.course.experienceclass.template.template_system.bean;

/**
 * Created by The_P on 2018/4/13.
 */

public class TitleTemplate {
    public TitleTemplate(String title) {
        this.title = title;
    }

    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TitleTemplate{" +
                "title='" + title + '\'' +
                '}';
    }
}
