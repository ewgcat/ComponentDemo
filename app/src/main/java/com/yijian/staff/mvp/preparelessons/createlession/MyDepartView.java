package com.yijian.staff.mvp.preparelessons.createlession;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangk on 2018/3/19.
 */

public class MyDepartView extends LinearLayout {

    private Context mContext;
    private List<TextView> viewDepartList;

    public MyDepartView(Context context) {
        super(context);
        this.mContext = context;
        viewDepartList = new ArrayList<TextView>();
    }

    public MyDepartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        viewDepartList = new ArrayList<TextView>();
    }

    public MyDepartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        viewDepartList = new ArrayList<TextView>();
    }

    public void addLineView(List<String> departArray,OnDepartOprationListener onDepartOprationListener){
        for(int i = 0;i < departArray.size(); i++){
            LinearLayout linContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_depart, null);
            TextView tv_depart_1 = (TextView) linContain.findViewById(R.id.tv_depart_1);
            tv_depart_1.setText(departArray.get(i));
            TextView tv_depart_2 = (TextView) linContain.findViewById(R.id.tv_depart_2);
            tv_depart_2.setText(departArray.get(i+1));
            tv_depart_1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDepartOprationListener.departOpration((TextView) v);
                }
            });
            tv_depart_2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDepartOprationListener.departOpration((TextView) v);
                }
            });
            viewDepartList.add(tv_depart_1);
            viewDepartList.add(tv_depart_2);
            i = i+1;
            this.addView(linContain);
        }
    }

    public interface OnDepartOprationListener{
        void departOpration(TextView txtView);
    }

}
