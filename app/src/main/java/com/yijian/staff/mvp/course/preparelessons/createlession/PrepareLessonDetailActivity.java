package com.yijian.staff.mvp.course.preparelessons.createlession;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.bean.TemplateBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.bean.PrivatePrepareLessonBody;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PrepareLessonDetailActivity extends MvcBaseActivity {

    @BindView(R.id.rv_detail)
    RecyclerView rv_detail;
    private TemplateBean tempBean;
    private PrepareLessonDetailAdapter adapter;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_prepare_lesson_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tempBean = (TemplateBean) getIntent().getSerializableExtra("TemplateBean");
        initTitle();
        initView();
        loadData();
    }

    private void initView() {
        rv_detail.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PrepareLessonDetailAdapter();
        rv_detail.setAdapter(adapter);
    }

    private void loadData() {
        List<TemplateBean.TemplateContextListBean> list = tempBean.getTemplateContextList();
        adapter.resetList(list);
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle(tempBean.getTemplateName());
        navigationBar2.setmRightTvText("确定");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String privateApplyId = getIntent().getStringExtra("id");
        List<TemplateBean.TemplateContextListBean> templateContextListBeans = tempBean.getTemplateContextList();
        List<PrivatePrepareLessonBody.ContentListBean> contentListBeans = new ArrayList<>();
        PrivatePrepareLessonBody privatePrepareLessonBody = new PrivatePrepareLessonBody();
        privatePrepareLessonBody.setPrivateApplyId(privateApplyId);
        for (TemplateBean.TemplateContextListBean templateContextListBean : templateContextListBeans) {
            PrivatePrepareLessonBody.ContentListBean contentListBean = new PrivatePrepareLessonBody.ContentListBean();
            contentListBean.setBuildDesc(templateContextListBean.getBuildDesc());
            contentListBean.setMoApplianceName(templateContextListBean.getMoApplianceName());
            contentListBean.setMoDifficulty(templateContextListBean.getMoDifficulty());
            contentListBean.setMoName(templateContextListBean.getMoName());
            contentListBean.setMoParts(templateContextListBean.getMoParts());
            contentListBeans.add(contentListBean);
        }
        privatePrepareLessonBody.setContentList(contentListBeans);
        showLoading();
        HttpManager.savePrivatePrepareLesson(HttpManager.COACH_PRIVATE_COURSE_STOCK_SAVE_PREPARE_URL, privatePrepareLessonBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                showToast("绑定模板成功");
                finish();
            }

            @Override
            public void onFail(String msg) {
                showToast("绑定模板失败");
                hideLoading();
            }
        });

    }

}
