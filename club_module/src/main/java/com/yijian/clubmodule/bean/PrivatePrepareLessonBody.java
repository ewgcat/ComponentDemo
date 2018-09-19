package com.yijian.clubmodule.bean;

import java.util.List;

public class PrivatePrepareLessonBody {


    /**
     * contentList : [{"buildDesc":"string","moApplianceName":"string","moDifficulty":"string","moName":"string","moParts":"string"}]
     * privateApplyId : string
     */

    private String privateApplyId;
    private List<ContentListBean> contentList;

    public String getPrivateApplyId() {
        return privateApplyId;
    }

    public void setPrivateApplyId(String privateApplyId) {
        this.privateApplyId = privateApplyId;
    }

    public List<ContentListBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentListBean> contentList) {
        this.contentList = contentList;
    }

    public static class ContentListBean {
        /**
         * buildDesc : string
         * moApplianceName : string
         * moDifficulty : string
         * moName : string
         * moParts : string
         */

        private String buildDesc;
        private String moApplianceName;
        private String moDifficulty;
        private String moName;
        private String moParts;

        public String getBuildDesc() {
            return buildDesc;
        }

        public void setBuildDesc(String buildDesc) {
            this.buildDesc = buildDesc;
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
    }
}
