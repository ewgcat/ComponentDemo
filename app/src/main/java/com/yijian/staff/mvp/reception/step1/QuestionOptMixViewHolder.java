package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.yijian.staff.R;
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

    public void bind(QuestOptBean child, int parentPosition, int childPosition) {
        ctvMixCheck.setChecked(child.isSelected());

        ctvMixCheck.setText(child.getName());

        ctvMixCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onMixClick(child,parentPosition,childPosition);
                }
            }
        });


        etMix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (listener!=null)listener.onMixWrited(parentPosition,childPosition,s);
            }
        });

    }

    public  interface MixListener{
        void onMixWrited( int parentPosition,int childPosition,Editable s);

        void onMixClick(QuestOptBean child, int parentPosition, int childPosition);
    }

    private MixListener listener;
    public void setMixWriteListener(MixListener writeListener){
        listener=writeListener;
    }


}
