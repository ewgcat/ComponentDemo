package com.yijian.clubmodule.ui.vipermanage.viper.detail.picker;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class JsonBean_Service  implements IPickerViewData {


    /**
     * id : 1
     * provinceName : 北京市
     * citys : [{"id":1,"cityName":"北京市","zipCode":"100000","provinceId":1,"districts":[{"id":1,"districtName":"东城区","cityId":1},{"id":2,"districtName":"西城区","cityId":1},{"id":5,"districtName":"朝阳区","cityId":1},{"id":6,"districtName":"丰台区","cityId":1},{"id":7,"districtName":"石景山区","cityId":1},{"id":8,"districtName":"海淀区","cityId":1},{"id":9,"districtName":"门头沟区","cityId":1},{"id":10,"districtName":"房山区","cityId":1},{"id":11,"districtName":"通州区","cityId":1},{"id":12,"districtName":"顺义区","cityId":1},{"id":13,"districtName":"昌平区","cityId":1},{"id":14,"districtName":"大兴区","cityId":1},{"id":15,"districtName":"怀柔区","cityId":1},{"id":16,"districtName":"平谷区","cityId":1},{"id":17,"districtName":"密云县","cityId":1},{"id":18,"districtName":"延庆县","cityId":1}]}]
     */

    private int id;
    private String provinceName;
    private List<CitysBean> citys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<CitysBean> getCitys() {
        return citys;
    }

    public void setCitys(List<CitysBean> citys) {
        this.citys = citys;
    }

    @Override
    public String getPickerViewText() {
        return this.provinceName;
    }

    public static class CitysBean {
        /**
         * id : 1
         * cityName : 北京市
         * zipCode : 100000
         * provinceId : 1
         * districts : [{"id":1,"districtName":"东城区","cityId":1},{"id":2,"districtName":"西城区","cityId":1},{"id":5,"districtName":"朝阳区","cityId":1},{"id":6,"districtName":"丰台区","cityId":1},{"id":7,"districtName":"石景山区","cityId":1},{"id":8,"districtName":"海淀区","cityId":1},{"id":9,"districtName":"门头沟区","cityId":1},{"id":10,"districtName":"房山区","cityId":1},{"id":11,"districtName":"通州区","cityId":1},{"id":12,"districtName":"顺义区","cityId":1},{"id":13,"districtName":"昌平区","cityId":1},{"id":14,"districtName":"大兴区","cityId":1},{"id":15,"districtName":"怀柔区","cityId":1},{"id":16,"districtName":"平谷区","cityId":1},{"id":17,"districtName":"密云县","cityId":1},{"id":18,"districtName":"延庆县","cityId":1}]
         */

        private int id;
        private String cityName;
        private String zipCode;
        private int provinceId;
        private List<DistrictsBean> districts;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public List<DistrictsBean> getDistricts() {
            return districts;
        }

        public void setDistricts(List<DistrictsBean> districts) {
            this.districts = districts;
        }

        public static class DistrictsBean {
            /**
             * id : 1
             * districtName : 东城区
             * cityId : 1
             */

            private int id;
            private String districtName;
            private int cityId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }
        }
    }
}
