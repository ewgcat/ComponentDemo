package com.yijian.staff.mvp.base.mvc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无MVP的Fragment基类
 */

public abstract class MvcBaseFragment extends Fragment {

    private Unbinder mUnBinder;
    protected Context mContext;
    protected View rootView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        initView();
        mUnBinder = ButterKnife.bind(this, rootView);
        mContext = getActivity();

        return rootView;
    }

    public abstract int getLayoutId();

    public abstract void initView();



    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public <T extends View> T findView(int viewId) {
        return (T) rootView.findViewById(viewId);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

}
