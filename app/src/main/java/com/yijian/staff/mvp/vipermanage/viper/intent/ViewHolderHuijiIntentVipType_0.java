package com.yijian.staff.mvp.vipermanage.viper.intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.ViperDetailBean;
import com.yijian.staff.mvp.vipermanage.viper.detail.ViewHolderHuijiVipper;
import com.yijian.staff.util.ImageLoader;

/**
 * Created by The_P on 2018/5/16.
 */

class ViewHolderHuijiIntentVipType_0 extends ViewHolderHuijiVipper {
    //    @BindView(R.id.iv_head)
    ImageView ivHead;
    //    @BindView(R.id.tv_name)
    TextView tvName;
    TextView tv_card_no;
    TextView tv_card_tips;
    ImageView ivVisit;

    public ViewHolderHuijiIntentVipType_0(View itemView) {
        super(itemView);
        ivHead = itemView.findViewById(R.id.iv_head);
        tvName = itemView.findViewById(R.id.tv_name);
        tv_card_no = itemView.findViewById(R.id.tv_card_no);
        tv_card_tips = itemView.findViewById(R.id.tv_card_tips);

        ivVisit = itemView.findViewById(R.id.iv_visit);

    }

    @Override
    public void bindView(ViperDetailBean viperDetailBean) {
        ImageLoader.setHeadImageResource((viperDetailBean.getHeadImg() == null) ? "" : BuildConfig.FILE_HOST + viperDetailBean.getHeadImg(), itemView.getContext(), ivHead);
        tvName.setText(judgeNull(viperDetailBean.getName()));

//        if (TextUtils.isEmpty(viperDetailBean.getMemberCardNo())){
//            tv_card_tips.setVisibility(View.GONE);
//        }else {
//            tv_card_tips.setVisibility(View.VISIBLE);
//            tv_card_no.setText(viperDetailBean.getMemberCardNo());
//        }
        ivVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huijiVipInterface != null) huijiVipInterface.visit();
            }
        });
    }
}
