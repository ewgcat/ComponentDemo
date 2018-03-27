package com.yijian.staff.mvp.huiji.invitation.index;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvateIndexActivity extends AppCompatActivity {

    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.tv_viper_type)
    TextView tvViperType;
    @BindView(R.id.tv_telephone)
    TextView tvTelephone;
    @BindView(R.id.tv_fuyue_time)
    TextView tvFuyueTime;
    @BindView(R.id.et_invate_content)
    EditText etInvateContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invate_index);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.invate_index_navigation_bar);
        navigationBar2.setTitle("邀约");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightTvText("完成");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.rl_fuyue_time)
    public void onViewClicked() {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        tvFuyueTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
