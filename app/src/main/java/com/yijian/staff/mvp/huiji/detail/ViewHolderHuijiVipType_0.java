package com.yijian.staff.mvp.huiji.detail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.util.ImageLoader;

/**
 * Created by The_P on 2018/5/15.
 */

public class ViewHolderHuijiVipType_0 extends  ViewHolderHuijiVipper{
//    @BindView(R.id.iv_head)
    ImageView ivHead;
//    @BindView(R.id.tv_name)
    TextView tvName;
//    @BindView(R.id.tv_card_no)
    TextView tv_card_no;
    private final ImageView ivVisit;


    public ViewHolderHuijiVipType_0(View itemView) {
        super(itemView);
        ivHead=   itemView.findViewById(R.id.iv_head);
        tvName=   itemView.findViewById(R.id.tv_name);
        tv_card_no=   itemView.findViewById(R.id.tv_card_no);

        ivVisit = itemView.findViewById(R.id.iv_visit);
    }

    @Override
   public void bindView(VipDetailBean vipDetailBean) {
        ImageLoader.setImageResource((vipDetailBean.getHeadImg() == null) ? "" : vipDetailBean.getHeadImg(), itemView.getContext(), ivHead);
        tvName.setText(judgeNull(vipDetailBean.getName()));
        tv_card_no.setText(judgeNull(vipDetailBean.getMemberCardNo()));

        ivVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (huijiVipInterface!=null)huijiVipInterface.visit();
            }
        });
    }

//    private HuijiVipInterface huijiVipInterface;
//
//    public void setHuijiVipInterface(HuijiVipInterface huijiVipInterface) {
//        this.huijiVipInterface = huijiVipInterface;
//    }
}
