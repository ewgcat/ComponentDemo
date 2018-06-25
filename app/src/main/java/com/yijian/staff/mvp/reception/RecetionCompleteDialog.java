package com.yijian.staff.mvp.reception;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yijian.staff.R;

/**
 * Created by The_P on 2018/4/23.
 */

public class RecetionCompleteDialog extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reception_complete, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView tvOk = view.findViewById(R.id.tv_ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (oklisenter!=null)oklisenter.onClick();
                dismiss();
            }
        });


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.shape_fillet_white_8);

    }

}
