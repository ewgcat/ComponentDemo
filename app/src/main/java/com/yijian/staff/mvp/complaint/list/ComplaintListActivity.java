package com.yijian.staff.mvp.complaint.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yijian.staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplaintListActivity extends AppCompatActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.lin_staff)
    LinearLayout linStaff;
    @BindView(R.id.lin_coach)
    LinearLayout linCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);

        ButterKnife.bind(this);

    }

    @OnClick({R.id.lin_handling_condition, R.id.lin_handling_task,R.id.lin_feipei_condition, R.id.lin_feipei_task})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_handling_condition:

                break;
            case R.id.lin_handling_task:

                break;
            case R.id.lin_feipei_condition:
                break;
            case R.id.lin_feipei_task:
                break;
        }
    }

}
