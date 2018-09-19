package com.yijian.clubmodule.ui.main.mine.userinfo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.GlideCircleTransform;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.UserInfo;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;

import org.json.JSONObject;

import java.util.HashMap;



public class UserInfoActivity extends MvcBaseActivity {
    private static final String TAG = UserInfoActivity.class.getSimpleName();
    ImageView ivHead;
    TextView tvName;
    TextView tvSex;
    TextView tvPhone;
    TextView tvWorkNum;
    TextView tvMendian;
    TextView tvDepartment;
    TextView tvPosition;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_user_info;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        ivHead=findViewById(R.id.iv_head);
        tvSex=findViewById(R.id.tv_sex);
        tvName=findViewById(R.id.tv_name);
        tvPhone=findViewById(R.id.tv_phone);
        tvWorkNum=findViewById(R.id.tv_work_num);
        tvMendian=findViewById(R.id.tv_mendian);
        tvDepartment=findViewById(R.id.tv_department);
        tvPosition=findViewById(R.id.tv_position);
        initView();
    }

    protected void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.setting_activity_navigation_bar);
        navigationBar.setTitle("");
        navigationBar .setBackClickListener(this);
        navigationBar .hideLeftSecondIv();
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
                    setImageResource(SharePreferenceUtil.getImageUrl()+userInfo.getHeadImg(), ivHead);

                }

                @Override
                public void onFail(String msg) {

                }
            });
        }

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
