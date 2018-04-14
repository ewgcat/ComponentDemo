package com.yijian.staff.mvp.coach.experienceclass.step3;

import com.yijian.staff.mvp.coach.experienceclass.step1.ExperienceClassProcess1Bean;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/13 16:15:48
 */
public class ExperienceClassProcess3Bean {
    /**
     会商方案节点返回对象 {
     bodyCheck (BodyCheck, optional): 体侧数据 ,
     processId (string, optional): 体验课流程id ,
     programmeContext (string, optional): 会商方案内容 ,
     visitRecord (回访记录节点返回对象, optional): 回访记录
     }
     BodyCheck {
     age (integer, optional),
     bHead (string, optional),
     bHipJ (string, optional),
     bKnee (string, optional),
     bPelvis (string, optional),
     bScapula (string, optional),
     bShoulders (string, optional),
     bTLV (string, optional),
     basalMetabolism (number, optional),
     beartLungMovement (string, optional),
     bfoot (string, optional),
     bmi (number, optional),
     bodyTargetSelect (string, optional),
     bodyTimesSelect (integer, optional),
     checkId (string, optional),
     createBy (string, optional),
     createTime (string, optional),
     diastoleBloodPressure (number, optional),
     extensionalMovement (string, optional),
     fat (number, optional),
     fatController (number, optional),
     heartbeat (integer, optional),
     height (number, optional),
     hipline (number, optional),
     isDelete (integer, optional),
     leftHand (number, optional),
     leftHhigh (number, optional),
     leftShins (number, optional),
     memberId (string, optional),
     muscleController (number, optional),
     pbf (number, optional),
     rightHand (number, optional),
     rightHhigh (number, optional),
     rightShins (number, optional),
     sAnkle (string, optional),
     sCervicaIV (string, optional),
     sFibula (string, optional),
     sHead (string, optional),
     sHipJ (string, optional),
     sKnee (string, optional),
     sLumbarV (string, optional),
     sPelvis (string, optional),
     sScapula (string, optional),
     sThoracicV (string, optional),
     shrinkBloodPressure (number, optional),
     skeletalMuscle (number, optional),
     skinFatLand (number, optional),
     updateBy (string, optional),
     updateTime (string, optional),
     waist (number, optional),
     water (number, optional),
     weight (number, optional),
     weightNoFat (number, optional),
     weightTraining (string, optional),
     whr (number, optional)
     }
     回访记录节点返回对象 {
     coachVisitRecord (string, optional): 回访记录-教练 ,
     processId (string, optional): 体验课流程id ,
     sellerVisitRecord (string, optional): 回访记录-会籍
     }
     */

    /**
     * bodyCheck : {"age":0,"bHead":"string","bHipJ":"string","bKnee":"string","bPelvis":"string","bScapula":"string","bShoulders":"string","bTLV":"string","basalMetabolism":0,"beartLungMovement":"string","bfoot":"string","bmi":0,"bodyTargetSelect":"string","bodyTimesSelect":0,"checkId":"string","createBy":"string","createTime":"2018-04-13T05:58:09.601Z","diastoleBloodPressure":0,"extensionalMovement":"string","fat":0,"fatController":0,"heartbeat":0,"height":0,"hipline":0,"isDelete":0,"leftHand":0,"leftHhigh":0,"leftShins":0,"memberId":"string","muscleController":0,"pbf":0,"rightHand":0,"rightHhigh":0,"rightShins":0,"sAnkle":"string","sCervicaIV":"string","sFibula":"string","sHead":"string","sHipJ":"string","sKnee":"string","sLumbarV":"string","sPelvis":"string","sScapula":"string","sThoracicV":"string","shrinkBloodPressure":0,"skeletalMuscle":0,"skinFatLand":0,"updateBy":"string","updateTime":"2018-04-13T05:58:09.601Z","waist":0,"water":0,"weight":0,"weightNoFat":0,"weightTraining":"string","whr":0}
     * processId : string
     * programmeContext : string
     * visitRecord : {"coachVisitRecord":"string","processId":"string","sellerVisitRecord":"string"}
     */

    private BodyCheckBean bodyCheck;
    private String processId;
    private String programmeContext;
    private VisitRecordBean visitRecord;


    public ExperienceClassProcess3Bean(JSONObject jsonObject) {
        try {
            if (jsonObject.has("bodyCheck")) {
                this.bodyCheck = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.getJSONObject("bodyCheck").toString(), BodyCheckBean.class);
            }
            if (jsonObject.has("visitRecord")) {
                this.visitRecord = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.getJSONObject("visitRecord").toString(), VisitRecordBean.class);
            }
            if (jsonObject.has("processId")) {
                this.processId = JsonUtil.getString(jsonObject, "processId");
                this.programmeContext = JsonUtil.getString(jsonObject, "programmeContext");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public BodyCheckBean getBodyCheck() {
        return bodyCheck;
    }

    public void setBodyCheck(BodyCheckBean bodyCheck) {
        this.bodyCheck = bodyCheck;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProgrammeContext() {
        return programmeContext;
    }

    public void setProgrammeContext(String programmeContext) {
        this.programmeContext = programmeContext;
    }

    public VisitRecordBean getVisitRecord() {
        return visitRecord;
    }

    public void setVisitRecord(VisitRecordBean visitRecord) {
        this.visitRecord = visitRecord;
    }

    public static class BodyCheckBean {
        /**
         * age : 0
         * bHead : string
         * bHipJ : string
         * bKnee : string
         * bPelvis : string
         * bScapula : string
         * bShoulders : string
         * bTLV : string
         * basalMetabolism : 0
         * beartLungMovement : string
         * bfoot : string
         * bmi : 0
         * bodyTargetSelect : string
         * bodyTimesSelect : 0
         * checkId : string
         * createBy : string
         * createTime : 2018-04-13T05:58:09.601Z
         * diastoleBloodPressure : 0
         * extensionalMovement : string
         * fat : 0
         * fatController : 0
         * heartbeat : 0
         * height : 0
         * hipline : 0
         * isDelete : 0
         * leftHand : 0
         * leftHhigh : 0
         * leftShins : 0
         * memberId : string
         * muscleController : 0
         * pbf : 0
         * rightHand : 0
         * rightHhigh : 0
         * rightShins : 0
         * sAnkle : string
         * sCervicaIV : string
         * sFibula : string
         * sHead : string
         * sHipJ : string
         * sKnee : string
         * sLumbarV : string
         * sPelvis : string
         * sScapula : string
         * sThoracicV : string
         * shrinkBloodPressure : 0
         * skeletalMuscle : 0
         * skinFatLand : 0
         * updateBy : string
         * updateTime : 2018-04-13T05:58:09.601Z
         * waist : 0
         * water : 0
         * weight : 0
         * weightNoFat : 0
         * weightTraining : string
         * whr : 0
         */

        private int age;
        private String bHead;
        private String bHipJ;
        private String bKnee;
        private String bPelvis;
        private String bScapula;
        private String bShoulders;
        private String bTLV;
        private int basalMetabolism;
        private String beartLungMovement;
        private String bfoot;
        private int bmi;
        private String bodyTargetSelect;
        private int bodyTimesSelect;
        private String checkId;
        private String createBy;
        private String createTime;
        private int diastoleBloodPressure;
        private String extensionalMovement;
        private int fat;
        private int fatController;
        private int heartbeat;
        private int height;
        private int hipline;
        private int isDelete;
        private int leftHand;
        private int leftHhigh;
        private int leftShins;
        private String memberId;
        private int muscleController;
        private int pbf;
        private int rightHand;
        private int rightHhigh;
        private int rightShins;
        private String sAnkle;
        private String sCervicaIV;
        private String sFibula;
        private String sHead;
        private String sHipJ;
        private String sKnee;
        private String sLumbarV;
        private String sPelvis;
        private String sScapula;
        private String sThoracicV;
        private int shrinkBloodPressure;
        private int skeletalMuscle;
        private int skinFatLand;
        private String updateBy;
        private String updateTime;
        private int waist;
        private int water;
        private int weight;
        private int weightNoFat;
        private String weightTraining;
        private int whr;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBHead() {
            return bHead;
        }

        public void setBHead(String bHead) {
            this.bHead = bHead;
        }

        public String getBHipJ() {
            return bHipJ;
        }

        public void setBHipJ(String bHipJ) {
            this.bHipJ = bHipJ;
        }

        public String getBKnee() {
            return bKnee;
        }

        public void setBKnee(String bKnee) {
            this.bKnee = bKnee;
        }

        public String getBPelvis() {
            return bPelvis;
        }

        public void setBPelvis(String bPelvis) {
            this.bPelvis = bPelvis;
        }

        public String getBScapula() {
            return bScapula;
        }

        public void setBScapula(String bScapula) {
            this.bScapula = bScapula;
        }

        public String getBShoulders() {
            return bShoulders;
        }

        public void setBShoulders(String bShoulders) {
            this.bShoulders = bShoulders;
        }

        public String getBTLV() {
            return bTLV;
        }

        public void setBTLV(String bTLV) {
            this.bTLV = bTLV;
        }

        public int getBasalMetabolism() {
            return basalMetabolism;
        }

        public void setBasalMetabolism(int basalMetabolism) {
            this.basalMetabolism = basalMetabolism;
        }

        public String getBeartLungMovement() {
            return beartLungMovement;
        }

        public void setBeartLungMovement(String beartLungMovement) {
            this.beartLungMovement = beartLungMovement;
        }

        public String getBfoot() {
            return bfoot;
        }

        public void setBfoot(String bfoot) {
            this.bfoot = bfoot;
        }

        public int getBmi() {
            return bmi;
        }

        public void setBmi(int bmi) {
            this.bmi = bmi;
        }

        public String getBodyTargetSelect() {
            return bodyTargetSelect;
        }

        public void setBodyTargetSelect(String bodyTargetSelect) {
            this.bodyTargetSelect = bodyTargetSelect;
        }

        public int getBodyTimesSelect() {
            return bodyTimesSelect;
        }

        public void setBodyTimesSelect(int bodyTimesSelect) {
            this.bodyTimesSelect = bodyTimesSelect;
        }

        public String getCheckId() {
            return checkId;
        }

        public void setCheckId(String checkId) {
            this.checkId = checkId;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDiastoleBloodPressure() {
            return diastoleBloodPressure;
        }

        public void setDiastoleBloodPressure(int diastoleBloodPressure) {
            this.diastoleBloodPressure = diastoleBloodPressure;
        }

        public String getExtensionalMovement() {
            return extensionalMovement;
        }

        public void setExtensionalMovement(String extensionalMovement) {
            this.extensionalMovement = extensionalMovement;
        }

        public int getFat() {
            return fat;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public int getFatController() {
            return fatController;
        }

        public void setFatController(int fatController) {
            this.fatController = fatController;
        }

        public int getHeartbeat() {
            return heartbeat;
        }

        public void setHeartbeat(int heartbeat) {
            this.heartbeat = heartbeat;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHipline() {
            return hipline;
        }

        public void setHipline(int hipline) {
            this.hipline = hipline;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getLeftHand() {
            return leftHand;
        }

        public void setLeftHand(int leftHand) {
            this.leftHand = leftHand;
        }

        public int getLeftHhigh() {
            return leftHhigh;
        }

        public void setLeftHhigh(int leftHhigh) {
            this.leftHhigh = leftHhigh;
        }

        public int getLeftShins() {
            return leftShins;
        }

        public void setLeftShins(int leftShins) {
            this.leftShins = leftShins;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public int getMuscleController() {
            return muscleController;
        }

        public void setMuscleController(int muscleController) {
            this.muscleController = muscleController;
        }

        public int getPbf() {
            return pbf;
        }

        public void setPbf(int pbf) {
            this.pbf = pbf;
        }

        public int getRightHand() {
            return rightHand;
        }

        public void setRightHand(int rightHand) {
            this.rightHand = rightHand;
        }

        public int getRightHhigh() {
            return rightHhigh;
        }

        public void setRightHhigh(int rightHhigh) {
            this.rightHhigh = rightHhigh;
        }

        public int getRightShins() {
            return rightShins;
        }

        public void setRightShins(int rightShins) {
            this.rightShins = rightShins;
        }

        public String getSAnkle() {
            return sAnkle;
        }

        public void setSAnkle(String sAnkle) {
            this.sAnkle = sAnkle;
        }

        public String getSCervicaIV() {
            return sCervicaIV;
        }

        public void setSCervicaIV(String sCervicaIV) {
            this.sCervicaIV = sCervicaIV;
        }

        public String getSFibula() {
            return sFibula;
        }

        public void setSFibula(String sFibula) {
            this.sFibula = sFibula;
        }

        public String getSHead() {
            return sHead;
        }

        public void setSHead(String sHead) {
            this.sHead = sHead;
        }

        public String getSHipJ() {
            return sHipJ;
        }

        public void setSHipJ(String sHipJ) {
            this.sHipJ = sHipJ;
        }

        public String getSKnee() {
            return sKnee;
        }

        public void setSKnee(String sKnee) {
            this.sKnee = sKnee;
        }

        public String getSLumbarV() {
            return sLumbarV;
        }

        public void setSLumbarV(String sLumbarV) {
            this.sLumbarV = sLumbarV;
        }

        public String getSPelvis() {
            return sPelvis;
        }

        public void setSPelvis(String sPelvis) {
            this.sPelvis = sPelvis;
        }

        public String getSScapula() {
            return sScapula;
        }

        public void setSScapula(String sScapula) {
            this.sScapula = sScapula;
        }

        public String getSThoracicV() {
            return sThoracicV;
        }

        public void setSThoracicV(String sThoracicV) {
            this.sThoracicV = sThoracicV;
        }

        public int getShrinkBloodPressure() {
            return shrinkBloodPressure;
        }

        public void setShrinkBloodPressure(int shrinkBloodPressure) {
            this.shrinkBloodPressure = shrinkBloodPressure;
        }

        public int getSkeletalMuscle() {
            return skeletalMuscle;
        }

        public void setSkeletalMuscle(int skeletalMuscle) {
            this.skeletalMuscle = skeletalMuscle;
        }

        public int getSkinFatLand() {
            return skinFatLand;
        }

        public void setSkinFatLand(int skinFatLand) {
            this.skinFatLand = skinFatLand;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getWaist() {
            return waist;
        }

        public void setWaist(int waist) {
            this.waist = waist;
        }

        public int getWater() {
            return water;
        }

        public void setWater(int water) {
            this.water = water;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getWeightNoFat() {
            return weightNoFat;
        }

        public void setWeightNoFat(int weightNoFat) {
            this.weightNoFat = weightNoFat;
        }

        public String getWeightTraining() {
            return weightTraining;
        }

        public void setWeightTraining(String weightTraining) {
            this.weightTraining = weightTraining;
        }

        public int getWhr() {
            return whr;
        }

        public void setWhr(int whr) {
            this.whr = whr;
        }
    }

    public static class VisitRecordBean {
        /**
         * coachVisitRecord : string
         * processId : string
         * sellerVisitRecord : string
         */

        private String coachVisitRecord;
        private String processId;
        private String sellerVisitRecord;

        public String getCoachVisitRecord() {
            return coachVisitRecord;
        }

        public void setCoachVisitRecord(String coachVisitRecord) {
            this.coachVisitRecord = coachVisitRecord;
        }

        public String getProcessId() {
            return processId;
        }

        public void setProcessId(String processId) {
            this.processId = processId;
        }

        public String getSellerVisitRecord() {
            return sellerVisitRecord;
        }

        public void setSellerVisitRecord(String sellerVisitRecord) {
            this.sellerVisitRecord = sellerVisitRecord;
        }
    }


}
