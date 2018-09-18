package com.yijian.staff.mvp.main.mine.userinfo;

import android.os.Bundle;
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
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijan.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;

public class UserInfoActivity extends MvcBaseActivity {
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


    @Override
    protected int getLayoutID() {
        return R.layout.activity_user_info;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initView();
    }

    protected void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.setting_activity_navigation_bar2);
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
                    setImageResource(userInfo.getHeadImg(), ivHead);

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
