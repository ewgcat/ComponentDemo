package com.yijan.workspace.workspace.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijan.workspace.R;

import java.util.ArrayList;
import java.util.List;

public class TableView extends LinearLayout {

    private Context mContext;
    private LinearLayout tab_title_container; //tab容器
    private View view_curssor; // 游标View
    private FrameLayout fl_curssor_container;
    private int textSize;
    private int textSelectColor;
    private int textNormalColor;
    private int curssorColor;
    private float scale;
    private int paddingLeft;
    private int paddingRight;
    private List<TextView> tvList = new ArrayList<>();
    private List<Integer> resList = new ArrayList<>();
    AnimatorSet animationSet = new AnimatorSet();
    PropertyValuesHolder holder_large_x = PropertyValuesHolder.ofFloat("scaleX", 1f,1.05f);
    PropertyValuesHolder holder_large_y = PropertyValuesHolder.ofFloat("scaleY", 1f,1.05f);
    PropertyValuesHolder holder_small_x = PropertyValuesHolder.ofFloat("scaleX", 1.05f,1f);
    PropertyValuesHolder holder_small_y = PropertyValuesHolder.ofFloat("scaleY", 1.05f,1f);
    private int currentId = -1;
    private TabCallBack listener;

    public void setListener(TabCallBack listener) {
        this.listener = listener;
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            setSelectionById(id);
        }
    };


    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_tab_commen, this);
        tab_title_container = findViewById(R.id.tab_title_container);
        view_curssor = findViewById(R.id.view_curssor);
        fl_curssor_container = findViewById(R.id.fl_curssor_container);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StyleTabCommen);
        textSize = typedArray.getInt(R.styleable.StyleTabCommen_textTabSize,15);
        paddingLeft = typedArray.getInt(R.styleable.StyleTabCommen_paddingLeft,15);
        paddingRight = typedArray.getInt(R.styleable.StyleTabCommen_paddingRight,15);
        textSelectColor = typedArray.getColor(R.styleable.StyleTabCommen_textSelectColor, Color.parseColor("#000000"));
        textNormalColor = typedArray.getColor(R.styleable.StyleTabCommen_textNormalColor, Color.parseColor("#999999"));
        curssorColor = typedArray.getColor(R.styleable.StyleTabCommen_curssorColor, Color.parseColor("#333333"));
        scale = typedArray.getFloat(R.styleable.StyleTabCommen_scale, 1f);
        typedArray.recycle();
        view_curssor.setBackgroundColor(curssorColor);
    }

    public void createButton(String... title){
        for(int i = 0; i < title.length; i++){
            TextView tv = new TextView(mContext);
            tv.setTextSize(textSize);
            tv.setText(title[i]);
            tv.setTextColor(textNormalColor);
            tv.setPadding(paddingLeft, 0 ,paddingRight, 0);
            tv.setOnClickListener(clickListener);
            int id = View.generateViewId();
            tv.setId(id);
            resList.add(id);
            tvList.add(tv);
            tab_title_container.addView(tv);
        }
        tvList.get(0).post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) fl_curssor_container.getLayoutParams();
                lp.width = tvList.get(0).getWidth();
                fl_curssor_container.setLayoutParams(lp);
            }
        });
        fl_curssor_container.setVisibility(View.VISIBLE);
    }

    private void setSelectionById(int id){
        if(currentId != id){
            for(int i = 0; i < resList.size(); i++){
                if (id == resList.get(i)) {
                    ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvList.get(i), holder_large_x, holder_large_y);
                    objectAnimator1.start();
                    tvList.get(i).setTextColor(textSelectColor);
                    final int finalI = i;
                    tvList.get(i).post(new Runnable() {
                        @Override
                        public void run() {
                            ObjectAnimator.ofFloat(fl_curssor_container, "translationX",  finalI *tvList.get(0).getWidth()).setDuration(200).start();
                        }
                    });
                    currentId = id;
                    listener.callExchangeBack(i);
                    continue;
                }
                tvList.get(i).setTextColor(textNormalColor);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(tvList.get(i),holder_small_x, holder_small_y);
                objectAnimator2.start();
            }
        }
    }

    public void setCurrentPosition(int index){
        setSelectionById(resList.get(index));
    }

    public interface TabCallBack{
        void callExchangeBack(int index);
    }

}
