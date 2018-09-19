package com.yijian.commonlib.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijian.commonlib.R;

import java.util.List;

/**
 * Created by The_P on 2018/3/23.
 */

public class PopupWindowUtil {
    private static final String TAG = "PopupWindowUtil";
    private final PopupWindow window;
    private final RecyclerView recyclerView;
    private final int popupHeight;
    private final int popupWidth;
    private Context context;

    public PopupWindowUtil(Context context, List<String> datas) {
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.pupopwindow_simple_item, null, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new PopupWindowRecyclerAdapter(datas));
//        lsvMore.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,datas));
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        window.setElevation(dp2px(context, 24));

        //获取自身的长宽高
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = contentView.getMeasuredHeight();
        popupWidth = contentView.getMeasuredWidth();

//        Log.e(TAG, "PopupWindowUtil:popupWidth== "+popupWidth+"--------popupHeight=="+popupHeight );
    }


//    /**
//     * @param paramView 点击的按钮
//     */
//    public void show(View paramView) {
//        window.showAsDropDown(paramView, 0, -(window.getHeight()+recyclerView.getHeight()));
//        //更新窗口状态
//        window.update();
//    }

    /**
     * 设置显示在v上方(以v的左边距为开始位置)
     *
     * @param v
     */
    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);

//        Log.e(TAG, "showUp:location0== "+location[0]+"-------location1=="+ location[1]);
        int x = location[0] + Math.abs((v.getWidth() - popupWidth) / 2);
        //在控件上方显示
        window.showAtLocation(v, Gravity.NO_GRAVITY, x, location[1] - popupHeight);
    }


    public void dissmiss() {
        window.dismiss();
    }

    public class PopupWindowRecyclerAdapter extends RecyclerView.Adapter<PopupWindowRecyclerAdapter.MyHolder> {
        private List<String> list;

        public PopupWindowRecyclerAdapter(List<String> datas) {
            list = datas;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.simple_item, parent, false);

            return new MyHolder(inflate);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.bindView(position);
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            private final TextView tvName;
            private View view;

            public MyHolder(View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tv_name);
                view = itemView;
            }

            public void bindView(int posi) {
                tvName.setText("" + list.get(posi));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null && list.get(posi) != null)
                            listener.onItemClick(list.get(posi), posi);
                    }
                });
            }
        }


    }


    public interface PopupWindowsItemListener {
        void onItemClick(String name, int position);
    }

    private PopupWindowsItemListener listener;

    public void setPopupWindowsItemListener(PopupWindowsItemListener popupWindowsItemListener) {
        listener = popupWindowsItemListener;
    }

    /**
     * dp转换成px
     */
    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
