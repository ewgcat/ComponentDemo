package com.yijian.staff.mvp.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;

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
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
}
