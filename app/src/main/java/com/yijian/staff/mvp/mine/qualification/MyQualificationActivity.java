package com.yijian.staff.mvp.mine.qualification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.mine.selectheadicon.ClipActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.authcertificate.AuthBean;
import com.yijian.staff.net.requestbody.authcertificate.CertBean;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;

public class MyQualificationActivity extends MvcBaseActivity {

    @BindView(R.id.lin_iv)
    LinearLayout lin_iv;
    @BindView(R.id.rcl)
    RecyclerView rcl;
    @BindView(R.id.ll_my_zili)
    LinearLayout llMyZili;
    private List<AuthBean> authList;
    private ZiLiAdapter adapter;
    private CertificateBean certificateBean;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_qualification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar2.setTitle("资格证书");
        navigationBar2.setmRightTvText("编辑");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyQualificationActivity.this, EditQualificationActivity.class);
                startActivityForResult(intent,101);
            }
        });


        rcl.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ZiLiAdapter(authList);
        rcl.setAdapter(adapter);
        initData();
    }

    private void initData() {

        User user = DBManager.getInstance().queryUser();
        HashMap<String, String> param = new HashMap<>();
        param.put("coach_id", user.getUserId());
        HttpManager.postHasHeaderHasParam(HttpManager.GET_CERTIFICATE_URL, param, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                certificateBean = new CertificateBean(result);
                authList = certificateBean.getAuthList();
                adapter.update(authList);
                List<CertBean> certList = certificateBean.getCertList();
                setImageList(certList);
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });


    }



    public void setImageList(List<CertBean> certList) {

        for (int i = 0; i < certList.size(); i++) {
            CertBean certListBean = certList.get(i);
            ImageView iv = new ImageView(this);
            FrameLayout.LayoutParams ivFlp = new FrameLayout.LayoutParams(DensityUtil.dip2px(this, 200), FrameLayout.LayoutParams.MATCH_PARENT);
            ivFlp.setMargins(DensityUtil.dip2px(this, 13), 0, 0, 0);
            iv.setLayoutParams(ivFlp);

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(this).load(certListBean.getCertificate()).apply(options).into(iv);
            lin_iv.addView(iv);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 101) {
            initData();
        }

    }
}
