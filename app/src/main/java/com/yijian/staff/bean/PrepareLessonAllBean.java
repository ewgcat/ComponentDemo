package com.yijian.staff.bean;

import java.util.List;

public class PrepareLessonAllBean {


    /**
     * courseName : string
     * courseNum : string
     * prepareList : [{"buildDesc":"string","createTime":"2018-04-24T06:28:34.184Z","isDelete":0,"memberId":"string","moApplianceName":"string","moDifficulty":"string","moDifficultyDesc":"string","moName":"string","moParts":"string","moPartsDesc":"string","prepareId":"string","privateApplyId":"string","sort":0}]
     */

    private String courseName;
    private String courseNum;
    private String startDate;
    private String startTime;
    private String endTime;

    private List<PrepareListBean> prepareList;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
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

    public List<PrepareListBean> getPrepareList() {
        return prepareList;
    }

    public void setPrepareList(List<PrepareListBean> prepareList) {
        this.prepareList = prepareList;
    }

    public static class PrepareListBean {
        /**
         * buildDesc : string
         * createTime : 2018-04-24T06:28:34.184Z
         * isDelete : 0
         * memberId : string
         * moApplianceName : string
         * moDifficulty : string
         * moDifficultyDesc : string
         * moName : string
         * moParts : string
         * moPartsDesc : string
         * prepareId : string
         * privateApplyId : string
         * sort : 0
         */

        private boolean isShowHeader = false;
        private String buildDesc;
        private String createTime;
        private int isDelete;
        private String memberId;
        private String moApplianceName;
        private String moDifficulty;
        private String moDifficultyDesc;
        private String moName;
        private String moParts;
        private String moPartsDesc;
        private String prepareId;
        private String privateApplyId;
        private int sort;

        public String getBuildDesc() {
            return buildDesc;
        }

        public void setBuildDesc(String buildDesc) {
            this.buildDesc = buildDesc;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getMoApplianceName() {
            return moApplianceName;
        }

        public void setMoApplianceName(String moApplianceName) {
            this.moApplianceName = moApplianceName;
        }

        public String getMoDifficulty() {
            return moDifficulty;
        }

        public void setMoDifficulty(String moDifficulty) {
            this.moDifficulty = moDifficulty;
        }

        public String getMoDifficultyDesc() {
            return moDifficultyDesc;
        }

        public void setMoDifficultyDesc(String moDifficultyDesc) {
            this.moDifficultyDesc = moDifficultyDesc;
        }

        public String getMoName() {
            return moName;
        }

        public void setMoName(String moName) {
            this.moName = moName;
        }

        public String getMoParts() {
            return moParts;
        }

        public void setMoParts(String moParts) {
            this.moParts = moParts;
        }

        public String getMoPartsDesc() {
            return moPartsDesc;
        }

        public void setMoPartsDesc(String moPartsDesc) {
            this.moPartsDesc = moPartsDesc;
        }

        public String getPrepareId() {
            return prepareId;
        }

        public void setPrepareId(String prepareId) {
            this.prepareId = prepareId;
        }

        public String getPrivateApplyId() {
            return privateApplyId;
        }

        public void setPrivateApplyId(String privateApplyId) {
            this.privateApplyId = privateApplyId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public boolean isShowHeader() {
            return isShowHeader;
        }

        public void setShowHeader(boolean showHeader) {
            isShowHeader = showHeader;
        }
    }
}
