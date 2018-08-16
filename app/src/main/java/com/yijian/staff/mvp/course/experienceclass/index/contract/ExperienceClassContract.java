package com.yijian.staff.mvp.course.experienceclass.index.contract;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yijian.staff.bean.ExperienceClassBean;

import java.util.List;


public interface ExperienceClassContract {
    interface View {
        void showExperienceClassListView(List<ExperienceClassBean> experienceClassBeanList);
    }

    interface Presenter {
        void getExperienceClassListInfo(SmartRefreshLayout refreshLayout, boolean isRefresh);
    }
}
