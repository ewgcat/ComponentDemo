package com.yijian.staff.mvp.course.experienceclass.index.presenter;

import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.bean.ExperienceClassBean;
import com.yijian.staff.mvp.course.experienceclass.index.contract.ExperienceClassContract;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/13 10:10:39
 */
public class ExperienceClassPresenter implements ExperienceClassContract.Presenter {

    private Context context;
    private final User user;
    private ExperienceClassContract.View view;
    private final HashMap<String, String> headerParam;
    private int pageNum = 1;
    private int pageSize = 6;
    private int pages;
    List<ExperienceClassBean> experienceClassBeanList = new ArrayList<>();

    public ExperienceClassPresenter(Context context) {
        this.context = context;
        user = DBManager.getInstance().queryUser();
        headerParam = new HashMap<>();
        headerParam.put("token", user.getToken());

    }

    public void setView(ExperienceClassContract.View view) {
        this.view = view;
    }

    @Override
    public void getExperienceClassListInfo(SmartRefreshLayout refreshLayout, boolean isRefresh) {

        if (isRefresh) {
            experienceClassBeanList.clear();
            pageNum = 1;
            pageSize = 6;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_CLASS_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                List<ExperienceClassBean> experienceClassBeans = com.alibaba.fastjson.JSONObject.parseArray(records.toString(), ExperienceClassBean.class);

                experienceClassBeanList.addAll(experienceClassBeans);
                view.showExperienceClassListView(experienceClassBeanList);

                if (isRefresh) {
                    refreshLayout.finishRefresh(2000, true);
                } else {

                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                }

            }

            @Override
            public void onFail(String msg) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(2000, false);
                } else {

                    refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                }
            }
        });
    }
}
