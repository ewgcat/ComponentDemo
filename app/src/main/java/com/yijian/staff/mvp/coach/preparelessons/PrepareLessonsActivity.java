package com.yijian.staff.mvp.coach.preparelessons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.TempBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.coach.preparelessons.all.PrepareAllLessonActivity;
import com.yijian.staff.mvp.coach.preparelessons.createlession.CreatePrivateLessionActivity;
import com.yijian.staff.mvp.coach.preparelessons.createlession.PrepareLessonDetailActivity;
import com.yijian.staff.mvp.coach.preparelessons.list.PrepareLessonsBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PrepareLessonsActivity extends MvcBaseActivity {

    @BindView(R.id.tv_memberName)
    TextView tv_memberName;
    @BindView(R.id.tv_member_phone)
    TextView tv_member_phone;
    @BindView(R.id.tv_lessonName)
    TextView tv_lessonName;
    @BindView(R.id.rv_temple)
    RecyclerView rv_temple;
    @BindView(R.id.tv_template)
    TextView tv_template;
    @BindView(R.id.tv_template_position)
    TextView tv_template_position;
    @BindView(R.id.ck_select)
    CheckBox ck_select;
    @BindView(R.id.lin_select_num)
    LinearLayout lin_select_num;
    List<TempBean> tempBeans = new ArrayList<>();
    TempleAdater templeAdater;
    PrepareLessonsBean prepareLessonsBean;



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
        prepareLessonsBean = (PrepareLessonsBean) getIntent().getSerializableExtra(
                "parepareLessonBean");
        tv_memberName.setText(prepareLessonsBean.getMemberName());
        tv_member_phone.setText(prepareLessonsBean.getMobile());
        tv_lessonName.setText(prepareLessonsBean.getLessonName());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_temple.setLayoutManager(linearLayoutManager);
        templeAdater = new TempleAdater();
        rv_temple.setAdapter(templeAdater);
        ck_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rv_temple.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                lin_select_num.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void loadData() {
        showLoading();
        HttpManager.getHasHeaderNoParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_TEMPLE_URL, new ResultJSONArrayObserver() {
            @Override
            public void onSuccess(JSONArray result) {
                tempBeans = com.alibaba.fastjson.JSONObject.parseArray(result.toString(), TempBean.class);
                templeAdater.notifyDataSetChanged();
                tv_template.setText("/" + tempBeans.size());
                hideLoading();
            }

            @Override
            public void onFail(String msg) {

                hideLoading();

            }

        });
    }

    @OnClick({R.id.lin_create, R.id.rel_all_lesson})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lin_create: //创建私教备课
                Intent intent = new Intent(PrepareLessonsActivity.this, CreatePrivateLessionActivity.class);
                intent.putExtra("privateApplyId",prepareLessonsBean.getId());
                startActivity(intent);
                break;

            case R.id.rel_all_lesson: //查询所有备课内容
                Intent all_intent = new Intent(PrepareLessonsActivity.this, PrepareAllLessonActivity.class);
                all_intent.putExtra("memberId",prepareLessonsBean.getMemberId());
                startActivity(all_intent);
                break;
        }
    }

    /***************************  模板列表Adapter  ************************************/
    class TempleAdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_temp, null, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            TempBean tempBean = tempBeans.get(position);
            ((ViewHolder) holder).bind(tempBean, position);
        }

        @Override
        public int getItemCount() {
            return tempBeans == null ? 0 : tempBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_templateName;
            private RelativeLayout rel_select;
            private View view_line_cussor;
            private TextView tv_deail;


            public ViewHolder(View itemView) {
                super(itemView);
                tv_templateName = itemView.findViewById(R.id.tv_templateName);
                rel_select = itemView.findViewById(R.id.rel_select);
                view_line_cussor = itemView.findViewById(R.id.view_line_cussor);
                tv_deail = itemView.findViewById(R.id.tv_deail);
            }

            private void bind(TempBean tempBean, int position) {
                tv_templateName.setText(tempBean.getTemplateName());
                view_line_cussor.setVisibility((tempBean.isCheck()) ? View.VISIBLE : View.INVISIBLE);
                rel_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_template_position.setText((position + 1) + "");
                        for (TempBean tb : tempBeans) {
                            tb.setCheck(false);
                        }
                        tempBean.setCheck(true);
                        notifyDataSetChanged();
                    }
                });
                tv_deail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PrepareLessonsActivity.this, PrepareLessonDetailActivity.class);
                        intent.putExtra("tempBean", tempBean);
                        intent.putExtra("id", prepareLessonsBean.getId());
                        startActivity(intent);
                    }
                });
            }

        }

    }

}
