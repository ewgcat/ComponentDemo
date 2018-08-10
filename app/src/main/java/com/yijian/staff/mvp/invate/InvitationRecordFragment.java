package com.yijian.staff.mvp.invate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.InvitationRecordBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.HuiJiInviteListRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/10 16:32:03
 */

public class InvitationRecordFragment extends Fragment {

    private static InvitationRecordFragment invitationRecordFragment;

    public static InvitationRecordFragment getInstance() {
        if (invitationRecordFragment == null) {
            invitationRecordFragment = new InvitationRecordFragment();
        }
        return invitationRecordFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invitation_record, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }



}
