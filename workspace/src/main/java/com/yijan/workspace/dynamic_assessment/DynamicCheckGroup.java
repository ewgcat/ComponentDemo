package com.yijan.workspace.dynamic_assessment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yijan.workspace.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DynamicCheckGroup extends RadioGroup implements View.OnClickListener {

    private Context mContext;
    private List<RadioButton> dataList;
    private RadioButton checkedRadioBtn;

    public DynamicCheckGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
        dataList = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createChekTab(List<String> tabs){
        for (String str : tabs){
            RadioButton radioButton = new RadioButton(mContext);
            radioButton.setText(str);
            radioButton.setPadding(10,6,10,6);
            radioButton.setButtonDrawable(null);
            radioButton.setOnClickListener(this);
            radioButton.setBackgroundResource(R.drawable.selector_check_dynamic);
            ColorStateList colorStateList = getResources().getColorStateList(R.color.selector_txt_blue_gray,null);
            radioButton.setTextColor(colorStateList);
            radioButton.setId(View.generateViewId());
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 30;
            radioButton.setLayoutParams(lp);
            addView(radioButton);
            dataList.add(radioButton);
        }
    }

    public String getCheckedResult(){
        return  checkedRadioBtn == null ? "" : checkedRadioBtn.getText().toString();
    }

    @Override
    public void onClick(View v) {
        RadioButton radioButton = (RadioButton) v;
        if(radioButton != checkedRadioBtn){
            checkedRadioBtn = radioButton;
        }else{
            clearCheck();
            checkedRadioBtn = null;
        }
    }
}
