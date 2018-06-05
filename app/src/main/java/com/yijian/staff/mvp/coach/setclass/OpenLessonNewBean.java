package com.yijian.staff.mvp.coach.setclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/30.
 * 上课的动作Bean
 */

public class OpenLessonNewBean {

    /**
     * 难易程度
     */
    private String degree;

    private List<SubOpenLessonNewBean> subOpenLessonNewBeans = new ArrayList<SubOpenLessonNewBean>();


    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<SubOpenLessonNewBean> getSubOpenLessonNewBeans() {
        return subOpenLessonNewBeans;
    }

    public void setSubOpenLessonNewBeans(List<SubOpenLessonNewBean> subOpenLessonNewBeans) {
        this.subOpenLessonNewBeans = subOpenLessonNewBeans;
    }

    /**
     * 个动作的数据装载对象
     */
    static class SubOpenLessonNewBean {

        /**
         * 是否可以启动秒表计时的标志位
         */
        private boolean isStartClolck = false;

        /**
         * 动作内容的Map容器
         */
        private Map<String, String> actionMap;
        /**
         * 个动作的操作项目Map容器
         */
        private Map<String, String> actionOprationMap;

        public boolean isStartClolck() {
            return isStartClolck;
        }

        public void setStartClolck(boolean startClolck) {
            isStartClolck = startClolck;
        }

        public Map<String, String> getActionMap() {
            return actionMap;
        }

        public void setActionMap(Map<String, String> actionMap) {
            this.actionMap = actionMap;
        }

        public Map<String, String> getActionOprationMap() {
            return actionOprationMap;
        }

        public void setActionOprationMap(Map<String, String> actionOprationMap) {
            this.actionOprationMap = actionOprationMap;
        }
    }

}
