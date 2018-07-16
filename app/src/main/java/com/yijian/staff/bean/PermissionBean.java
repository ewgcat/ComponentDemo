package com.yijian.staff.bean;

import java.io.Serializable;
import java.util.List;

public class PermissionBean implements Serializable {


    /**
     * menuId : 442
     * menuKey : app_workbench
     * title : 工作台
     * icon : null
     * path : null
     * count : 0
     * className : null
     * level : 1
     * menuActionList : null
     * subMeneModelList : [{"menuId":445,"menuKey":"app_formal_member","title":"正式会员","icon":"/app/iOS/zhengshihuiyuan.png","path":"/test/1","count":0,"className":"YJMemberInfoViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":446,"menuKey":"app_intention_member","title":"意向会员","icon":"/app/iOS/yixianghuiyuan.png","path":"/test/2","count":0,"className":"YJIntentionMemberViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":447,"menuKey":"app_expire_member","title":"过期会员","icon":"/app/iOS/guoqihuiyuan.png","path":"/test/4","count":0,"className":"YJExpireMemberViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":448,"menuKey":"app_potential_member","title":"潜在会员","icon":"/app/iOS/qianzaihuiyuan.png","path":"/test/3","count":0,"className":"YJPotentialMemberViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":449,"menuKey":"app_formal_student","title":"正式学员","icon":"/app/iOS/zhengshixueyuan.png","path":"/test/1.1","count":0,"className":"YJFullStudentViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":450,"menuKey":"app_intention_student","title":"意向学员","icon":"/app/iOS/yixiangxueyuan.png","path":"/test/2.1","count":0,"className":"YJIntentionStudentViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":451,"menuKey":"app_expire_student","title":"过期学员","icon":"/app/iOS/guoqixueyuan.png","path":"/test/4.1","count":0,"className":"YJExpireStudentViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":455,"menuKey":"app_add_potential","title":"添加潜在","icon":"/app/iOS/tianjiaqianzaihuiyuan.png","path":"/test/7","count":0,"className":"YJAddPotentialViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":456,"menuKey":"app_production_list","title":"卡品报价","icon":null,"path":null,"count":0,"className":null,"level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":457,"menuKey":"app_course_price","title":"课程报价","icon":null,"path":null,"count":0,"className":null,"level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":460,"menuKey":"app_course_appoint_info","title":"排课信息","icon":"/app/iOS/gn_yueke.png","path":"/test/15","count":0,"className":"YJMyScheduleViewController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]},{"menuId":462,"menuKey":"app_storage_course_num","title":"存课信息","icon":"/app/iOS/gn_cunke.png","path":"/test/16","count":0,"className":"YJTrailStoreClassController","level":2,"menuActionList":[{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}],"subMeneModelList":[]}]
     */

    private int menuId;
    private String menuKey;
    private String title;
    private String icon;
    private String path;
    private int count;
    private String className;
    private int level;
    private List<MenuActionListBean> menuActionList;
    private List<SubMeneModelListBean> subMeneModelList;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Object getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public static class MenuActionListBean  implements Serializable{}

    public static class SubMeneModelListBean implements Serializable {
        /**
         * menuId : 445
         * menuKey : app_formal_member
         * title : 正式会员
         * icon : /app/iOS/zhengshihuiyuan.png
         * path : /test/1
         * count : 0
         * className : YJMemberInfoViewController
         * level : 2
         * menuActionList : [{"id":4,"name":"查看","check":true,"tag":"query"},{"id":5,"name":"编辑","check":false,"tag":"edit"}]
         * subMeneModelList : []
         */

        private int menuId;
        private String menuKey;
        private String title;
        private String icon;
        private String path;
        private int count;
        private String className;
        private int level;
        private List<MenuActionListBean> menuActionList;
        private List<?> subMeneModelList;

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public String getMenuKey() {
            return menuKey;
        }

        public void setMenuKey(String menuKey) {
            this.menuKey = menuKey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<MenuActionListBean> getMenuActionList() {
            return menuActionList;
        }

        public void setMenuActionList(List<MenuActionListBean> menuActionList) {
            this.menuActionList = menuActionList;
        }

        public List<?> getSubMeneModelList() {
            return subMeneModelList;
        }

        public void setSubMeneModelList(List<?> subMeneModelList) {
            this.subMeneModelList = subMeneModelList;
        }

        public static class MenuActionListBean implements Serializable {
            /**
             * id : 4
             * name : 查看
             * check : true
             * tag : query
             */

            private int id;
            private String name;
            private boolean check;
            private String tag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }
}
