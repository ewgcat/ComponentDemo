package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestionOption;
import com.yijian.staff.mvp.reception.step1.bean.Step1Bean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;
import com.yijian.staff.mvp.reception.step1.recyclerView.ParentViewHolderGroup;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionWriteViewHolder extends AbsParentViewHolder {
    private final TextView tvTitle;
    private EditText editText;
    private final TextView tvLimit;
    private static final String TAG = "QuestionWriteViewHolder";

    public QuestionWriteViewHolder(@NonNull View itemView) {
        super(itemView);
        editText=(EditText)itemView.findViewById(R.id.et_write);
        tvLimit = itemView.findViewById(R.id.tv_limit);
        tvTitle = itemView.findViewById(R.id.tv_question_title);
    }


    public void bind(Step1Bean child, int parentPosition) {
        tvTitle.setText(child.getQuestName());

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
                tvLimit.setText(s.toString().length()+"字");
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
