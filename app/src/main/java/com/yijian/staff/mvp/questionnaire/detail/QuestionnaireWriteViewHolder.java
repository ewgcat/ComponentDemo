package com.yijian.staff.mvp.questionnaire.detail;

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

public class QuestionnaireWriteViewHolder extends ChildViewHolderGroup {
    private TextView editText;
    private final TextView tvLimit;
    private static final String TAG = "QuestionWriteViewHolder";

    public QuestionnaireWriteViewHolder(@NonNull View itemView) {
        super(itemView);
        editText = itemView.findViewById(R.id.et_write);
        tvLimit = itemView.findViewById(R.id.tv_limit);
    }


    public void bind(ItemsBean child, int parentPosition, int childPosition) {
        if (!"null".equals(child.getInputContent()) && !TextUtils.isEmpty(child.getInputContent()))
            editText.setText("" + child.getInputContent());

    }


}
