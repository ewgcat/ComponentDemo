package com.yijian.staff.mvp.coach.experienceclass.index.contract;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yijian.staff.mvp.coach.experienceclass.index.ExperienceClassBean;
import com.yijian.staff.mvp.reception.bean.RecptionRecordListBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;

import java.util.List;


public interface ExperienceClassContract {
    interface View {
        void showExperienceClassListView(List<ExperienceClassBean> experienceClassBeanList);
    }

    interface Presenter {
        void getExperienceClassListInfo(SmartRefreshLayout refreshLayout, boolean isRefresh);
    }
}
