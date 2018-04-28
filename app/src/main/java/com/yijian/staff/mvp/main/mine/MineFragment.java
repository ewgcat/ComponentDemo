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

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.mine.addadvice.AddAdviceActivity;
import com.yijian.staff.mvp.user.login.LoginActivity;
import com.yijian.staff.mvp.mine.aboutus.AboutUsActivity;
import com.yijian.staff.mvp.mine.club.ClubActivity;
import com.yijian.staff.mvp.mine.calendartable.CalendarTableActivity;
import com.yijian.staff.mvp.mine.editpassword.EditPasswordActivity;
import com.yijian.staff.mvp.mine.qrcode.MyQRCodeActivity;
import com.yijian.staff.mvp.mine.qualification.MyQualificationActivity;
import com.yijian.staff.mvp.mine.setting.SettingActivity;
import com.yijian.staff.util.GlideCircleTransform;

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
            // 1 会籍客服 2教练  3会籍总监 4教练总监 5操课教练 6行政  7店长
            if (user.getRole() == 1) {
                tvUserJobPostion.setText("会籍客服");
            } else if (user.getRole() == 2) {
                tvUserJobPostion.setText("教练");
            } else if (user.getRole() == 3) {
                tvUserJobPostion.setText("会籍总监");
            } else if (user.getRole() == 4) {
                tvUserJobPostion.setText("教练总监");
            } else if (user.getRole() == 5) {
                tvUserJobPostion.setText("操课教练");
            } else if (user.getRole() == 6) {
                tvUserJobPostion.setText("行政");
            } else if (user.getRole() == 7) {
                tvUserJobPostion.setText("店长");
            }
            setImageResource(user.getHeadImg(),ivUserHead);
        }
        return view;
    }


    private void setImageResource(String path,ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .transform(new GlideCircleTransform())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(path).apply(options).into(imageView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user_head, R.id.ll_more, R.id.ll_club, R.id.ll_my_zhengshu, R.id.ll_my_date, R.id.ll_erweima, R.id.ll_edit_password, R.id.ll_about_us, R.id.ll_suggestion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_more:
                startActivityForResult(new Intent(getContext(), SettingActivity.class), 1234);
                break;
            case R.id.ll_club:
                startActivity(new Intent(getContext(), ClubActivity.class));
                break;
            case R.id.ll_my_zhengshu:
                if(user.getRole()==2||user.getRole()==4){
                    startActivity(new Intent(getContext(), MyQualificationActivity.class));
                }else {
                    ARouter.getInstance().build("/test/empty").navigation();

                }
                break;
            case R.id.ll_my_date:
                if(user.getRole()==2||user.getRole()==4){
                    startActivity(new Intent(getContext(), CalendarTableActivity.class));
                }else {
                    ARouter.getInstance().build("/test/empty").navigation();
                }
                break;
            case R.id.ll_erweima:
                startActivity(new Intent(getContext(), MyQRCodeActivity.class));
                break;
            case R.id.ll_edit_password:
                startActivityForResult(new Intent(getContext(), EditPasswordActivity.class), 1234);
                break;
            case R.id.ll_about_us:
                startActivity(new Intent(getContext(), AboutUsActivity.class));
                break;
            case R.id.ll_suggestion:
                startActivity(new Intent(getContext(), AddAdviceActivity.class));
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
