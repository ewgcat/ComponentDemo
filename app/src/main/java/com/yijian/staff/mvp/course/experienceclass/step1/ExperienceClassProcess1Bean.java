package com.yijian.staff.mvp.course.experienceclass.step1;

import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/13 14:40:40
 */
public class ExperienceClassProcess1Bean {


    /**
     * coachName : string
     * courseCurrent : 0
     * courseNum : 0
     * memberId : string
     * memberName : string
     * startTime:long
     * processId : string
     * templateList : [{"aerobicsList":[{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","sort":0,"templateContextId":"string","time":0,"weight":0}],"noInstrumentList":[{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","sort":0,"templateContextId":"string","time":0,"weight":0}],"powerList":[{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","sort":0,"templateContextId":"string","time":0,"weight":0}],"templateId":"string","templateName":"string"}]
     */
    /**
     * 邀约节点返回对象 {
     * coachName (string, optional): 教练名字 ,
     * courseCurrent (integer, optional): 上课次数 ,
     * courseNum (integer, optional): 课程数量 ,
     * memberId (string, optional): 会员id(受邀人) ,
     * memberName (string, optional): 会员名字(受邀人) ,
     * processId (string, optional): 体验课流程id，假如当前教练没有邀约过则无 ,
     * templateList (Array[体验课备课模板返回对象], optional): 课程模板
     * }
     * 体验课备课模板返回对象 {
     * aerobicsList (Array[体验课备课模板内容返回对象], optional): 有氧器械 ,
     * noInstrumentList (Array[体验课备课模板内容返回对象], optional): 无氧器械 ,
     * powerList (Array[体验课备课模板内容返回对象], optional): 力量器械 ,
     * templateId (string, optional): 体验课备课模板id ,
     * templateName (string, optional): 模板名称
     * }
     * 体验课备课模板内容返回对象 {
     * contextType (integer, optional): 体验课内容类型：1:无器械,2:有氧器械,3:力量器械 ,
     * grade (string, optional): 程式/级数(类型为2) ,
     * groupNum (integer, optional): 训练组数(类型为1,3) ,
     * groupTime (integer, optional): 每组次数(类型为1,3) ,
     * name (string, optional): 名称 ,
     * sort (integer, optional): 每种类型的排序 ,
     * templateContextId (string, optional): 体验课备课模板内容id ,
     * time (integer, optional): 时间(s)(类型为2) ,
     * weight (integer, optional): 重量(kg)(类型为3)
     * }
     */
    private String coachName;
    private int courseCurrent;
    private int courseNum;
    private String memberId;
    private String memberName;
    private String processId;
    private String remark;
    private Long startTime;
    private List<TemplateListBean> templateList;


    public String getRemark() {
        return remark;
    }

    public Long getStartTime() {
        return startTime;
    }

    public ExperienceClassProcess1Bean(JSONObject jsonObject) {
        try {

            this.coachName = jsonObject.getString("coachName");
            this.memberName = jsonObject.getString("memberName");
            this.memberId = jsonObject.getString("memberId");
            this.processId = jsonObject.getString("processId");
            this.remark = JsonUtil.getString(jsonObject, "remark");
            if (jsonObject.has("startTime")) {
                this.startTime = jsonObject.getLong("startTime");
            }
            this.courseNum = jsonObject.getInt("courseNum");
            this.courseCurrent = jsonObject.getInt("courseCurrent");

            if (jsonObject.has("templateList")) {
                this.templateList = com.alibaba.fastjson.JSONObject.parseArray(jsonObject.getJSONArray("templateList").toString(), ExperienceClassProcess1Bean.TemplateListBean.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getCourseCurrent() {
        return courseCurrent;
    }

    public void setCourseCurrent(int courseCurrent) {
        this.courseCurrent = courseCurrent;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public List<TemplateListBean> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateListBean> templateList) {
        this.templateList = templateList;
    }

    public static class TemplateListBean {
        /**
         * aerobicsList : [{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","sort":0,"templateContextId":"string","time":0,"weight":0}]
         * noInstrumentList : [{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","sort":0,"templateContextId":"string","time":0,"weight":0}]
         * powerList : [{"contextType":0,"grade":"string","groupNum":0,"groupTime":0,"name":"string","sort":0,"templateContextId":"string","time":0,"weight":0}]
         * templateId : string
         * templateName : string
         */

        private String templateId;
        private String templateName;
        private List<AerobicsListBean> aerobicsList;
        private List<NoInstrumentListBean> noInstrumentList;
        private List<PowerListBean> powerList;

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
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
             * sort : 0
             * templateContextId : string
             * time : 0
             * weight : 0
             */

            private int contextType;
            private String grade;
            private int groupNum;
            private int groupTime;
            private String name;
            private int sort;
            private String templateContextId;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTemplateContextId() {
                return templateContextId;
            }

            public void setTemplateContextId(String templateContextId) {
                this.templateContextId = templateContextId;
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
             * sort : 0
             * templateContextId : string
             * time : 0
             * weight : 0
             */

            private int contextType;
            private String grade;
            private int groupNum;
            private int groupTime;
            private String name;
            private int sort;
            private String templateContextId;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTemplateContextId() {
                return templateContextId;
            }

            public void setTemplateContextId(String templateContextId) {
                this.templateContextId = templateContextId;
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
             * sort : 0
             * templateContextId : string
             * time : 0
             * weight : 0
             */

            private int contextType;
            private String grade;
            private int groupNum;
            private int groupTime;
            private String name;
            private int sort;
            private String templateContextId;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTemplateContextId() {
                return templateContextId;
            }

            public void setTemplateContextId(String templateContextId) {
                this.templateContextId = templateContextId;
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
}
