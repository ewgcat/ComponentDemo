package com.yijian.staff.bean;

import java.io.Serializable;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 16:22:37
 */
public class HuiFangInfo implements Serializable {


    /**
     * fitnessHobby : string
     * gender : 0
     * headUrl : string
     * healthStatus : string
     * hobby : string
     * id : string
     * interviewName : string
     * invite : 0
     * inviteContent : string
     * inviteVisitTime : 2018-08-13T01:54:42.665Z
     * memberBirthdayInterview : {"birthday":"2018-08-13T01:54:42.665Z","birthdayTypeName":"string"}
     * memberId : string
     * memberPastDueInterview : {"expireDate":"2018-08-13T01:54:42.665Z"}
     * memberPotentialInterview : {}
     * memberQuietInterview : {"intervalDay":0,"lastTime":"2018-08-13T01:54:42.665Z"}
     * memberWillExpireInterview : {"amount":0,"cardType":0,"cardTypeName":"string","cardprodName":"string","endTime":"2018-08-13T01:54:42.665Z","surplusValidTime":0}
     * memberYesterdayBuyCardInterview : {"cardTypeName":"string","cardprodName":"string"}
     * memberYesterdayVisitInterview : {"yesterdayVisitTime":"2018-08-13T01:54:42.665Z"}
     * mobile : string
     * name : string
     * result : string
     * reviewReason : string
     * status : 0
     * studentBirthdayInterview : {"birthday":"2018-08-13T01:54:42.665Z","birthdayType":0,"birthdayTypeName":"string"}
     * studentPrivateCoursePastDueInterview : {"endTime":"2018-08-13T01:54:42.665Z"}
     * studentPrivateCourseWillExpireInterview : {"endTime":"2018-08-13T01:54:42.665Z"}
     * studentYesterdayBuyCourseInterview : {"buyCourseTime":"2018-08-13T01:54:42.665Z"}
     * studentYesterdayInCourseInterview : {"inviteTime":"2018-08-13T01:54:42.665Z"}
     */

    private String fitnessHobby;
    private int gender;
    private String headUrl;
    private String healthStatus;
    private String lastInterviewTime;
    private String hobby;
    private String id;
    private String interviewName;
    private int invite;
    private int memberMedalType;
    private String inviteContent;
    private String inviteVisitTime;
    private MemberBirthdayInterviewBean memberBirthdayInterview;
    private String memberId;
    private MemberPastDueInterviewBean memberPastDueInterview;
    private MemberPotentialInterviewBean memberPotentialInterview;
    private MemberQuietInterviewBean memberQuietInterview;
    private MemberWillExpireInterviewBean memberWillExpireInterview;
    private MemberYesterdayBuyCardInterviewBean memberYesterdayBuyCardInterview;
    private MemberYesterdayVisitInterviewBean memberYesterdayVisitInterview;
    private String mobile;
    private String name;
    private String result;
    private String reviewReason;
    private int status;
    private StudentBirthdayInterviewBean studentBirthdayInterview;
    private StudentPrivateCoursePastDueInterviewBean studentPrivateCoursePastDueInterview;
    private StudentPrivateCourseWillExpireInterviewBean studentPrivateCourseWillExpireInterview;
    private StudentYesterdayBuyCourseInterviewBean studentYesterdayBuyCourseInterview;
    private StudentYesterdayInCourseInterviewBean studentYesterdayInCourseInterview;

    public String getFitnessHobby() {
        return fitnessHobby;
    }

    public int getMemberMedalType() {
        return memberMedalType;
    }

    public void setFitnessHobby(String fitnessHobby) {
        this.fitnessHobby = fitnessHobby;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public int getInvite() {
        return invite;
    }

    public void setInvite(int invite) {
        this.invite = invite;
    }

    public String getInviteContent() {
        return inviteContent;
    }

    public void setInviteContent(String inviteContent) {
        this.inviteContent = inviteContent;
    }

    public String getInviteVisitTime() {
        return inviteVisitTime;
    }

    public void setInviteVisitTime(String inviteVisitTime) {
        this.inviteVisitTime = inviteVisitTime;
    }

    public MemberBirthdayInterviewBean getMemberBirthdayInterview() {
        return memberBirthdayInterview;
    }

    public void setMemberBirthdayInterview(MemberBirthdayInterviewBean memberBirthdayInterview) {
        this.memberBirthdayInterview = memberBirthdayInterview;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public MemberPastDueInterviewBean getMemberPastDueInterview() {
        return memberPastDueInterview;
    }

    public void setMemberPastDueInterview(MemberPastDueInterviewBean memberPastDueInterview) {
        this.memberPastDueInterview = memberPastDueInterview;
    }

    public MemberPotentialInterviewBean getMemberPotentialInterview() {
        return memberPotentialInterview;
    }

    public void setMemberPotentialInterview(MemberPotentialInterviewBean memberPotentialInterview) {
        this.memberPotentialInterview = memberPotentialInterview;
    }

    public MemberQuietInterviewBean getMemberQuietInterview() {
        return memberQuietInterview;
    }

    public void setMemberQuietInterview(MemberQuietInterviewBean memberQuietInterview) {
        this.memberQuietInterview = memberQuietInterview;
    }

    public MemberWillExpireInterviewBean getMemberWillExpireInterview() {
        return memberWillExpireInterview;
    }

    public String getLastInterviewTime() {
        return lastInterviewTime;
    }

    public void setLastInterviewTime(String lastInterviewTime) {
        this.lastInterviewTime = lastInterviewTime;
    }

    public void setMemberWillExpireInterview(MemberWillExpireInterviewBean memberWillExpireInterview) {
        this.memberWillExpireInterview = memberWillExpireInterview;
    }

    public MemberYesterdayBuyCardInterviewBean getMemberYesterdayBuyCardInterview() {
        return memberYesterdayBuyCardInterview;
    }

    public void setMemberYesterdayBuyCardInterview(MemberYesterdayBuyCardInterviewBean memberYesterdayBuyCardInterview) {
        this.memberYesterdayBuyCardInterview = memberYesterdayBuyCardInterview;
    }

    public MemberYesterdayVisitInterviewBean getMemberYesterdayVisitInterview() {
        return memberYesterdayVisitInterview;
    }

    public void setMemberYesterdayVisitInterview(MemberYesterdayVisitInterviewBean memberYesterdayVisitInterview) {
        this.memberYesterdayVisitInterview = memberYesterdayVisitInterview;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public StudentBirthdayInterviewBean getStudentBirthdayInterview() {
        return studentBirthdayInterview;
    }

    public void setStudentBirthdayInterview(StudentBirthdayInterviewBean studentBirthdayInterview) {
        this.studentBirthdayInterview = studentBirthdayInterview;
    }

    public StudentPrivateCoursePastDueInterviewBean getStudentPrivateCoursePastDueInterview() {
        return studentPrivateCoursePastDueInterview;
    }

    public void setStudentPrivateCoursePastDueInterview(StudentPrivateCoursePastDueInterviewBean studentPrivateCoursePastDueInterview) {
        this.studentPrivateCoursePastDueInterview = studentPrivateCoursePastDueInterview;
    }

    public StudentPrivateCourseWillExpireInterviewBean getStudentPrivateCourseWillExpireInterview() {
        return studentPrivateCourseWillExpireInterview;
    }

    public void setStudentPrivateCourseWillExpireInterview(StudentPrivateCourseWillExpireInterviewBean studentPrivateCourseWillExpireInterview) {
        this.studentPrivateCourseWillExpireInterview = studentPrivateCourseWillExpireInterview;
    }

    public StudentYesterdayBuyCourseInterviewBean getStudentYesterdayBuyCourseInterview() {
        return studentYesterdayBuyCourseInterview;
    }

    public void setStudentYesterdayBuyCourseInterview(StudentYesterdayBuyCourseInterviewBean studentYesterdayBuyCourseInterview) {
        this.studentYesterdayBuyCourseInterview = studentYesterdayBuyCourseInterview;
    }

    public StudentYesterdayInCourseInterviewBean getStudentYesterdayInCourseInterview() {
        return studentYesterdayInCourseInterview;
    }

    public void setStudentYesterdayInCourseInterview(StudentYesterdayInCourseInterviewBean studentYesterdayInCourseInterview) {
        this.studentYesterdayInCourseInterview = studentYesterdayInCourseInterview;
    }

    public static class MemberBirthdayInterviewBean implements Serializable {
        /**
         * birthday : 2018-08-13T01:54:42.665Z
         * birthdayTypeName : string
         */

        private String birthday;
        private String birthdayTypeName;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBirthdayTypeName() {
            return birthdayTypeName;
        }

        public void setBirthdayTypeName(String birthdayTypeName) {
            this.birthdayTypeName = birthdayTypeName;
        }
    }

    public static class MemberPastDueInterviewBean implements Serializable {
        private String expireDate;
        private String cardprodName;
        private String cardType;
        private String cardTypeName;

        public String getCardprodName() {
            return cardprodName;
        }

        public void setCardprodName(String cardprodName) {
            this.cardprodName = cardprodName;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardTypeName() {
            return cardTypeName;
        }

        public void setCardTypeName(String cardTypeName) {
            this.cardTypeName = cardTypeName;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }
    }

    public static class MemberPotentialInterviewBean implements Serializable {
    }

    public static class MemberQuietInterviewBean implements Serializable {
        /**
         * intervalDay : 0
         * lastTime : 2018-08-13T01:54:42.665Z
         */

        private int intervalDay;
        private String lastTime;

        public int getIntervalDay() {
            return intervalDay;
        }

        public void setIntervalDay(int intervalDay) {
            this.intervalDay = intervalDay;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }
    }

    public static class MemberWillExpireInterviewBean implements Serializable {
        /**
         * amount : 0
         * cardType : 0
         * cardTypeName : string
         * cardprodName : string
         * endTime : 2018-08-13T01:54:42.665Z
         * surplusValidTime : 0
         */

        private int amount;
        private int cardType;
        private String cardTypeName;
        private String cardprodName;
        private String endTime;
        private int surplusValidTime;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getCardTypeName() {
            return cardTypeName;
        }

        public void setCardTypeName(String cardTypeName) {
            this.cardTypeName = cardTypeName;
        }

        public String getCardprodName() {
            return cardprodName;
        }

        public void setCardprodName(String cardprodName) {
            this.cardprodName = cardprodName;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getSurplusValidTime() {
            return surplusValidTime;
        }

        public void setSurplusValidTime(int surplusValidTime) {
            this.surplusValidTime = surplusValidTime;
        }
    }

    public static class MemberYesterdayBuyCardInterviewBean implements Serializable {
        /**
         * cardTypeName : string
         * cardprodName : string
         */

        private String cardTypeName;
        private String cardprodName;

        public String getCardTypeName() {
            return cardTypeName;
        }

        public void setCardTypeName(String cardTypeName) {
            this.cardTypeName = cardTypeName;
        }

        public String getCardprodName() {
            return cardprodName;
        }

        public void setCardprodName(String cardprodName) {
            this.cardprodName = cardprodName;
        }
    }

    public static class MemberYesterdayVisitInterviewBean implements Serializable {
        /**
         * yesterdayVisitTime : 2018-08-13T01:54:42.665Z
         */

        private String yesterdayVisitTime;

        public String getYesterdayVisitTime() {
            return yesterdayVisitTime;
        }

        public void setYesterdayVisitTime(String yesterdayVisitTime) {
            this.yesterdayVisitTime = yesterdayVisitTime;
        }
    }

    public static class StudentBirthdayInterviewBean implements Serializable {
        /**
         * birthday : 2018-08-13T01:54:42.665Z
         * birthdayType : 0
         * birthdayTypeName : string
         */

        private String birthday;
        private int birthdayType;
        private String birthdayTypeName;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getBirthdayType() {
            return birthdayType;
        }

        public void setBirthdayType(int birthdayType) {
            this.birthdayType = birthdayType;
        }

        public String getBirthdayTypeName() {
            return birthdayTypeName;
        }

        public void setBirthdayTypeName(String birthdayTypeName) {
            this.birthdayTypeName = birthdayTypeName;
        }
    }

    public static class StudentPrivateCoursePastDueInterviewBean implements Serializable {
        /**
         * endTime : 2018-08-13T01:54:42.665Z
         */

        private String endTime;
        private String courseName;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

    public static class StudentPrivateCourseWillExpireInterviewBean implements Serializable {
        /**
         * endTime : 2018-08-13T01:54:42.665Z
         */

        private String endTime;
        private String courseName;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

    public static class StudentYesterdayBuyCourseInterviewBean implements Serializable {
        /**
         * buyCourseTime : 2018-08-13T01:54:42.665Z
         */

        private String buyCourseTime;
        private String courseName;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getBuyCourseTime() {
            return buyCourseTime;
        }

        public void setBuyCourseTime(String buyCourseTime) {
            this.buyCourseTime = buyCourseTime;
        }


    }

    public static class StudentYesterdayInCourseInterviewBean implements Serializable {
        /**
         * inviteTime : 2018-08-13T01:54:42.665Z
         */

        private String inviteTime;

        public String getInviteTime() {
            return inviteTime;
        }

        public void setInviteTime(String inviteTime) {
            this.inviteTime = inviteTime;
        }
    }
}
