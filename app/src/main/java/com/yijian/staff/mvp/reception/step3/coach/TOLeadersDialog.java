package com.yijian.staff.mvp.reception.step3.coach;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.lifecycle.Lifecycle;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.coach.bean.LeaderBean;
import com.yijian.staff.mvp.reception.step3.coach.bean.LeadersBeanWrap;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by The_P on 2018/4/19.
 */
@SuppressLint("ValidFragment")
public class TOLeadersDialog extends DialogFragment {

    private TextView cancel;
    private TextView confirm;
    private EditText etToReason;
    private TOLeaderAdapter toLeaderAdapter;
    private Integer postId;//岗位id
    private Lifecycle lifecycle;

    public TOLeadersDialog(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reception_to_leaders, container, false);

        initView(view);
        initData();

        return view;

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

    private void initData() {
        HttpManager.getHasHeaderNoParam(HttpManager.RECEPTION_STEP3_GET_LEADERS, new ResultJSONObjectObserver(lifecycle) {
            @Override
            public void onSuccess(JSONObject result) {
                LeadersBeanWrap leadersBean = GsonNullString.getGson().fromJson(result.toString(), LeadersBeanWrap.class);
                if (leadersBean == null || leadersBean.getDataList().isEmpty()) {
                    return;
                } else {
                    toLeaderAdapter.resetData(leadersBean.getDataList());
                }


            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


    private void initView(View view) {
        cancel = view.findViewById(R.id.tv_cancel);
        confirm = view.findViewById(R.id.tv_confirm);
        etToReason = view.findViewById(R.id.et_to_reason);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layout);

        toLeaderAdapter = new TOLeaderAdapter(getActivity());

        recyclerView.setAdapter(toLeaderAdapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        toLeaderAdapter.setItemSelectLisenter(new TOLeaderAdapter.ItemSelectLisenter() {


            @Override
            public void onclick(LeaderBean bean) {
                postId = bean.getPost();
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etToReason.getText().toString())) {
                    Toast.makeText(getActivity(), "请填写详情", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (postId == null) {
                    Toast.makeText(getActivity(), "领导id不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (lisenter != null) lisenter.onConfirm(postId, etToReason.getText().toString().trim());


            }
        });


    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        postId = null;
        etToReason.setText("");
    }

    public interface ToLeaderLisenter {
        void onConfirm(Integer postid, String content);
    }

    private ToLeaderLisenter lisenter;

    public void setLisenter(ToLeaderLisenter lisenter) {
        this.lisenter = lisenter;
    }
}
