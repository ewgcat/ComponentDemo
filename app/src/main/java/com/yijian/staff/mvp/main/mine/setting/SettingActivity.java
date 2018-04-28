package com.yijian.staff.mvp.main.mine.setting;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.main.mine.selectheadicon.ClipActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

public class SettingActivity extends MvcBaseActivity {

    private static final java.lang.String TAG = SettingActivity.class.getSimpleName();
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    private Dialog dialog;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.setting_activity_navigation_bar2);
        navigationBar2.setTitle("");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        User user = DBManager.getInstance().queryUser();

        HashMap<String,String> map=new HashMap<>();
        map.put("userId",user.getUserId());
        HttpManager.getHasHeaderHasParam(HttpManager.GET_USER_INFO_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        if (user != null) {
            tvName.setText(user.getName());
            tvSex.setText(user.getSex());
            tvAge.setText(user.getAge()+"");
            tvPhone.setText(user.getMobile());
            setImageResource(user.getHeadImg(),ivHead);
        }


    }

    @OnClick({ R.id.tv_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_exit_login:
                exitLogin();
                break;
        }
    }

    private void exitLogin() {
        //发送退出登录请求

        DBManager.getInstance().clearUser();
        setResult(RESULT_OK);
        finish();
    }

    private void setImageResource(String path, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .transform(new GlideCircleTransform())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(path).apply(options).into(imageView);
    }

}
