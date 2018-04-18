package com.yijian.staff.mvp.coach.experienceclass.step5.coach;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.step2.ExperienceClassProcess2Activity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.ClassTimeBar;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassProcess5Activity extends AppCompatActivity {

    @BindView(R.id.tv_post_price)
    TextView tvPostPrice;
    @BindView(R.id.tv_post_leader)
    TextView tvPostLeader;
    @BindView(R.id.tv_end)
    TextView tv_end;
    @BindView(R.id.et_huishang_fangan_result)
    EditText etHuishangFanganResult;

    private int selectButtonPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_class_process5);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.step_five_navigation_bar2);

        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        navigationBar2.setTitle("体验课流程");

        navigationBar2.setmRightTvText("确定");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }


        });

        ClassTimeBar timeBar = findViewById(R.id.step_five_timebar);
        timeBar.showTimeBar(5);


        String memberId = getIntent().getStringExtra("memberId");
        String processId = getIntent().getStringExtra("processId");
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("processId", processId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_HUI_SHANG_RESULT_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ExperienceClassProcess5Bean experienceClassProcess5Bean = new ExperienceClassProcess5Bean(result);
                etHuishangFanganResult.setText(experienceClassProcess5Bean.getConclusionContext());
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(ExperienceClassProcess5Activity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.tv_post_price, R.id.tv_post_leader, R.id.tv_end, R.id.ll_first_class, R.id.ll_second_class})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_post_price:
                selectButton(0);
                break;
            case R.id.tv_post_leader:
                selectButton(1);
                break;
            case R.id.tv_end:
                selectButton(2);
                break;
            case R.id.ll_first_class:
                break;
            case R.id.ll_second_class:
                break;
        }
    }

    private void selectButton(int i) {
        if (i == 0) {
            tvPostPrice.setTextColor(Color.parseColor("#ffffff"));
            tv_end.setTextColor(Color.parseColor("#666666"));
            tvPostLeader.setTextColor(Color.parseColor("#666666"));
            tvPostPrice.setBackground(getDrawable(R.drawable.blue_stroke_40dp_bg));
            tvPostLeader.setBackground(getDrawable(R.drawable.wihte_stroke_40dp_bg));
            tv_end.setBackground(getDrawable(R.drawable.wihte_stroke_40dp_bg));
            selectButtonPosition = 0;
        } else if (i == 1) {
            tvPostLeader.setTextColor(Color.parseColor("#ffffff"));
            tvPostPrice.setTextColor(Color.parseColor("#666666"));
            tv_end.setTextColor(Color.parseColor("#666666"));
            tvPostLeader.setBackground(getDrawable(R.drawable.blue_stroke_40dp_bg));
            tvPostPrice.setBackground(getDrawable(R.drawable.wihte_stroke_40dp_bg));
            tv_end.setBackground(getDrawable(R.drawable.wihte_stroke_40dp_bg));
            selectButtonPosition = 1;
        } else {
            tv_end.setTextColor(Color.parseColor("#ffffff"));
            tvPostLeader.setTextColor(Color.parseColor("#666666"));
            tvPostPrice.setTextColor(Color.parseColor("#666666"));
            tv_end.setBackground(getDrawable(R.drawable.blue_stroke_40dp_bg));
            tvPostLeader.setBackground(getDrawable(R.drawable.wihte_stroke_40dp_bg));
            tvPostPrice.setBackground(getDrawable(R.drawable.wihte_stroke_40dp_bg));
            selectButtonPosition = 2;
        }

    }

    private void commit() {

        if (selectButtonPosition==0){

        }else   if (selectButtonPosition==1){

        }else   if (selectButtonPosition==2){

        }
    }
}
