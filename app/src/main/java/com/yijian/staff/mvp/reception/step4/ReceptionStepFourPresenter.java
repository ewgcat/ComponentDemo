package com.yijian.staff.mvp.reception.step4;

import android.content.Context;
import android.widget.Toast;

import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/19.
 */

public class ReceptionStepFourPresenter  implements ReceptionStepFourContract.Presenter{
    private Context context;
    private  ReceptionStepFourContract.View view;

    public ReceptionStepFourPresenter(Context context) {
        this.context=context;
    }

    public void setView(ReceptionStepFourContract.View view){
        this.view=view;
    }


    @Override
    public void getProductDetail(String memberId) {
        Map<String,String> params=new HashMap<>();
        params.put("memberId",memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STEP4_GET_ORDER_DETAIL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ProductDetail productDetail = GsonNullString.getGson().fromJson(result.toString(), ProductDetail.class);
                if (productDetail==null)return;
                view.showProductDetail(productDetail);

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void toReceptionStepFive(String memberId) {
        Map<String,String> params=new HashMap<>();
        params.put("memberId",memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STEP4_TOFINISH, params, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                view.showToStepFive();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT);
            }
        });

    }

    @Override
    public void getStatus(boolean isFirst) {
        HttpManager.getHasHeaderNoParam(HttpManager.RECEPTION_STATUS, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ReceptionStastuBean receptionStastuBean = GsonNullString.getGson().fromJson(result.toString(), ReceptionStastuBean.class);
                if (receptionStastuBean==null||receptionStastuBean.getOperatorType()==null)return;
                ////  SALEFINISHCON(40, "会籍完成产品报价，签订合同中”),
                // SALEFINISHCON(41, “已签订合同”),
                //ORDERDETAILNEXT(50, "订单详情点击下一步"),
                if (receptionStastuBean.getOperatorType()==50||receptionStastuBean.getOperatorType()>50){
                    view.showStatus(receptionStastuBean);
                }else {
                    if (isFirst) {
                        view.toReceptionStepFive();
                    }else {
                        Toast.makeText(context,"节点错误",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
