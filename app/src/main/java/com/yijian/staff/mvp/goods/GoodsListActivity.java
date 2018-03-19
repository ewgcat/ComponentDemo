package com.yijian.staff.mvp.goods;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.goods.adapter.GoodsListAdapter;
import com.yijian.staff.mvp.goods.FilterDialog ;
import com.yijian.staff.mvp.goods.bean.GoodsInfo;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = "/test/9")
public class GoodsListActivity extends AppCompatActivity {


    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;

    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;

    private List<GoodsInfo> mGoodsInfoList = new ArrayList<>();
    private GoodsListAdapter goodsListAdapter;
    private FilterDialog filterDialog;
    private GoodsInfo selectedGoodsInfo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
          ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       EditText etSearch = findViewById(R.id.et_search);
        etSearch.setHintTextColor(Color.parseColor("#fafbfb"));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        //TODO 点击搜索键,触发搜索请求

                        break;
                }
                return true;
            }
        });



        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        goodsRcv.setLayoutManager(layoutmanager);
        goodsListAdapter = new GoodsListAdapter(this, mGoodsInfoList);
        goodsRcv.setAdapter(goodsListAdapter);

        initGoodsList();

        filterDialog = new FilterDialog(this);
//        goodsListAdapter.setOnItemClickListener(new HuiJiProductQuotationListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, GoodsInfo goodsInfo) {
//                selectedGoodsInfo = goodsInfo;
//                //TODO 跳转到商品详情
//            }
//        });
        selectZongHe();

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

    @OnClick({R.id.ll_zong_he, R.id.ll_price, R.id.ll_shai_xuan})
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
            Drawable drawablePrice = getResources().getDrawable(R.mipmap.jd_normal_arrow);
            drawablePrice.setBounds(0, 0, drawablePrice.getMinimumWidth(), drawablePrice.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawablePrice, null);

            Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_blue);
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
                Drawable drawable = getResources().getDrawable(R.mipmap.jd_down_arrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvPrice.setCompoundDrawables(null, null, drawable, null);
                priceUp = false;
                //TODO 请求 (价格从高到低）
            } else {
                Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvPrice.setCompoundDrawables(null, null, drawable, null);
                priceUp = true;
                //TODO 请求 (价格从低到高）
            }
        } else {
            tvPrice.setTextColor(Color.parseColor("#1997F8"));
            tvZongHe.setTextColor(Color.parseColor("#666666"));
            tvShaixuan.setTextColor(Color.parseColor("#666666"));
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_black);
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
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_normal_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_black);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
            //TODO 请求
            initGoodsList();
        }

    }


    private void showFilterDialog() {
        filterDialog.showFilterDialog();
    }
}
