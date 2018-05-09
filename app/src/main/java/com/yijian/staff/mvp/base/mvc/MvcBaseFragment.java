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

import com.yijian.staff.widget.BlueLoadingDialog;
import com.yijian.staff.widget.WhiteLoadingDialog;

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

    protected BlueLoadingDialog blueLoadingDialog;


    public void showBlueProgress() {
        if (blueLoadingDialog == null) {
            blueLoadingDialog =new BlueLoadingDialog(mContext);
        }
        if (blueLoadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!mContext.isFinishing()) {
                blueLoadingDialog.show();
                blueLoadingDialog.setCancelable(false);
            }
        }

    }

    public void hideBlueProgress() {
        if (blueLoadingDialog != null && blueLoadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!mContext.isFinishing()) {
                blueLoadingDialog.dismiss();
                blueLoadingDialog = null;
            }
        }
    }

    protected WhiteLoadingDialog whiteLoadingDialog;


    public void showWhiteProgress() {
        if (whiteLoadingDialog == null) {
            whiteLoadingDialog =new WhiteLoadingDialog(mContext);
        }
        if (whiteLoadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!mContext.isFinishing()) {
                whiteLoadingDialog.show();
                whiteLoadingDialog.setCancelable(false);
            }
        }

    }

    public void hideWhiteProgress() {
        if (whiteLoadingDialog != null && whiteLoadingDialog.isShowing()) {
            //防止显示期间activity已经被销毁了
            if (!mContext.isFinishing()) {
                whiteLoadingDialog.dismiss();
                whiteLoadingDialog = null;
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
