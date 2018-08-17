package com.yijian.staff.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/31 11:40:45
 */
public class GroupedStudentBean implements Serializable{

    /**
     * headPath : string
     * memberId : string
     * memberName : string
     * memberSex : 0
     * privateCoachCourseVOS : [{"consumingMinute":0,"memberCourseId":"string","memberCourseName":"string"}]
     */

    private String headPath;
    private String memberId;
    private String memberName;
    private int memberSex;
    private List<PrivateCoachCourseVOSBean> privateCoachCourseVOS;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(int memberSex) {
        this.memberSex = memberSex;
    }

    public List<PrivateCoachCourseVOSBean> getPrivateCoachCourseVOS() {
        return privateCoachCourseVOS;
    }

    public void setPrivateCoachCourseVOS(List<PrivateCoachCourseVOSBean> privateCoachCourseVOS) {
        this.privateCoachCourseVOS = privateCoachCourseVOS;
    }

    public static class PrivateCoachCourseVOSBean implements Serializable{
        /**
         * consumingMinute : 0
         * memberCourseId : string
         * memberCourseName : string
         */

        private int consumingMinute;
        private String memberCourseId;
        private String memberCourseName;

        public int getConsumingMinute() {
            return consumingMinute;
        }

        public void setConsumingMinute(int consumingMinute) {
            this.consumingMinute = consumingMinute;
        }

        public String getMemberCourseId() {
            return memberCourseId;
        }

        public void setMemberCourseId(String memberCourseId) {
            this.memberCourseId = memberCourseId;
        }

        public String getMemberCourseName() {
            return memberCourseName;
        }

        public void setMemberCourseName(String memberCourseName) {
            this.memberCourseName = memberCourseName;
        }
    }
}
