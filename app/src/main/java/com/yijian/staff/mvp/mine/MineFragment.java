package com.yijian.staff.mvp.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.util.system.StatusBarUtils;

/**
 * Created by lishuaihua on 2018/2/5.
 */

@SuppressLint("ValidFragment")
public class MineFragment extends Fragment {

    public static MineFragment mMineFragment = null;
    public static MineFragment getInstance() {
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
        }
        return mMineFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StatusBarUtils.setLightStatusBar(getActivity(), Color.parseColor("#3699FC"));

        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
}
