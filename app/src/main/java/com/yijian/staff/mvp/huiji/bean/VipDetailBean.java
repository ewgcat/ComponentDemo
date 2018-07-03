package com.yijian.staff.mvp.huiji.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangk on 2018/4/2.
 */

public class VipDetailBean implements Serializable {


    /**
     * age : 24
     * birthday : 765043200000
     * memberId : b51f8a29bd0e413789afbdb1a5554d1e
     * name : 硬件测试
     * sex : 男
     * birthdayType : 公历
     * mobile : 15965411376
     * dictItemKey : 0
     * underProtected : false
     * headImg : /memberHeadPortrait/8a891d0c44454df284aa72af4dda0c52.jpg
     * memberType :
     * cardName :
     * cardType : 0
     * memberCardNo : 7046097
     * certificateType : 身份证
     * certificateNo : 445332199903095800
     * fingerprint :
     * vein :
     * deadline : 1556269004000
     * totalConsumption : 1000.0
     * recentlyFitTime : 1526437553000
     * customerServiceInfo : {"sellerId":"","referee":"硬件测试","refereeMobile":"15965411376","userChannel":"","receptionSale":"","serviceSale":"","serviceCoach":"","privateCourses":null}
     * detail : {"wechatNo":"","email":"aaaa@asdf.com","height":"","weight":"","nationality":"","nativePlace":"","nation":"","healthStatus":"","fitnessHobby":"","fitnessGoal":"","hobby":"","onceJoinedClub":false,"clubBrand":"","yearIncome":"","carPrice":"","marriageStatus":"未婚","childrenStatus":"无","occupation":"","position":"","company":"","companyPhone":"","companyAddress":"","address":"","urgentContact":"4324","contactPhone":"15965334376"}
     * contractIds : ["d049225016f940a98a73c6d7f0d02c4c"]
     * cardprods : [{"cardprodId":"74c6b3004ed7464eb8f04ea0bfcd2452","cardName":"硬件相关","activateTime":1524733004000,"cardType":"会员制卡","cardTypeId":3}]
     */

    private int age;
    private long birthday;
    private String memberId;
    private String name;
    private String sex;
    private String birthdayType;
    private String mobile;
    private int dictItemKey;
    private boolean underProtected;
    private String headImg;
    private boolean editEnable;
    private String memberType;
    private String cardName;
    private int cardType;
    private String memberCardNo;
    private String certificateType;
    private String certificateNo;
    private String fingerprint;
    private String vein;
    private long deadline;
    private String totalConsumption;
    private long recentlyFitTime;
    private CustomerServiceInfoBean customerServiceInfo;
    private PrivateCourseAndConsumeInfoBean privateCourseAndConsumeInfo;
    private DetailBean detail;
    private List<String> contractIds;
    private List<CardprodsBean> cardprods;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public boolean isEditEnable() {
        return editEnable;
    }

    public void setEditEnable(boolean editEnable) {
        this.editEnable = editEnable;
    }

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

    public int getDictItemKey() {
        return dictItemKey;
    }

    public void setDictItemKey(int dictItemKey) {
        this.dictItemKey = dictItemKey;
    }

    public boolean isUnderProtected() {
        return underProtected;
    }

    public void setUnderProtected(boolean underProtected) {
        this.underProtected = underProtected;
    }

    public String getHeadImg() {
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

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public long getRecentlyFitTime() {
        return recentlyFitTime;
    }

    public void setRecentlyFitTime(long recentlyFitTime) {
        this.recentlyFitTime = recentlyFitTime;
    }

    public CustomerServiceInfoBean getCustomerServiceInfo() {
        return customerServiceInfo;
    }

    public void setCustomerServiceInfo(CustomerServiceInfoBean customerServiceInfo) {
        this.customerServiceInfo = customerServiceInfo;
    }

    public PrivateCourseAndConsumeInfoBean getPrivateCourseAndConsumeInfo() {
        return privateCourseAndConsumeInfo;
    }

    public void setPrivateCourseAndConsumeInfo(PrivateCourseAndConsumeInfoBean privateCourseAndConsumeInfo) {
        this.privateCourseAndConsumeInfo = privateCourseAndConsumeInfo;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public List<String> getContractIds() {
        return contractIds;
    }

    public void setContractIds(List<String> contractIds) {
        this.contractIds = contractIds;
    }

    public List<CardprodsBean> getCardprods() {
        return cardprods;
    }

    public void setCardprods(List<CardprodsBean> cardprods) {
        this.cardprods = cardprods;
    }


    public static class PrivateCourseAndConsumeInfoBean implements Serializable {

        private int courseAmount; //课程总金额
        private int courseConsumeNum; //课程消耗总节数
        private int courseNum; //课程总节数
        private int courseSurplusNum; //课程剩余总节数
        private int cardSurplusAmount; //会员卡剩余金额
        private int consumeAmount; //消耗总金额

        public int getCardSurplusAmount() {
            return cardSurplusAmount;
        }

        public void setCardSurplusAmount(int cardSurplusAmount) {
            this.cardSurplusAmount = cardSurplusAmount;
        }

        public int getConsumeAmount() {
            return consumeAmount;
        }

        public void setConsumeAmount(int consumeAmount) {
            this.consumeAmount = consumeAmount;
        }

        public int getCourseAmount() {
            return courseAmount;
        }

        public void setCourseAmount(int courseAmount) {
            this.courseAmount = courseAmount;
        }

        public int getCourseConsumeNum() {
            return courseConsumeNum;
        }

        public void setCourseConsumeNum(int courseConsumeNum) {
            this.courseConsumeNum = courseConsumeNum;
        }

        public int getCourseNum() {
            return courseNum;
        }

        public void setCourseNum(int courseNum) {
            this.courseNum = courseNum;
        }

        public int getCourseSurplusNum() {
            return courseSurplusNum;
        }

        public void setCourseSurplusNum(int courseSurplusNum) {
            this.courseSurplusNum = courseSurplusNum;
        }
    }

    public static class CustomerServiceInfoBean implements Serializable {
        /**
         * sellerId :
         * referee : 硬件测试
         * refereeMobile : 15965411376
         * userChannel :
         * receptionSale :
         * serviceSale :
         * serviceCoach :
         * privateCourses : null
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
         * wechatNo :
         * email : aaaa@asdf.com
         * height :
         * weight :
         * nationality :
         * nativePlace :
         * nation :
         * healthStatus :
         * fitnessHobby :
         * fitnessGoal :
         * hobby :
         * onceJoinedClub : false
         * clubBrand :
         * yearIncome :
         * carPrice :
         * marriageStatus : 未婚
         * childrenStatus : 无
         * occupation :
         * position :
         * company :
         * companyPhone :
         * companyAddress :
         * address :
         * urgentContact : 4324
         * contactPhone : 15965334376
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

    public static class CardprodsBean implements Serializable {
        /**
         * cardprodId : 74c6b3004ed7464eb8f04ea0bfcd2452
         * cardName : 硬件相关
         * activateTime : 1524733004000
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
