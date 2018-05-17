package com.yijian.staff.mvp.main.work.face;

public class FaceBean {

    /**
     * face_set_id : 10
     * people_id : f99df79b307e42c982b921f4c1077704
     * face_id : b49efc2df3a94e3284317d1cb87af09d
     * score : 0.6581010818481445
     */

    private String face_set_id;
    private String people_id;
    private String face_id;
    private double score;

    public String getFace_set_id() {
        return face_set_id;
    }

    public void setFace_set_id(String face_set_id) {
        this.face_set_id = face_set_id;
    }

    public String getPeople_id() {
        return people_id;
    }

    public void setPeople_id(String people_id) {
        this.people_id = people_id;
    }

    public String getFace_id() {
        return face_id;
    }

    public void setFace_id(String face_id) {
        this.face_id = face_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
