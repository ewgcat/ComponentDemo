package com.yijian.staff.mvp.questionnaireresult;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;

/**
 * Created by The_P on 2018/3/17.
 */

public class QuestionnaireResultMixViewHolder extends ChildViewHolderGroup {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    private CheckedTextView ctvMixCheck;
    private  TextView tvMix;

    public QuestionnaireResultMixViewHolder(@NonNull View itemView) {
        super(itemView);
        ctvMixCheck = (CheckedTextView) itemView.findViewById(R.id.ctv_mix);
        tvMix = itemView.findViewById(R.id.tv_mix);
    }

    public void bind(QuestOptBean child, int parentPosition, int childPosition) {
        ctvMixCheck.setChecked(child.isSelected());

        ctvMixCheck.setText(child.getName());

        tvMix.setText("this is a answer");

    }



}
