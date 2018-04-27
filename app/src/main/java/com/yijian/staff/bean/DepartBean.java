package com.yijian.staff.bean;

public class DepartBean {

    private String id;
    private String partName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        DepartBean departBean = (DepartBean) obj;
        if(id.equals(departBean.getId())){
            return true;
        }
        return super.equals(obj);
    }
}
