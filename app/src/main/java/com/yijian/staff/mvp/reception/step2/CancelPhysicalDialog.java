package com.yijian.staff.mvp.reception.step2;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;

/**
 * Created by The_P on 2018/4/19.
 */

public class CancelPhysicalDialog extends DialogFragment implements View.OnClickListener {

    private TextView tvCancel;
    private TextView tvReason0;
    private TextView tvReason1;
    private TextView tvReason2;
    private TextView tvOk;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.shape_fillet_white_8);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reception_step2_cancel, container, false);
//        //添加这一行
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        initView(view);
        return view;
    }

    private void initView(View view) {
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvReason0 = view.findViewById(R.id.tv_reason_0);
        tvReason1 = view.findViewById(R.id.tv_reason_1);
        tvReason2 = view.findViewById(R.id.tv_reason_2);
        tvOk = view.findViewById(R.id.tv_ok);

        tvCancel.setOnClickListener(this);
        tvReason0.setOnClickListener(this);
        tvReason1.setOnClickListener(this);
        tvReason2.setOnClickListener(this);
        tvOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;

            case R.id.tv_reason_0:
                resetState();
                tvReason0.setSelected(true);

                break;

            case R.id.tv_reason_1:
                resetState();
                tvReason1.setSelected(true);
                break;

            case R.id.tv_reason_2:
                resetState();
                tvReason2.setSelected(true);
                break;

            case R.id.tv_ok:
                String reason = getReason();
                if (TextUtils.isEmpty(reason)){
                    Toast.makeText(getActivity(),"请选择原因",Toast.LENGTH_SHORT).show();
                return;
                }

                if (cancelLisenter!=null)cancelLisenter.cancelReason(reason);
                break;
        }
    }

    private String getReason() {
        String reason="";
        if (tvReason0.isSelected()){
            reason= tvReason0.getText().toString();
        }
        else if (tvReason1.isSelected()){

            reason=tvReason1.getText().toString();
        }
        else if (tvReason2.isSelected())
        {
            reason= tvReason2.getText().toString();
        }
        else{
            reason="";
        }
            return reason;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        resetState();
    }

    public void resetState(){
        tvReason0.setSelected(false);
        tvReason1.setSelected(false);
        tvReason2.setSelected(false);
    }

    public interface CancelLisenter{
        void cancelReason(String reason);
    }

    private CancelLisenter cancelLisenter;

    public void setCancelLisenter(CancelLisenter cancelLisenter) {
        this.cancelLisenter = cancelLisenter;
    }
}
