package com.yijian.staff.mvp.reception.step2.step2Bean;

import java.util.ArrayList;

/**
 * Created by The_P on 2018/3/14.
 */

public class OptionItemData {

    private final OptionDataFactory optionDataFactory;

    public OptionItemData() {
        optionDataFactory = new OptionDataFactory();
    }

    public OptionDataFactory getOptionDataFactory() {
        return optionDataFactory;
    }

    public ArrayList<String> getDecimal() {
        return optionDataFactory.getDecimal();
    }



    public ArrayList<String> getSymbol() {
        return optionDataFactory.getSymbol();
    }

    public ArrayList<String> getHeightInteger() {
        return optionDataFactory.initIntegerRange(120, 300);
    }

    public ArrayList<String> getAgeInteger() {
        return optionDataFactory.initIntegerRange(18, 100);
    }


    //---------------人体成分分析


    /**
     * 体重
     */
    public ArrayList<String> getWeightInteger() {
        return optionDataFactory.initIntegerRange(0, 300);
    }

    /**
     * 骨骼肌
     */
    public ArrayList<String> getSkeletalMuscleInteger() {
        return optionDataFactory.initIntegerRange(0, 300);
    }

    /**
     * 体脂肪
     */
    public ArrayList<String> getFatInteger() {
        return optionDataFactory.initIntegerRange(0, 300);
    }

    /**
     * 身体水分含量
     */
    public ArrayList<String> getWaterInteger() {
        return optionDataFactory.initIntegerRange(0, 300);
    }

    /**
     * 去脂体重
     */
    public ArrayList<String> getHeightNoFatInteger() {
        return optionDataFactory.initIntegerRange(0, 300);
    }

    /**
     * 肌肉控制
     */
    public ArrayList<String> getMuscleControllerInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    /**
     * 脂肪控制
     */
    public ArrayList<String> getFatControllerInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    //------------基础测量数据
    ///** 腰围 */
    public ArrayList<String> getWaistInteger() {
        return optionDataFactory.initIntegerRange(0, 200);
    }

    ///** 皮质厚度 */
    public ArrayList<String> getSkinFatLandInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    ///** 左臂 */
    public ArrayList<String> getLeftHandInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    ///** 右臂 */
    public ArrayList<String> getRightHandInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    ///** 左大腿 */
    public ArrayList<String> getLeftHhighInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    ///** 右大腿 */
    public ArrayList<String> getRightHhighInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    ///** 左小腿 */
    public ArrayList<String> getLeftShinInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    ///** 右小腿 */
    public ArrayList<String> getRightShinsInteger() {
        return optionDataFactory.initIntegerRange(0, 100);
    }

    ///** 血压(收缩压) */
    public ArrayList<String> getShrinkBloodPressureInteger() {
        return optionDataFactory.initIntegerRange(0, 200);
    }

    ///** 血压(舒张压) */
    public ArrayList<String> getDiastoleBloodPressureInteger() {
        return optionDataFactory.initIntegerRange(0, 200);
    }

    ///** 静态心跳率 */
    public ArrayList<String> getHeartbeatInteger() {
        return optionDataFactory.initIntegerRange(0, 200);
    }


    ///*************************** 体态评估-侧面 *******************************/
///** 侧面-头 */
    private ArrayList<String> sHead = new ArrayList<>();

    public ArrayList<String> getsHeadOpt() {
        sHead.clear();
//        1st	头: 中立位 □ 前倾 □ 后仰 □
        sHead.add("中立位");
        sHead.add("前倾");
        sHead.add("后仰");
        return sHead;
    }


    ///** 侧面-颈椎 */
//    @property (nonatomic, copy) NSString *sCervicalV;   //cervical vertebra
    private ArrayList<String> sCervicalV = new ArrayList<>();

    public ArrayList<String> getsCervicalV() {
        sCervicalV.clear();
//        1st	颈椎: 中立位 □	过于前曲 □
        sCervicalV.add("中立位");
        sCervicalV.add("过于前曲");
        return sCervicalV;
    }


    ///** 侧面-肩胛骨 */
//    @property (nonatomic, copy) NSString *sScapula;
    private ArrayList<String> sScapula = new ArrayList<>();

    public ArrayList<String> getsScapula() {
        sScapula.clear();
//        1st	肩胛骨: 中立位 □	圆肩 □
        sScapula.add("中立位");
        sScapula.add("圆肩");
        return sScapula;
    }

    ///** 侧面-胸椎 */
//    @property (nonatomic, copy) NSString *sThoracicV;  //thoracic vertebra
    private ArrayList<String> vertebra = new ArrayList<>();

    public ArrayList<String> getvertebra() {
        vertebra.clear();
//        1st	胸椎: 中立位 □	过于后曲 □
        vertebra.add("中立位");
        vertebra.add("过于后曲");
        return vertebra;
    }


    ///** 侧面-腰椎 */
//    @property (nonatomic, copy) NSString *sLumbarV;   // lumbar vertebra
    private ArrayList<String> sLumbarV = new ArrayList<>();

    public ArrayList<String> getsLumbarV() {
        sLumbarV.clear();
//        1st	腰椎: 中立位 □	过于前曲 □
        sLumbarV.add("中立位");
        sLumbarV.add("过于前曲");
        return sLumbarV;
    }


    ///** 侧面-骨盆 */
//    @property (nonatomic, copy) NSString *sPelvis;
    private ArrayList<String> sPelvis = new ArrayList<>();

    public ArrayList<String> getsPelvis() {
        sPelvis.clear();
//        1st	骨盆: 中立位 □	前倾 □	后倾 □
        sPelvis.add("中立位");
        sPelvis.add("前倾");
        sPelvis.add("后倾");
        return sPelvis;
    }


    ///** 侧面-髋关节 */
//    @property (nonatomic, copy) NSString *sHipJ;   //hip joint
    private ArrayList<String> sHipJ = new ArrayList<>();

    public ArrayList<String> getsHipJ() {
        sHipJ.clear();
//        1st	髋关节: 中立位 □	屈曲 □	伸展 □
        sHipJ.add("中立位");
        sHipJ.add("屈曲");
        sHipJ.add("伸展");
        return sHipJ;
    }


    ///** 侧面-膝关节 */
//    @property (nonatomic, copy) NSString *sKnee;
    private ArrayList<String> sKnee = new ArrayList<>();

    public ArrayList<String> getsKnee() {
        sKnee.clear();
//        1st	膝关节: 中立位 □	超伸 □
        sKnee.add("中立位");
        sKnee.add("超伸");
        return sKnee;
    }


    ///** 侧面-踝关节 */
//    @property (nonatomic, copy) NSString *sAnkle;
    private ArrayList<String> sAnkle = new ArrayList<>();

    public ArrayList<String> getsAnkle() {
        sAnkle.clear();
//        1st	踝关节: 中立位 □ 足背屈 □	跖屈 □
        sAnkle.add("中立位");
        sAnkle.add("足背屈");
        sAnkle.add("跖屈");
        return sAnkle;
    }


    ///** 侧面-腓骨外髁 */
//    @property (nonatomic, copy) NSString *sFibula;
    private ArrayList<String> sFibula = new ArrayList<>();

    public ArrayList<String> getsFibula() {
        sFibula.clear();
//        1st	腓骨外髁: 垂直线前面 □	垂直线后面 □
        sFibula.add("垂直线前面");
        sFibula.add("垂直线后面");
        return sFibula;
    }


    ///*************************** 体态评估-背面 *******************************/
///** 背面-头 */
//@property (nonatomic, copy) NSString *bHead;
    private ArrayList<String> bHead = new ArrayList<>();

    public ArrayList<String> getbHead() {
        bHead.clear();
//        1st	头部: 中立位 □	___ 侧倾 □	___ 扭转 □
        bHead.add("中立位");
        bHead.add("侧倾");
        bHead.add("扭转");
        return bHead;
    }


    ///** 背面-肩部 */
//@property (nonatomic, copy) NSString *bShoulders;
    private ArrayList<String> bShoulders = new ArrayList<>();

    public ArrayList<String> getbShoulders() {
        bShoulders.clear();
//        1st	肩部: 中立位 □	___ 耸肩 □	___ 塌肩 □
        bShoulders.add("中立位");
        bShoulders.add("耸肩");
        bShoulders.add("塌肩");
        return bShoulders;
    }

    ///** 背面-肩胛骨 */
//@property (nonatomic, copy) NSString *bScapula;
    private ArrayList<String> bScapula = new ArrayList<>();

    public ArrayList<String> getbScapula() {
        bScapula.clear();
//        1st	肩胛骨: 中立位 □	肩带前引 □	肩带缩回 □
        bScapula.add("中立位");
        bScapula.add("肩带前引");
        bScapula.add("肩带缩回");
        return bScapula;
    }

    ///** 背面-胸腰椎 */
//@property (nonatomic, copy) NSString *bTLV;  //thoracic lumbar vertebra
    private ArrayList<String> bTLV = new ArrayList<>();

    public ArrayList<String> getbTLV() {
        bTLV.clear();
//        1st	胸腰椎: 成一直线 □	S 形 □ C 形 □
        bTLV.add("成一直线");
        bTLV.add("S 形");
        bTLV.add("C 形");
        return bTLV;
    }


    ///** 背面-骨盆 */
//@property (nonatomic, copy) NSString *bPelvis;
    private ArrayList<String> bPelvis = new ArrayList<>();

    public ArrayList<String> getbPelvis() {
        bPelvis.clear();
//        1st	骨盆: 中立位 □	骨盆侧倾 □
        bPelvis.add("中立位");
        bPelvis.add("骨盆侧倾");
        return bPelvis;
    }

    ///** 背面-髋关节 */
//@property (nonatomic, copy) NSString *bHipJ;   //hip joint
    private ArrayList<String> bHipJ = new ArrayList<>();

    public ArrayList<String> getbHipJ() {
        bHipJ.clear();
//        1st	髋关节: 中立位 □	髋内收 □	髋外展 □	髋外旋 □	髋内旋 □
        bHipJ.add("中立位");
        bHipJ.add("髋内收");
        bHipJ.add("髋外展");
        bHipJ.add("髋外旋");
        bHipJ.add("髋内旋");
        return bHipJ;
    }

    ///** 背面-膝关节 */
//@property (nonatomic, copy) NSString *bKnee;
    private ArrayList<String> bKnee = new ArrayList<>();

    public ArrayList<String> getbKnee() {
        bKnee.clear();
//        1st	膝关节: 中立位 □	膝外翻 □	膝内翻 □
        bKnee.add("中立位");
        bKnee.add("膝外翻");
        bKnee.add("膝内翻");
        return bKnee;
    }

    ///** 背面-足部 */
//@property (nonatomic, copy) NSString *bfoot;
    private ArrayList<String> bfoot = new ArrayList<>();

    public ArrayList<String> getbfoot() {
        bfoot.clear();
//        1st	足部: 中立位 □	扁平足 □
        bfoot.add("中立位");
        bfoot.add("扁平足");
        return bfoot;
    }


//    /*************************** 健康处方 *******************************/
///** 健康目的 */
//    @property (nonatomic, copy) NSString *bodyTargetSelect;
///** 健身次数 */
//    @property (nonatomic, copy) NSString *bodyTimesSelect;

    public ArrayList<String> getBodyTimes() {
        return optionDataFactory.initIntegerRange(1, 7);
    }

///** 心肺运动 */
//    @property (nonatomic, copy) NSString *beartLungMovement;
///** 伸展运动 */
//    @property (nonatomic, copy) NSString *extensionalMovement;
///** 重量运动 */
//    @property (nonatomic, strong) NSString *weightTraining;
//    @end


}