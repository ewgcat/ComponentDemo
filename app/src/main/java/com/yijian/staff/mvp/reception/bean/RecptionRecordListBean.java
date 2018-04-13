package com.yijian.staff.mvp.reception.bean;

import java.util.List;

/**
 * Created by The_P on 2018/3/29.
 */

public class RecptionRecordListBean {

    /**
     * pageSize : 10
     * pages : 1
     * total : 1
     * pageNum : 1
     * records : [{"receptionId":"03a9cb612c5e4569b960cfd42adaa113","memberId":"076c3096caf04559b9abe112542a9cd0","merchantId":"333","shopId":"","cardId":"1","coachId":"03a9cb612c5e4569b960cfd42adaa113","saleId":"03a9cb612c5e4569b960cfd42adaa113","createBy":"","updateBy":"","isDelete":0,"isFinish":0,"smallStatus":10,"smallStatusDesc":"","bigStatus":10,"bigStatusDesc":"","memberMobile":"值3","memberName":"值3","coachName":"aa","saleName":"aa"}]
     */

    private int pageSize;
    private int pages;
    private int total;
    private int pageNum;
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

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * receptionId : 03a9cb612c5e4569b960cfd42adaa113
         * memberId : 076c3096caf04559b9abe112542a9cd0
         * merchantId : 333
         * shopId :
         * cardId : 1
         * coachId : 03a9cb612c5e4569b960cfd42adaa113
         * saleId : 03a9cb612c5e4569b960cfd42adaa113
         * createBy :
         * updateBy :
         * isDelete : 0
         * isFinish : 0
         * smallStatus : 10
         * smallStatusDesc :
         * bigStatus : 10
         * bigStatusDesc :
         * memberMobile : 值3
         * memberName : 值3
         * coachName : aa
         * saleName : aa
         *
         *
         * bigStatus (integer, optional): 大状态 ,
         bigStatusDesc (string, optional): 大状态 描述 ,
         cardId (string, optional): 卡id ,
         coachId (string, optional): 教练ID ,
         coachName (string, optional): 教练姓名 ,
         createBy (string, optional),
         createTime (string, optional),
         isDelete (integer, optional),
         isFinish (integer, optional): 状态 0未完成，1已经完成 ,
         memberId (string, optional): 会员ID ,
         memberMobile (string, optional): 会员手机号码 ,
         memberName (string, optional): 会员姓名 ,
         merchantId (string, optional): 商户id ,
         receptionId (string, optional): 接待id ,
         saleId (string, optional): 会籍ID ,
         saleName (string, optional): 会籍姓名 ,
         shopId (string, optional): 门店id ,
         smallStatus (integer, optional): 小状态 ,
         smallStatusDesc (string, optional): 小状态 描述 ,
         updateBy (string, optional),
         updateTime (string, optional)
         */

        private String receptionId;
        private String memberId;
        private String merchantId;
        private String shopId;
        private String cardId;
        private String coachId;
        private String saleId;
        private String createBy;
        private String updateBy;
        private int isDelete;
        private int isFinish;
        private int smallStatus;
        private String smallStatusDesc;
        private int bigStatus;
        private String bigStatusDesc;
        private String memberMobile;
        private String memberName;
        private String coachName;
        private String saleName;

        public String getReceptionId() {
            return receptionId;
        }

        public void setReceptionId(String receptionId) {
            this.receptionId = receptionId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getCoachId() {
            return coachId;
        }

        public void setCoachId(String coachId) {
            this.coachId = coachId;
        }

        public String getSaleId() {
            return saleId;
        }

        public void setSaleId(String saleId) {
            this.saleId = saleId;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public int getSmallStatus() {
            return smallStatus;
        }

        public void setSmallStatus(int smallStatus) {
            this.smallStatus = smallStatus;
        }

        public String getSmallStatusDesc() {
            return smallStatusDesc;
        }

        public void setSmallStatusDesc(String smallStatusDesc) {
            this.smallStatusDesc = smallStatusDesc;
        }

        public int getBigStatus() {
            return bigStatus;
        }

        public void setBigStatus(int bigStatus) {
            this.bigStatus = bigStatus;
        }

        public String getBigStatusDesc() {
            return bigStatusDesc;
        }

        public void setBigStatusDesc(String bigStatusDesc) {
            this.bigStatusDesc = bigStatusDesc;
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
    }
}
