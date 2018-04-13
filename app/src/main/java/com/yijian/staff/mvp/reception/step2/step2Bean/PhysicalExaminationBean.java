package com.yijian.staff.mvp.reception.step2.step2Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by The_P on 2018/3/31.
 */

public class PhysicalExaminationBean implements Parcelable {
    @Override
    public String toString() {
        return "PhysicalExaminationBean{" +
                "height=" + height +
                ", age=" + age +
                ", weight=" + weight +
                ", skeletalMuscle=" + skeletalMuscle +
                ", fat=" + fat +
                ", water=" + water +
                ", weightNoFat=" + weightNoFat +
                ", bmi=" + bmi +
                ", pbf=" + pbf +
                ", whr=" + whr +
                ", basalMetabolism=" + basalMetabolism +
                ", muscleController=" + muscleController +
                ", fatController=" + fatController +
                ", waist=" + waist +
                ", hipline=" + hipline +
                ", skinFatLand=" + skinFatLand +
                ", leftHand=" + leftHand +
                ", rightHand=" + rightHand +
                ", leftHhigh=" + leftHhigh +
                ", rightHhigh=" + rightHhigh +
                ", leftShins=" + leftShins +
                ", rightShins=" + rightShins +
                ", shrinkBloodPressure=" + shrinkBloodPressure +
                ", diastoleBloodPressure=" + diastoleBloodPressure +
                ", heartbeat=" + heartbeat +
                ", sHead='" + sHead + '\'' +
                ", sCervicalV='" + sCervicaIV + '\'' +
                ", sScapula='" + sScapula + '\'' +
                ", sThoracicV='" + sThoracicV + '\'' +
                ", sLumbarV='" + sLumbarV + '\'' +
                ", sPelvis='" + sPelvis + '\'' +
                ", sHipJ='" + sHipJ + '\'' +
                ", sKnee='" + sKnee + '\'' +
                ", sAnkle='" + sAnkle + '\'' +
                ", sFibula='" + sFibula + '\'' +
                ", bHead='" + bHead + '\'' +
                ", bShoulders='" + bShoulders + '\'' +
                ", bScapula='" + bScapula + '\'' +
                ", bTLV='" + bTLV + '\'' +
                ", bPelvis='" + bPelvis + '\'' +
                ", bHipJ='" + bHipJ + '\'' +
                ", bKnee='" + bKnee + '\'' +
                ", bfoot='" + bfoot + '\'' +
                ", bodyTargetSelect='" + bodyTargetSelect + '\'' +
                ", bodyTimesSelect=" + bodyTimesSelect +
                ", beartLungMovement='" + beartLungMovement + '\'' +
                ", extensionalMovement='" + extensionalMovement + '\'' +
                ", weightTraining='" + weightTraining + '\'' +
                '}';
    }
//    /** 身高 */
//    @property (nonatomic, copy) NSString *height;

    BigDecimal height;
///** 年龄 */
//    @property (nonatomic, copy) NSString *age;

    Integer age;
    //
//
///*************************** 人体成分分析 *******************************/
///** 体重 */
//    @property (nonatomic, copy) NSString *weight;
    BigDecimal weight;

    ///** 骨骼肌 */
    BigDecimal skeletalMuscle;


    ///** 体脂肪 */
//    @property (nonatomic, copy) NSString *fat;
    BigDecimal fat;

    ///** 身体水分含量 */
//    @property (nonatomic, copy) NSString *water;
    BigDecimal water;

    ///** 去脂体重 */
//    @property (nonatomic, copy) NSString *weightNoFat;
    BigDecimal weightNoFat;

    ///** 身体质量指数 */
//    @property (nonatomic, copy) NSString *bmi;
    BigDecimal bmi;

    ///** 体脂百分比 */
//    @property (nonatomic, copy) NSString *pbf;
    BigDecimal pbf;

    ///** 腰臀比 */
//    @property (nonatomic, copy) NSString *whr;
    BigDecimal whr;

    ///** 基础代谢 */
//    @property (nonatomic, copy) NSString *basalMetabolism;
    BigDecimal basalMetabolism;

    ///** 肌肉控制 */
//    @property (nonatomic, copy) NSString *muscleController;
    BigDecimal muscleController;


    ///** 脂肪控制 */
//    @property (nonatomic, copy) NSString *fatController;
    BigDecimal fatController;

    //
///*************************** 基础测量数据 *******************************/
///** 腰围 */
//    @property (nonatomic, copy) NSString *waist;
    BigDecimal waist;

    ///** 臀围 */
//    @property (nonatomic, copy) NSString *hipline;
    BigDecimal hipline;


    ///** 皮质厚度 */
//    @property (nonatomic, copy) NSString *skinFatLand;
    BigDecimal skinFatLand;

    ///** 左臂 */
//    @property (nonatomic, copy) NSString *leftHand;
    BigDecimal leftHand;

    ///** 右臂 */
//    @property (nonatomic, copy) NSString *rightHand;
    BigDecimal rightHand;

    ///** 左大腿 */
//    @property (nonatomic, copy) NSString *leftHhigh;
    BigDecimal leftHhigh;

    ///** 右大腿 */
//    @property (nonatomic, copy) NSString *rightHhigh;
    BigDecimal rightHhigh;

    ///** 左小腿 */
//    @property (nonatomic, copy) NSString *leftShins;
    BigDecimal leftShins;

    ///** 右小腿 */
//    @property (nonatomic, copy) NSString *rightShins;
    BigDecimal rightShins;


    ///** 血压(收缩) */
//    @property (nonatomic, copy) NSString *shrinkBloodPressure ;
    BigDecimal shrinkBloodPressure;

    ///** 血压(舒张) */
//    @property (nonatomic, copy) NSString *diastoleBloodPressure;
    BigDecimal diastoleBloodPressure;


    ///** 静态心跳率 */
//    @property (nonatomic, copy) NSString *heartbeat;
    Integer heartbeat;

    //
//
///*************************** 体态评估-侧面 *******************************/
//    sCervicalV (string, optional): 侧面-颈椎 ,

///** 侧面-头 */
//    @property (nonatomic, copy) NSString *sHead;
    String sHead;


    ///** 侧面-颈椎 */
//    @property (nonatomic, copy) NSString *sSpine;
    String sCervicaIV;

    ///** 侧面-肩胛骨 */
//    @property (nonatomic, copy) NSString *sScapula;
    String sScapula;

    ///** 侧面-胸椎 */
//    @property (nonatomic, copy) NSString *sThoracicV;  //thoracic vertebra
    String sThoracicV;

    ///** 侧面-腰椎 */
//    @property (nonatomic, copy) NSString *sLumbarV;   // lumbar vertebra
    String sLumbarV;

    ///** 侧面-骨盆 */
//    @property (nonatomic, copy) NSString *sPelvis;
    String sPelvis;

    ///** 侧面-髋关节 */
//    @property (nonatomic, copy) NSString *sHipJ;   //hip joint
    String sHipJ;

    ///** 侧面-膝关节 */
//    @property (nonatomic, copy) NSString *sKnee;
    String sKnee;

    ///** 侧面-踝关节 */
//    @property (nonatomic, copy) NSString *sAnkle;
    String sAnkle;

    ///** 侧面-腓骨 */
//    @property (nonatomic, copy) NSString *sFibula;
    String sFibula;
    //
//
//
//
///*************************** 体态评估-背面 *******************************/
///** 背面-头 */
    String bHead;

    ///** 背面-肩部 */
//    @property (nonatomic, copy) NSString *bShoulders;
    String bShoulders;

    ///** 背面-肩胛骨 */
//    @property (nonatomic, copy) NSString *bScapula;
    String bScapula;

    ///** 背面-胸腰椎 */
//    @property (nonatomic, copy) NSString *bTLV;  //thoracic lumbar vertebra
    String bTLV;

    ///** 背面-骨盘 */
//    @property (nonatomic, copy) NSString *bPelvis;
    String bPelvis;

    ///** 背面-髋关节 */
//    @property (nonatomic, copy) NSString *bHipJ;   //hip joint
    String bHipJ;

    ///** 背面-膝关节 */
//    @property (nonatomic, copy) NSString *bKnee;
    String bKnee;

    ///** 背面-足部 */
//    @property (nonatomic, copy) NSString *bfoot;
    String bfoot;
    //
//
///*************************** 健康处方 *******************************/
///** 健康目的 */
//    @property (nonatomic, copy) NSString *bodyTargetSelect;
    String bodyTargetSelect;

    ///** 健身次数 */
//    @property (nonatomic, copy) NSString *bodyTimesSelect;
    Integer bodyTimesSelect;

    ///** 心肺运动 */
//    @property (nonatomic, copy) NSString *beartLungMovement;
    String beartLungMovement;

    ///** 伸展运动 */
//    @property (nonatomic, copy) NSString *extensionalMovement;
    String extensionalMovement;

    ///** 重量运动 */
//    @property (nonatomic, strong) NSString *weightTraining;
    String weightTraining;

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSkeletalMuscle() {
        return skeletalMuscle;
    }

    public void setSkeletalMuscle(BigDecimal skeletalMuscle) {
        this.skeletalMuscle = skeletalMuscle;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public BigDecimal getWater() {
        return water;
    }

    public void setWater(BigDecimal water) {
        this.water = water;
    }

    public BigDecimal getWeightNoFat() {
        return weightNoFat;
    }

    public void setWeightNoFat(BigDecimal weightNoFat) {
        this.weightNoFat = weightNoFat;
    }

    public BigDecimal getBmi() {
        return bmi;
    }

    public void setBmi(BigDecimal bmi) {
        this.bmi = bmi;
    }

    public BigDecimal getPbf() {
        return pbf;
    }

    public void setPbf(BigDecimal pbf) {
        this.pbf = pbf;
    }

    public BigDecimal getWhr() {
        return whr;
    }

    public void setWhr(BigDecimal whr) {
        this.whr = whr;
    }

    public BigDecimal getBasalMetabolism() {
        return basalMetabolism;
    }

    public void setBasalMetabolism(BigDecimal basalMetabolism) {
        this.basalMetabolism = basalMetabolism;
    }

    public BigDecimal getMuscleController() {
        return muscleController;
    }

    public void setMuscleController(BigDecimal muscleController) {
        this.muscleController = muscleController;
    }

    public BigDecimal getFatController() {
        return fatController;
    }

    public void setFatController(BigDecimal fatController) {
        this.fatController = fatController;
    }

    public BigDecimal getWaist() {
        return waist;
    }

    public void setWaist(BigDecimal waist) {
        this.waist = waist;
    }

    public BigDecimal getHipline() {
        return hipline;
    }

    public void setHipline(BigDecimal hipline) {
        this.hipline = hipline;
    }

    public BigDecimal getSkinFatLand() {
        return skinFatLand;
    }

    public void setSkinFatLand(BigDecimal skinFatLand) {
        this.skinFatLand = skinFatLand;
    }

    public BigDecimal getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(BigDecimal leftHand) {
        this.leftHand = leftHand;
    }

    public BigDecimal getRightHand() {
        return rightHand;
    }

    public void setRightHand(BigDecimal rightHand) {
        this.rightHand = rightHand;
    }

    public BigDecimal getLeftHhigh() {
        return leftHhigh;
    }

    public void setLeftHhigh(BigDecimal leftHhigh) {
        this.leftHhigh = leftHhigh;
    }

    public BigDecimal getRightHhigh() {
        return rightHhigh;
    }

    public void setRightHhigh(BigDecimal rightHhigh) {
        this.rightHhigh = rightHhigh;
    }

    public BigDecimal getLeftShins() {
        return leftShins;
    }

    public void setLeftShins(BigDecimal leftShins) {
        this.leftShins = leftShins;
    }

    public BigDecimal getRightShins() {
        return rightShins;
    }

    public void setRightShins(BigDecimal rightShins) {
        this.rightShins = rightShins;
    }

    public BigDecimal getDiastoleBloodPressure() {
        return diastoleBloodPressure;
    }

    public void setDiastoleBloodPressure(BigDecimal diastoleBloodPressure) {
        this.diastoleBloodPressure = diastoleBloodPressure;
    }

    public BigDecimal getShrinkBloodPressure() {
        return shrinkBloodPressure;
    }

    public void setShrinkBloodPressure(BigDecimal shrinkBloodPressure) {
        this.shrinkBloodPressure = shrinkBloodPressure;
    }

    public Integer getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getsHead() {
        return sHead;
    }

    public void setsHead(String sHead) {
        this.sHead = sHead;
    }

    public String getsCervicalV() {
        return sCervicaIV;
    }

    public void setsCervicalV(String sCervicalV) {
        this.sCervicaIV = sCervicalV;
    }

    public String getsScapula() {
        return sScapula;
    }

    public void setsScapula(String sScapula) {
        this.sScapula = sScapula;
    }

    public String getsThoracicV() {
        return sThoracicV;
    }

    public void setsThoracicV(String sThoracicV) {
        this.sThoracicV = sThoracicV;
    }

    public String getsLumbarV() {
        return sLumbarV;
    }

    public void setsLumbarV(String sLumbarV) {
        this.sLumbarV = sLumbarV;
    }

    public String getsPelvis() {
        return sPelvis;
    }

    public void setsPelvis(String sPelvis) {
        this.sPelvis = sPelvis;
    }

    public String getsHipJ() {
        return sHipJ;
    }

    public void setsHipJ(String sHipJ) {
        this.sHipJ = sHipJ;
    }

    public String getsKnee() {
        return sKnee;
    }

    public void setsKnee(String sKnee) {
        this.sKnee = sKnee;
    }

    public String getsAnkle() {
        return sAnkle;
    }

    public void setsAnkle(String sAnkle) {
        this.sAnkle = sAnkle;
    }

    public String getsFibula() {
        return sFibula;
    }

    public void setsFibula(String sFibula) {
        this.sFibula = sFibula;
    }

    public String getbHead() {
        return bHead;
    }

    public void setbHead(String bHead) {
        this.bHead = bHead;
    }

    public String getbShoulders() {
        return bShoulders;
    }

    public void setbShoulders(String bShoulders) {
        this.bShoulders = bShoulders;
    }

    public String getbScapula() {
        return bScapula;
    }

    public void setbScapula(String bScapula) {
        this.bScapula = bScapula;
    }

    public String getbTLV() {
        return bTLV;
    }

    public void setbTLV(String bTLV) {
        this.bTLV = bTLV;
    }

    public String getbPelvis() {
        return bPelvis;
    }

    public void setbPelvis(String bPelvis) {
        this.bPelvis = bPelvis;
    }

    public String getbHipJ() {
        return bHipJ;
    }

    public void setbHipJ(String bHipJ) {
        this.bHipJ = bHipJ;
    }

    public String getbKnee() {
        return bKnee;
    }

    public void setbKnee(String bKnee) {
        this.bKnee = bKnee;
    }

    public String getBfoot() {
        return bfoot;
    }

    public void setBfoot(String bfoot) {
        this.bfoot = bfoot;
    }

    public String getBodyTargetSelect() {
        return bodyTargetSelect;
    }

    public void setBodyTargetSelect(String bodyTargetSelect) {
        this.bodyTargetSelect = bodyTargetSelect;
    }

    public Integer getBodyTimesSelect() {
        return bodyTimesSelect;
    }

    public void setBodyTimesSelect(Integer bodyTimesSelect) {
        this.bodyTimesSelect = bodyTimesSelect;
    }

    public String getBeartLungMovement() {
        return beartLungMovement;
    }

    public void setBeartLungMovement(String beartLungMovement) {
        this.beartLungMovement = beartLungMovement;
    }

    public String getExtensionalMovement() {
        return extensionalMovement;
    }

    public void setExtensionalMovement(String extensionalMovement) {
        this.extensionalMovement = extensionalMovement;
    }

    public String getWeightTraining() {
        return weightTraining;
    }

    public void setWeightTraining(String weightTraining) {
        this.weightTraining = weightTraining;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.height);
        dest.writeValue(this.age);
        dest.writeSerializable(this.weight);
        dest.writeSerializable(this.skeletalMuscle);
        dest.writeSerializable(this.fat);
        dest.writeSerializable(this.water);
        dest.writeSerializable(this.weightNoFat);
        dest.writeSerializable(this.bmi);
        dest.writeSerializable(this.pbf);
        dest.writeSerializable(this.whr);
        dest.writeSerializable(this.basalMetabolism);
        dest.writeSerializable(this.muscleController);
        dest.writeSerializable(this.fatController);
        dest.writeSerializable(this.waist);
        dest.writeSerializable(this.hipline);
        dest.writeSerializable(this.skinFatLand);
        dest.writeSerializable(this.leftHand);
        dest.writeSerializable(this.rightHand);
        dest.writeSerializable(this.leftHhigh);
        dest.writeSerializable(this.rightHhigh);
        dest.writeSerializable(this.leftShins);
        dest.writeSerializable(this.rightShins);
        dest.writeSerializable(this.shrinkBloodPressure);
        dest.writeSerializable(this.diastoleBloodPressure);
        dest.writeValue(this.heartbeat);
        dest.writeString(this.sHead);
        dest.writeString(this.sCervicaIV);
        dest.writeString(this.sScapula);
        dest.writeString(this.sThoracicV);
        dest.writeString(this.sLumbarV);
        dest.writeString(this.sPelvis);
        dest.writeString(this.sHipJ);
        dest.writeString(this.sKnee);
        dest.writeString(this.sAnkle);
        dest.writeString(this.sFibula);
        dest.writeString(this.bHead);
        dest.writeString(this.bShoulders);
        dest.writeString(this.bScapula);
        dest.writeString(this.bTLV);
        dest.writeString(this.bPelvis);
        dest.writeString(this.bHipJ);
        dest.writeString(this.bKnee);
        dest.writeString(this.bfoot);
        dest.writeString(this.bodyTargetSelect);
        dest.writeValue(this.bodyTimesSelect);
        dest.writeString(this.beartLungMovement);
        dest.writeString(this.extensionalMovement);
        dest.writeString(this.weightTraining);
    }

    public PhysicalExaminationBean() {
    }

    protected PhysicalExaminationBean(Parcel in) {
        this.height = (BigDecimal) in.readSerializable();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weight = (BigDecimal) in.readSerializable();
        this.skeletalMuscle = (BigDecimal) in.readSerializable();
        this.fat = (BigDecimal) in.readSerializable();
        this.water = (BigDecimal) in.readSerializable();
        this.weightNoFat = (BigDecimal) in.readSerializable();
        this.bmi = (BigDecimal) in.readSerializable();
        this.pbf = (BigDecimal) in.readSerializable();
        this.whr = (BigDecimal) in.readSerializable();
        this.basalMetabolism = (BigDecimal) in.readSerializable();
        this.muscleController = (BigDecimal) in.readSerializable();
        this.fatController = (BigDecimal) in.readSerializable();
        this.waist = (BigDecimal) in.readSerializable();
        this.hipline = (BigDecimal) in.readSerializable();
        this.skinFatLand = (BigDecimal) in.readSerializable();
        this.leftHand = (BigDecimal) in.readSerializable();
        this.rightHand = (BigDecimal) in.readSerializable();
        this.leftHhigh = (BigDecimal) in.readSerializable();
        this.rightHhigh = (BigDecimal) in.readSerializable();
        this.leftShins = (BigDecimal) in.readSerializable();
        this.rightShins = (BigDecimal) in.readSerializable();
        this.shrinkBloodPressure = (BigDecimal) in.readSerializable();
        this.diastoleBloodPressure = (BigDecimal) in.readSerializable();
        this.heartbeat = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sHead = in.readString();
        this.sCervicaIV = in.readString();
        this.sScapula = in.readString();
        this.sThoracicV = in.readString();
        this.sLumbarV = in.readString();
        this.sPelvis = in.readString();
        this.sHipJ = in.readString();
        this.sKnee = in.readString();
        this.sAnkle = in.readString();
        this.sFibula = in.readString();
        this.bHead = in.readString();
        this.bShoulders = in.readString();
        this.bScapula = in.readString();
        this.bTLV = in.readString();
        this.bPelvis = in.readString();
        this.bHipJ = in.readString();
        this.bKnee = in.readString();
        this.bfoot = in.readString();
        this.bodyTargetSelect = in.readString();
        this.bodyTimesSelect = (Integer) in.readValue(Integer.class.getClassLoader());
        this.beartLungMovement = in.readString();
        this.extensionalMovement = in.readString();
        this.weightTraining = in.readString();
    }

    public static final Parcelable.Creator<PhysicalExaminationBean> CREATOR = new Parcelable.Creator<PhysicalExaminationBean>() {
        @Override
        public PhysicalExaminationBean createFromParcel(Parcel source) {
            return new PhysicalExaminationBean(source);
        }

        @Override
        public PhysicalExaminationBean[] newArray(int size) {
            return new PhysicalExaminationBean[size];
        }
    };
}
