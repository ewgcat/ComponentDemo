package com.yijian.staff.mvp.reception.step3.coach.bean;

import com.yijian.staff.mvp.reception.step3.bean.SelectedBean;

/**
 * Created by The_P on 2018/4/19.
 */

public class LeaderBean extends SelectedBean {
    /**
     * id : null
     * post : 3
     * postName : 会籍总监
     */

    private String id;
    private Integer post;
    private String postName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


}
