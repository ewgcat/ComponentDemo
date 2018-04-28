package com.yijian.staff.mvp.coach.setclass.bean;

import android.text.TextUtils;

import java.util.List;

public class PrivateShangKeBean {


    /**
     * privateApplyId : string
     * recordContextList : [{"actionForm":0,"actionStrength":0,"id":"string","interval":0,"num":0,"prepareId":"string","time":"string"}]
     * recordId : string
     */

    private String privateApplyId;
    private String recordId;
    private List<RecordContextListBean> recordContextList;

    public String getPrivateApplyId() {
        return privateApplyId;
    }

    public void setPrivateApplyId(String privateApplyId) {
        this.privateApplyId = privateApplyId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public List<RecordContextListBean> getRecordContextList() {
        return recordContextList;
    }

    public void setRecordContextList(List<RecordContextListBean> recordContextList) {
        this.recordContextList = recordContextList;
    }

    public static class RecordContextListBean {
        /**
         * actionForm : 0
         * actionStrength : 0
         * id : string
         * interval : 0
         * num : 0
         * prepareId : string
         * time : string
         */

        private Integer actionForm;
        private Integer actionStrength;
        private String id;
        private Integer interval;
        private int num;
        private String prepareId;
        private String time;

        public Integer getActionForm() {
            return actionForm;
        }

        public void setActionForm(Integer actionForm) {
            this.actionForm = actionForm;
            if(actionForm == null){
                this.actionForm = 0;
            }
        }

        public Integer getActionStrength() {
            return actionStrength;
        }

        public void setActionStrength(Integer actionStrength) {
            this.actionStrength = actionStrength;
            if(actionStrength == null){
                this.actionStrength = 0;
            }
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            if(TextUtils.isEmpty(id)){
                this.id = "";
            }
        }

        public Integer getInterval() {
            return interval;
        }

        public void setInterval(Integer interval) {
            this.interval = interval;
            if(interval == null){
                this.interval = 0;
            }
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPrepareId() {
            return prepareId;
        }

        public void setPrepareId(String prepareId) {
            this.prepareId = prepareId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
            if(time == null){
                this.time = "";
            }
        }
    }
}
