package com.yijian.clubmodule.bean;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/7 19:24:51
 */
public class ClubDetailBean {


    /**
     * brandId : 40a8414599764733b4c5c7cbcf68286b
     * merchantId : 333
     * logoPath : /trademarkSetting/a85f09b7998f4523af713da137c0c533.png
     * name : 原力健身俱乐部
     * des : 原力健身唤醒生命原动力，东莞唯一女子训
     练专区，唯一360度景观健身俱乐部。东莞最顶
     级的健身俱乐部。
     * isDelete : 0
     * pics : [{"picId":"1d85d6b3072f4fb8a6a26ab9fc56ba32","path":"/trademarkAlbum/aa545fda94d645d38777b752cb1395f9.png","fileName":"修改密码.png","createTime":1528340273000,"createBy":"1","updateTime":1528340273000,"updateBy":"1","isDelete":0},{"picId":"4ef145e90db54db9a53452f3be123504","path":"/trademarkAlbum/0dcd9cc0296746458169fe729b16f013.png","fileName":"logo320-320.png","createTime":1528340273000,"createBy":"1","updateTime":1528340273000,"updateBy":"1","isDelete":0},{"picId":"515e88a7d2a54893900cd1fc273af830","path":"/trademarkAlbum/0dc9ccd578154122833800e8cb240105.png","fileName":"易健-B_登陆界面.png","createTime":1528340273000,"createBy":"1","updateTime":1528340273000,"updateBy":"1","isDelete":0},{"picId":"8f8b60b49d0a48478f755e684f5eebb2","path":"/trademarkAlbum/8e48932d281a42c68a5290d2f28e7832.png","fileName":"修改密码.png","createTime":1528340273000,"createBy":"1","updateTime":1528340273000,"updateBy":"1","isDelete":0},{"picId":"a33831f0c5a749fc9358de62340090e7","path":"/trademarkAlbum/0ad541eef1584e3dac3200867f7cf7f0.png","fileName":"易健-B_登陆界面.png","createTime":1528340273000,"createBy":"1","updateTime":1528340273000,"updateBy":"1","isDelete":0},{"picId":"bf53bff0e6d34c35b547b15f52e78e24","path":"/trademarkAlbum/cc8e4db09fb94f13995116e739be3093.png","fileName":"易健-B_登陆界面.png","createTime":1528340273000,"createBy":"1","updateTime":1528340273000,"updateBy":"1","isDelete":0}]
     */

    private String brandId;
    private String merchantId;
    private String logoPath;
    private String name;
    private String des;
    private int isDelete;
    private List<PicsBean> pics;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public List<PicsBean> getPics() {
        return pics;
    }

    public void setPics(List<PicsBean> pics) {
        this.pics = pics;
    }

    public static class PicsBean {
        /**
         * picId : 1d85d6b3072f4fb8a6a26ab9fc56ba32
         * path : /trademarkAlbum/aa545fda94d645d38777b752cb1395f9.png
         * fileName : 修改密码.png
         * createTime : 1528340273000
         * createBy : 1
         * updateTime : 1528340273000
         * updateBy : 1
         * isDelete : 0
         */

        private String picId;
        private String path;
        private String fileName;
        private long createTime;
        private String createBy;
        private long updateTime;
        private String updateBy;
        private int isDelete;

        public String getPicId() {
            return picId;
        }

        public void setPicId(String picId) {
            this.picId = picId;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }
    }
}
