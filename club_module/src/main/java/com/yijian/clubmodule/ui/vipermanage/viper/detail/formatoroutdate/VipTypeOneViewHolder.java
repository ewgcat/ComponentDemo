package com.yijian.clubmodule.ui.vipermanage.viper.detail.formatoroutdate;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ViperDetailBean;
import com.yijian.clubmodule.ui.vipermanage.viper.detail.BaseVipperViewHolder;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.ImageLoader;

/**
 * Created by The_P on 2018/5/15.
 */

public class VipTypeOneViewHolder extends BaseVipperViewHolder {
    ImageView ivHead;
    ImageView iv_rank;
    TextView tv_protect_seven;
    TextView tvName;
    TextView tv_card_no;
    LinearLayout ll_card_tips;

    private final ImageView ivVisit;


    public VipTypeOneViewHolder(View itemView) {
        super(itemView);
        ivHead = itemView.findViewById(R.id.iv_head);
        iv_rank = itemView.findViewById(R.id.iv_rank);
        tvName = itemView.findViewById(R.id.tv_name);
        tv_protect_seven = itemView.findViewById(R.id.tv_protect_seven);
        ll_card_tips = itemView.findViewById(R.id.ll_card_tips);
        tv_card_no = itemView.findViewById(R.id.tv_card_no);

        ivVisit = itemView.findViewById(R.id.iv_visit);
    }

    @Override
    public void bindView(ViperDetailBean viperDetailBean) {
        ImageLoader.setHeadImageResource((viperDetailBean.getHeadImg() == null) ? "" : SharePreferenceUtil.getHostUrl() + viperDetailBean.getHeadImg(), itemView.getContext(), ivHead);
        tvName.setText(judgeNull(viperDetailBean.getName()));
        tv_card_no.setText(judgeNull(viperDetailBean.getMemberCardNo()));

        if (TextUtils.isEmpty(viperDetailBean.getMemberCardNo())){
            ll_card_tips.setVisibility(View.GONE);
        }else {
            ll_card_tips.setVisibility(View.VISIBLE);
            tv_card_no.setText(viperDetailBean.getMemberCardNo());
        }
        //回访
        Boolean isProtected = viperDetailBean.isUnderProtected();
        tv_protect_seven.setVisibility(isProtected ? View.VISIBLE : View.GONE);
        tv_protect_seven.setText("保护"+viperDetailBean.getProtectedDay()+"天");
        ivVisit.setVisibility(isProtected ? View.GONE : View.VISIBLE);

        ivVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huijiVipInterface != null) huijiVipInterface.visit();
            }
        });
        Integer  medalType = viperDetailBean.getMedalType();
        if(medalType==null){

        }else{
            if (medalType==0){

            }else if (medalType==1){
                ImageLoader.setImageResource(R.mipmap.member_gray, itemView.getContext(), iv_rank);
            }else if (medalType==2){
                ImageLoader.setImageResource(R.mipmap.member_gold, itemView.getContext(), iv_rank);
            }
        }


    }

}
