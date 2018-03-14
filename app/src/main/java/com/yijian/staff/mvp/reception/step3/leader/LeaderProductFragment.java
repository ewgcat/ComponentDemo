package com.yijian.staff.mvp.reception.step3.leader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijian.staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/13 19:25:37
 */
public class LeaderProductFragment extends Fragment {

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

    @BindView(R.id.tv_to_reason)
    TextView tvToReason;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_leader_product_quotation, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_wenjuan, R.id.tv_ticebaogao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wenjuan:
                break;
            case R.id.tv_ticebaogao:
                break;

        }
    }


}
