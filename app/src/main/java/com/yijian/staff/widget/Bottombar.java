package com.yijian.staff.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;


public class Bottombar extends LinearLayout implements View.OnClickListener {

    protected static final String TAG = Bottombar.class.getSimpleName();

    protected LinearLayout mWorkLL;
    protected ImageView mWorkIv;
    protected TextView mWorkTv;
//    protected LinearLayout mReportingLl;
//    protected ImageView mReportingIv;
//    protected TextView mReportingTv;
    protected LinearLayout mMessageLl;
    protected ImageView mMessageIv;
    protected TextView mMessageTv;
    protected LinearLayout mMimeLl;
    protected ImageView mMimeIv;
    protected TextView mMimeTv;

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
//        mReportingLl = (LinearLayout) this.findViewById(R.id.ll_main_reporting);
//        mReportingIv = (ImageView) this.findViewById(R.id.iv_main_reporting);
//        mReportingTv = (TextView) this.findViewById(R.id.tv_main_reporting);
        mMessageLl = (LinearLayout) this.findViewById(R.id.ll_main_message);
        mMessageIv = (ImageView) this.findViewById(R.id.iv_main_message);
        mMessageTv = (TextView) this.findViewById(R.id.tv_main_message);
        mMimeLl = (LinearLayout) this.findViewById(R.id.ll_main_mime);
        mMimeIv = (ImageView) this.findViewById(R.id.iv_main_mime);
        mMimeTv = (TextView) this.findViewById(R.id.tv_main_mime);

        mWorkLL.setOnClickListener(this);
//        mReportingLl.setOnClickListener(this);
        mMessageLl.setOnClickListener(this);
        mMimeLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        resetAllButtonState();
        switch (view.getId()) {
            case R.id.ll_main_work:
                mListener.selectTab(0);
                break;
//            case R.id.ll_main_reporting:
//                mListener.selectTab(1);
//                break;
            case R.id.ll_main_message:
                mListener.selectTab(2);
                break;
            case R.id.ll_main_mime:
                mListener.selectTab(3);
                break;
            default:
                break;
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
//            case 1:
//                selectreporting();
//
//                break;
            case 2:
                selectMessage();
                break;
            case 3:
                selectMine();
                break;
        }
    }

    public void resetAllButtonState() {
        mWorkTv.setTextColor(Color.parseColor("#757575"));
        mWorkIv.setImageResource(R.mipmap.icon_work_unselected);

//        mReportingIv.setImageResource(R.mipmap.icon_report_unselected);
//        mReportingTv.setTextColor(Color.parseColor("#757575"));

        mMessageIv.setImageResource(R.mipmap.icon_message_unselected);
        mMessageTv.setTextColor(Color.parseColor("#757575"));

        mMimeIv.setImageResource(R.mipmap.icon_mine_unselected);
        mMimeTv.setTextColor(Color.parseColor("#757575"));
    }

    public void selectreporting() {
        resetAllButtonState();
//        mReportingIv.setImageResource(R.mipmap.icon_report_selected);
//        mReportingTv.setTextColor(Color.parseColor("#1997f8"));
    }

    public void selectWork() {
        resetAllButtonState();
        mWorkIv.setImageResource(R.mipmap.icon_work_seleceted);
        mWorkTv.setTextColor(Color.parseColor("#1997f8"));
    }

    public void selectMessage() {
        resetAllButtonState();
        mMessageIv.setImageResource(R.mipmap.icon_message_selected);
        mMessageTv.setTextColor(Color.parseColor("#1997f8"));
    }

    public void selectMine() {
        resetAllButtonState();
        mMimeIv.setImageResource(R.mipmap.icon_mine_selected);
        mMimeTv.setTextColor(Color.parseColor("#1997f8"));
    }


}
