package com.yijian.staff.net.requestbody.course;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/17 10:42:46
 */
public class SaveCourseRequestBody {


    private List<PrivateCoachCAPDTOsBean> privateCoachCAPDTOs;

    public List<PrivateCoachCAPDTOsBean> getPrivateCoachCAPDTOs() {
        return privateCoachCAPDTOs;
    }

    public void setPrivateCoachCAPDTOs(List<PrivateCoachCAPDTOsBean> privateCoachCAPDTOs) {
        this.privateCoachCAPDTOs = privateCoachCAPDTOs;
    }

    public static class PrivateCoachCAPDTOsBean {
        /**
         * capId : string
         * coachId : string
         * colour : string
         * dataType : 0
         * eTime : string
         * memberCourseId : string
         * memberId : string
         * sTime : string
         * week : 0
         */

        private String capId;//私教排课计划id
        private String coachId;//教练id
        private String colour;//颜色,标记颜色（当dataType==1时，需要传）


        private int dataType;// 数据类型（0锁时间，1排课程） ,

        private String memberCourseId;//会员课程id（当dataType==1时，需要传）

        private String memberId;// 会员id（当dataType==1时，需要传）
        private int week;//星期（0周日-6周六）
        private String sTime;//开始时间（格式HH:mm） ,
        private String eTime;//结束时间（格式HH:mm） ,

        public String getCapId() {
            return capId;
        }

        public void setCapId(String capId) {
            this.capId = capId;
        }

        public String getCoachId() {
            return coachId;
        }

        public void setCoachId(String coachId) {
            this.coachId = coachId;
        }

        public String getColour() {
            return colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
        }

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }

        public String getETime() {
            return eTime;
        }

        public void setETime(String eTime) {
            this.eTime = eTime;
        }

        public String getMemberCourseId() {
            return memberCourseId;
        }

        public void setMemberCourseId(String memberCourseId) {
            this.memberCourseId = memberCourseId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getSTime() {
            return sTime;
        }

        public void setSTime(String sTime) {
            this.sTime = sTime;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }
    }
}
