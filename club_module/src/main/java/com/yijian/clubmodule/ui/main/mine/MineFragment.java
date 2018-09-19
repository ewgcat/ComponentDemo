package com.yijian.clubmodule.ui.main.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.clubmodule.ui.main.mine.addadvice.AddAdviceActivity;
import com.yijian.clubmodule.ui.main.mine.club.ClubActivity;
import com.yijian.clubmodule.ui.main.mine.qrcode.MyQRCodeActivity;
import com.yijian.clubmodule.ui.main.mine.qualification.MyQualificationActivity;
import com.yijian.clubmodule.ui.main.mine.setting.SettingActivity;
import com.yijian.clubmodule.ui.main.mine.userinfo.UserInfoActivity;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.db.ClubDBManager;
import com.yijian.clubmodule.db.bean.RoleVoBean;
import com.yijian.commonlib.util.GlideCircleTransform;

/**
 * Created by lishuaihua on 2018/2/5.
 */

@SuppressLint("ValidFragment")
public class MineFragment extends Fragment implements View.OnClickListener {

    public static MineFragment mMineFragment = null;
    ImageView ivUserHead;
    TextView tvUserName;
    TextView tvUserJobPostion;
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
        ivUserHead= view.findViewById(R.id.iv_user_head);
        tvUserName= view.findViewById(R.id.tv_user_name);
        tvUserJobPostion= view.findViewById(R.id.tv_user_job_postion);
        user = DBManager.getInstance().queryUser();
        if (user != null) {
            tvUserName.setText(user.getName());
            RoleVoBean roleVoBean = ClubDBManager.getInstance().queryRoleVoBean();
            tvUserJobPostion.setText(roleVoBean.getRoleName());
            setImageResource(SharePreferenceUtil.getImageUrl() + user.getHeadImg(), ivUserHead);
        }
                view.findViewById(R.id.ll_more).setOnClickListener(this);
                view.findViewById(R.id.ll_club).setOnClickListener(this);
                view.findViewById(R.id.ll_erweima).setOnClickListener(this);
                view.findViewById(R.id.ll_coach).setOnClickListener(this);
                view.findViewById(R.id.ll_system_set).setOnClickListener(this);
                view.findViewById(R.id.ll_suggestion).setOnClickListener(this);
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
            ARouter.getInstance().build("/app/login").navigation();
            getActivity().finish();

        }
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_more) {
            if (user == null) {
                ARouter.getInstance().build("/app/login").navigation();

            } else {
                startActivityForResult(new Intent(getContext(), UserInfoActivity.class), 1234);
            }

        } else if (i == R.id.ll_erweima) {
            startActivity(new Intent(getContext(), MyQRCodeActivity.class));

        } else if (i == R.id.ll_club) {
            startActivity(new Intent(getContext(), ClubActivity.class));

        } else if (i == R.id.ll_coach) {
            startActivity(new Intent(getContext(), MyQualificationActivity.class));

        } else if (i == R.id.ll_system_set) {
            startActivityForResult(new Intent(getContext(), SettingActivity.class), 1234);

        } else if (i == R.id.ll_suggestion) {
            startActivity(new Intent(getContext(), AddAdviceActivity.class));

        }
    }
}
