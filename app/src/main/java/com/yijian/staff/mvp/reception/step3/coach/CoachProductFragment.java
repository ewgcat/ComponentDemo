package com.yijian.staff.mvp.reception.step3.coach;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/13 19:25:37
 */
public class CoachProductFragment extends Fragment {
    @BindView(R.id.rl_coach_goods)
    RelativeLayout rlCoachGoods;
    @BindView(R.id.tv_send_to_status)
    TextView tvSendToStatus;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_viper_phone)
    TextView tvViperPhone;
    @BindView(R.id.tv_jiedai_name)
    TextView tvJiedaiName;
    @BindView(R.id.tv_coach_name)
    TextView tvCoachName;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_jianshenplace)
    TextView tvJianshenplace;
    @BindView(R.id.tv_yu_er)
    TextView tvYuEr;
    @BindView(R.id.tv_chuzhiyouhui)
    TextView tvChuzhiyouhui;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.item_view)
    LinearLayout itemView;
    Unbinder unbinder;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.ll_to_leader)
    LinearLayout llToLeader;

    private View view;
    private PopupWindow popupWindow;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coach_product_quotation, container, false);

        unbinder = ButterKnife.bind(this, view);

        initSelectLeaderPopupWindow();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_wenjuan, R.id.tv_ticebaogao, R.id.ll_to_leader})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wenjuan:
                startActivity(new Intent(getContext(),QuestionnaireResultActivity.class));
                break;
            case R.id.tv_ticebaogao:
                startActivity(new Intent(getContext(),PhysicalReportActivity.class));
                break;

            case R.id.ll_to_leader:
                showPopupWindow();
                break;
        }
    }

    private void initSelectLeaderPopupWindow() {

        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.select_leader_pop_window, null);
        int width = WindowManager.LayoutParams.WRAP_CONTENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        popupWindow = new PopupWindow(contentView, width, height, true);


        final TextView cancel = contentView.findViewById(R.id.tv_cancel);
        final TextView confirm = contentView.findViewById(R.id.tv_confirm);
        final EditText etToReason = contentView.findViewById(R.id.et_to_reason);
        final ImageView ivKeFuHead = contentView.findViewById(R.id.iv_kefu_head);
        final TextView tvKefuName = contentView.findViewById(R.id.tv_kefu_name);
        final TextView tvSelectKefu = contentView.findViewById(R.id.tv_select_kefu);

        final ImageView ivCoachHead = contentView.findViewById(R.id.iv_coach_head);
        final TextView tvCoachName = contentView.findViewById(R.id.tv_coach_name);
        final TextView tvSelectCoach = contentView.findViewById(R.id.tv_select_coach);

        final ImageView ivDianzhangHead = contentView.findViewById(R.id.iv_dianzhang_head);
        final TextView tvDianzhangName = contentView.findViewById(R.id.tv_dianzhang_name);
        final TextView tvSelectDianzhang = contentView.findViewById(R.id.tv_select_dianzhang);


        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setTouchable(true);
        //添加popupWindow窗口关闭监听
        popupWindow.setOnDismissListener(() -> backgroundAlpha(1f));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                //TODO 发送请求
                rlCoachGoods.setVisibility(View.GONE);
                tvSendToStatus.setVisibility(View.VISIBLE);

            }
        });

        tvSelectKefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSelectKefu.setTextColor(Color.parseColor("#FFFFFF"));
                tvSelectCoach.setTextColor(Color.parseColor("#999999"));
                tvSelectDianzhang.setTextColor(Color.parseColor("#999999"));
                tvSelectKefu.setBackground(getContext().getDrawable(R.drawable.blue_solid_select_bg));
                tvSelectCoach.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSelectDianzhang.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
            }
        });

        tvSelectCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSelectCoach.setTextColor(Color.parseColor("#FFFFFF"));
                tvSelectKefu.setTextColor(Color.parseColor("#999999"));
                tvSelectDianzhang.setTextColor(Color.parseColor("#999999"));
                tvSelectCoach.setBackground(getContext().getDrawable(R.drawable.blue_solid_select_bg));
                tvSelectKefu.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSelectDianzhang.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
            }
        });

        tvSelectDianzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSelectDianzhang.setTextColor(Color.parseColor("#FFFFFF"));
                tvSelectCoach.setTextColor(Color.parseColor("#999999"));
                tvSelectKefu.setTextColor(Color.parseColor("#999999"));
                tvSelectDianzhang.setBackground(getContext().getDrawable(R.drawable.blue_solid_select_bg));
                tvSelectCoach.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSelectKefu.setBackground(getContext().getDrawable(R.drawable.gray_stroke_unselect_bg));
            }
        });


        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        // 设置好参数之后再show
        popupWindow.setOutsideTouchable(false);

    }

    private void showPopupWindow() {
        backgroundAlpha(0.3f);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


}
