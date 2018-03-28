package com.yijian.staff.mvp.setclass;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.setclass.chronometer.ChronometerWithMillis;

import java.lang.reflect.Field;

/**
 * Created by The_P on 2018/3/26.
 */

public class BottomSheetDialogFragmentLesson extends BottomSheetDialogFragment {
    private static final String TAG = "BottomSheetDialogFragme";
    private long mRecordTime;//暂停的时刻
    private boolean isPause=true;
    private BottomSheetDialog bottomSheetDialog;
    private ChronometerWithMillis chronometer;
    private Button btnStartUp;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Log.e(TAG, "onCreateDialog: "+mRecordTime );
        mRecordTime=0;
        isPause=true;

        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet_chronometer);
        bottomSheetDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackground(new ColorDrawable(Color.TRANSPARENT));
        Button btnRestore = bottomSheetDialog.findViewById(R.id.btn_restore);
        btnStartUp = bottomSheetDialog.findViewById(R.id.btn_start_up);
        chronometer = bottomSheetDialog.findViewById(R.id.chronometer);

        btnStartUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e(TAG, "onClick: " );
                if (!isPause){//暂停
//                    Log.e(TAG, "onClick: 暂停" );
                    chronometer.stop();
                    mRecordTime = SystemClock.elapsedRealtime();
                    btnStartUp.setText("继续");
                    btnStartUp.setBackgroundResource(R.mipmap.lesson_activate);
                    isPause=true;
                }else {//开启
                    long l = mRecordTime - chronometer.getBase();
                    if (l<=0)l=0;
//                    Log.e(TAG, "onClick: 开启" );
                    chronometer.setBase(SystemClock.elapsedRealtime()- l);//设置初始时刻
                    chronometer.start();
                    mRecordTime=0;
                    btnStartUp.setText("暂停");
                    btnStartUp.setBackgroundResource(R.mipmap.lesson_stop);
                    isPause=false;
                }

            }
        });

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//重置
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                mRecordTime=0;
                isPause=true;
                btnStartUp.setText("启动");
                btnStartUp.setBackgroundResource(R.mipmap.lesson_activate);
            }
        });


        TextView tvCancel = bottomSheetDialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCancelDialog();
            }
        });


        TextView tvOk = bottomSheetDialog.findViewById(R.id.tv_ok);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOkDialog();
            }
        });

        //禁止滑动
        forbidScroll();


        return bottomSheetDialog;
    }



    private void showOkDialog() {

        chronometer.stop();
        mRecordTime = SystemClock.elapsedRealtime();
        btnStartUp.setText("启动");
        isPause=true;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("是否提交此次计时数据？");
        builder.setMessage("(计时已经暂停)");

        String positiveText = "确定";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        if (listener!=null){
                            String text = chronometer.getText().toString();
                            Log.e(TAG, "onClick: text=="+text );
                            String[] split = text.split(":");
                           String minute=split[0];
                            String[] split1 = split[1].split("\\.");
                           String ss= split1[0];
                            String sss= split1[1];

                           Long time=(Long.valueOf(minute)*60*1000)+Long.valueOf(ss)*1000+Long.valueOf(sss);
                            listener.getTimes(time);

                        }
                        bottomSheetDialog.dismiss();
                    }
                });

        String negativeText = "取消";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }


    private void showCancelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("是否取消此次计时操作？");
        builder.setMessage("(计时仍在继续中...)");

        String positiveText = "确定";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic

                        bottomSheetDialog.dismiss();
                    }
                });

        String negativeText = "取消";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public interface ResultChronometerListener{
        void getTimes( long time);
    }

    private ResultChronometerListener listener;

    public void setResultChronometerListener(ResultChronometerListener resultChronometerListener){
        listener=resultChronometerListener;
    }


    private void forbidScroll() {
        try {
            Field mBehaviorField = bottomSheetDialog.getClass().getDeclaredField("mBehavior");
            mBehaviorField.setAccessible(true);
            final BottomSheetBehavior behavior = (BottomSheetBehavior) mBehaviorField.get(bottomSheetDialog);
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
