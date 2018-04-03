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

import com.yijian.staff.R;
import com.yijian.staff.constant.BundleKeyConstant;
import com.yijian.staff.mvp.advice.AdviceActivity;
import com.yijian.staff.mvp.advice.AdviceListActivity;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.mine.club.ClubActivity;
import com.yijian.staff.mvp.mine.calendartable.CalendarTableActivity;
import com.yijian.staff.mvp.mine.editpassword.EditPasswordActivity;
import com.yijian.staff.mvp.mine.qrcode.MyQRCodeActivity;
import com.yijian.staff.mvp.mine.qualification.MyQualificationActivity;
import com.yijian.staff.mvp.mine.setting.SettingActivity;
import com.yijian.staff.mvp.seepic.SeePicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

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
                String path="";
                intent.putExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, path);
                startActivity(intent);
                break;
            case R.id.ll_more:
                startActivityForResult(new Intent(getContext(), SettingActivity.class),1234);
                break;
            case R.id.ll_club:
                startActivity(new Intent(getContext(),ClubActivity.class));
                break;
            case R.id.ll_my_zhengshu:
                startActivity(new Intent(getContext(),MyQualificationActivity.class));
                break;
            case R.id.ll_my_date:
                startActivity(new Intent(getContext(),CalendarTableActivity.class));
                break;
            case R.id.ll_erweima:
                startActivity(new Intent(getContext(),MyQRCodeActivity.class));
                break;
            case R.id.ll_edit_password:
                startActivity(new Intent(getContext(),EditPasswordActivity .class));
                break;
            case R.id.ll_about_us:
                break;
            case R.id.ll_suggestion:
                startActivity(new Intent(getContext(),AdviceActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1234) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();

        }
    }
}
