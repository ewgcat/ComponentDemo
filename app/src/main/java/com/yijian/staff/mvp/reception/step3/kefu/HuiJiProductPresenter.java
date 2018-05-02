package com.yijian.staff.mvp.reception.step3.kefu;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.step3.bean.CardInfo;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.reception.step3.bean.RecptionCards;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by The_P on 2018/4/11.
 */

public class HuiJiProductPresenter implements HuiJiProductContract.Presenter {
    private Context context;
    private HuiJiProductContract.View view;


    public HuiJiProductPresenter(Context context) {
        this.context = context;
    }

    public void setView(HuiJiProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getRecptionCards(ConditionBody bodyCondition, boolean isRefresh) {

        HttpManager.getHuiJiCardGoodsList_ycm(bodyCondition, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                RecptionCards recptionCards = new Gson().fromJson(result.toString(), RecptionCards.class);

                List<CardInfo> records = recptionCards.getRecords();
                if (records == null || records.size() == 0) {
                    view.showNoCards(isRefresh, true);
                } else {
                    view.showCards(records, isRefresh);
                }

            }

            @Override
            public void onFail(String msg) {
                view.showNoCards(isRefresh, false);
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void toCoach(String memberId, String cardId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("cardId", cardId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP3_TO_COACH, params, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                view.showToCoachSucceed();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void getStatus(boolean isFirst, String memberId) {

        Map<String, String> params = new HashMap<>();
        params.put("memberId",memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STATUS,params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ReceptionStastuBean receptionStastuBean = GsonNullString.getGson().fromJson(result.toString(), ReceptionStastuBean.class);
                Integer operatorType = receptionStastuBean.getOperatorType();
                if (receptionStastuBean == null || operatorType == null)
                    return;

                if (operatorType ==40|| operatorType >40) {
                    view.showStatus(receptionStastuBean);
                } else {
                    if (isFirst) {
                        view.shouldCardToOrder();
                    } else {


//            case 30:// SALEJUMPCOACH(30, "会籍跳过教练"),
//            case 33://SALETOCOACH(33, "会员没购买意愿，会籍TO教练"),
//            case 34:// COACHTOSALE(34, "教练接待会员，会员同意购买,TO回会籍"),
//            case 35:// COACHTOLEADER(35, "教练接待会员，会员不同意购买,TO领导 "),
//            case 36:// LEADERTOSALE(36, "领导接待会员,TO回会籍 "),
                        if (operatorType==33){
                            Toast.makeText(context,"会员没购买意愿，会籍通知教练",Toast.LENGTH_SHORT).show();
                        }else if (operatorType==34){
                            Toast.makeText(context,"教练接待会员，会员同意购买,通知回会籍",Toast.LENGTH_SHORT).show();
                        }else if (operatorType==35){
                            Toast.makeText(context,"教练接待会员，会员不同意购买,通知领导 ",Toast.LENGTH_SHORT).show();
                        }else if (operatorType==36){
                            Toast.makeText(context,"领导接待会员,通知回会籍",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"节点出现异常",Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void cardToOrder(String memberId, String cardprodbaseId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("cardId", cardprodbaseId);


        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP3_CARD_TO_ORDER, params, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                view.showCardToOrder();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void getProductDetail(String memberId) {
        Map<String,String> params=new HashMap<>();
        params.put("memberId",memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STEP3_PRODUCT_DETAIL, params, new ResultJSONObjectObserver() {
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


    public ConditionBody resetBody(ConditionBody body) {

        body.setPageNum(1);
        body.setPageSize(10);
        body.setCardName(null);
        body.setCardType(null);
        body.setStartPrice(null);
        body.setEndPrice(null);
        body.setIsSortByPrice(null);
        body.setVenueName(null);

        return body;
    }

    public ConditionBody resetBodyPage(ConditionBody body) {

        body.setPageNum(1);
        body.setPageSize(10);

        return body;
    }
}
