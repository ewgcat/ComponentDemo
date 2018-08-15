package com.yijian.staff.bean;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/15 15:50:59
 */
public class CourseInfoBean {


    /**
     * endDatetime : 11:00:00
     * privateCourseCoachSummaryDTO : {"actionComplete":0,"actionEvaluate":0,"adaptStrength":0,"privateApplyId":"string"}
     * punchStatus : 0
     * startDate : 2018-01-01
     * startDatetime : 09:00:00
     */

    private String endDatetime;
    private PrivateCourseCoachSummaryDTOBean privateCourseCoachSummaryDTO;
    private int punchStatus;
    private String startDate;
    private String startDatetime;

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public PrivateCourseCoachSummaryDTOBean getPrivateCourseCoachSummaryDTO() {
        return privateCourseCoachSummaryDTO;
    }

    public void setPrivateCourseCoachSummaryDTO(PrivateCourseCoachSummaryDTOBean privateCourseCoachSummaryDTO) {
        this.privateCourseCoachSummaryDTO = privateCourseCoachSummaryDTO;
    }

    public int getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(int punchStatus) {
        this.punchStatus = punchStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public static class PrivateCourseCoachSummaryDTOBean {
        /**
         * actionComplete : 0
         * actionEvaluate : 0
         * adaptStrength : 0
         * privateApplyId : string
         */

        private int actionComplete;
        private int actionEvaluate;
        private int adaptStrength;
        private String privateApplyId;

        public int getActionComplete() {
            return actionComplete;
        }

        public void setActionComplete(int actionComplete) {
            this.actionComplete = actionComplete;
        }

        public int getActionEvaluate() {
            return actionEvaluate;
        }

        public void setActionEvaluate(int actionEvaluate) {
            this.actionEvaluate = actionEvaluate;
        }

        public int getAdaptStrength() {
            return adaptStrength;
        }

        public void setAdaptStrength(int adaptStrength) {
            this.adaptStrength = adaptStrength;
        }

        public String getPrivateApplyId() {
            return privateApplyId;
        }

        public void setPrivateApplyId(String privateApplyId) {
            this.privateApplyId = privateApplyId;
        }
    }
}
