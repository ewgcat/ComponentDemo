package com.yijian.staff.mvp.coach.preparelessons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.all.PrepareAllLessonActivity;
import com.yijian.staff.mvp.coach.preparelessons.createlession.CreatePrivateLessionActivity;
import com.yijian.staff.mvp.setclass.OpenLessonNewActivity;
import com.yijian.staff.mvp.setclass.bean.PrivateLessonRecordBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrepareLessonsActivity extends AppCompatActivity {

    @BindView(R.id.rv_temple)
    RecyclerView rv_temple;
    @BindView(R.id.tv_template)
    TextView tv_template;
    @BindView(R.id.tv_template_position)
    TextView tv_template_position;
    List<TempBean> tempBeans = new ArrayList<>();
    TempleAdater templeAdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_lessons);
        ButterKnife.bind(this);
        initTitle();
        initView();
        loadData();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_temple.setLayoutManager(linearLayoutManager);
        templeAdater = new TempleAdater();
        rv_temple.setAdapter(templeAdater);
    }


    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("备课");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }


    private void loadData() {
        HttpManager.getHasHeaderNoParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_TEMPLE_URL, new ResultJSONArrayObserver() {
            @Override
            public void onSuccess(JSONArray result) {
                tempBeans = com.alibaba.fastjson.JSONObject.parseArray(result.toString(), TempBean.class);
                templeAdater.notifyDataSetChanged();
                tv_template.setText("/"+tempBeans.size());
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(PrepareLessonsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        });
    }

    @OnClick({R.id.lin_create, R.id.rel_all_lesson})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lin_create: //创建私教备课
                startActivity(new Intent(PrepareLessonsActivity.this, CreatePrivateLessionActivity.class));
                break;

            case R.id.rel_all_lesson:
                startActivity(new Intent(PrepareLessonsActivity.this, PrepareAllLessonActivity.class));
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
            ((ViewHolder) holder).bind(tempBean,position);
        }

        @Override
        public int getItemCount() {
            return tempBeans == null ? 0 : tempBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_templateName;
            private CheckBox ck_select;
            private RelativeLayout rel_select;
            private View view_line_cussor;


            public ViewHolder(View itemView) {
                super(itemView);
                ck_select = itemView.findViewById(R.id.ck_select);
                tv_templateName = itemView.findViewById(R.id.tv_templateName);
                rel_select = itemView.findViewById(R.id.rel_select);
                view_line_cussor = itemView.findViewById(R.id.view_line_cussor);
            }

            private void bind(TempBean tempBean,int position) {
                tv_templateName.setText(tempBean.getTemplateName());
                view_line_cussor.setVisibility((tempBean.isCheck())?View.VISIBLE:View.INVISIBLE);
                ck_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        rv_temple.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                    }
                });
                rel_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_template_position.setText((position+1)+"");
                        for(TempBean tb : tempBeans){
                            tb.setCheck(false);
                        }
                        tempBean.setCheck(true);
                        notifyDataSetChanged();
                    }
                });
            }

        }

    }

}
