package com.yijian.staff.mvp.main.mine.userinfo;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.bean.UserInfo;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.main.mine.setting.SettingActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends AppCompatActivity {
    private static final String TAG = UserInfoActivity.class.getSimpleName();
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_work_num)
    TextView tvWorkNum;
    @BindView(R.id.tv_mendian)
    TextView tvMendian;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ButterKnife.bind(this);
        initView();
    }


    protected void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.setting_activity_navigation_bar2);
        navigationBar2.setTitle("");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        User user = DBManager.getInstance().queryUser();

        HashMap<String, String> map = new HashMap<>();
        if (user != null) {
            map.put("userId", user.getUserId());
            HttpManager.getHasHeaderHasParam(HttpManager.GET_USER_INFO_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
                @Override
                public void onSuccess(JSONObject result) {
                    UserInfo userInfo = new UserInfo(result);
                    tvName.setText(userInfo.getName());
                    tvSex.setText(userInfo.getSex());
                    tvPhone.setText(userInfo.getMobile());
                    tvWorkNum.setText(userInfo.getJobNo());
                    tvMendian.setText(userInfo.getShop());
                    tvDepartment.setText(userInfo.getDepartment());
                    tvPosition.setText(userInfo.getPost());
                    user.setAge(userInfo.getAge());
                    user.setHeadImg(userInfo.getHeadImg());
                    DBManager.getInstance().insertOrReplaceUser(user);
                    setImageResource(userInfo.getHeadImg(), ivHead);

                }

                @Override
                public void onFail(String msg) {

                }
            });
        }

        tvVersion.setText(CommonUtil.getVersionName(this) + " " + CommonUtil.getVersionCode(this));
    }

    @OnClick({R.id.tv_exit_login})
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
        DBManager.getInstance().clearRoleVoBean();
        DBManager.getInstance().clearOthermodelVo();
        setResult(1234);
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
