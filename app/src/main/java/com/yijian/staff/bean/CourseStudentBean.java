package com.yijian.staff.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/30 15:17:19
 */
public class CourseStudentBean implements Serializable{


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

    @Override
    public String toString() {
        return "CourseStudentBean{" +
                "day='" + day + '\'' +
                ", dayAlias='" + dayAlias + '\'' +
                ", localDate='" + localDate + '\'' +
                ", weekCode=" + weekCode +
                ", weekName='" + weekName + '\'' +
                ", privateCoachCurriculumArrangementPlanVOS=" + privateCoachCurriculumArrangementPlanVOS +
                '}';
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

    public static class PrivateCoachCurriculumArrangementPlanVOSBean implements Serializable{
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
        private String id;
        private PrivateCoachCourseVOBean privateCoachCourseVO;
        private PrivateCourseMemberVOBean privateCourseMemberVO;
        private String sTime;
        private String eTime;
        private int week;

        @Override
        public String toString() {
            return "PrivateCoachCurriculumArrangementPlanVOSBean{" +
                    "coachId='" + coachId + '\'' +
                    ", colour='" + colour + '\'' +
                    ", dataType=" + dataType +
                    ", duration=" + duration +
                    ", id='" + id + '\'' +
                    ", privateCoachCourseVO=" + privateCoachCourseVO +
                    ", privateCourseMemberVO=" + privateCourseMemberVO +
                    ", sTime='" + sTime + '\'' +
                    ", eTime='" + eTime + '\'' +
                    ", week=" + week +
                    '}';
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

        public static class PrivateCoachCourseVOBean implements Serializable{
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

            @Override
            public String toString() {
                return "PrivateCoachCourseVOBean{" +
                        "consumingMinute=" + consumingMinute +
                        ", memberCourseId='" + memberCourseId + '\'' +
                        ", memberCourseName='" + memberCourseName + '\'' +
                        '}';
            }
        }

        public static class PrivateCourseMemberVOBean implements Serializable{
            /**
             * headPath : string
             * memberId : string
             * memberName : string
             */

            private String headPath;
            private String memberId;
            private String memberName;
            private int memberSex;

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

            @Override
            public String toString() {
                return "PrivateCourseMemberVOBean{" +
                        "headPath='" + headPath + '\'' +
                        ", memberId='" + memberId + '\'' +
                        ", memberName='" + memberName + '\'' +
                        ", memberSex=" + memberSex +
                        '}';
            }
        }
    }
}
