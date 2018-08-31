package com.yijian.staff.bean;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/15 15:50:59
 */
public class CourseInfoBean {


    /**
     * startDate : 2018-08-31
     * startDatetime : 10:33:52
     * endDatetime : 18:20:42
     * punchStatus : 2
     * privateCourseCoachSummaryDTO : {"privateApplyId":"9f4fe28a489d49d3913aa25f47fd912e","actionComplete":0.43,"actionEvaluate":0.5,"adaptStrength":1}
     */

    private String startDate;
    private String startDatetime;
    private String endDatetime;
    private int punchStatus;
    private PrivateCourseCoachSummaryDTOBean privateCourseCoachSummaryDTO;

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

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public int getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(int punchStatus) {
        this.punchStatus = punchStatus;
    }

    public PrivateCourseCoachSummaryDTOBean getPrivateCourseCoachSummaryDTO() {
        return privateCourseCoachSummaryDTO;
    }

    public void setPrivateCourseCoachSummaryDTO(PrivateCourseCoachSummaryDTOBean privateCourseCoachSummaryDTO) {
        this.privateCourseCoachSummaryDTO = privateCourseCoachSummaryDTO;
    }

    public static class PrivateCourseCoachSummaryDTOBean {
        /**
         * privateApplyId : 9f4fe28a489d49d3913aa25f47fd912e
         * actionComplete : 0.43
         * actionEvaluate : 0.5
         * adaptStrength : 1.0
         */

        private String privateApplyId;
        private float actionComplete;
        private float actionEvaluate;
        private float adaptStrength;

        public String getPrivateApplyId() {
            return privateApplyId;
        }

        public void setPrivateApplyId(String privateApplyId) {
            this.privateApplyId = privateApplyId;
        }

        public float getActionComplete() {
            return actionComplete;
        }

        public void setActionComplete(float actionComplete) {
            this.actionComplete = actionComplete;
        }

        public float getActionEvaluate() {
            return actionEvaluate;
        }

        public void setActionEvaluate(float actionEvaluate) {
            this.actionEvaluate = actionEvaluate;
        }

        public float getAdaptStrength() {
            return adaptStrength;
        }

        public void setAdaptStrength(float adaptStrength) {
            this.adaptStrength = adaptStrength;
        }
    }
}
