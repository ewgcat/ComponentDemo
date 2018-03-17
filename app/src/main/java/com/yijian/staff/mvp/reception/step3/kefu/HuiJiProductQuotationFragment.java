package com.yijian.staff.mvp.reception.step3.kefu;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.bean.GoodsInfo;
import com.yijian.staff.mvp.reception.step3.kefu.adapter.HuiJiProductQuotationListAdapter;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HuiJiProductQuotationFragment extends Fragment {

    @BindView(R.id.rl_goods)
    RelativeLayout rlGoods;
    @BindView(R.id.tv_send_to_status)
    TextView tvSendToStatus;

    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;

    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;




    Unbinder unbinder;



    private List<GoodsInfo> mGoodsInfoList = new ArrayList<>();
    private HuiJiProductQuotationListAdapter goodsListAdapter;
    private FilterDialog filterDialog;
    private GoodsInfo selectedGoodsInfo;


    public HuiJiProductQuotationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hui_ji_product_quotation, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局
        goodsRcv.setLayoutManager(layoutmanager);
        goodsListAdapter = new HuiJiProductQuotationListAdapter(getContext(), mGoodsInfoList);
        goodsRcv.setAdapter(goodsListAdapter);
        initGoodsList();
        filterDialog = new FilterDialog(getActivity());
        goodsListAdapter.setOnItemClickListener(new HuiJiProductQuotationListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, GoodsInfo goodsInfo) {
                selectedGoodsInfo = goodsInfo;
            }
        });
        return view;
    }

    private void initGoodsList() {
        mGoodsInfoList.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("goodsName", "十周年纪念卡");
            jsonObject.put("jianshenplace", "游泳");
            jsonObject.put("yuer", "23次");
            jsonObject.put("chuzhiyouhui", "赠送20%");
            jsonObject.put("price", "1400元");
            for (int i = 0; i < 10; i++) {
                GoodsInfo goodsInfo = new GoodsInfo(jsonObject);
                mGoodsInfoList.add(goodsInfo);
            }
            goodsListAdapter.update(mGoodsInfoList);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }


    @OnClick({R.id.ll_zong_he, R.id.ll_price, R.id.ll_shai_xuan, R.id.ll_to_coach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zong_he:
                selectZongHe();
                break;
            case R.id.ll_price:
                selectPrice();
                break;
            case R.id.ll_shai_xuan:
                selectShaixuan();
                break;
            case R.id.ll_to_coach:
                //TODO 转给教练
                if (selectedGoodsInfo != null) {
                    rlGoods.setVisibility(View.GONE);
                    tvSendToStatus.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "请先点击选取一个产品,再TO给教练!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    //点击筛选
    private void selectShaixuan() {
        if (tvShaixuan.getTextColors().getDefaultColor() == Color.parseColor("#1997F8")) {
            showFilterDialog();

        } else {
            tvShaixuan.setTextColor(Color.parseColor("#1997F8"));
            tvZongHe.setTextColor(Color.parseColor("#666666"));
            tvPrice.setTextColor(Color.parseColor("#666666"));
            Drawable drawablePrice = getContext().getResources().getDrawable(R.mipmap.jd_normal_arrow);
            drawablePrice.setBounds(0, 0, drawablePrice.getMinimumWidth(), drawablePrice.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawablePrice, null);

            Drawable drawableShaixuan = getContext().getResources().getDrawable(R.mipmap.shaixuan_blue);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
            showFilterDialog();

        }
    }

    private boolean priceUp = false;

    //点击价格
    private void selectPrice() {
        if (tvPrice.getTextColors().getDefaultColor() == Color.parseColor("#1997F8")) {
            if (priceUp) {
                Drawable drawable = getContext().getResources().getDrawable(R.mipmap.jd_down_arrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvPrice.setCompoundDrawables(null, null, drawable, null);
                priceUp = false;
                //TODO 请求 (价格从高到低）
            } else {
                Drawable drawable = getContext().getResources().getDrawable(R.mipmap.jd_up_arrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvPrice.setCompoundDrawables(null, null, drawable, null);
                priceUp = true;
                //TODO 请求 (价格从低到高）
            }
        } else {
            tvPrice.setTextColor(Color.parseColor("#1997F8"));
            tvZongHe.setTextColor(Color.parseColor("#666666"));
            tvShaixuan.setTextColor(Color.parseColor("#666666"));
            Drawable drawable = getContext().getResources().getDrawable(R.mipmap.jd_up_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Drawable drawableShaixuan = getContext().getResources().getDrawable(R.mipmap.shaixuan_black);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
            //TODO 请求 (价格从低到高）
        }
    }

    private void selectZongHe() {
        if (tvZongHe.getTextColors().getDefaultColor() == Color.parseColor("#1997F8")) {

        } else {
            tvZongHe.setTextColor(Color.parseColor("#1997F8"));
            tvPrice.setTextColor(Color.parseColor("#666666"));
            tvShaixuan.setTextColor(Color.parseColor("#666666"));
            Drawable drawable = getContext().getResources().getDrawable(R.mipmap.jd_normal_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Drawable drawableShaixuan = getContext().getResources().getDrawable(R.mipmap.shaixuan_black);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
            //TODO 请求
            initGoodsList();
        }

    }


    private void showFilterDialog() {
        filterDialog.showFilterDialog();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
