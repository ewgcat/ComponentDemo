package com.yijian.staff.mvp.coach.search;

import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/29 16:17:53
 */
public class CoachSearchViperBean {

    /**
     * {
     "memberId": "85fec0bef849466fb95697d4bb7f2901",
     "name": "值0",
     "sex": "2",
     "headImg": "值0",
     "cardprodId": "0063514bd77a4f77979393c8efe27b68",
     "cardName": "值4",
     "cardType": "员工卡",
     "favorCourse": "卡卡技术",
     "favorTeacher": "faker大魔王",
     "registerTime": 1521118474000,
     "contractId": "60d3b1b531c946f1b6ed6294f21efea5",
     "contractDeadline": 1520920430000,
     "contractBalance": "3.00",
     "purchaseCount": 0,
     "seller": "树根",
     "experienceClassTimes": 2,
     "viperRole": "意向会员",
     "subclassName": "CoachIntentionVO"
     }
     */
    //("头像图片")
    private String headImg;
    //会员姓名
    private String name;
    //性别  0 未知 1 男 2 女
    private String sex;
    //会员角色 普通会员
    private String viperRole;
    //会员id
    private String memberId;

    //生日
    private String birthday;
    //生日类型
    private String birthdayType;
    //("卡品id")
    private String cardprodId;
    //("卡名")
    private String cardName;
    //("卡类型")
    private String cardType;

    //("私教教练")
    private String privateCoach;
    //("私教课")
    private String privateCourse;
    //("喜欢课程")
    private String favorCourse;
    //("喜欢老师")
    private String favorTeacher;
    //("注册时间")
    private String registerTime;
    //("购买次数")
    private String purchaseCount;


    //("过期时间")
    private String deadline;
    //("过期原因")
    private String expiryReason;
    //("服务会籍")
    private String seller;
    //("体验课次数")
    private String experienceClassTimes;

    //("历史课程")
    private String historyCourse;
    //("身体状态")
    private String healthStatus;
    //("健身爱好")
    private String fitnessHobby;
    //("兴趣爱好")
    private String hobby;
    //("使用车辆")
    private String useCar;

    public CoachSearchViperBean() {
    }

    public CoachSearchViperBean(JSONObject jsonObject) {
        this.headImg = JsonUtil.getString(jsonObject, "headImg");
        this.name = JsonUtil.getString(jsonObject, "name");
        this.sex = JsonUtil.getString(jsonObject, "sex");
        this.viperRole = JsonUtil.getString(jsonObject, "viperRole");
        this.memberId = JsonUtil.getString(jsonObject, "memberId");

        this.cardprodId = JsonUtil.getString(jsonObject, "cardprodId");
        this.birthday = JsonUtil.getString(jsonObject, "birthday");
        this.birthdayType = JsonUtil.getString(jsonObject, "birthdayType");
        this.cardName = JsonUtil.getString(jsonObject, "cardName");
        this.cardType = JsonUtil.getString(jsonObject, "cardType");

        this.privateCoach = JsonUtil.getString(jsonObject, "privateCoach");
        this.privateCourse = JsonUtil.getString(jsonObject, "privateCourse");
        this.favorCourse = JsonUtil.getString(jsonObject, "favorCourse");
        this.favorTeacher = JsonUtil.getString(jsonObject, "favorTeacher");
        this.purchaseCount = JsonUtil.getString(jsonObject, "purchaseCount");
        this.registerTime = JsonUtil.getString(jsonObject, "registerTime");

        this.useCar = JsonUtil.getString(jsonObject, "useCar");
        this.healthStatus = JsonUtil.getString(jsonObject, "healthStatus");
        this.fitnessHobby = JsonUtil.getString(jsonObject, "fitnessHobby");
        this.hobby = JsonUtil.getString(jsonObject, "hobby");
        this.historyCourse = JsonUtil.getString(jsonObject, "historyCourse");

        this.experienceClassTimes = JsonUtil.getString(jsonObject, "experienceClassTimes");
        this.deadline = JsonUtil.getString(jsonObject, "deadline");
        this.expiryReason = JsonUtil.getString(jsonObject, "expiryReason");
        this.seller = JsonUtil.getString(jsonObject, "seller");
    }

    public String getPrivateCourse() {
        return privateCourse;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getViperRole() {
        return viperRole;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public String getCardprodId() {
        return cardprodId;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getPrivateCoach() {
        return privateCoach;
    }

    public String getFavorCourse() {
        return favorCourse;
    }

    public String getFavorTeacher() {
        return favorTeacher;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public String getPurchaseCount() {
        return purchaseCount;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getExpiryReason() {
        return expiryReason;
    }

    public String getSeller() {
        return seller;
    }

    public String getExperienceClassTimes() {
        return experienceClassTimes;
    }

    public String getHistoryCourse() {
        return historyCourse;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getFitnessHobby() {
        return fitnessHobby;
    }

    public String getHobby() {
        return hobby;
    }

    public String getUseCar() {
        return useCar;
    }

    @Override
    public String toString() {
        return "CoachSearchViperBean{" +
                "headImg='" + headImg + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", viperRole='" + viperRole + '\'' +
                ", memberId='" + memberId + '\'' +
                ", birthday='" + birthday + '\'' +
                ", birthdayType='" + birthdayType + '\'' +
                ", cardprodId='" + cardprodId + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", privateCoach='" + privateCoach + '\'' +
                ", privateCourse='" + privateCourse + '\'' +
                ", favorCourse='" + favorCourse + '\'' +
                ", favorTeacher='" + favorTeacher + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", purchaseCount='" + purchaseCount + '\'' +
                ", deadline='" + deadline + '\'' +
                ", expiryReason='" + expiryReason + '\'' +
                ", seller='" + seller + '\'' +
                ", experienceClassTimes='" + experienceClassTimes + '\'' +
                ", historyCourse='" + historyCourse + '\'' +
                ", healthStatus='" + healthStatus + '\'' +
                ", fitnessHobby='" + fitnessHobby + '\'' +
                ", hobby='" + hobby + '\'' +
                ", useCar='" + useCar + '\'' +
                '}';
    }
}
