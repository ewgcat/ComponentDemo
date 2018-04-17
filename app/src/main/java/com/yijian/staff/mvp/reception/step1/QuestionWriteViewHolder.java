package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.ItemsBean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionWriteViewHolder extends ChildViewHolderGroup {
    private EditText editText;
    private final TextView tvLimit;
    private static final String TAG = "QuestionWriteViewHolder";

    public QuestionWriteViewHolder(@NonNull View itemView) {
        super(itemView);
        editText=(EditText)itemView.findViewById(R.id.et_write);
        tvLimit = itemView.findViewById(R.id.tv_limit);
    }


    public void bind(ItemsBean child, int parentPosition, int childPosition) {
        if (!"null".equals(child.getInputContent())&&!TextUtils.isEmpty(child.getInputContent()))
            editText.setText(""+child.getInputContent());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (listener!=null)listener.onWrited(child,parentPosition,childPosition,s);
                tvLimit.setText(s.toString().length()+"字");
            }
        });


    }

    public  interface WriteListener{
        void onWrited(ItemsBean child, int position, int parentPosition, Editable s);
    }

    private WriteListener listener;
    public void setWriteListener(WriteListener writeListener){
        listener=writeListener;
    }

}
