package com.yijian.staff.mvp.reception.step2;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;


/**
 * Created by The_P on 2018/3/15.
 */

public class Child_Step2ViewHolder_edit extends ChildViewHolder {

    private final TextView tvKey;
    private final EditText etValue;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Child_Step2ViewHolder_edit(@NonNull View itemView) {
        super(itemView);
        tvKey = (TextView) itemView.findViewById(R.id.tv_key);
        etValue = itemView.findViewById(R.id.et_value);
    }


    public void bind(ChildOptBean child, int childPosition, int parentPosition) {
        tvKey.setText(child.getQustion());

        if (!TextUtils.isEmpty(child.getUserValue()) && !"请选择".equals(child.getUserValue()))
            etValue.setText(child.getUserValue());

        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (listener != null) listener.onChildEdited(child, childPosition, parentPosition, s);
            }
        });
    }


    public interface ChildEditListener {
        void onChildEdited(ChildOptBean child, int childPosition, int parentPosition, Editable s);
    }

    private ChildEditListener listener;

    public void setChildEditListener(ChildEditListener childEditListener) {
        listener = childEditListener;
    }
}
