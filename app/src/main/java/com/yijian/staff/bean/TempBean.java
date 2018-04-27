package com.yijian.staff.bean;

import java.io.Serializable;
import java.util.List;

public class TempBean implements Serializable {


    /**
     * templateId : 2
     * templateName : 不存在上课记录的测试
     * templateContextList : [{"sort":1,"motionId":"1","moName":"原地高抬腿","moDifficulty":"3","moParts":"2,3,4","moApplianceName":"瑜伽垫","buildTime":"2组 × 60秒"},{"sort":2,"motionId":"6","moName":"动作7","moDifficulty":"3","moParts":"6,7","moApplianceName":"哑铃","buildTime":"1组 × 30秒"},{"sort":3,"motionId":"5","moName":"测试123","moDifficulty":"1","moParts":"1","moApplianceName":"单杠","buildTime":"23组 × 312次"},{"sort":4,"motionId":"4","moName":"动作11","moDifficulty":"3","moParts":"6,7","moApplianceName":"xxx","buildTime":"4组 × 5秒"}]
     */

    private boolean isCheck; // 自定义字段，是否选中的标志位
    private String templateId;
    private String templateName;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private List<TemplateContextListBean> templateContextList;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<TemplateContextListBean> getTemplateContextList() {
        return templateContextList;
    }

    public void setTemplateContextList(List<TemplateContextListBean> templateContextList) {
        this.templateContextList = templateContextList;
    }

    public static class TemplateContextListBean implements Serializable {
        /**
         * sort : 1
         * motionId : 1
         * moName : 原地高抬腿
         * moDifficulty : 3
         * moParts : 2,3,4
         * moApplianceName : 瑜伽垫
         * buildTime : 2组 × 60秒
         */

        private String contentId;
        private String buildDesc;
        private String groupNum;
        private String groupTime;
        private String moApplianceName;
        private String moDifficulty;
        private String moDifficultyDesc ;
        private String moPartsDesc  ;
        private String moName;
        private String moParts;
        private int sort;
        private String unit;

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(String groupNum) {
            this.groupNum = groupNum;
        }

        public String getMoName() {
            return moName;
        }

        public void setMoName(String moName) {
            this.moName = moName;
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getGroupTime() {
            return groupTime;
        }

        public void setGroupTime(String groupTime) {
            this.groupTime = groupTime;
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

        public String getMoPartsDesc() {
            return moPartsDesc;
        }

        public void setMoPartsDesc(String moPartsDesc) {
            this.moPartsDesc = moPartsDesc;
        }

        public String getMoParts() {
            return moParts;
        }

        public void setMoParts(String moParts) {
            this.moParts = moParts;
        }

        public String getMoApplianceName() {
            return moApplianceName;
        }

        public void setMoApplianceName(String moApplianceName) {
            this.moApplianceName = moApplianceName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getBuildDesc() {
            return buildDesc;
        }

        public void setBuildDesc(String buildDesc) {
            this.buildDesc = buildDesc;
        }
    }
}
