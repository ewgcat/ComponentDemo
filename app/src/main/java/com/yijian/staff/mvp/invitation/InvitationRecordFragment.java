package com.yijian.staff.mvp.invitation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yijian.staff.R;
import com.yijian.staff.util.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * 邀约记录
 */
public class InvitationRecordFragment extends Fragment {

    private static InvitationRecordFragment invitationRecordFragment;
    public static InvitationRecordFragment getInstance(){
        if(invitationRecordFragment == null){
            invitationRecordFragment = new InvitationRecordFragment();
        }
        return invitationRecordFragment;
    }

    RecyclerView rv_invitation;
    private List<InvitationInfo> invitationInfoList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invitation_record, container, false);
        rv_invitation = view.findViewById(R.id.rv_invitation);
        initIinvitationList();
        return view;
    }

    private void initIinvitationList(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("overTime", "1990-8-9");
            jsonObject.put("overReason", "农历");
            jsonObject.put("invitationType", "打橄榄球");
            for (int i = 0; i < 10; i++) {
                InvitationInfo vipPeopleInfo = new InvitationInfo(jsonObject);
                invitationInfoList.add(vipPeopleInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            //设置RecyclerView 布局
            rv_invitation.setLayoutManager(layoutmanager);
            InvitationRecordAdatper invitationRecordAdatper = new InvitationRecordAdatper(getActivity(), invitationInfoList);
            rv_invitation.setAdapter(invitationRecordAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }

}
