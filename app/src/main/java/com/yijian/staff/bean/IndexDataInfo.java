package com.yijian.staff.bean;


import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/6/25 17:09:12
 */
public class IndexDataInfo {


    /**
     * completePercent : string
     * menuModelList : [{"className":"string","count":0,"icon":"string","level":0,"menuActionList":[{"check":false,"id":0,"name":"string","tag":"string"}],"menuId":0,"path":"string","subMeneModelList":[{}],"title":"string"}]
     * monthRank : 0
     *
     * todayScore : string
     */

    private String completePercent;
    private int monthRank;
    private String todayScore;

    private OthermodelVoBean othermodelVo;

    public OthermodelVoBean getOthermodelVo() {
        return othermodelVo;
    }

    public void setOthermodelVo(OthermodelVoBean othermodelVo) {
        this.othermodelVo = othermodelVo;
    }

    public static class OthermodelVoBean {


        /**
         * faceRecognition : false
         * schedule : false
         * reception : false
         */

        private boolean faceRecognition;
        private boolean schedule;
        private boolean reception;

        public boolean isFaceRecognition() {
            return faceRecognition;
        }

        public void setFaceRecognition(boolean faceRecognition) {
            this.faceRecognition = faceRecognition;
        }

        public boolean isSchedule() {
            return schedule;
        }

        public void setSchedule(boolean schedule) {
            this.schedule = schedule;
        }

        public boolean isReception() {
            return reception;
        }

        public void setReception(boolean reception) {
            this.reception = reception;
        }
    }
        private List<MenuModelListBean> menuModelList;

    public String getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(String completePercent) {
        this.completePercent = completePercent;
    }

    public int getMonthRank() {
        return monthRank;
    }

    public void setMonthRank(int monthRank) {
        this.monthRank = monthRank;
    }

    public String getTodayScore() {
        return todayScore;
    }

    public void setTodayScore(String todayScore) {
        this.todayScore = todayScore;
    }

    public List<MenuModelListBean> getMenuModelList() {
        return menuModelList;
    }

    public void setMenuModelList(List<MenuModelListBean> menuModelList) {
        this.menuModelList = menuModelList;
    }

    public static class MenuModelListBean {
        /**
         * className : string
         * count : 0
         * icon : string
         * level : 0
         * menuActionList : [{"check":false,"id":0,"name":"string","tag":"string"}]
         * menuId : 0
         * path : string
         * subMeneModelList : [{}]
         * title : string
         */
        /**
         *
         * {"menuId":402,"title":"意向会员","icon":"/app/iOS/gn_yixiang.","path":"/test/2","count":0,"className":"YJIntentionMemberViewController","level":2,
         * "menuActionList":[{"id":4,"name":"查看","check":false,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]}
         */

        private String className;
        private int count;
        private String icon;
        private int level;
        private int menuId;
        private String path;
        private String title;
        private List<MenuActionListBean> menuActionList;
        private List<SubMeneModelListBean> subMeneModelList;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MenuActionListBean> getMenuActionList() {
            return menuActionList;
        }

        public void setMenuActionList(List<MenuActionListBean> menuActionList) {
            this.menuActionList = menuActionList;
        }

        public List<SubMeneModelListBean> getSubMeneModelList() {
            return subMeneModelList;
        }

        public void setSubMeneModelList(List<SubMeneModelListBean> subMeneModelList) {
            this.subMeneModelList = subMeneModelList;
        }

        public static class MenuActionListBean {
        }

        public static class SubMeneModelListBean {

            private String className;
            private int count;
            private String icon;
            private int level;
            private int menuId;
            private String path;
            private String menuKey;
            private String title;
            private List<SubMeneModelListBean.MenuActionListBean2> menuActionList;
            private List<SubMeneModelListBean.SubMeneModelListBean2> subMeneModelList;

            public String getMenuKey() {
                return menuKey;
            }

            public void setMenuKey(String menuKey) {
                this.menuKey = menuKey;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getMenuId() {
                return menuId;
            }

            public void setMenuId(int menuId) {
                this.menuId = menuId;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<SubMeneModelListBean.MenuActionListBean2> getMenuActionList() {
                return menuActionList;
            }

            public void setMenuActionList(List<SubMeneModelListBean.MenuActionListBean2> menuActionList) {
                this.menuActionList = menuActionList;
            }

            public List<SubMeneModelListBean.SubMeneModelListBean2> getSubMeneModelList() {
                return subMeneModelList;
            }

            public void setSubMeneModelList(List<SubMeneModelListBean.SubMeneModelListBean2> subMeneModelList) {
                this.subMeneModelList = subMeneModelList;
            }

            public static class MenuActionListBean2 {
            }

            public static class SubMeneModelListBean2 {

            }
        }
    }
}
