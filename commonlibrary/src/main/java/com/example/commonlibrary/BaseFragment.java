package com.example.commonlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commonlibrary.baseadapter.empty.EmptyLayout;
import com.example.commonlibrary.cusotomview.ToolBarOption;
import com.example.commonlibrary.mvp.presenter.BasePresenter;
import com.example.commonlibrary.mvp.view.IView;
import com.example.commonlibrary.utils.CommonLogger;
import com.example.commonlibrary.utils.ToastUtils;
import com.example.commonlibrary.widget.NavigationBar;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

import javax.inject.Inject;

import static android.view.View.GONE;

/**
 * 项目名称:    Cugappplat
 * 创建人:        陈锦军
 * 创建时间:    2017/4/3      14:24
 * QQ:             1981367757
 */

public abstract class BaseFragment<T, P extends BasePresenter> extends RxFragment implements IView<T> {

    /**
     * 采用懒加载
     */
    protected View root;
    private EmptyLayout mEmptyLayout;
    private boolean hasInit = false;
    private RelativeLayout headerLayout;
    private ImageView icon;
    private TextView right;
    private TextView title;
    private ImageView rightImage;
    protected ImageView back;

    @Nullable
    @Inject
    protected P presenter;


    protected abstract boolean isNeedHeadLayout();

    protected abstract boolean isNeedEmptyLayout();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (root == null) {

                if (isNeedEmptyLayout()) {
                    FrameLayout frameLayout = new FrameLayout(getActivity());
                    frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    mEmptyLayout = new EmptyLayout(getActivity());
                    mEmptyLayout.setVisibility(GONE);
                    frameLayout.addView(LayoutInflater.from(getActivity()).inflate(getContentLayout(), null));
                    frameLayout.addView(mEmptyLayout);
                    root = frameLayout;
                } else {
                    root = inflater.inflate(getContentLayout(), container, false);
                }
            if (root.getParent() != null) {
                ((ViewGroup) root.getParent()).removeView(root);
            }
            if (container != null) {
                CommonLogger.e("添加父类");
                container.addView(root);
            }
            initBaseView();
            initData();
        }
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        return root;
    }

    private void initBaseView() {
        initView();
    }


    protected View findViewById(int id) {
        if (root != null) {
            return root.findViewById(id);
        }
        return null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (root != null && getUserVisibleHint() && !hasInit) {
            hasInit = true;
            updateView();
        }
    }


    /**
     * 视图真正可见的时候才调用
     */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (root != null && isVisibleToUser && !hasInit) {
            hasInit = true;
            updateView();
        }
    }

    protected abstract int getContentLayout();


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void updateView();




    @Override
    public void showLoading(String loadingMsg) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setCurrentStatus(EmptyLayout.STATUS_LOADING);
        } else {
            if (!getActivity().isFinishing()) {
                if (getActivity() instanceof BaseActivity) {
                    ((BaseActivity) getActivity()).showLoadDialog(loadingMsg);
                }
            }
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            if (mEmptyLayout.getCurrentStatus() !=EmptyLayout.STATUS_HIDE) {
                mEmptyLayout.setCurrentStatus(EmptyLayout.STATUS_HIDE);
            }
        } else {
            if (!getActivity().isFinishing()) {
                if (getActivity() instanceof BaseActivity) {
                    ((BaseActivity) getActivity()).dismissLoadDialog();
                }
            }
        }
    }


    protected void hideBaseDialog() {
        if (getActivity() instanceof BaseActivity && !getActivity().isFinishing()) {
            ((BaseActivity) getActivity()).dismissBaseDialog();
        }
    }


    protected void showChooseDialog(String title, List<String> list, AdapterView.OnItemClickListener listener) {
        if (getActivity() instanceof BaseActivity && !getActivity().isFinishing()) {
            ((BaseActivity) getActivity()).showChooseDialog(title, list, listener);
        }
    }

    @Override
    public void showError(String errorMsg, EmptyLayout.OnRetryListener listener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setCurrentStatus(EmptyLayout.STATUS_NO_NET);
            if (listener != null) {
                mEmptyLayout.setOnRetryListener(listener);
            }
        } else {
            ToastUtils.showShortToast(errorMsg);
        }
    }


    public void showEmptyLayout(int status) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setCurrentStatus(status);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void showEmptyView() {
        showEmptyLayout(EmptyLayout.STATUS_NO_DATA);
    }

    @Override
    public <Y> LifecycleTransformer<Y> bindLife() {
        return bindToLifecycle();
    }


    public ImageView getIcon() {
        return icon;
    }


}
