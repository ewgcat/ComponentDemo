package com.yijian.staff.mvp.questionnaire.detail;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.ItemsBean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;

/**
 * Created by The_P on 2018/3/17.
 */

public class QuestionnaireOptMixViewHolder extends ChildViewHolderGroup {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    private CheckedTextView ctvMixCheck;
    private final TextView etMix;

    public QuestionnaireOptMixViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvMixCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_mix);
        etMix = itemView.findViewById(R.id.et_mix);
    }

    public void bind(ItemsBean child, int parentPosition, int childPosition) {
        ctvMixCheck.setChecked(child.isSelect());

        ctvMixCheck.setText(child.getItem());



        if (!"null".equals(child.getInputContent())&&!TextUtils.isEmpty(child.getInputContent()))
            etMix.setText(""+child.getInputContent());


    }




}
