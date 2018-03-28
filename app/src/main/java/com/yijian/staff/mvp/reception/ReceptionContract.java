package com.yijian.staff.mvp.reception;

import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;

/**
 * Created by The_P on 2018/3/28.
 */

public interface ReceptionContract {
    interface View{
        void showRecptionInfo(RecptionerInfoBean bean);
    }

    interface Presenter{
       void getRecptionerInfo();
        void getRecptionRecord(boolean isRefresh);
    }
}
