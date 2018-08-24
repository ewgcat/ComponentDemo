package com.yijian.staff.mvp.course.appointcourse;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/23 14:23:22
 */
public class AppointCourseBean {


    /**
     * endTime : {"hour":0,"minute":0,"nano":0,"second":0}
     * headPath : string
     * memberCourseId : string
     * memberCourseName : string
     * memberId : string
     * memberName : string
     * privateapply2memberId : string
     * startDate : 2018-08-23
     * startTime : {"hour":0,"minute":0,"nano":0,"second":0}
     * status : 0
     */

    private String endTime;
    private String headPath;
    private String memberCourseId;
    private String memberCourseName;
    private String memberId;
    private String memberName;
    private String privateapply2memberId;
    private String startDate;
    private String startTime;
    private int status;//约课状态（1已约课，2取消约课，3：会员已上课，4：会员爽约（此状态为系统定时服务更新用），5：已评价，6：不可约）

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
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

    public String getPrivateapply2memberId() {
        return privateapply2memberId;
    }

    public void setPrivateapply2memberId(String privateapply2memberId) {
        this.privateapply2memberId = privateapply2memberId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
