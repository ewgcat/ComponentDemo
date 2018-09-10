package com.yijian.staff.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangk on 2018/4/2.
 */

public class ViperDetailBean implements Serializable {


    /**
     * activateCardTime : 2018-09-10T06:36:05.704Z
     * age : 0
     * birthday : 2018-09-10T06:36:05.704Z
     * birthdayType : string
     * cardName : string
     * cardType : 0
     * cardprods : [{"cardName":"string","cardType":"string","cardprodId":"string"}]
     * certificateNo : string
     * certificateType : string
     * contractIds : [{}]
     * customerServiceInfo : {"leagueCourses":["string"],"privateCourses":["string"],"receptionSale":"string","referee":"string","refereeMobile":"string","sellerId":"string","serviceCoach":"string","serviceSale":"string","userChannel":"string"}
     * deadline : 2018-09-10T06:36:05.704Z
     * detail : {"address":"string","carPrice":"string","childrenStatus":"string","clubBrand":"string","company":"string","companyAddress":"string","companyPhone":"string","companyRegion":{"cityId":0,"cityName":"string","districtId":0,"districtName":"string","provinceId":0,"provinceName":"string"},"contactPhone":"string","email":"string","fitnessGoal":"string","fitnessHobby":"string","healthStatus":"string","height":"string","hobby":"string","homeRegion":{"cityId":0,"cityName":"string","districtId":0,"districtName":"string","provinceId":0,"provinceName":"string"},"marriageStatus":"string","nation":"string","nationality":"string","nativePlace":"string","newestAddress":"string","newestCompanyAddress":"string","occupation":"string","onceJoinedClub":false,"position":"string","urgentContact":"string","wechatNo":"string","weight":"string","yearIncome":"string"}
     * dictItemKey : 0
     * editEnable : false
     * faceInfo : string
     * fingerprint : string
     * fitTimes : 0
     * headImg : string
     * invitationEnable : false
     * leagueCourseLeftCount : 0
     * medalType : 0
     * memberCardId : string
     * memberCardNo : string
     * memberId : string
     * memberMark : string
     * memberType : string
     * memberTypeCode : 0
     * menuKey : string
     * mobile : string
     * name : string
     * nickName : string
     * openCardTime : 2018-09-10T06:36:05.704Z
     * privateCourseAndConsumeInfo : {"cardSurplusAmount":0,"consumeAmount":0,"courseAmount":0,"courseConsumeNum":0,"courseNum":0,"courseSurplusNum":0}
     * privateCourseLeftCount : 0
     * protectedDay : 0
     * recentlyFitTime : 2018-09-10T06:36:05.705Z
     * sex : string
     * subclassName : string
     * totalConsumption : 0
     * underProtected : false
     * vein : string
     * viperRole : string
     */

    private String activateCardTime;
    private Integer age;
    private String birthday;
    private String birthdayType;
    private String cardName;
    private Integer cardType;
    private String certificateNo;
    private String certificateType;
    private CustomerServiceInfoBean customerServiceInfo;
    private String deadline;
    private DetailBean detail;
    private Integer dictItemKey;
    private Boolean editEnable;
    private String faceInfo;
    private String fingerprint;
    private Integer fitTimes;
    private String headImg;
    private Boolean invitationEnable;
    private Integer leagueCourseLeftCount;
    private Integer medalType;
    private String memberCardId;
    private String memberCardNo;
    private String memberId;
    private String memberMark;
    private String memberType;
    private Integer memberTypeCode;
    private String menuKey;
    private String mobile;
    private String name;
    private String nickName;
    private String openCardTime;
    private PrivateCourseAndConsumeInfoBean privateCourseAndConsumeInfo;
    private Integer privateCourseLeftCount;
    private Integer protectedDay;
    private String recentlyFitTime;
    private String sex;
    private String subclassName;
    private String totalConsumption;
    private Boolean underProtected;
    private String vein;
    private String viperRole;
    private List<CardprodsBean> cardprods;
    private List<String> contractIds;

    public String getActivateCardTime() {
        return activateCardTime;
    }

    public void setActivateCardTime(String activateCardTime) {
        this.activateCardTime = activateCardTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public CustomerServiceInfoBean getCustomerServiceInfo() {
        return customerServiceInfo;
    }

    public void setCustomerServiceInfo(CustomerServiceInfoBean customerServiceInfo) {
        this.customerServiceInfo = customerServiceInfo;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public Integer getDictItemKey() {
        return dictItemKey;
    }

    public void setDictItemKey(Integer dictItemKey) {
        this.dictItemKey = dictItemKey;
    }

    public Boolean isEditEnable() {
        return editEnable;
    }

    public void setEditEnable(Boolean editEnable) {
        this.editEnable = editEnable;
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

    public Integer getFitTimes() {
        return fitTimes;
    }

    public void setFitTimes(Integer fitTimes) {
        this.fitTimes = fitTimes;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Boolean isInvitationEnable() {
        return invitationEnable;
    }

    public void setInvitationEnable(Boolean invitationEnable) {
        this.invitationEnable = invitationEnable;
    }

    public Integer getLeagueCourseLeftCount() {
        return leagueCourseLeftCount;
    }

    public void setLeagueCourseLeftCount(Integer leagueCourseLeftCount) {
        this.leagueCourseLeftCount = leagueCourseLeftCount;
    }

    public Integer getMedalType() {
        return medalType;
    }

    public void setMedalType(Integer medalType) {
        this.medalType = medalType;
    }

    public String getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(String memberCardId) {
        this.memberCardId = memberCardId;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberMark() {
        return memberMark;
    }

    public void setMemberMark(String memberMark) {
        this.memberMark = memberMark;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Integer getMemberTypeCode() {
        return memberTypeCode;
    }

    public void setMemberTypeCode(Integer memberTypeCode) {
        this.memberTypeCode = memberTypeCode;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenCardTime() {
        return openCardTime;
    }

    public void setOpenCardTime(String openCardTime) {
        this.openCardTime = openCardTime;
    }

    public PrivateCourseAndConsumeInfoBean getPrivateCourseAndConsumeInfo() {
        return privateCourseAndConsumeInfo;
    }

    public void setPrivateCourseAndConsumeInfo(PrivateCourseAndConsumeInfoBean privateCourseAndConsumeInfo) {
        this.privateCourseAndConsumeInfo = privateCourseAndConsumeInfo;
    }

    public Integer getPrivateCourseLeftCount() {
        return privateCourseLeftCount;
    }

    public void setPrivateCourseLeftCount(Integer privateCourseLeftCount) {
        this.privateCourseLeftCount = privateCourseLeftCount;
    }

    public Integer getProtectedDay() {
        return protectedDay;
    }

    public void setProtectedDay(Integer protectedDay) {
        this.protectedDay = protectedDay;
    }

    public String getRecentlyFitTime() {
        return recentlyFitTime;
    }

    public void setRecentlyFitTime(String recentlyFitTime) {
        this.recentlyFitTime = recentlyFitTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public Boolean isUnderProtected() {
        return underProtected;
    }

    public void setUnderProtected(Boolean underProtected) {
        this.underProtected = underProtected;
    }

    public String getVein() {
        return vein;
    }

    public void setVein(String vein) {
        this.vein = vein;
    }

    public String getViperRole() {
        return viperRole;
    }

    public void setViperRole(String viperRole) {
        this.viperRole = viperRole;
    }

    public List<CardprodsBean> getCardprods() {
        return cardprods;
    }

    public void setCardprods(List<CardprodsBean> cardprods) {
        this.cardprods = cardprods;
    }

    public List<String> getContractIds() {
        return contractIds;
    }

    public void setContractIds(List<String> contractIds) {
        this.contractIds = contractIds;
    }

    public static class CustomerServiceInfoBean implements Serializable{
        /**
         * leagueCourses : ["string"]
         * privateCourses : ["string"]
         * receptionSale : string
         * referee : string
         * refereeMobile : string
         * sellerId : string
         * serviceCoach : string
         * serviceSale : string
         * userChannel : string
         */

        private String receptionSale;
        private String referee;
        private String refereeMobile;
        private String sellerId;
        private String serviceCoach;
        private String serviceSale;
        private String userChannel;
        private List<String> leagueCourses;
        private List<String> privateCourses;

        public String getReceptionSale() {
            return receptionSale;
        }

        public void setReceptionSale(String receptionSale) {
            this.receptionSale = receptionSale;
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

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getServiceCoach() {
            return serviceCoach;
        }

        public void setServiceCoach(String serviceCoach) {
            this.serviceCoach = serviceCoach;
        }

        public String getServiceSale() {
            return serviceSale;
        }

        public void setServiceSale(String serviceSale) {
            this.serviceSale = serviceSale;
        }

        public String getUserChannel() {
            return userChannel;
        }

        public void setUserChannel(String userChannel) {
            this.userChannel = userChannel;
        }

        public List<String> getLeagueCourses() {
            return leagueCourses;
        }

        public void setLeagueCourses(List<String> leagueCourses) {
            this.leagueCourses = leagueCourses;
        }

        public List<String> getPrivateCourses() {
            return privateCourses;
        }

        public void setPrivateCourses(List<String> privateCourses) {
            this.privateCourses = privateCourses;
        }
    }

    public static class DetailBean implements Serializable{
        /**
         * address : string
         * carPrice : string
         * childrenStatus : string
         * clubBrand : string
         * company : string
         * companyAddress : string
         * companyPhone : string
         * companyRegion : {"cityId":0,"cityName":"string","districtId":0,"districtName":"string","provinceId":0,"provinceName":"string"}
         * contactPhone : string
         * email : string
         * fitnessGoal : string
         * fitnessHobby : string
         * healthStatus : string
         * height : string
         * hobby : string
         * homeRegion : {"cityId":0,"cityName":"string","districtId":0,"districtName":"string","provinceId":0,"provinceName":"string"}
         * marriageStatus : string
         * nation : string
         * nationality : string
         * nativePlace : string
         * newestAddress : string
         * newestCompanyAddress : string
         * occupation : string
         * onceJoinedClub : false
         * position : string
         * urgentContact : string
         * wechatNo : string
         * weight : string
         * yearIncome : string
         */

        private String address;
        private String carPrice;
        private String childrenStatus;
        private String clubBrand;
        private String company;
        private String companyAddress;
        private String companyPhone;
        private CompanyRegion companyRegion;
        private String contactPhone;
        private String email;
        private String fitnessGoal;
        private String fitnessHobby;
        private String healthStatus;
        private String height;
        private String hobby;
        private HomeRegion homeRegion;
        private String marriageStatus;
        private String nation;
        private String nationality;
        private String nativePlace;
        private String newestAddress;
        private String newestCompanyAddress;
        private String occupation;
        private Boolean onceJoinedClub;
        private String position;
        private String urgentContact;
        private String wechatNo;
        private String weight;
        private String yearIncome;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }

        public String getChildrenStatus() {
            return childrenStatus;
        }

        public void setChildrenStatus(String childrenStatus) {
            this.childrenStatus = childrenStatus;
        }

        public String getClubBrand() {
            return clubBrand;
        }

        public void setClubBrand(String clubBrand) {
            this.clubBrand = clubBrand;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public CompanyRegion getCompanyRegion() {
            return companyRegion;
        }

        public void setCompanyRegion(CompanyRegion companyRegion) {
            this.companyRegion = companyRegion;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFitnessGoal() {
            return fitnessGoal;
        }

        public void setFitnessGoal(String fitnessGoal) {
            this.fitnessGoal = fitnessGoal;
        }

        public String getFitnessHobby() {
            return fitnessHobby;
        }

        public void setFitnessHobby(String fitnessHobby) {
            this.fitnessHobby = fitnessHobby;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public void setHealthStatus(String healthStatus) {
            this.healthStatus = healthStatus;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public HomeRegion getHomeRegion() {
            return homeRegion;
        }

        public void setHomeRegion(HomeRegion homeRegion) {
            this.homeRegion = homeRegion;
        }

        public String getMarriageStatus() {
            return marriageStatus;
        }

        public void setMarriageStatus(String marriageStatus) {
            this.marriageStatus = marriageStatus;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
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

        public String getNewestAddress() {
            return newestAddress;
        }

        public void setNewestAddress(String newestAddress) {
            this.newestAddress = newestAddress;
        }

        public String getNewestCompanyAddress() {
            return newestCompanyAddress;
        }

        public void setNewestCompanyAddress(String newestCompanyAddress) {
            this.newestCompanyAddress = newestCompanyAddress;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public Boolean isOnceJoinedClub() {
            return onceJoinedClub;
        }

        public void setOnceJoinedClub(Boolean onceJoinedClub) {
            this.onceJoinedClub = onceJoinedClub;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getUrgentContact() {
            return urgentContact;
        }

        public void setUrgentContact(String urgentContact) {
            this.urgentContact = urgentContact;
        }

        public String getWechatNo() {
            return wechatNo;
        }

        public void setWechatNo(String wechatNo) {
            this.wechatNo = wechatNo;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getYearIncome() {
            return yearIncome;
        }

        public void setYearIncome(String yearIncome) {
            this.yearIncome = yearIncome;
        }

        public static class CompanyRegion implements Serializable{
            /**
             * cityId : 0
             * cityName : string
             * districtId : 0
             * districtName : string
             * provinceId : 0
             * provinceName : string
             */

            private Integer cityId;
            private String cityName;
            private Integer districtId;
            private String districtName;
            private Integer provinceId;
            private String provinceName;

            public Integer getCityId() {
                return cityId;
            }

            public void setCityId(Integer cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public Integer getDistrictId() {
                return districtId;
            }

            public void setDistrictId(Integer districtId) {
                this.districtId = districtId;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public Integer getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(Integer provinceId) {
                this.provinceId = provinceId;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }
        }

        public static class HomeRegion implements Serializable{
            /**
             * cityId : 0
             * cityName : string
             * districtId : 0
             * districtName : string
             * provinceId : 0
             * provinceName : string
             */

            private Integer cityId;
            private String cityName;
            private Integer districtId;
            private String districtName;
            private Integer provinceId;
            private String provinceName;

            public Integer getCityId() {
                return cityId;
            }

            public void setCityId(Integer cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public Integer getDistrictId() {
                return districtId;
            }

            public void setDistrictId(Integer districtId) {
                this.districtId = districtId;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public Integer getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(Integer provinceId) {
                this.provinceId = provinceId;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }
        }
    }

    public static class PrivateCourseAndConsumeInfoBean implements Serializable{
        /**
         * cardSurplusAmount : 0
         * consumeAmount : 0
         * courseAmount : 0
         * courseConsumeNum : 0
         * courseNum : 0
         * courseSurplusNum : 0
         */

        private String cardSurplusAmount;
        private String consumeAmount;
        private String courseAmount;
        private String courseConsumeNum;
        private String courseNum;
        private String courseSurplusNum;

        public String getCardSurplusAmount() {
            return cardSurplusAmount;
        }

        public void setCardSurplusAmount(String cardSurplusAmount) {
            this.cardSurplusAmount = cardSurplusAmount;
        }

        public String getConsumeAmount() {
            return consumeAmount;
        }

        public void setConsumeAmount(String consumeAmount) {
            this.consumeAmount = consumeAmount;
        }

        public String getCourseAmount() {
            return courseAmount;
        }

        public void setCourseAmount(String courseAmount) {
            this.courseAmount = courseAmount;
        }

        public String getCourseConsumeNum() {
            return courseConsumeNum;
        }

        public void setCourseConsumeNum(String courseConsumeNum) {
            this.courseConsumeNum = courseConsumeNum;
        }

        public String getCourseNum() {
            return courseNum;
        }

        public void setCourseNum(String courseNum) {
            this.courseNum = courseNum;
        }

        public String getCourseSurplusNum() {
            return courseSurplusNum;
        }

        public void setCourseSurplusNum(String courseSurplusNum) {
            this.courseSurplusNum = courseSurplusNum;
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
        private Long activateTime;
        private String cardType;
        private Long cardTypeId;

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

        public Long getActivateTime() {
            return activateTime;
        }

        public void setActivateTime(Long activateTime) {
            this.activateTime = activateTime;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public Long getCardTypeId() {
            return cardTypeId;
        }

        public void setCardTypeId(Long cardTypeId) {
            this.cardTypeId = cardTypeId;
        }
    }




}
