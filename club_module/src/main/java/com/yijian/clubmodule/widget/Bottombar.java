package com.yijian.clubmodule.widget;

import androidx.lifecycle.Lifecycle;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.jpush.ClearRedPointUtil;
import com.yijian.commonlib.prefs.SharePreferenceUtil;


public class Bottombar extends LinearLayout implements View.OnClickListener {

    protected static final String TAG = Bottombar.class.getSimpleName();

    protected LinearLayout mWorkLL;
    protected ImageView mWorkIv;
    protected TextView mWorkTv;

    protected LinearLayout mMessageLl;
    protected ImageView mMessageIv;
    protected TextView mMessageTv;
    protected LinearLayout mMimeLl;
    protected ImageView mMimeIv;
    protected TextView mMimeTv;
    protected View view_notice;

    protected Context mContext;
    protected OnClickBottomButtonListener mListener;

    public Bottombar(Context context) {
        super(context);
        init(context);
    }

    public Bottombar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Bottombar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mWorkLL = (LinearLayout) this.findViewById(R.id.ll_main_work);
        mWorkIv = (ImageView) this.findViewById(R.id.iv_main_work);
        mWorkTv = (TextView) this.findViewById(R.id.tv_main_work);

        mMessageLl = (LinearLayout) this.findViewById(R.id.ll_main_message);
        mMessageIv = (ImageView) this.findViewById(R.id.iv_main_message);
        mMessageTv = (TextView) this.findViewById(R.id.tv_main_message);
        mMimeLl = (LinearLayout) this.findViewById(R.id.ll_main_mime);
        mMimeIv = (ImageView) this.findViewById(R.id.iv_main_mime);
        mMimeTv = (TextView) this.findViewById(R.id.tv_main_mime);
        view_notice = this.findViewById(R.id.view_notice);

        mWorkLL.setOnClickListener(this);
        mMessageLl.setOnClickListener(this);
        mMimeLl.setOnClickListener(this);
    }


    public void showRedPointNotice(int i) {
        view_notice.setVisibility(i);
    }


    private Lifecycle lifecycle;


    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }


    @Override
    public void onClick(View view) {
        resetAllButtonState();

        int i = view.getId();
        if (i == R.id.ll_main_work) {
            mListener.selectTab(0);

        } else if (i == R.id.ll_main_message) {
            mListener.selectTab(1);
            if (lifecycle != null) {
                ClearRedPointUtil.clearBusinessNotice(lifecycle);
            }
            SharePreferenceUtil.setHasNewSellBusinessPush(false);
            showRedPointNotice(View.INVISIBLE);

        } else if (i == R.id.ll_main_mime) {
            mListener.selectTab(2);

        } else {
        }
    }


    public interface OnClickBottomButtonListener {
        void selectTab(int index);
    }

    public void setmListener(OnClickBottomButtonListener mListener) {
        this.mListener = mListener;
    }

    public void setBottomBarStyle(int index) {
        switch (index) {
            case 0:
                selectWork();
                break;

            case 1:
                selectMessage();
                break;
            case 2:
                selectMine();
                break;
        }
    }

    public void resetAllButtonState() {
        mWorkTv.setTextColor(Color.parseColor("#757575"));
        mWorkIv.setImageResource(R.mipmap.home_normal);


        mMessageIv.setImageResource(R.mipmap.message_norml);
        mMessageTv.setTextColor(Color.parseColor("#757575"));

        mMimeIv.setImageResource(R.mipmap.my_normal);
        mMimeTv.setTextColor(Color.parseColor("#757575"));
    }


    public void selectWork() {
        resetAllButtonState();
        mWorkIv.setImageResource(R.mipmap.home_select);
        mWorkTv.setTextColor(Color.parseColor("#1997f8"));
    }

    public void selectMessage() {
        resetAllButtonState();
        mMessageIv.setImageResource(R.mipmap.message_select);
        mMessageTv.setTextColor(Color.parseColor("#1997f8"));
    }

    public void selectMine() {
        resetAllButtonState();
        mMimeIv.setImageResource(R.mipmap.my_select);
        mMimeTv.setTextColor(Color.parseColor("#1997f8"));
    }


}
