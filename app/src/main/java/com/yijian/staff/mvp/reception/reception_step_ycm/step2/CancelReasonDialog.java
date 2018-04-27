package com.yijian.staff.mvp.reception.reception_step_ycm.step2;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yijian.staff.R;

/**
 * Created by The_P on 2018/4/23.
 */

public class CancelReasonDialog extends DialogFragment {

    private String cancelReason;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reception_step2_cancel_reason, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView tvOk = view.findViewById(R.id.tv_ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oklisenter!=null)oklisenter.onClick();
                dismiss();
            }
        });

        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(TextUtils.isEmpty(cancelReason)?"其他":cancelReason);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        cancelReason = arguments.getString("cancelReason");

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.shape_fillet_white_8);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    public interface DialogOklisenter{
        void onClick();
    }
    private DialogOklisenter oklisenter;

    public void setOklisenter(DialogOklisenter oklisenter) {
        this.oklisenter = oklisenter;
    }
}
