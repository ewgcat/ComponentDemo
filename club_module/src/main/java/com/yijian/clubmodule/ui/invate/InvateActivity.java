package com.yijian.clubmodule.ui.invate;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.requestbody.invite.SaveInviteBody;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.DateUtil;
import com.yijian.commonlib.util.ImageLoader;

import org.json.JSONObject;

import java.util.Calendar;




public class InvateActivity extends MvcBaseActivity implements View.OnClickListener {

    ImageView ivHeader;
    TextView tvName;
    ImageView ivGender;
    TextView tvViperType;
    TextView tvTelephone;
    TextView tvFuyueTime;
    EditText etInvateContent;
    TextView tvCanInputNumber;
    private String memberId;
    private String memberName;
    private String headUrl;
    private String sex;
    private DatePickerDialog dialog;


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        ivHeader=findViewById(R.id.iv_header);
        tvName=findViewById(R.id.tv_name);
        ivGender=findViewById(R.id.iv_gender);
        tvViperType=findViewById(R.id.tv_viper_type);
        tvTelephone=findViewById(R.id.tv_telephone);
        tvFuyueTime=findViewById(R.id.tv_fuyue_time);
        etInvateContent=findViewById(R.id.et_invate_content);
        tvCanInputNumber=findViewById(R.id.tv_can_input_number);
        findViewById(R.id.rl_fuyue_time).setOnClickListener(this);
        initTitle();
        initView();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_invate_index;
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.invate_index_navigation_bar);
        navigationBar.setTitle("邀约");
        navigationBar .setBackClickListener(this);
        navigationBar .hideLeftSecondIv();
        navigationBar.setmRightTvText("完成");
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
        etInvateContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();

                if (!TextUtils.isEmpty(s1)) {
                    int num = 140 - s1.length();
                    tvCanInputNumber.setText(num + "字");
                }else {
                    tvCanInputNumber.setText( "140字");
                }
            }
        });
    }

    private void initView() {
        String memberType = getIntent().getStringExtra("memberType");
        memberId = getIntent().getStringExtra("memberId");
        memberName = getIntent().getStringExtra("memberName");
        headUrl = SharePreferenceUtil.getHostUrl() + getIntent().getStringExtra("headUrl");
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

        dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

                        String time = "";
                        if (month < 9 && dayOfMonth < 10) {
                            time += year + "-0" + (month + 1) + "-0" + dayOfMonth;
                        } else if (month > 9 && dayOfMonth >= 10) {
                            time += year + "-" + (month + 1) + "-" + dayOfMonth;
                        } else if (month < 9 && dayOfMonth >= 10) {
                            time += year + "-0" + (month + 1) + "-" + dayOfMonth;
                        } else if (month > 9 && dayOfMonth < 10) {
                            time += year + "-" + (month + 1) + "-0" + dayOfMonth;
                        }


                        String s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay();
                        String s1 = "" + year + month + dayOfMonth;
                        if (Integer.parseInt(s1) >= Integer.parseInt(s)) {
                            tvFuyueTime.setText(time);
                        } else {
                            tvFuyueTime.setText("");
                            showToast("赴约时间不得小于当前时间");
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

    }



    private void postData() {

        String content = etInvateContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            content="";
        }
        String visitTime = tvFuyueTime.getText().toString().trim();
        if (TextUtils.isEmpty(visitTime)){
            Toast.makeText(this,"请选择来访日期",Toast.LENGTH_SHORT).show();
            return;
        }
        String memberType = getIntent().getStringExtra("memberType");


        if ("潜在会员".equals(memberType)) { //正式会员
            memberType = "0";
        } else if ("意向会员".equals(memberType)) {//意向会员
            memberType = "1";
        } else if ("过期会员".equals(memberType)) {//过期会员
            memberType = "3";
        }
        SaveInviteBody saveInviteBody = new SaveInviteBody();
        saveInviteBody.setChief(true);
        saveInviteBody.setContent(content);
        saveInviteBody.setMemberId(memberId);
        saveInviteBody.setVisitTime(visitTime);
        saveInviteBody.setMemberType(memberType);

        HttpManager.postInvateContent(HttpManager.INDEX_HUI_JI_INVITATION_SAVE_URL, saveInviteBody, new ResultJSONObjectObserver(getLifecycle()) {
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

    @Override
    public void onClick(View v) {
        dialog.show();
    }
}
