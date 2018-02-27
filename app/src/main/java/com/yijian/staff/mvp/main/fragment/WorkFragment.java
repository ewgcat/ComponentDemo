package com.yijian.staff.mvp.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonlibrary.baseadapter.SuperRecyclerView;
import com.example.commonlibrary.baseadapter.listener.OnSimpleItemClickListener;
import com.example.commonlibrary.baseadapter.manager.WrappedGridLayoutManager;
import com.example.commonlibrary.utils.system.StatusBarUtil;
import com.yijian.staff.adapter.MainAdapter;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.mvp.main.bean.MainItemBean;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuaihua on 2018/2/5.
 */
@SuppressLint("ValidFragment")
public class WorkFragment extends Fragment implements View.OnClickListener {
    private MainAdapter mainAdapter;
    private SuperRecyclerView display;
    public static WorkFragment mWorkFragment = null;
    private ImageView ivRotate;
    private EditText etSearch;
    private View view;

    public static WorkFragment getInstance() {
        if (mWorkFragment == null) {
            mWorkFragment = new WorkFragment();
        }
        return mWorkFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StatusBarUtil.setLightStatusBar(getActivity(), Color.parseColor("#3699FC"));
        view = inflater.inflate(R.layout.fragment_work, container, false);
        init();
        return view;
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        view.findViewById(R.id.rl_jiedai).setOnClickListener(this);
        etSearch = view.findViewById(R.id.et_search);
        ivRotate = view.findViewById(R.id.iv_rotate);
        etSearch.setHintTextColor(Color.parseColor("#fafbfb"));
    }

    protected void initData() {

        startRotateAnimation();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.rl_jiedai:
                Intent i = new Intent(getActivity(), ReceptionActivity.class);
                startActivity(i);

                break;

        }
    }


    /**
     * 开始动画
     */
    private void startRotateAnimation() {
        RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(2000);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间
        ivRotate.setAnimation(rotate);
    }

    /**
     * 移除动画
     */
    private void clearRotateAnimation() {
        ivRotate.clearAnimation();
    }
}
