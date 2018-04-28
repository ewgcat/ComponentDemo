package com.yijian.staff.mvp.main.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemNoticeFragment extends Fragment {


    public SystemNoticeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_notice, container, false);
        return view;
    }

}
