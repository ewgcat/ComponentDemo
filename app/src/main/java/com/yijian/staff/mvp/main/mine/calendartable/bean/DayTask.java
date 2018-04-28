package com.yijian.staff.mvp.main.mine.calendartable.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by The_P on 2018/3/22.
 */

public class DayTask {


    /**
     * startDate : 2018-05-02
     * courses : [{"id":"af95dd91-4921-11e8-9f9b-00163e04","memberId":null,"lessonName":"瑜伽课咯","isExperience":"0","isUseTemplate":null,"experienceRecordId":null,"lessonPlace":"test门店2","startDatetime":"11:00","endDatetime":"13:00","startDate":"2018-05-02","startTimeActual":null,"endTimeActual":null,"punchStatus":0,"isPrepare":1,"memberName":"Sunny","courseNum":null,"gender":null,"headPath":null,"currentNum":null,"mobile":null,"intervalTime":10}]
     */

    private String startDate;
    private List<CoursesBean> courses;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<CoursesBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesBean> courses) {
        this.courses = courses;
    }

    public static class CoursesBean {
        /**
         * id : af95dd91-4921-11e8-9f9b-00163e04
         * memberId : null
         * lessonName : 瑜伽课咯
         * isExperience : 0
         * isUseTemplate : null
         * experienceRecordId : null
         * lessonPlace : test门店2
         * startDatetime : 11:00
         * endDatetime : 13:00
         * startDate : 2018-05-02
         * startTimeActual : null
         * endTimeActual : null
         * punchStatus : 0
         * isPrepare : 1
         * memberName : Sunny
         * courseNum : null
         * gender : null
         * headPath : null
         * currentNum : null
         * mobile : null
         * intervalTime : 10
         */

        private String id;
        private Object memberId;
        private String lessonName;
        private String isExperience;
        private Object isUseTemplate;
        private Object experienceRecordId;
        private String lessonPlace;
        private String startDatetime;
        private String endDatetime;
        private String startDate;
        private Object startTimeActual;
        private Object endTimeActual;
        private int punchStatus;
        private int isPrepare;
        private String memberName;
        private Object courseNum;
        private Object gender;
        private Object headPath;
        private Object currentNum;
        private Object mobile;
        private int intervalTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getMemberId() {
            return memberId;
        }

        public void setMemberId(Object memberId) {
            this.memberId = memberId;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getIsExperience() {
            return isExperience;
        }

        public void setIsExperience(String isExperience) {
            this.isExperience = isExperience;
        }

        public Object getIsUseTemplate() {
            return isUseTemplate;
        }

        public void setIsUseTemplate(Object isUseTemplate) {
            this.isUseTemplate = isUseTemplate;
        }

        public Object getExperienceRecordId() {
            return experienceRecordId;
        }

        public void setExperienceRecordId(Object experienceRecordId) {
            this.experienceRecordId = experienceRecordId;
        }

        public String getLessonPlace() {
            return lessonPlace;
        }

        public void setLessonPlace(String lessonPlace) {
            this.lessonPlace = lessonPlace;
        }

        public String getStartDatetime() {
            return startDatetime;
        }

        public void setStartDatetime(String startDatetime) {
            this.startDatetime = startDatetime;
        }

        public String getEndDatetime() {
            return endDatetime;
        }

        public void setEndDatetime(String endDatetime) {
            this.endDatetime = endDatetime;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public Object getStartTimeActual() {
            return startTimeActual;
        }

        public void setStartTimeActual(Object startTimeActual) {
            this.startTimeActual = startTimeActual;
        }

        public Object getEndTimeActual() {
            return endTimeActual;
        }

        public void setEndTimeActual(Object endTimeActual) {
            this.endTimeActual = endTimeActual;
        }

        public int getPunchStatus() {
            return punchStatus;
        }

        public void setPunchStatus(int punchStatus) {
            this.punchStatus = punchStatus;
        }

        public int getIsPrepare() {
            return isPrepare;
        }

        public void setIsPrepare(int isPrepare) {
            this.isPrepare = isPrepare;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public Object getCourseNum() {
            return courseNum;
        }

        public void setCourseNum(Object courseNum) {
            this.courseNum = courseNum;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getHeadPath() {
            return headPath;
        }

        public void setHeadPath(Object headPath) {
            this.headPath = headPath;
        }

        public Object getCurrentNum() {
            return currentNum;
        }

        public void setCurrentNum(Object currentNum) {
            this.currentNum = currentNum;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public int getIntervalTime() {
            return intervalTime;
        }

        public void setIntervalTime(int intervalTime) {
            this.intervalTime = intervalTime;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof DayTask){
            DayTask dayTask = (DayTask) obj;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date1 = simpleDateFormat.parse(dayTask.getStartDate());
                Date date2 = simpleDateFormat.parse(startDate);
                if(date1.getTime() == date2.getTime()){
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
