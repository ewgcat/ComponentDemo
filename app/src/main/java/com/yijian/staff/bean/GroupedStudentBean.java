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
     * memberId : 784acf55dbfa43f392f27f5294fe5703
     * memberName : 舒会员
     * memberSex : 1
     * headPath :
     * privateCoachCourseVOS : [{"memberCourseId":"sko20180809200840ccd9fe","memberCourseName":"私课yb0530","consumingMinute":40},{"memberCourseId":"sko20180814162120a3a534","memberCourseName":"私课yb2","consumingMinute":60}]
     */

    private String memberId;
    private String memberName;
    private int memberSex;
    private String headPath;
    private List<PrivateCoachCourseVOSBean> privateCoachCourseVOS;

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

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public List<PrivateCoachCourseVOSBean> getPrivateCoachCourseVOS() {
        return privateCoachCourseVOS;
    }

    public void setPrivateCoachCourseVOS(List<PrivateCoachCourseVOSBean> privateCoachCourseVOS) {
        this.privateCoachCourseVOS = privateCoachCourseVOS;
    }

    public static class PrivateCoachCourseVOSBean implements Serializable {
        /**
         * memberCourseId : sko20180809200840ccd9fe
         * memberCourseName : 私课yb0530
         * consumingMinute : 40
         */

        private String memberCourseId;
        private String memberCourseName;
        private int consumingMinute;

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

        public int getConsumingMinute() {
            return consumingMinute;
        }

        public void setConsumingMinute(int consumingMinute) {
            this.consumingMinute = consumingMinute;
        }
    }
}
