package com.yijian.staff.mvp.invate;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.InviteDetailBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResponseObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class InvateDetailActivity extends MvcBaseActivity {

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
    @BindView(R.id.tv_invate_content)
    TextView tvInvateContent;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_invate_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initView();
    }

    private void initTitle() {
        NavigationBar NavigationBar = findViewById(R.id.invate_detail_navigation_bar);
        NavigationBar.setTitle("邀约内容");
        NavigationBar.setBackClickListener(this);
        NavigationBar.hideLeftSecondIv();

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

        headUrl= BuildConfig.FILE_HOST + headUrl;
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
