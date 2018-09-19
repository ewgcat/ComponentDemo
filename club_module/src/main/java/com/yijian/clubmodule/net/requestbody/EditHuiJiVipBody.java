package com.yijian.clubmodule.net.requestbody;

/**
 * Created by yangk on 2018/4/3.
 */

public class EditHuiJiVipBody {


    /**
     * address : string
     * carPrice : string
     * clubBrand : string
     * companyAddress : string
     * companyAddressIds : {"cityId":0,"districtId":0,"provinceId":0}
     * contactPhone : string
     * email : string
     * fitnessGoal : string
     * hasChildren : false
     * healthStatus : string
     * height : string
     * hobby : string
     * homeAddressIds : {"cityId":0,"districtId":0,"provinceId":0}
     * marriageStatus : 0
     * memberId : string
     * nation : string
     * nationality : string
     * occupation : string
     * onceJoinedClub : false
     * position : string
     * source : string
     * urgentContact : string
     * wechatNo : string
     * weight : string
     * yearIncome : string
     */

    private String address;
    private String carPrice;
    private String clubBrand;
    private String companyAddress;
    private CompanyAddressIdsBean companyAddressIds;
    private String contactPhone;
    private String email;
    private String fitnessGoal;
    private boolean hasChildren;
    private String healthStatus;
    private String height;
    private String hobby;
    private HomeAddressIdsBean homeAddressIds;
    private int marriageStatus;
    private String memberId;
    private String nation;
    private String nationality;
    private String occupation;
    private boolean onceJoinedClub;
    private String position;
    private String source;
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

    public String getClubBrand() {
        return clubBrand;
    }

    public void setClubBrand(String clubBrand) {
        this.clubBrand = clubBrand;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public CompanyAddressIdsBean getCompanyAddressIds() {
        return companyAddressIds;
    }

    public void setCompanyAddressIds(CompanyAddressIdsBean companyAddressIds) {
        this.companyAddressIds = companyAddressIds;
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

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
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

    public HomeAddressIdsBean getHomeAddressIds() {
        return homeAddressIds;
    }

    public void setHomeAddressIds(HomeAddressIdsBean homeAddressIds) {
        this.homeAddressIds = homeAddressIds;
    }

    public int getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(int marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public static class CompanyAddressIdsBean {
        /**
         * cityId : 0
         * districtId : 0
         * provinceId : 0
         */

        private int cityId;
        private int districtId;
        private int provinceId;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }
    }

    public static class HomeAddressIdsBean {
        /**
         * cityId : 0
         * districtId : 0
         * provinceId : 0
         */

        private int cityId;
        private int districtId;
        private int provinceId;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }
    }
}
