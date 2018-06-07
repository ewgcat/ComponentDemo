package com.yijian.staff.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yijian.staff.R;

import java.util.Calendar;

public class DialogManager {

    private Context mContext;
    private AlertDialog.Builder builder;

    public DialogManager(Context context) {
        mContext = context;
        builder = new AlertDialog.Builder(mContext);
    }

    /**
     * 简易对话框
     *
     * @param title
     * @param msg
     */
    public void simpleDialog(String title, String msg) {
        setButton(title);
        builder.setMessage(msg).create().show();
    }

    /**
     * 列表对话框
     *
     * @param title
     * @param str
     */
    public void listDialog(String title, final String[] str) {
        setButton(title);
        // 设置了列表就不能设置内容了，否则就会出问题
        builder.setItems(str, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("选中了：" + str[which]);
            }
        }).create().show();
    }

    /**
     * 单选对话框
     *
     * @param title
     * @param str
     */
    public void singleChoiceDialog(String title, final String[] str) {
        setButton(title);
        builder
                // 设置选中了第二项
                .setSingleChoiceItems(str, 1, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("选中了：" + str[which]);
                    }
                }).create().show();
    }

    /**
     * 多选对话框
     *
     * @param title
     * @param str
     */
    public void MultiChoiceDialog(String title, final String[] str) {
        setButton(title);
        builder
                // 默认选中几项
                .setMultiChoiceItems(str, new boolean[]{true, false, true},
                        new OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                showToast("你选择的id为" + which + " , " + "选中了："
                                        + str[which]);
                            }
                        }).create().show();
    }

    /**
     * 适配器对话框
     *
     * @param title
     * @param str
     */
    public void adapterDialog(String title, final String[] str) {
        setButton(title);
        builder.setAdapter(
                new ArrayAdapter<String>(mContext,
                        android.R.layout.simple_list_item_multiple_choice, str),
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("选中了：" + str[which]);
                    }
                }).create().show();
    }


    /**
     * 进度条对话框
     *
     * @param title
     * @param msg
     */
    public void progressDialog(String title, String msg) {
        final ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(false);// 设置点击空白处也不能关闭该对话框

        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置采用圆形进度条

        dialog.setMax(100);
        // dialog.setIndeterminate(true);//设置不显示明确的进度
        dialog.setIndeterminate(false);// 设置显示明确的进度

        dialog.setButton(ProgressDialog.BUTTON_POSITIVE, "确定",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 这里添加点击后的逻辑
                    }
                });
        dialog.setButton(ProgressDialog.BUTTON_NEUTRAL, "中立",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 这里添加点击后的逻辑
                    }
                });
        dialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "取消",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 这里添加点击后的逻辑
                    }
                });
        dialog.show();

        //启动线程，模拟一个耗时的操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                int Progress = 0;
                while (Progress < 100) {
                    try {
                        Thread.sleep(100);
                        Progress++;
                        // dialog.setProgress(Progress);
                        dialog.incrementProgressBy(1);// 进度条一次加10
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();// 完成后消失
            }
        }).start();
    }
//
//	/**
//	 * PopupWindow做的对话框 感谢： http://www.apkbus.com/android-56965-1-1.html
//	 * http://blog.csdn.net/zhufuing/article/details/17783333
//	 * http://www.open-open.com/lib/view/open1379383271818.html
//	 *
//	 * @param title
//	 * @param v
//	 */
//	public void popupWindowDialog(String title, View v) {
//		// 装载布局文件
//		View view = LayoutInflater.from(mContext).inflate(
//				R.layout.dialog_layout, null);
//		// 创建PopupWindow对象，添加视图，设置宽高，最后一个参数为设置点击屏幕空白处(按返回键)对话框消失。
//		// 也可以用.setFocusable(true);.
//		final PopupWindow pWindow = new PopupWindow(view,
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
//		pWindow.setBackgroundDrawable(new BitmapDrawable());// 为了让对话框点击空白处消失，必须有这条语句
//		pWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);// 出现输入法时，重新布局
//		pWindow.setAnimationStyle(R.style.myAnimationstyle);// 设置动画
//
//		TextView titleTv = (TextView) view
//				.findViewById(R.id.dialog_textView_id);
//		titleTv.setText(title);
//		Button btn = (Button) view.findViewById(R.id.dialog_logout_button_id);
//		btn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				showToast("按下PopupWindow中的按钮了~");
//				pWindow.dismiss();
//			}
//		});
//		// 用下拉方式显示
//		// pWindow.showAsDropDown(v);
//		pWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//	}

    /**
     * 日期对话框
     */
    public void dateDialog() {
        Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker dp, int year, int month,
                                          int dayOfMonth) {
                        showToast(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    /**
     * 时间对话框
     */
    public void timeDialog() {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(mContext,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker arg0, int hourOfDay, int minute) {
                        showToast(hourOfDay + ":" + minute);
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
                .show();
    }

    /**
     * 设置对话框的标题+图标+按钮
     *
     * @param title
     */
    private void setButton(String title) {
        builder.setTitle(title).setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("好", new positiveListener())
                .setNeutralButton("中", new NeutralListener())
                .setNegativeButton("差", new NegativeListener());
        // .setCancelable(false);//设置点击空白处，不能消除该对话框
    }

    /**
     * @author:Jack Tony
     * @tips : 监听器
     * @date :2014-7-25
     */
    private class positiveListener implements OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            // dialog.dismiss();//设置对话框强制退出
            showToast("好");

        }
    }

    private class NeutralListener implements OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            showToast("中");
        }
    }

    private class NegativeListener implements OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            showToast("差");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
