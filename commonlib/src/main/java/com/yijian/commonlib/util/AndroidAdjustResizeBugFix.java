package com.yijian.commonlib.util;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/7 15:05:14
 */

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

public class AndroidAdjustResizeBugFix {
    private View mChildOfContent;
    private int usableHeightPrevious;
    private int statusBarHeight;
    private FrameLayout.LayoutParams frameLayoutParams;
    private Activity mActivity;
    private CallKeyBoardStatu listener;

    public void setListener(CallKeyBoardStatu listener) {
        this.listener = listener;
    }

    private AndroidAdjustResizeBugFix(Activity activity, CallKeyBoardStatu listener) {
        mActivity = activity;
        this.listener = listener;
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        statusBarHeight = getStatusBarHeight();
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }
    public static void assistActivity(Activity activity, CallKeyBoardStatu listener) {
        new AndroidAdjustResizeBugFix(activity, listener);
    }
    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                // 如果有高度变化，mChildOfContent.requestLayout()之后界面才会重新测量
                // 这里就随便让原来的高度减去了1
                frameLayoutParams.height = usableHeightSansKeyboard - 1;
                listener.callkeyboardstatu(true, heightDifference);
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
                listener.callkeyboardstatu(false, heightDifference);
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }
    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return r.bottom - r.top + statusBarHeight;
    }
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            int dimensionPixelSize = mActivity.getResources().getDimensionPixelSize(x);
            return dimensionPixelSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public interface CallKeyBoardStatu{
        void callkeyboardstatu(boolean showFlag, int heightDifference);
    }

}
