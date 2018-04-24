package com.yijian.staff.mvp.reception.step4;

import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;

/**
 * Created by The_P on 2018/4/19.
 */

public interface ReceptionStepFourContract  {
    interface View{
        void showProductDetail(ProductDetail productDetail);

        void showToStepFive();

        void showStatus(ReceptionStastuBean receptionStastuBean);

//        void shouldOrderToFinish();
    }

    interface Presenter{
        void getProductDetail(String memberId);//获取产品详情

        void toReceptionStepFive(String memberId);//到接待第5步

        void getStatus(boolean isFirst);
    }
}
