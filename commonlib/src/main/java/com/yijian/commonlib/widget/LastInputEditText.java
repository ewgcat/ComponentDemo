package com.yijian.commonlib.widget;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 14:07:03
 */
public class LastInputEditText extends AppCompatEditText {

    public LastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context) {
        super(context);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if (selStart == selEnd) {//防止不能多选
            setSelection(getText().length());
        }

    }
}

