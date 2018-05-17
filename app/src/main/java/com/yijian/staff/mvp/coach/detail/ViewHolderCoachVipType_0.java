package com.yijian.staff.mvp.coach.detail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoachVipDetailBean;
import com.yijian.staff.util.ImageLoader;

/**
 * Created by The_P on 2018/5/17.
 */

public class ViewHolderCoachVipType_0 extends ViewHolderCoachVipper {
//    @BindView(R.id.iv_head)
    ImageView ivHead;
//    @BindView(R.id.tv_name)
    TextView tvName;
//    @BindView(R.id.tv_card_no)
    TextView tv_card_no;
    private final ImageView ivCall;

    public ViewHolderCoachVipType_0(View itemView) {
        super(itemView);
        ivHead= itemView.findViewById(R.id.iv_head);
        tvName= itemView.findViewById(R.id.tv_name);
        tv_card_no= itemView.findViewById(R.id.tv_card_no);

        ivCall = itemView.findViewById(R.id.iv_call);
    }

    @Override
    public void bindView(CoachVipDetailBean coachVipDetailBean) {
        ImageLoader.setImageResource(coachVipDetailBean.getHeadImg(), itemView.getContext(), ivHead);
        tvName.setText(coachVipDetailBean.getName());
        tv_card_no.setText(coachVipDetailBean.getMemberCardNo());
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huijiVipInterface!=null)huijiVipInterface.visit();
            }
        });

    }

}
