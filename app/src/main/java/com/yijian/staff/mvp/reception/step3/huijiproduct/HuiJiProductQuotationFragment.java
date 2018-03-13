package com.yijian.staff.mvp.reception.step3.huijiproduct;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.bean.GoodsInfo;
import com.yijian.staff.mvp.reception.step3.huijiproduct.adapter.HuiJiProductQuotationListAdapter;
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


    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_price)
    ImageView ivPrice;

    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;

    Unbinder unbinder;

    private List<GoodsInfo> mGoodsInfoList = new ArrayList<>();

    public HuiJiProductQuotationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hui_ji_product_quotation, container, false);
        unbinder = ButterKnife.bind(this, view);
        initGoodsList();

        return view;
    }

    private void initGoodsList() {
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

            LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
            //设置RecyclerView 布局
            goodsRcv.setLayoutManager(layoutmanager);
            HuiJiProductQuotationListAdapter goodsListAdapter = new HuiJiProductQuotationListAdapter(getContext(), mGoodsInfoList);
            goodsRcv.setAdapter(goodsListAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }




    @OnClick({R.id.ll_zong_he, R.id.ll_price, R.id.ll_shai_xuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zong_he:
                break;
            case R.id.ll_price:
                break;
            case R.id.ll_shai_xuan:
                showPopuWindow();
                break;
        }
    }

    private void showPopuWindow() {
        FilterDialog filterDialog = new FilterDialog(getActivity());
        filterDialog.showFilterDialog();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
