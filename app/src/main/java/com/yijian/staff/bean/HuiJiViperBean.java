package com.yijian.staff.bean;

import com.yijian.staff.R;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/29.
 */

public class HuiJiViperBean implements Serializable {

    //("头像图片")
    private String headImg;
    //会员姓名
    private String name;
    //性别  0 未知 1 男 2 女(这里存的是图片的路径)
    private String sex;
//    private String sex;
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
    //("合同余额")
    private String contractBalance;
    //("到场时间")
    private String visitTime;
    //("离场时间")
    private String leaveTime;
    //("合同ID列表")
    private List<String> contractIds;
    //("卡对象集合")
    private List<CardprodsBean> cardprodsBeans;

    //("过期时间")
    private String deadline;
    //("过期原因")
    private String expiryReason;
    //("合同到期日期")
    private String contractDeadline;
    //("已过期天数")
    private String expiredDay;
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

    private String mobile ;
    private Boolean underProtected;
    private int clockedCount;

    /**
     * CoachExpireVO：教练过期
     CoachInfoVO ：教练正式
     CoachIntentionVO：教练意向
     CoachTodayVisitVO：教练今日来访
     CustomerInfoVO：会籍正式
     CustomerTodayVisitVO：会籍今日来访
     CustomerExpireVO：会籍过期
     CustomerIntentionVO：会籍意向
     PotentialVO：潜在（会籍教练共用）
     */
    private String subclassName;


    public HuiJiViperBean() {
    }

    public HuiJiViperBean(JSONObject jsonObject) {
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
        this.clockedCount = JsonUtil.getInt(jsonObject, "clockedCount");

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

        this.contractDeadline = JsonUtil.getString(jsonObject, "contractDeadline");
        this.expiredDay = JsonUtil.getString(jsonObject, "expiredDay");
        this.contractBalance = JsonUtil.getString(jsonObject, "contractBalance");
        this.visitTime = JsonUtil.getString(jsonObject, "visitTime");
        this.leaveTime = JsonUtil.getString(jsonObject, "leaveTime");
        this.mobile = JsonUtil.getString(jsonObject, "mobile");
        this.underProtected = JsonUtil.getBoolean(jsonObject, "protected");

        try {
            if (jsonObject.has("contractIds")){
                this.contractIds = com.alibaba.fastjson.JSONArray.parseArray(JsonUtil.getJsonArray(jsonObject,"contractIds").toString(),String.class);
            }
            if (jsonObject.has("cardprods")){

                this.cardprodsBeans = com.alibaba.fastjson.JSONObject.parseArray(jsonObject.getJSONArray("cardprods").toString(),CardprodsBean.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.seller = JsonUtil.getString(jsonObject, "seller");
        this.subclassName = JsonUtil.getString(jsonObject, "subclassName");

    }

    public int getClockedCount() {
        return clockedCount;
    }

    public String getMobile() {
        return mobile;
    }

    public Boolean getProtected() {
        return underProtected;
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

    public String getSubclassName() {
        return subclassName;
    }

    public String getContractDeadline() {
        return contractDeadline;
    }

    public String getExpiredDay() {
        return expiredDay;
    }

    public String getContractBalance() {
        return contractBalance;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public List<String> getContractIds() {
        return contractIds;
    }


    public List<CardprodsBean> getCardprodsBeans() {
        return cardprodsBeans;
    }

    public static class CardprodsBean  implements Serializable  {
        /**
         * cardName : string
         * cardType : string
         * cardprodId : string
         */

        private String cardName;
        private String cardType;
        private String cardprodId;

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardprodId() {
            return cardprodId;
        }

        public void setCardprodId(String cardprodId) {
            this.cardprodId = cardprodId;
        }
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
