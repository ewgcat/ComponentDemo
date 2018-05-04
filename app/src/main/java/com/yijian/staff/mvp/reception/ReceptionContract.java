package com.yijian.staff.mvp.reception;

import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionRecordListBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;

import java.util.List;

/**
 * Created by The_P on 2018/3/28.
 */

public interface ReceptionContract {
    interface View{
        void showRecptionInfo(RecptionerInfoBean bean);
        void showRecptionRecordList(List<RecptionRecordListBean.RecordsBean> recordList, boolean isRefresh);

        void finishRefresh(boolean isRefresh);

        void showStatus(ReceptionStastuBean receptionStastuBean);

        void showEndRecption();

    }

    interface Presenter{
       void getRecptionerInfo();
        void getRecptionRecord(boolean isRefresh);

        void getRecptionStatus(String id);

        void endRecption(String memberId);
    }
}
