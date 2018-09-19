package com.yijian.clubmodule.ui.invate;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.InviteDetailBean;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.net.response.ResponseObserver;
import com.yijian.commonlib.util.DateUtil;
import com.yijian.commonlib.util.ImageLoader;

import java.util.HashMap;
import java.util.Map;



public class InvateDetailActivity extends MvcBaseActivity {

    ImageView ivHeader;
    TextView tvName;
    ImageView ivGender;
    TextView tvViperType;
    TextView tvTelephone;
    TextView tvFuyueTime;
    TextView tvInvateContent;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_invate_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        ivHeader=findView(R.id.iv_header);
        tvName=findView(R.id.tv_name);
        ivGender=findView(R.id.iv_gender);
        tvViperType=findView(R.id.tv_viper_type);
        tvTelephone=findView(R.id.tv_telephone);
        tvFuyueTime=findView(R.id.tv_fuyue_time);
        tvInvateContent=findView(R.id.tv_invate_content);
        initTitle();
        initView();
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.invate_detail_navigation_bar);
        navigationBar.setTitle("邀约内容");
        navigationBar .setBackClickListener(this);
        navigationBar .hideLeftSecondIv();

    }

    private void initView() {
        String memberType = getIntent().getStringExtra("memberType");
        memberId = getIntent().getStringExtra("memberId");
        String mobile = getIntent().getStringExtra("mobile");
        if (!TextUtils.isEmpty(memberType)){
            tvViperType.setText(memberType);
        }
        if (!TextUtils.isEmpty(mobile)){
            tvTelephone.setText(mobile);
        }

        String memberName = getIntent().getStringExtra("memberName");
        String headUrl = getIntent().getStringExtra("headUrl");

        headUrl= SharePreferenceUtil.getHostUrl() + headUrl;
        String sex = getIntent().getStringExtra("sex");
        ImageLoader.setHeadImageResource(headUrl,this,ivHeader);
        int resId="男".equals(sex)?R.mipmap.lg_man:R.mipmap.lg_women;
        ImageLoader.setImageResource(resId,this,ivGender);
        tvName.setText(memberName);

        query();

    }


    private String memberId;

    private void query() {
        Map<String, String> map = new HashMap<>();

        map.put("memberId", memberId);


        HttpManager.postHasHeaderHasParam(HttpManager.INDEX_HUI_JI_INVITATION_GET_URL, map, new ResponseObserver<InviteDetailBean>(getLifecycle()) {
            @Override
            public void onSuccess(InviteDetailBean inviteDetailBean) {
                tvInvateContent.setText(inviteDetailBean.getContent());
                tvFuyueTime.setText(DateUtil.parseLongDateToDateString(inviteDetailBean.getVisitTime()));

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(InvateDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
