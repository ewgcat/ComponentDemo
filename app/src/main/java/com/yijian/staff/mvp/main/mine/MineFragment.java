package com.yijian.staff.mvp.main.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.RoleVoBean;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.course.punch.CoursePunchActivity;
import com.yijian.staff.mvp.course.yueke.YueKeBiaoActivity;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.main.mine.club.ClubActivity;
import com.yijian.staff.mvp.main.mine.qrcode.MyQRCodeActivity;
import com.yijian.staff.mvp.main.mine.qualification.MyQualificationActivity;
import com.yijian.staff.mvp.main.mine.setting.SettingActivity;
import com.yijian.staff.mvp.main.mine.userinfo.UserInfoActivity;
import com.yijian.staff.util.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lishuaihua on 2018/2/5.
 */

@SuppressLint("ValidFragment")
public class MineFragment extends Fragment {

    public static MineFragment mMineFragment = null;
    @BindView(R.id.iv_user_head)
    ImageView ivUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_job_postion)
    TextView tvUserJobPostion;
    Unbinder unbinder;
    private User user;

    public static MineFragment getInstance() {
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
        }
        return mMineFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        user = DBManager.getInstance().queryUser();
        if (user != null) {
            tvUserName.setText(user.getName());
            RoleVoBean roleVoBean = DBManager.getInstance().queryRoleVoBean();
            tvUserJobPostion.setText(roleVoBean.getRoleName());
            setImageResource(user.getHeadImg(), ivUserHead);
        }
        return view;
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



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1234) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();

        }
    }

    @OnClick({R.id.ll_more, R.id.ll_club, R.id.ll_erweima, R.id.ll_coach, R.id.ll_system_set, R.id.ll_suggestion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_more:
                if (user == null) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    startActivityForResult(new Intent(getContext(), UserInfoActivity.class), 1234);
                }
                break;
            case R.id.ll_erweima:
                startActivity(new Intent(getContext(), MyQRCodeActivity.class));
                break;
            case R.id.ll_club:
                startActivity(new Intent(getContext(), ClubActivity.class));
                break;
            case R.id.ll_coach:
                startActivity(new Intent(getContext(), MyQualificationActivity.class));
                break;
            case R.id.ll_system_set:
                startActivityForResult(new Intent(getContext(), SettingActivity.class), 1234);
                break;
            case R.id.ll_suggestion:
                startActivity(new Intent(getContext(), YueKeBiaoActivity.class));
//                startActivity(new Intent(getContext(), AddAdviceActivity.class));
                break;
        }
    }
}
