package com.yijian.clubmodule.bean;

/**
 * Created by The_P on 2018/4/11.
 */

public class VenueBean extends SelectedBean {
//    id (string, optional): id ,
//    name (string, optional): 名称

    private String venueId;
    private String option;

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


}
