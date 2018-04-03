package com.yijian.staff.mvp.vip.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yangk on 2018/4/3.
 */

public class EditHuiJiVipBody {


    /**
     * address : string
     * carPrice : string
     * clubBrand : string
     * fitnessGoal : string
     * hasChildren : false
     * hobby : string
     * marriageStatus : 0
     * memberId : string
     * nation : string
     * nationality : string
     * occupation : string
     * onceJoinedClub : false
     * source : string
     * yearIncome : string
     */

    private String address;
    private String carPrice;
    private String clubBrand;
    private String fitnessGoal;
    private boolean hasChildren;
    private String hobby;
    private int marriageStatus;
    private String memberId;
    private String nation;
    private String nationality;
    private String occupation;
    private boolean onceJoinedClub;
    private String source;
    private String yearIncome;

    public EditHuiJiVipBody(Map<String, Object> map){
        this.address = (String) map.get("address");
        this.carPrice = (String) map.get("carPrice");
        this.clubBrand = (String) map.get("clubBrand");
        this.fitnessGoal = (String) map.get("fitnessGoal");
        this.hasChildren = (boolean) map.get("hasChildren");
        this.hobby = (String) map.get("hobby");
        this.marriageStatus = (int) map.get("marriageStatus");
        this.memberId = (String) map.get("memberId");
        this.nation = (String) map.get("nation");
        this.nationality = (String) map.get("nationality");
        this.occupation = (String) map.get("occupation");
        this.onceJoinedClub = (boolean) map.get("onceJoinedClub");
        this.source = (String) map.get("source");
        this.yearIncome = (String) map.get("yearIncome");
    }

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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(String yearIncome) {
        this.yearIncome = yearIncome;
    }
}
