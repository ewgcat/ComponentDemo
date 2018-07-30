package com.yijian.staff.mvp.price.cardprice.filter;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.arch.lifecycle.Lifecycle;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.bean.CardRequestBody;

import com.yijian.staff.bean.VenueBean;
import com.yijian.staff.bean.VenueWrapBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DensityUtil;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */
@SuppressLint("ValidFragment")
public class OptionDialog extends DialogFragment implements View.OnClickListener {

    private OptionAdapter optionAdapter;
    private TextView tvTimeCard;
    private TextView tvCishuCard;
    private TextView tvChuzhiCard;
    private TextView tvHuiyuanCard;
    private TextView tvPrice1;
    private TextView tvPrice2;
    private TextView tvPrice3;
    private TextView tvPrice4;
    private TextView tvReset;
    private TextView tvConfirm;
    private static final String TAG = "OptionDialog";
    private Lifecycle lifecycle;

    private CardRequestBody conditionBody = new CardRequestBody();

    public OptionDialog(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        String cardType = arguments.getString("cardType");
        String startPrice = arguments.getString("startPrice");
        String venueId = arguments.getString("venueId");
        String cardName = arguments.getString("cardName");
        conditionBody.setVenueId(venueId);
        conditionBody.setStartPrice(startPrice);
        conditionBody.setCardType(cardType);
        conditionBody.setCardName(cardName);

    }


    @Override
    public void onStart() {

        //设置DialogFragment所依附的window背景透明（不设置会有一块灰色的背景）
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置dialog的位置（自定义的布局并没有显示在window的中间，没达到我想要的效果）
        getDialog().getWindow().setGravity(Gravity.RIGHT);
        //设置Window的大小，想要自定义Dialog的位置摆放正确，将Window的大小保持和自定义Dialog的大小一样
        getDialog().getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_filter_goods_ycm, container, false);
        initView(view);
        initData();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
    }


    private void initData() {
        HttpManager.getHasHeaderNoParam(HttpManager.RECEPTION_STEP3_VENUES, new ResultJSONObjectObserver(lifecycle) {
            @Override
            public void onSuccess(JSONObject result) {
                VenueWrapBean venueWrapBean = new Gson().fromJson(result.toString(), VenueWrapBean.class);
                List<VenueBean> dataList = venueWrapBean.getOptions();
                if (dataList == null || dataList.size() == 0) {
                    return;
                }

                String venueId = conditionBody.getVenueId();
                for (int i = 0; i < dataList.size(); i++) {
                    String id = dataList.get(i).getVenueId();
                    if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(venueId)) {
                        if (id.equals(venueId.trim()))
                            dataList.get(i).setSelect(true);
                    }

                }

                optionAdapter.resetData(dataList);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView(View view) {
        View empty_view = view.findViewById(R.id.empty_view);

        tvTimeCard = view.findViewById(R.id.tv_time_card);
        tvCishuCard = view.findViewById(R.id.tv_cishu_card);
        tvChuzhiCard = view.findViewById(R.id.tv_chuzhi_card);
        tvHuiyuanCard = view.findViewById(R.id.tv_huiyuan_card);

        tvPrice1 = view.findViewById(R.id.tv_price1);
        tvPrice2 = view.findViewById(R.id.tv_price2);
        tvPrice3 = view.findViewById(R.id.tv_price3);
        tvPrice4 = view.findViewById(R.id.tv_price4);

        tvReset = view.findViewById(R.id.tv_reset);
        tvConfirm = view.findViewById(R.id.tv_confirm);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        optionAdapter = new OptionAdapter(getActivity());
        recyclerView.setAdapter(optionAdapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, 0);
                outRect.bottom = DensityUtil.dip2px(getActivity(), 10);
                if (parent.getChildLayoutPosition(view) % 2 == 0) {
                    outRect.left = 0;
                } else {
                    outRect.left = DensityUtil.dip2px(getActivity(), 10);
                }
            }
        });
        optionAdapter.setLisenter(new OptionAdapter.OptionLisenter() {
            @Override
            public void onVenueClick(int position, VenueBean venueBean) {
                conditionBody.setVenueId(venueBean.getVenueId());

            }
        });

        tvTimeCard.setOnClickListener(this);
        tvCishuCard.setOnClickListener(this);
        tvChuzhiCard.setOnClickListener(this);
        tvHuiyuanCard.setOnClickListener(this);
        tvPrice1.setOnClickListener(this);
        tvPrice2.setOnClickListener(this);
        tvPrice3.setOnClickListener(this);
        tvPrice4.setOnClickListener(this);

        tvReset.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        empty_view.setOnClickListener(this);


//        conditionBody.setIsSortByPrice(bodyCondition.getIsSortByPrice());
//        conditionBody.setVenueName(bodyCondition.getVenueName());
//        conditionBody.setStartPrice(bodyCondition.getStartPrice());
//        conditionBody.setEndPrice(bodyCondition.getEndPrice());
//        conditionBody.setCardType(bodyCondition.getCardType());

//        Log.e(TAG, "initView: " );
        String cardType = conditionBody.getCardType();
        if (!TextUtils.isEmpty(cardType)) {
            if ("0".equals(cardType.trim())) {
                setSelectStyle(tvTimeCard);
                conditionBody.setCardType("0");
            } else if ("1".equals(cardType.trim())) {
                setSelectStyle(tvCishuCard);
                conditionBody.setCardType("1");
            } else if ("2".equals(cardType.trim())) {
                setSelectStyle(tvChuzhiCard);
                conditionBody.setCardType("2");
            } else if ("3".equals(cardType.trim())) {
                setSelectStyle(tvHuiyuanCard);
                conditionBody.setCardType("3");
            }
        }
        String startPrice = conditionBody.getStartPrice();
        if (!TextUtils.isEmpty(startPrice)) {
            if (startPrice.trim().equals("0")) {
                setSelectStyle(tvPrice1);
                conditionBody.setStartPrice("0");
                conditionBody.setEndPrice("1000");
            } else if (startPrice.trim().equals("1000")) {
                setSelectStyle(tvPrice2);
                conditionBody.setStartPrice("1000");
                conditionBody.setEndPrice("2000");
            } else if (startPrice.trim().equals("2000")) {
                setSelectStyle(tvPrice3);
                conditionBody.setStartPrice("2000");
                conditionBody.setEndPrice("3000");
            } else if (startPrice.trim().equals("3000")) {
                setSelectStyle(tvPrice4);
                conditionBody.setStartPrice("3000");
                conditionBody.setEndPrice(null);
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        conditionBody.setStartPrice(null);
        conditionBody.setEndPrice(null);
        conditionBody.setCardType(null);
        conditionBody.setVenueId(null);
        conditionBody.setCardName(null);
        super.onDismiss(dialog);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_time_card:
                resetCardType();
                setSelectStyle(tvTimeCard);
                conditionBody.setCardType("0");
                break;
            case R.id.tv_cishu_card:
                resetCardType();
                setSelectStyle(tvCishuCard);
                conditionBody.setCardType("1");
                break;
            case R.id.tv_chuzhi_card:
                resetCardType();
                setSelectStyle(tvChuzhiCard);
                conditionBody.setCardType("2");
                break;
            case R.id.tv_huiyuan_card:
                resetCardType();
                setSelectStyle(tvHuiyuanCard);
                conditionBody.setCardType("3");
                break;
            case R.id.tv_price1:
                resetPriseStyle();
                setSelectStyle(tvPrice1);
                conditionBody.setStartPrice("0");
                conditionBody.setEndPrice("1000");
                break;
            case R.id.tv_price2:
                resetPriseStyle();
                setSelectStyle(tvPrice2);
                conditionBody.setStartPrice("1000");
                conditionBody.setEndPrice("2000");
                break;
            case R.id.tv_price3:
                resetPriseStyle();
                setSelectStyle(tvPrice3);
                conditionBody.setStartPrice("2000");
                conditionBody.setEndPrice("3000");
                break;
            case R.id.tv_price4:
                resetPriseStyle();
                setSelectStyle(tvPrice4);
                conditionBody.setStartPrice("3000");
                conditionBody.setEndPrice(null);
                break;

            case R.id.tv_reset:
                resetCardType();
                resetPriseStyle();
                conditionBody.setStartPrice(null);
                conditionBody.setEndPrice(null);
                conditionBody.setCardType(null);
                conditionBody.setVenueId(null);
                conditionBody.setCardName(null);
                List<VenueBean> list = optionAdapter.getList();
                if (list != null && list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setSelect(false);
                    }
                    optionAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_confirm:
                if (onDismissListener != null) {
//                    String cardType = conditionBody.getCardType();
//                    String endPrice = conditionBody.getEndPrice();
//                    String startPrice = conditionBody.getStartPrice();
//                    String venueId = conditionBody.getVenueId();
//                    String cardName = conditionBody.getCardName();
//                    if (TextUtils.isEmpty(cardType)
//                            &&TextUtils.isEmpty(startPrice)
//                            &&TextUtils.isEmpty(venueId)
//                            &&TextUtils.isEmpty(endPrice)&&TextUtils.isEmpty(cardName)){
//                        onDismissListener.onDismiss(null);
//                    }else {
//                        onDismissListener.onDismiss(conditionBody);
//                    }
                    onDismissListener.onDismiss(conditionBody);
                }
                dismiss();
                break;

            case R.id.empty_view:
//                if (onDismissListener != null){
//                    String cardType = conditionBody.getCardType();
//                    String endPrice = conditionBody.getEndPrice();
//                    String startPrice = conditionBody.getStartPrice();
//                    String venueId = conditionBody.getVenueId();
//                    String cardName = conditionBody.getCardName();
//                    if (TextUtils.isEmpty(cardType)
//                            &&TextUtils.isEmpty(startPrice)
//                            &&TextUtils.isEmpty(venueId)
//                            &&TextUtils.isEmpty(endPrice)&&TextUtils.isEmpty(cardName)){
//                        onDismissListener.onDismiss(null);
//                    }
//                }
                dismiss();
                break;
        }
    }


    private void resetCardType() {
        setUnSelectStyle(tvTimeCard);
        setUnSelectStyle(tvCishuCard);
        setUnSelectStyle(tvChuzhiCard);
        setUnSelectStyle(tvHuiyuanCard);

    }

    private void resetPriseStyle() {
        setUnSelectStyle(tvPrice1);
        setUnSelectStyle(tvPrice2);
        setUnSelectStyle(tvPrice3);
        setUnSelectStyle(tvPrice4);
    }


    private void setSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#1997f8"));
        textView.setBackground(getActivity().getDrawable(R.drawable.blue_stroke_select_bg));
        Drawable jd_choose = getActivity().getResources().getDrawable(R.mipmap.jd_choose);
        jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
        textView.setCompoundDrawables(jd_choose, null, null, null);

    }

    private void setUnSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        textView.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        textView.setCompoundDrawables(null, null, null, null);
    }

    public interface OnDismissListener {
        void onDismiss(CardRequestBody body);
    }

    private OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }
}
