package com.yijian.staff.mvp.coach.experienceclass.step4;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/13 17:13:01
 */
public class ExperienceClassProcess4Bean {


    /**
     *
     体验课上课记录表返回对象 {
     aerobicsList (Array[体验课上课记录表内容返回对象], optional): 有氧器械 ,
     noInstrumentList (Array[体验课上课记录表内容返回对象], optional): 无氧器械 ,
     num (integer, optional): 第几个记录表 ,
     powerList (Array[体验课上课记录表内容返回对象], optional): 力量器械 ,
     recordId (string, optional): 记录表id ,
     templateName (string, optional): 模板名字
     }
     体验课上课记录表内容返回对象 {
     contextType (integer, optional): 体验课内容类型：1:无器械,2:有氧器械,3:力量器械 ,
     grade (string, optional),
     groupNum (integer, optional),
     groupTime (integer, optional),
     name (string, optional): 名称 ,
     recordContextId (string, optional): 内容id ,
     sort (integer, optional): 每种类型的排序 ,
     time (integer, optional),
     weight (integer, optional)
     }

     */

    /**
     * aerobicsList : [{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","recordContextId":"string","sort":0,"time":0,"weight":0}]
     * noInstrumentList : [{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","recordContextId":"string","sort":0,"time":0,"weight":0}]
     * num : 0
     * powerList : [{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","recordContextId":"string","sort":0,"time":0,"weight":0}]
     * recordId : string
     * templateName : string
     */

    private int num;
    private String recordId;
    private String templateName;
    private List<AerobicsListBean> aerobicsList;
    private List<NoInstrumentListBean> noInstrumentList;
    private List<PowerListBean> powerList;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<AerobicsListBean> getAerobicsList() {
        return aerobicsList;
    }

    public void setAerobicsList(List<AerobicsListBean> aerobicsList) {
        this.aerobicsList = aerobicsList;
    }

    public List<NoInstrumentListBean> getNoInstrumentList() {
        return noInstrumentList;
    }

    public void setNoInstrumentList(List<NoInstrumentListBean> noInstrumentList) {
        this.noInstrumentList = noInstrumentList;
    }

    public List<PowerListBean> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<PowerListBean> powerList) {
        this.powerList = powerList;
    }

    public static class AerobicsListBean {
        /**
         * contextType : 0
         * grade : string
         * groupNum : 0
         * groupTime : 0
         * name : string
         * recordContextId : string
         * sort : 0
         * time : 0
         * weight : 0
         */

        private int contextType;
        private String grade;
        private int groupNum;
        private int groupTime;
        private String name;
        private String recordContextId;
        private int sort;
        private int time;
        private int weight;

        public int getContextType() {
            return contextType;
        }

        public void setContextType(int contextType) {
            this.contextType = contextType;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(int groupNum) {
            this.groupNum = groupNum;
        }

        public int getGroupTime() {
            return groupTime;
        }

        public void setGroupTime(int groupTime) {
            this.groupTime = groupTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecordContextId() {
            return recordContextId;
        }

        public void setRecordContextId(String recordContextId) {
            this.recordContextId = recordContextId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    public static class NoInstrumentListBean {
        /**
         * contextType : 0
         * grade : string
         * groupNum : 0
         * groupTime : 0
         * name : string
         * recordContextId : string
         * sort : 0
         * time : 0
         * weight : 0
         */

        private int contextType;
        private String grade;
        private int groupNum;
        private int groupTime;
        private String name;
        private String recordContextId;
        private int sort;
        private int time;
        private int weight;

        public int getContextType() {
            return contextType;
        }

        public void setContextType(int contextType) {
            this.contextType = contextType;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(int groupNum) {
            this.groupNum = groupNum;
        }

        public int getGroupTime() {
            return groupTime;
        }

        public void setGroupTime(int groupTime) {
            this.groupTime = groupTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecordContextId() {
            return recordContextId;
        }

        public void setRecordContextId(String recordContextId) {
            this.recordContextId = recordContextId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    public static class PowerListBean {
        /**
         * contextType : 0
         * grade : string
         * groupNum : 0
         * groupTime : 0
         * name : string
         * recordContextId : string
         * sort : 0
         * time : 0
         * weight : 0
         */

        private int contextType;
        private String grade;
        private int groupNum;
        private int groupTime;
        private String name;
        private String recordContextId;
        private int sort;
        private int time;
        private int weight;

        public int getContextType() {
            return contextType;
        }

        public void setContextType(int contextType) {
            this.contextType = contextType;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(int groupNum) {
            this.groupNum = groupNum;
        }

        public int getGroupTime() {
            return groupTime;
        }

        public void setGroupTime(int groupTime) {
            this.groupTime = groupTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecordContextId() {
            return recordContextId;
        }

        public void setRecordContextId(String recordContextId) {
            this.recordContextId = recordContextId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
