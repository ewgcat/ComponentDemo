package com.yijian.staff.mvp.reception.step1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.ReceptionStepTwoActivity;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.List;

public class ReceptionStepOneActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    RadioGroup infoSourceGroup1;
    RadioGroup infoSourceGroup2;
    RadioGroup infoSourceGroup3;
    RadioGroup infoSourceGroup4;
    LinearLayout targetGroup1;
    LinearLayout targetGroup2;
    List<RadioGroup> infoSourceGroups = new ArrayList<>();
    EditText etElse;
    EditText etCare;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reception_step_one);

        initView();

    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_step_one_navigation_bar);

        navigationBar2.setNavigationBarBackgroudColor(Color.parseColor("#1997F8"));
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.setTitle("填写问卷(1/5)");
        navigationBar2.setmRightTvText("下一步");


        TimeBar timeBar = findViewById(R.id.step_one_timebar);
        timeBar.showTimeBar(1);

        infoSourceGroup1 = findViewById(R.id.infoSourceGroup1);
        infoSourceGroup2 = findViewById(R.id.infoSourceGroup2);
        infoSourceGroup3 = findViewById(R.id.infoSourceGroup3);
        infoSourceGroup4 = findViewById(R.id.infoSourceGroup4);
        infoSourceGroups.add(infoSourceGroup1);
        infoSourceGroups.add(infoSourceGroup2);
        infoSourceGroups.add(infoSourceGroup3);
        infoSourceGroups.add(infoSourceGroup4);
        infoSourceGroup1.setOnCheckedChangeListener(this);
        infoSourceGroup2.setOnCheckedChangeListener(this);
        infoSourceGroup3.setOnCheckedChangeListener(this);
        infoSourceGroup4.setOnCheckedChangeListener(this);

        targetGroup1 = findViewById(R.id.targetGroup1);
        targetGroup2 = findViewById(R.id.targetGroup2);

        etElse = findViewById(R.id.et_else);
        etCare = findViewById(R.id.et_care);
        save = findViewById(R.id.btn_save);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        infoSourceGroups.remove(group);

        for (RadioGroup radioGroup : infoSourceGroups) {
            radioGroup.setOnCheckedChangeListener(null);
            radioGroup.clearCheck();
        }

        for (RadioGroup radioGroup : infoSourceGroups) {
            radioGroup.setOnCheckedChangeListener(this);
        }

        infoSourceGroups.add(group);

        if (group == infoSourceGroup4) {
            RadioButton radioButton = group.findViewById(checkedId);
            if (radioButton.isChecked()) {
                etElse.setVisibility(View.VISIBLE);
            }
        } else {
            etElse.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;

            case R.id.right_tv:
                Intent intent = new Intent(ReceptionStepOneActivity.this, ReceptionStepTwoActivity.class);
                startActivity(intent);

                break;

        }
    }
}
