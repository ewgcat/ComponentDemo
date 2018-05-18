package com.yijian.staff.mvp.huiji.intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.mvp.huiji.detail.ViewHolderHuijiVipper;
import com.yijian.staff.util.ImageLoader;

/**
 * Created by The_P on 2018/5/16.
 */

class ViewHolderHuijiIntentVipType_0 extends ViewHolderHuijiVipper {
//    @BindView(R.id.iv_head)
    ImageView ivHead;
//    @BindView(R.id.tv_name)
    TextView tvName;

    ImageView ivVisit;
    public ViewHolderHuijiIntentVipType_0(View itemView) {
        super(itemView);
        ivHead=   itemView.findViewById(R.id.iv_head);
        tvName=   itemView.findViewById(R.id.tv_name);
        ivVisit=   itemView.findViewById(R.id.iv_visit);

    }

    @Override
    public void bindView(VipDetailBean vipDetailBean) {
        ImageLoader.setImageResource((vipDetailBean.getHeadImg() == null) ? "" : BuildConfig.FILE_HOST+ vipDetailBean.getHeadImg(), itemView.getContext(), ivHead);
        tvName.setText(judgeNull(vipDetailBean.getName()));

        ivVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huijiVipInterface!=null)huijiVipInterface.visit();
            }
        });
    }
}
