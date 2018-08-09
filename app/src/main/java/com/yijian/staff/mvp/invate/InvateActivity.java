package com.yijian.staff.mvp.invate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvateActivity extends AppCompatActivity {

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
    TimePickerView timePickerView;
    private String memberId;
    private String memberName;
    private String headUrl;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invate_index);
        ButterKnife.bind(this);
        initTitle();
        initView();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.invate_index_navigation_bar);
        navigationBar2.setTitle("邀约");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightTvText("完成");
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }

    private void initView() {
        String memberType = getIntent().getStringExtra("memberType");
        memberId = getIntent().getStringExtra("memberId");
        memberName = getIntent().getStringExtra("memberName");
        headUrl = getIntent().getStringExtra("headUrl");
        sex = getIntent().getStringExtra("sex");
        ImageLoader.setHeadImageResource(headUrl,this,ivHeader);
        int resId="男".equals(sex)?R.mipmap.lg_man:R.mipmap.lg_women;
        ImageLoader.setImageResource(resId,this,ivGender);
        tvName.setText(memberName);
        String mobile = getIntent().getStringExtra("mobile");
        if (!TextUtils.isEmpty(memberType)){
            tvViperType.setText(memberType);
        }
         if (!TextUtils.isEmpty(mobile)){
             tvTelephone.setText(mobile);
        }
        Calendar calendar = Calendar.getInstance();
        tvFuyueTime.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        //提交结果
        timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View view) {
                String result = new SimpleDateFormat("yyyy-MM-dd").format(date);
                tvFuyueTime.setText(result);
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).build();
    }

    @OnClick(R.id.rl_fuyue_time)
    public void onViewClicked() {
        timePickerView.show();
    }

    private void postData() {
        Map<String, String> map = new HashMap<>();

        map.put("memberId", memberId);
        String content = etInvateContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            content="";
        }
        map.put("content", content);
        String visitTime = tvFuyueTime.getText().toString().trim();
        if (TextUtils.isEmpty(visitTime)){
            Toast.makeText(this,"请选择来访日期",Toast.LENGTH_SHORT).show();
            return;
        }
        map.put("visitTime", visitTime);
        String memberType = getIntent().getStringExtra("memberType");


        if ("潜在会员".equals(memberType)) { //正式会员
            memberType = "0";
        } else if ("意向会员".equals(memberType)) {//意向会员
            memberType = "1";
        } else if ("过期会员".equals(memberType)) {//过期会员
            memberType = "3";
        }
        map.put("memberType", memberType);

        HttpManager.getHasHeaderHasParam(HttpManager.INDEX_HUI_JI_INVITATION_SAVE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                Toast.makeText(InvateActivity.this, "邀约成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(InvateActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
