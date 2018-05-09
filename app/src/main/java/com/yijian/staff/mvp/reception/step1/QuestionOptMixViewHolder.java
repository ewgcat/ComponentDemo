package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.ItemsBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;

/**
 * Created by The_P on 2018/3/17.
 */

public class QuestionOptMixViewHolder extends ChildViewHolderGroup {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    private CheckedTextView ctvMixCheck;
    private final EditText etMix;

    public QuestionOptMixViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvMixCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_mix);
        etMix = itemView.findViewById(R.id.et_mix);
    }

    public void bind(ItemsBean child, int parentPosition, int childPosition) {
        ctvMixCheck.setChecked(child.isSelect());

        ctvMixCheck.setText(child.getItem());

        ctvMixCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onMixClick(child,parentPosition,childPosition);
                }
            }
        });

        if (!"null".equals(child.getInputContent())&&!TextUtils.isEmpty(child.getInputContent()))
            etMix.setText(""+child.getInputContent());


        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etMix.hasFocus()){
                    if (listener != null) listener.onMixWrited(child, parentPosition, childPosition, s);

                }
            }
        };

        //设置EditText的焦点监听器判断焦点变化，当有焦点时addTextChangedListener，失去焦点时removeTextChangedListener
        etMix.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    etMix.addTextChangedListener(watcher);
                }else{
                    etMix.removeTextChangedListener(watcher);
                }
            }
        });

    }

    public  interface MixListener{
        void onMixWrited( ItemsBean child,int parentPosition,int childPosition,Editable s);

        void onMixClick(ItemsBean child, int parentPosition, int childPosition);
    }

    private MixListener listener;
    public void setMixWriteListener(MixListener writeListener){
        listener=writeListener;
    }


}
