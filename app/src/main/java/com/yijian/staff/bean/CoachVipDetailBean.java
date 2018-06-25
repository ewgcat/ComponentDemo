package com.yijian.staff.bean;

import android.widget.ScrollView;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangk on 2018/4/2.
 */

public class CoachVipDetailBean {


    /**
     * memberId : 503634cd51734962a11463c87a0526a6
     * name : 回访过期会员0-0
     * sex : 男
     * birthday : 1523980800000
     * birthdayType : 农历
     * mobile : 值0
     * headImg : 值0
     * memberType :
     * cardName :
     * cardType : 0
     * memberCardNo : 值0
     * certificateType :
     * certificateNo : 值0
     * faceInfo : 值0
     * fingerprint : 值0
     * vein : 值0
     * customerServiceInfo : {"sellerId":"03a9cb612c5e4569b960cfd42adaa113","referee":"回访过期会员0-0","refereeMobile":"值0","userChannel":"","receptionSale":"会籍客服","serviceSale":"会籍客服","serviceCoach":"教练测试账号","privateCourses":["游泳课"]}
     * detail : {"wechatNo":"值0","email":"值0","height":"值0","weight":"值0","nationality":"","nativePlace":"","nation":"","healthStatus":"正常","fitnessHobby":"健身,跑步,爬山","fitnessGoal":"","hobby":"现金","onceJoinedClub":false,"clubBrand":"值0","yearIncome":"","carPrice":"","marriageStatus":"未婚","childrenStatus":"无","occupation":"","position":"","company":"值0","companyPhone":"值0","companyAddress":"值0","address":"值0","urgentContact":"值0","contactPhone":""}
     * contractIds : ["226b057175724d419f672d0b8ee9e282","34d7f0f4ddcc4cd3b9ff778fb4db7773","4e228f4a0bec4cc5b2c1d304a43a976a","5c6181e2d2d442818e45136f644557c8","5d8006e29e1d43159d25cf649d1682da","876ec015e9694d82a1de8d8d2fc7946f","db7e95a536314c74929177601d693286","ee27aebaf2c34909842e3d9be32ec49e"]
     * cardprods : [{"cardprodId":"3e7c7437eb594c3f86f24ec562822afa","cardName":"次卡","activateTime":1524021785000,"cardType":"次卡","cardTypeId":1},{"cardprodId":"3eea7f9ef62147ea9abaa515822af2ef","cardName":"会员制卡","activateTime":1524021785000,"cardType":"会员制卡","cardTypeId":3},{"cardprodId":"9c31410377b6400ebe68ff2398a3723f","cardName":"储值卡","activateTime":1524021785000,"cardType":"储值卡","cardTypeId":2},{"cardprodId":"d08765be426b406091151c1374ec4bc6","cardName":"时间卡","activateTime":1524021785000,"cardType":"时间卡","cardTypeId":0}]
     * underProtected : false
     */

    private String memberId;
    private String name;
    private String sex;
    private Long birthday;
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
    private boolean underProtected;

    private CustomerServiceInfoBean customerServiceInfo;
    private DetailBean detail;
    private List<String> contractIds;
    private List<CardprodsBean> cardprods;

    public CoachVipDetailBean(JSONObject jsonObject) {

        this.memberId = JsonUtil.getString(jsonObject, "memberId");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.birthday = JsonUtil.getLong(jsonObject, "birthday");
        this.birthdayType = JsonUtil.getString(jsonObject, "birthdayType");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
        this.headImg = BuildConfig.FILE_HOST + JsonUtil.getString(jsonObject, "headImg");
        this.memberType = JsonUtil.getString(jsonObject, "memberType");
        this.cardName = JsonUtil.getString(jsonObject, "cardName");
        this.cardType = JsonUtil.getInt(jsonObject, "cardType");
        this.memberCardNo = JsonUtil.getString(jsonObject, "memberCardNo");
        this.certificateType = JsonUtil.getString(jsonObject, "certificateType");
        this.certificateNo = JsonUtil.getString(jsonObject, "certificateNo");
        this.faceInfo = JsonUtil.getString(jsonObject, "faceInfo");
        this.fingerprint = JsonUtil.getString(jsonObject, "fingerprint");
        this.vein = JsonUtil.getString(jsonObject, "vein");
        this.underProtected = JsonUtil.getBoolean(jsonObject, "underProtected");
        this.customerServiceInfo = new CustomerServiceInfoBean(JsonUtil.getJsonObject(jsonObject, "customerServiceInfo"));
        this.detail = new DetailBean(JsonUtil.getJsonObject(jsonObject, "detail"));
        this.contractIds = com.alibaba.fastjson.JSONArray.parseArray(JsonUtil.getJsonArray(jsonObject, "contractIds").toString(), String.class);

        try {
            this.cardprods = com.alibaba.fastjson.JSONObject.parseArray(jsonObject.getJSONArray("cardprods").toString(), CardprodsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
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

    public static class CustomerServiceInfoBean {
        /**
         * sellerId : 03a9cb612c5e4569b960cfd42adaa113
         * referee : 回访过期会员0-0
         * refereeMobile : 值0
         * userChannel :
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

        public CustomerServiceInfoBean(JSONObject jsonObject) {
            this.sellerId = JsonUtil.getString(jsonObject, "sellerId");
            this.referee = JsonUtil.getString(jsonObject, "referee");
            this.refereeMobile = JsonUtil.getString(jsonObject, "refereeMobile");
            this.userChannel = JsonUtil.getString(jsonObject, "userChannel");
            this.receptionSale = JsonUtil.getString(jsonObject, "receptionSale");
            this.serviceSale = JsonUtil.getString(jsonObject, "serviceSale");
            this.serviceCoach = JsonUtil.getString(jsonObject, "serviceCoach");

            this.privateCourses = com.alibaba.fastjson.JSONArray.parseArray(JsonUtil.getJsonArray(jsonObject, "privateCourses").toString(), String.class);

        }

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
         * wechatNo : 值0
         * email : 值0
         * height : 值0
         * weight : 值0
         * nationality :
         * nativePlace :
         * nation :
         * healthStatus : 正常
         * fitnessHobby : 健身,跑步,爬山
         * fitnessGoal :
         * hobby : 现金
         * onceJoinedClub : false
         * clubBrand : 值0
         * yearIncome :
         * carPrice :
         * marriageStatus : 未婚
         * childrenStatus : 无
         * occupation :
         * position :
         * company : 值0
         * companyPhone : 值0
         * companyAddress : 值0
         * address : 值0
         * urgentContact : 值0
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

        public DetailBean(JSONObject jsonObject) {
            this.wechatNo = JsonUtil.getString(jsonObject, "wechatNo");
            this.email = JsonUtil.getString(jsonObject, "email");
            this.height = JsonUtil.getString(jsonObject, "height");
            this.weight = JsonUtil.getString(jsonObject, "weight");
            this.nationality = JsonUtil.getString(jsonObject, "nationality");
            this.nativePlace = JsonUtil.getString(jsonObject, "nativePlace");
            this.nation = JsonUtil.getString(jsonObject, "nation");
            this.healthStatus = JsonUtil.getString(jsonObject, "healthStatus");
            this.fitnessHobby = JsonUtil.getString(jsonObject, "fitnessHobby");
            this.fitnessGoal = JsonUtil.getString(jsonObject, "fitnessGoal");
            this.hobby = JsonUtil.getString(jsonObject, "hobby");
            this.clubBrand = JsonUtil.getString(jsonObject, "clubBrand");
            this.onceJoinedClub = JsonUtil.getBoolean(jsonObject, "onceJoinedClub");
            this.carPrice = JsonUtil.getString(jsonObject, "carPrice");
            this.yearIncome = JsonUtil.getString(jsonObject, "yearIncome");
            this.marriageStatus = JsonUtil.getString(jsonObject, "marriageStatus");
            this.childrenStatus = JsonUtil.getString(jsonObject, "childrenStatus");
            this.occupation = JsonUtil.getString(jsonObject, "occupation");
            this.position = JsonUtil.getString(jsonObject, "position");
            this.company = JsonUtil.getString(jsonObject, "company");
            this.companyPhone = JsonUtil.getString(jsonObject, "companyPhone");
            this.companyAddress = JsonUtil.getString(jsonObject, "companyAddress");
            this.address = JsonUtil.getString(jsonObject, "address");
            this.urgentContact = JsonUtil.getString(jsonObject, "urgentContact");
            this.contactPhone = JsonUtil.getString(jsonObject, "contactPhone");


        }

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
         * cardprodId : 3e7c7437eb594c3f86f24ec562822afa
         * cardName : 次卡
         * activateTime : 1524021785000
         * cardType : 次卡
         * cardTypeId : 1
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
