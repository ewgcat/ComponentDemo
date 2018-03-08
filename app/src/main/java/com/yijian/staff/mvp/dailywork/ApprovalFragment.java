package com.yijian.staff.mvp.dailywork;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;

/**
 * 审批
 */
public class ApprovalFragment extends Fragment {

    private static ApprovalFragment approvalFragment;
    public static ApprovalFragment getInstance(){
        if(approvalFragment == null){
            approvalFragment = new ApprovalFragment();
        }
        return approvalFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apporal,container,false);
        return view;
    }
}
