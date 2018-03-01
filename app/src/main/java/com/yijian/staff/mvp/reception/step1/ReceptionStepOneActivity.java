package com.yijian.staff.mvp.reception.step1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step2.ReceptionStepTwoActivity;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.List;

public class ReceptionStepOneActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup infoSourceGroup1;
    RadioGroup infoSourceGroup2;
    RadioGroup infoSourceGroup3;
    RadioGroup infoSourceGroup4;
    LinearLayout targetGroup1;
    LinearLayout targetGroup2;
    List<RadioGroup> infoSourceGroups = new ArrayList<>();
    EditText etElse;
    EditText etCare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_reception_step_one);

        initView();

    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_step_one_navigation_bar);
        navigationBar.setTitle("填写问卷(1/5)", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        TextView rightTextView = navigationBar.getmRightTextView();
        rightTextView.setText("下一步");
        rightTextView.setTextColor(Color.parseColor("#1997f8"));
        rightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceptionStepOneActivity.this, ReceptionStepTwoActivity.class);
                startActivity(intent);

            }
        });

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
            } else {
                etElse.setVisibility(View.GONE);
            }
        }
    }

}
