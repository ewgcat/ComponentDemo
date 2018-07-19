package com.yijian.staff.mvp.course.preparelessons.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.yijian.staff.R;
import com.yijian.staff.bean.TemplateBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.preparelessons.all.PrepareAllLessonActivity;
import com.yijian.staff.mvp.course.preparelessons.createlession.CreatePrivateLessionActivity;
import com.yijian.staff.bean.PrepareLessonsBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PrepareLessonsActivity extends MvcBaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;


    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_lesson_name)
    TextView tvLessonName;
    @BindView(R.id.tv_total_lessons_count)
    TextView tvTotalLessonsCount;
    @BindView(R.id.tv_remaining_lessons_count)
    TextView tvRemainingLessonsCount;
    @BindView(R.id.tv_last_lesson_time)
    TextView tvLastLessonTime;
    @BindView(R.id.lin_controller)
    LinearLayout linController;
    @BindView(R.id.ck_select)
    CheckBox ckSelect;

    @BindView(R.id.ll_template_container)
    LinearLayout llTemplateContainer;
    @BindView(R.id.tv_template_position)
    TextView tvTemplatePosition;
    @BindView(R.id.tv_template_total)
    TextView tvTemplateTotal;
    @BindView(R.id.rv_templalte)
    RecyclerView rvTemplate;


    private List<TemplateBean> templateBeanList;


    TemplatesAdapter templeAdater;
    PrepareLessonsBean prepareLessonsBean;
    private String mobile;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_prepare_lessons;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitle();
        initView();
        loadData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("备课");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }


    private void initView() {
        prepareLessonsBean = (PrepareLessonsBean) getIntent().getSerializableExtra("parepareLessonBean");
       if (prepareLessonsBean!=null){
           String headPath = prepareLessonsBean.getHeadPath();
           if (!TextUtils.isEmpty(headPath)){
               ImageLoader.setHeadImageResource(headPath,this,ivHeader);
           }
           String memberName = prepareLessonsBean.getMemberName();
           if (!TextUtils.isEmpty(headPath)){
               tvName.setText(memberName);
           }
           String lessonName = prepareLessonsBean.getLessonName();
           if (!TextUtils.isEmpty(headPath)){
               tvLessonName.setText(lessonName);
           }
            mobile = prepareLessonsBean.getMobile();
       }






        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTemplate.setLayoutManager(linearLayoutManager);

        templeAdater = new TemplatesAdapter(this, templateBeanList);
        templeAdater.setSelectTemplateListener(new TemplatesAdapter.SelectTemplateListener() {
            @Override
            public void onSelectTemplate(TemplateBean templateBean, int position) {
                tvTemplatePosition.setText((1 + position) + "");
            }
        });
        rvTemplate.setAdapter(templeAdater);
        ckSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rvTemplate.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                llTemplateContainer.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });


    }

    private void loadData() {
        showLoading();
        HttpManager.getHasHeaderNoParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_TEMPLE_URL, new ResultJSONArrayObserver() {
            @Override
            public void onSuccess(JSONArray result) {
                templateBeanList = JSONObject.parseArray(result.toString(), TemplateBean.class);
                templeAdater.notifyDataSetChanged();
                if (templateBeanList != null && templateBeanList.size() == 0) {
                    tvTemplateTotal.setText("0");
                    tvTemplatePosition.setText("0");
                } else if (templateBeanList != null && templateBeanList.size() > 0) {
                    tvTemplateTotal.setText("" + templateBeanList.size());
                    tvTemplatePosition.setText("1");
                }

                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
            }

        });
    }

    @OnClick({R.id.iv_call_phone, R.id.lin_create, R.id.rel_all_lesson})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_call_phone:
                if (!TextUtils.isEmpty(mobile)) {
                    CommonUtil.callPhone(PrepareLessonsActivity.this, mobile);
                } else {
                    Toast.makeText(PrepareLessonsActivity.this, "未录入手机号,无法进行电话沟通", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lin_create: //创建私教备课
                if (prepareLessonsBean!=null){
                    String id = prepareLessonsBean.getId();
                    if (!TextUtils.isEmpty(id)){
                        Intent intent = new Intent(PrepareLessonsActivity.this, CreatePrivateLessionActivity.class);
                        intent.putExtra("privateApplyId", id);
                        startActivity(intent);
                    }
                }

                break;

            case R.id.rel_all_lesson: //查询所有备课内容
                if (prepareLessonsBean!=null){
                    String memberId = prepareLessonsBean.getMemberId();
                    if (!TextUtils.isEmpty(memberId)){
                        Intent intent1 = new Intent(PrepareLessonsActivity.this, PrepareAllLessonActivity.class);
                        intent1.putExtra("memberId", memberId);
                        startActivity(intent1);
                    }
                }

                break;
        }
    }


}
