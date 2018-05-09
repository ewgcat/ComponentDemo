package com.yijian.staff.mvp.reception.bean;

import java.util.List;

/**
 * Created by The_P on 2018/3/29.
 */

public class RecptionRecordListBean {
    /**
     * pageSize : 10
     * pages : 1
     * total : 2
     * pageNum : 1
     * records : [{"receptionId":"20d3f46e2dda4be3a021f8733090b991","memberId":"9e12b4fedb63429ea588174f014214ac","merchantId":"4351681b3f0b11e89f9b00163e04d061","shopId":"eaf79bf34d274354890b7e55c3aeb250","cardId":"","coachId":"0f1374f5025246a093e115ac165b5076","saleId":"ee6972bcab6e481d9a56dbdbfd4915e1","leaderId":"","createTime":1525779839000,"createBy":"ee6972bcab6e481d9a56dbdbfd4915e1","updateTime":1525779842000,"updateBy":"","isDelete":0,"isFinish":0,"smallStatus":21,"smallStatusDesc":"会籍选择发送给教练","bigStatus":20,"memberBcRejectReason":"","toLeaderReason":"","qsCompletionRate":0,"bodyCheckCompletionRate":0,"memberMobile":"13600000028","memberName":"山东二八会员","coachName":"","saleName":"","cardName":"","sex":"女"},{"receptionId":"9f3f7336ab1d494f8abc6f3e38d4afcf","memberId":"5b7ce9fa730c424c94d9e5e9e894872f","merchantId":"4351681b3f0b11e89f9b00163e04d061","shopId":"eaf79bf34d274354890b7e55c3aeb250","cardId":"236e4d3c09144707a0e7021e2f841049","coachId":"0f1374f5025246a093e115ac165b5076","saleId":"ee6972bcab6e481d9a56dbdbfd4915e1","leaderId":"","createTime":1525761178000,"createBy":"ee6972bcab6e481d9a56dbdbfd4915e1","updateTime":1525762066000,"updateBy":"","isDelete":0,"isFinish":0,"smallStatus":40,"smallStatusDesc":"会籍完成产品报价，签订合同中","bigStatus":40,"memberBcRejectReason":"","toLeaderReason":"","qsCompletionRate":0,"bodyCheckCompletionRate":0,"memberMobile":"13600000026","memberName":"山东二六会员","coachName":"","saleName":"","cardName":"","sex":"男"}]
     * hasPermissionEdit : false
     * hasPermissionDel : false
     */

    private int pageSize;
    private int pages;
    private int total;
    private int pageNum;
    private boolean hasPermissionEdit;
    private boolean hasPermissionDel;
    private List<RecordsBean> records;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public boolean isHasPermissionEdit() {
        return hasPermissionEdit;
    }

    public void setHasPermissionEdit(boolean hasPermissionEdit) {
        this.hasPermissionEdit = hasPermissionEdit;
    }

    public boolean isHasPermissionDel() {
        return hasPermissionDel;
    }

    public void setHasPermissionDel(boolean hasPermissionDel) {
        this.hasPermissionDel = hasPermissionDel;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * receptionId : 20d3f46e2dda4be3a021f8733090b991
         * memberId : 9e12b4fedb63429ea588174f014214ac
         * merchantId : 4351681b3f0b11e89f9b00163e04d061
         * shopId : eaf79bf34d274354890b7e55c3aeb250
         * cardId :
         * coachId : 0f1374f5025246a093e115ac165b5076
         * saleId : ee6972bcab6e481d9a56dbdbfd4915e1
         * leaderId :
         * createTime : 1525779839000
         * createBy : ee6972bcab6e481d9a56dbdbfd4915e1
         * updateTime : 1525779842000
         * updateBy :
         * isDelete : 0
         * isFinish : 0
         * smallStatus : 21
         * smallStatusDesc : 会籍选择发送给教练
         * bigStatus : 20
         * memberBcRejectReason :
         * toLeaderReason :
         * qsCompletionRate : 0.0
         * bodyCheckCompletionRate : 0.0
         * memberMobile : 13600000028
         * memberName : 山东二八会员
         * coachName :
         * saleName :
         * cardName :
         * sex : 女
         */

//        private String receptionId;
        private String memberId;
//        private String merchantId;
//        private String shopId;
//        private String cardId;
//        private String coachId;
//        private String saleId;
//        private String leaderId;
//        private long createTime;
//        private String createBy;
//        private long updateTime;
//        private String updateBy;
//        private int isDelete;
        private int isFinish;
//        private int smallStatus;
//        private String smallStatusDesc;
//        private int bigStatus;
//        private String memberBcRejectReason;
//        private String toLeaderReason;
//        private double qsCompletionRate;
//        private double bodyCheckCompletionRate;
        private String memberMobile;
        private String memberName;
        private String coachName;
        private String saleName;
        private String cardName;
        private String sex;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public String getMemberMobile() {
            return memberMobile;
        }

        public void setMemberMobile(String memberMobile) {
            this.memberMobile = memberMobile;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getCoachName() {
            return coachName;
        }

        public void setCoachName(String coachName) {
            this.coachName = coachName;
        }

        public String getSaleName() {
            return saleName;
        }

        public void setSaleName(String saleName) {
            this.saleName = saleName;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }




}
