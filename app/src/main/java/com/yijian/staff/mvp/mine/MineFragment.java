package com.yijian.staff.mvp.mine;

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
import com.yijian.staff.R;
import com.yijian.staff.mvp.seepic.SeePicActivity;

import java.util.ArrayList;

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
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user_head, R.id.ll_more, R.id.ll_club, R.id.ll_my_zhengshu, R.id.ll_my_date, R.id.ll_erweima, R.id.ll_edit_password, R.id.ll_about_us, R.id.ll_suggestion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_head:
                Intent intent = new Intent(getContext(), SeePicActivity.class);
                ArrayList<String> picList = new ArrayList<>();
                intent.putStringArrayListExtra(BundleKeyConstant.KEY_SEE_PIC_ARRAY, picList);
                startActivity(intent);
                break;
            case R.id.ll_more:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.ll_club:
                break;
            case R.id.ll_my_zhengshu:
                break;
            case R.id.ll_my_date:
                break;
            case R.id.ll_erweima:
                break;
            case R.id.ll_edit_password:
                break;
            case R.id.ll_about_us:
                break;
            case R.id.ll_suggestion:
                break;
        }
    }
}
