package com.yijian.staff.mvp.huiji.bean;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/4/2.
 */

public class VipDetailBean implements Serializable {


    /**
     * memberId : 043ad824a0844a6ba49fd2ee9fbd4990
     * name : 回访过期会员14-0
     * sex : 男
     * birthday : 1523980800000
     * birthdayType : 农历
     * mobile : 值14
     * headImg : 值14
     * memberType :
     * cardName :
     * cardType : 0
     * memberCardNo : 值14
     * certificateType : 港澳通行证
     * certificateNo : 值14
     * faceInfo : 值14
     * fingerprint : 值14
     * vein : 值14
     * customerServiceInfo : {"sellerId":"03a9cb612c5e4569b960cfd42adaa113","referee":"回访过期会员14-0","refereeMobile":"值14","userChannel":"港澳通行证","receptionSale":"会籍客服","serviceSale":"会籍客服","serviceCoach":"教练测试账号","privateCourses":["游泳课"]}
     * detail : {"wechatNo":"值14","email":"值14","height":"值14","weight":"值14","nationality":"港澳通行证","nativePlace":"港澳通行证","nation":"港澳通行证","healthStatus":"正常","fitnessHobby":"健身,跑步,爬山","fitnessGoal":"","hobby":"现金","onceJoinedClub":false,"clubBrand":"值14","yearIncome":"港澳通行证","carPrice":"港澳通行证","marriageStatus":"未婚","childrenStatus":"无","occupation":"港澳通行证","position":"港澳通行证","company":"值14","companyPhone":"值14","companyAddress":"值14","address":"值14","urgentContact":"值14","contactPhone":""}
     * contractIds : ["06b9972deac949beae359d739e760ea8","ae675a00896a4e8f87f2d5c1783668c0","c80b3672055c457180448e9e27525a79","fad0146898954561a7a7fc9e3cc93318"]
     * cardprods : [{"cardprodId":"59aa744517bc425e99fb4fbd190f184f","cardName":"会员制卡","activateTime":1524022052000,"cardType":"会员制卡","cardTypeId":3},{"cardprodId":"9746a46f38ca4fbfbeeb2c509044aed0","cardName":"储值卡","activateTime":1524022052000,"cardType":"储值卡","cardTypeId":2},{"cardprodId":"99fb72733dc84870993018b3acb6d36a","cardName":"次卡","activateTime":1524022052000,"cardType":"次卡","cardTypeId":1},{"cardprodId":"e3276f72cc114c59b14ab7669baaf6f2","cardName":"时间卡","activateTime":1524022052000,"cardType":"时间卡","cardTypeId":0}]
     * underProtected : false
     */

    private String memberId;
    private String name;
    private String sex;
    private long birthday;
    private String birthdayType;
    private String mobile;
    private String headImg;
    private String memberType;
    private String cardName;
    private int cardType;
    private String memberCardNo;
    private String certificateType;
    private String certificateNo;
    private String faceInfo;
    private String fingerprint;
    private String vein;
    private CustomerServiceInfoBean customerServiceInfo;
    private DetailBean detail;
    private boolean underProtected;
    private ArrayList<String> contractIds;
    private List<CardprodsBean> cardprods;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGenderImg(){
        return "0".equals(sex) ? R.mipmap.lg_women : R.mipmap.lg_man;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImg() {
        if (headImg!=null&&!headImg.contains( BuildConfig.FILE_HOST)){
            headImg= BuildConfig.FILE_HOST+  headImg;
        }
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getFaceInfo() {
        return faceInfo;
    }

    public void setFaceInfo(String faceInfo) {
        this.faceInfo = faceInfo;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getVein() {
        return vein;
    }

    public void setVein(String vein) {
        this.vein = vein;
    }

    public CustomerServiceInfoBean getCustomerServiceInfo() {
        return customerServiceInfo;
    }

    public void setCustomerServiceInfo(CustomerServiceInfoBean customerServiceInfo) {
        this.customerServiceInfo = customerServiceInfo;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public boolean isUnderProtected() {
        return underProtected;
    }

    public void setUnderProtected(boolean underProtected) {
        this.underProtected = underProtected;
    }

    public ArrayList<String> getContractIds() {
        return contractIds;
    }

    public void setContractIds(ArrayList<String> contractIds) {
        this.contractIds = contractIds;
    }

    public List<CardprodsBean> getCardprods() {
        return cardprods;
    }

    public void setCardprods(List<CardprodsBean> cardprods) {
        this.cardprods = cardprods;
    }

    public static class CustomerServiceInfoBean {
        /**
         * sellerId : 03a9cb612c5e4569b960cfd42adaa113
         * referee : 回访过期会员14-0
         * refereeMobile : 值14
         * userChannel : 港澳通行证
         * receptionSale : 会籍客服
         * serviceSale : 会籍客服
         * serviceCoach : 教练测试账号
         * privateCourses : ["游泳课"]
         */

        private String sellerId;
        private String referee;
        private String refereeMobile;
        private String userChannel;
        private String receptionSale;
        private String serviceSale;
        private String serviceCoach;
        private List<String> privateCourses;

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getReferee() {
            return referee;
        }

        public void setReferee(String referee) {
            this.referee = referee;
        }

        public String getRefereeMobile() {
            return refereeMobile;
        }

        public void setRefereeMobile(String refereeMobile) {
            this.refereeMobile = refereeMobile;
        }

        public String getUserChannel() {
            return userChannel;
        }

        public void setUserChannel(String userChannel) {
            this.userChannel = userChannel;
        }

        public String getReceptionSale() {
            return receptionSale;
        }

        public void setReceptionSale(String receptionSale) {
            this.receptionSale = receptionSale;
        }

        public String getServiceSale() {
            return serviceSale;
        }

        public void setServiceSale(String serviceSale) {
            this.serviceSale = serviceSale;
        }

        public String getServiceCoach() {
            return serviceCoach;
        }

        public void setServiceCoach(String serviceCoach) {
            this.serviceCoach = serviceCoach;
        }

        public List<String> getPrivateCourses() {
            return privateCourses;
        }

        public void setPrivateCourses(List<String> privateCourses) {
            this.privateCourses = privateCourses;
        }
    }

    public static class DetailBean implements Serializable {
        /**
         * wechatNo : 值14
         * email : 值14
         * height : 值14
         * weight : 值14
         * nationality : 港澳通行证
         * nativePlace : 港澳通行证
         * nation : 港澳通行证
         * healthStatus : 正常
         * fitnessHobby : 健身,跑步,爬山
         * fitnessGoal :
         * hobby : 现金
         * onceJoinedClub : false
         * clubBrand : 值14
         * yearIncome : 港澳通行证
         * carPrice : 港澳通行证
         * marriageStatus : 未婚
         * childrenStatus : 无
         * occupation : 港澳通行证
         * position : 港澳通行证
         * company : 值14
         * companyPhone : 值14
         * companyAddress : 值14
         * address : 值14
         * urgentContact : 值14
         * contactPhone :
         */

        private String wechatNo;
        private String email;
        private String height;
        private String weight;
        private String nationality;
        private String nativePlace;
        private String nation;
        private String healthStatus;
        private String fitnessHobby;
        private String fitnessGoal;
        private String hobby;
        private boolean onceJoinedClub;
        private String clubBrand;
        private String yearIncome;
        private String carPrice;
        private String marriageStatus;
        private String childrenStatus;
        private String occupation;
        private String position;
        private String company;
        private String companyPhone;
        private String companyAddress;
        private String address;
        private String urgentContact;
        private String contactPhone;

        public String getWechatNo() {
            return wechatNo;
        }

        public void setWechatNo(String wechatNo) {
            this.wechatNo = wechatNo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getNativePlace() {
            return nativePlace;
        }

        public void setNativePlace(String nativePlace) {
            this.nativePlace = nativePlace;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public void setHealthStatus(String healthStatus) {
            this.healthStatus = healthStatus;
        }

        public String getFitnessHobby() {
            return fitnessHobby;
        }

        public void setFitnessHobby(String fitnessHobby) {
            this.fitnessHobby = fitnessHobby;
        }

        public String getFitnessGoal() {
            return fitnessGoal;
        }

        public void setFitnessGoal(String fitnessGoal) {
            this.fitnessGoal = fitnessGoal;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public boolean isOnceJoinedClub() {
            return onceJoinedClub;
        }

        public void setOnceJoinedClub(boolean onceJoinedClub) {
            this.onceJoinedClub = onceJoinedClub;
        }

        public String getClubBrand() {
            return clubBrand;
        }

        public void setClubBrand(String clubBrand) {
            this.clubBrand = clubBrand;
        }

        public String getYearIncome() {
            return yearIncome;
        }

        public void setYearIncome(String yearIncome) {
            this.yearIncome = yearIncome;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }

        public String getMarriageStatus() {
            return marriageStatus;
        }

        public void setMarriageStatus(String marriageStatus) {
            this.marriageStatus = marriageStatus;
        }

        public String getChildrenStatus() {
            return childrenStatus;
        }

        public void setChildrenStatus(String childrenStatus) {
            this.childrenStatus = childrenStatus;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUrgentContact() {
            return urgentContact;
        }

        public void setUrgentContact(String urgentContact) {
            this.urgentContact = urgentContact;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }
    }

    public static class CardprodsBean {
        /**
         * cardprodId : 59aa744517bc425e99fb4fbd190f184f
         * cardName : 会员制卡
         * activateTime : 1524022052000
         * cardType : 会员制卡
         * cardTypeId : 3
         */

        private String cardprodId;
        private String cardName;
        private long activateTime;
        private String cardType;
        private int cardTypeId;

        public String getCardprodId() {
            return cardprodId;
        }

        public void setCardprodId(String cardprodId) {
            this.cardprodId = cardprodId;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public long getActivateTime() {
            return activateTime;
        }

        public void setActivateTime(long activateTime) {
            this.activateTime = activateTime;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public int getCardTypeId() {
            return cardTypeId;
        }

        public void setCardTypeId(int cardTypeId) {
            this.cardTypeId = cardTypeId;
        }
    }
}
