package com.yijian.staff.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/30 15:17:19
 */
public class CourseStudentBean implements Serializable{
    private Long time;
    private String name;
    private String headImg;
    private String courseName;
    private String courseTime;
    private int sex;
    /**
     * day : string
     * dayAlias : string
     * localDate : string
     * privateCoachCurriculumArrangementPlanVOS : [{"coachId":"string","colour":"string","dataType":0,"duration":0,"eTime":"string","id":"string","privateCoachCourseVO":{"consumingMinute":0,"memberCourseId":"string","memberCourseName":"string"},"privateCourseMemberVO":{"headPath":"string","memberId":"string","memberName":"string"},"sTime":"string","week":0}]
     * weekCode : 0
     * weekName : string
     */

    private String day;
    private String dayAlias;
    private String localDate;
    private int weekCode;
    private String weekName;
    private List<PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOS;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayAlias() {
        return dayAlias;
    }

    public void setDayAlias(String dayAlias) {
        this.dayAlias = dayAlias;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public int getWeekCode() {
        return weekCode;
    }

    public void setWeekCode(int weekCode) {
        this.weekCode = weekCode;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public List<PrivateCoachCurriculumArrangementPlanVOSBean> getPrivateCoachCurriculumArrangementPlanVOS() {
        return privateCoachCurriculumArrangementPlanVOS;
    }

    public void setPrivateCoachCurriculumArrangementPlanVOS(List<PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOS) {
        this.privateCoachCurriculumArrangementPlanVOS = privateCoachCurriculumArrangementPlanVOS;
    }

    public static class PrivateCoachCurriculumArrangementPlanVOSBean {
        /**
         * coachId : string
         * colour : string
         * dataType : 0
         * duration : 0
         * eTime : string
         * id : string
         * privateCoachCourseVO : {"consumingMinute":0,"memberCourseId":"string","memberCourseName":"string"}
         * privateCourseMemberVO : {"headPath":"string","memberId":"string","memberName":"string"}
         * sTime : string
         * week : 0
         */

        private String coachId;
        private String colour;
        private int dataType;
        private int duration;
        private String eTime;
        private String id;
        private PrivateCoachCourseVOBean privateCoachCourseVO;
        private PrivateCourseMemberVOBean privateCourseMemberVO;
        private String sTime;
        private int week;

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

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getETime() {
            return eTime;
        }

        public void setETime(String eTime) {
            this.eTime = eTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public PrivateCoachCourseVOBean getPrivateCoachCourseVO() {
            return privateCoachCourseVO;
        }

        public void setPrivateCoachCourseVO(PrivateCoachCourseVOBean privateCoachCourseVO) {
            this.privateCoachCourseVO = privateCoachCourseVO;
        }

        public PrivateCourseMemberVOBean getPrivateCourseMemberVO() {
            return privateCourseMemberVO;
        }

        public void setPrivateCourseMemberVO(PrivateCourseMemberVOBean privateCourseMemberVO) {
            this.privateCourseMemberVO = privateCourseMemberVO;
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

        public static class PrivateCoachCourseVOBean {
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

        public static class PrivateCourseMemberVOBean {
            /**
             * headPath : string
             * memberId : string
             * memberName : string
             */

            private String headPath;
            private String memberId;
            private String memberName;

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
        }
    }
}
