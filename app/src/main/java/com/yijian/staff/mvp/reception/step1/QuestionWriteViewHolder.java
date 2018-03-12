package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestionOption;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolder;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionWriteViewHolder extends ChildViewHolder {
    private EditText editText;
    public QuestionWriteViewHolder(@NonNull View itemView) {
        super(itemView);
        editText=(EditText)itemView.findViewById(R.id.et_write);
    }

    public void bind(QuestionOption child, int parentPosition, int childPosition) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (listener!=null)listener.onWrited(parentPosition,s);
            }
        });
    }

    public  interface WriteListener{
        void onWrited( int parentPosition,Editable s);
    }

    private WriteListener listener;
    public void setWriteListener(WriteListener writeListener){
        listener=writeListener;
    }

}
