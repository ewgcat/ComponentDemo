package com.yijian.staff.mvp.huiji.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yangk on 2018/4/2.
 */

public class VipDetailBean  {


    /**
     * activateCardTime : 2018-04-02T06:11:49.306Z
     * birthday : 2018-04-02T06:11:49.306Z
     * birthdayType : string
     * cardName : string
     * cardType : 0
     * certificateNo : string
     * certificateType : string
     * customerServiceInfo : {"receptionSale":"string","referee":"string","refereeMobile":"string","serviceCoach":"string","serviceSale":"string","userChannel":"string"}
     * detail : {"address":"string","carPrice":"string","childrenStatus":"string","clubBrand":"string","company":"string","companyAddress":"string","companyPhone":"string","contactPhone":"string","email":"string","fitnessGoal":"string","fitnessHobby":"string","healthStatus":"string","height":"string","hobby":"string","marriageStatus":"string","nation":"string","nationality":"string","nativePlace":"string","occupation":"string","onceJoinedClub":false,"position":"string","urgentContact":"string","wechatNo":"string","weight":"string","yearIncome":"string"}
     * fingerprint : string
     * headImg : string
     * memberCardNo : string
     * memberId : string
     * memberType : string
     * mobile : string
     * name : string
     * openCardTime : 2018-04-02T06:11:49.307Z
     * sex : string
     * subclassName : string
     * vein : string
     */

    /**激活卡日期 **/
    private String activateCardTime;
    /**生日 **/
    private String birthday;
    /**生日类型 **/
    private String birthdayType;
    /**卡名**/
    private String cardName;
    /**卡类型(0:期限卡 1:次卡 2:储值卡 3:会员制卡 )**/
    private int cardType;
    /**卡类型(0:期限卡 1:次卡 2:储值卡 3:会员制卡 )**/
    private String certificateNo;
    /**证件类型**/
    private String certificateType;
    /**证会籍信息**/
    private CustomerServiceInfoBean customerServiceInfo;
    /**详细信息**/
    private DetailBean detail;
    /**指纹信息**/
    private String fingerprint;
    /**头像图片**/
    private String headImg;
    /**会员卡号**/
    private String memberCardNo;
    /**会员id**/
    private String memberId;
    /**会员类型 **/
    private String memberType;
    /**手机**/
    private String mobile;
    /**名称**/
    private String name;
    /**开卡日期**/
    private String openCardTime;
    /**性别**/
    private String sex;
    private String subclassName;
    /**静脉信息**/
    private String vein;

    public String getActivateCardTime() {
        return activateCardTime;
    }

    public void setActivateCardTime(String activateCardTime) {
        this.activateCardTime = activateCardTime;
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

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
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

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
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

    public String getOpenCardTime() {
        return openCardTime;
    }

    public void setOpenCardTime(String openCardTime) {
        this.openCardTime = openCardTime;
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

    public String getVein() {
        return vein;
    }

    public void setVein(String vein) {
        this.vein = vein;
    }


    /**
     * 会籍信息
     */
    public static class CustomerServiceInfoBean {
        /*receptionSale (string, optional): 接待会籍(添加人) ,
        referee (string, optional): 推荐人 ,
        refereeMobile (string, optional): 推荐人电话 ,
        serviceCoach (string, optional): 服务教练 ,
        serviceSale (string, optional): 服务会籍 ,
        userChannel (string, optional): 用户获取渠道*/

        private String receptionSale;
        private String referee;
        private String refereeMobile;
        private String serviceCoach;
        private String serviceSale;
        private String userChannel;
        private ArrayList<String> privateCourses;

        public ArrayList<String> getPrivateCourses() {
            return privateCourses;
        }
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
    }

    /**
     * 详情信息
     */
    public static class DetailBean implements Serializable {
       /* address (string, optional): 住址 ,
        carPrice (string, optional): 用车价值 ,
        childrenStatus (string, optional): 子女情况 ,
        clubBrand (string, optional): 俱乐部品牌 ,
        company (string, optional): 公司名称 ,
        companyAddress (string, optional): 工作地址 ,
        companyPhone (string, optional): 公司电话 ,
        contactPhone (string, optional): 联系人电话 ,
        email (string, optional): 邮箱 ,
        fitnessGoal (string, optional): 健身目的 ,
        fitnessHobby (string, optional): 健身爱好 ,
        healthStatus (string, optional): 身体状态 ,
        height (string, optional): 身高 ,
        hobby (string, optional): 兴趣爱好 ,
        marriageStatus (string, optional): 婚姻状态 ,
        nation (string, optional): 民族 ,
        nationality (string, optional): 国籍 ,
        nativePlace (string, optional): 籍贯 ,
        occupation (string, optional): 行业 ,
        onceJoinedClub (boolean, optional): 是否参见过俱乐部 ,
        position (string, optional): 职务 ,
        urgentContact (string, optional): 紧急联系人 ,
        wechatNo (string, optional): 微信号 ,
        weight (string, optional): 体重 ,
        yearIncome (string, optional): 年收入*/

        private String address;
        private String carPrice;
        private String childrenStatus;
        private String clubBrand;
        private String company;
        private String companyAddress;
        private String companyPhone;
        private String contactPhone;
        private String email;
        private String fitnessGoal;
        private String fitnessHobby;
        private String healthStatus;
        private String height;
        private String hobby;
        private String marriageStatus;
        private String nation;
        private String nationality;
        private String nativePlace;
        private String occupation;
        private boolean onceJoinedClub;
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

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public boolean isOnceJoinedClub() {
            return onceJoinedClub;
        }

        public void setOnceJoinedClub(boolean onceJoinedClub) {
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
    }
}
