package com.yijian.staff.bean;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/23 14:23:22
 */
public class AppointCourseBean {


    /**
     * p2mParentId : f9c31fecf5ee4be28902fa27d3d00adf
     * sysTime : 1535681624240
     * p2mToBCappVOS : [{"privateapply2memberId":"9f4fe28a489d49d3913aa25f47fd912e","status":1,"startDate":"2018-08-31","startTime":"10:00","endTime":"10:40","memberCourseId":"sko20180810162323de8375","memberCourseName":"私课yb0530","memberId":"2138200818dd4ed493b3c48a667b3f1e","memberName":"测试会员8","headPath":"","sign":0}]
     */

    private String p2mParentId;
    private long sysTime;
    private List<P2mToBCappVOSBean> p2mToBCappVOS;

    public String getP2mParentId() {
        return p2mParentId;
    }

    public void setP2mParentId(String p2mParentId) {
        this.p2mParentId = p2mParentId;
    }

    public long getSysTime() {
        return sysTime;
    }

    public void setSysTime(long sysTime) {
        this.sysTime = sysTime;
    }

    public List<P2mToBCappVOSBean> getP2mToBCappVOS() {
        return p2mToBCappVOS;
    }

    public void setP2mToBCappVOS(List<P2mToBCappVOSBean> p2mToBCappVOS) {
        this.p2mToBCappVOS = p2mToBCappVOS;
    }

    public static class P2mToBCappVOSBean {
        /**
         * privateapply2memberId : 9f4fe28a489d49d3913aa25f47fd912e
         * status : 1
         * startDate : 2018-08-31
         * startTime : 10:00
         * endTime : 10:40
         * memberCourseId : sko20180810162323de8375
         * memberCourseName : 私课yb0530
         * memberId : 2138200818dd4ed493b3c48a667b3f1e
         * memberName : 测试会员8
         * headPath :
         * sign : 0
         */

        private String privateapply2memberId;
        private int status;
        private String startDate;
        private String startTime;
        private String endTime;
        private String memberCourseId;
        private String memberCourseName;
        private String memberId;
        private String memberName;
        private String headPath;
        private int sign;

        public String getPrivateapply2memberId() {
            return privateapply2memberId;
        }

        public void setPrivateapply2memberId(String privateapply2memberId) {
            this.privateapply2memberId = privateapply2memberId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
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

        public String getHeadPath() {
            return headPath;
        }

        public void setHeadPath(String headPath) {
            this.headPath = headPath;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }
    }
}
