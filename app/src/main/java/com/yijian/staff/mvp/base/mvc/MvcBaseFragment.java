package com.yijian.staff.mvp.base.mvc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yijian.staff.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无MVP的Fragment基类
 */

public abstract class MvcBaseFragment extends Fragment {

    private Unbinder mUnBinder;
    protected Activity mContext;
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
        mContext = getActivity();
        mUnBinder = ButterKnife.bind(this, rootView);
        initView();

        return rootView;
    }

    public abstract int getLayoutId();

    public abstract void initView();

    protected LoadingDialog loadingDialog;


    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mContext);
        }
        if (loadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!mContext.isFinishing()) {
                loadingDialog.show();
                loadingDialog.setCancelable(false);
            }
        }

    }

    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!mContext.isFinishing()) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        }
    }

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
