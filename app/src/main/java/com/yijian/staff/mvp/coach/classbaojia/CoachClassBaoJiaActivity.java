package com.yijian.staff.mvp.coach.classbaojia;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.classbaojia.adapter.ClassListAdapter;
import com.yijian.staff.mvp.coach.classbaojia.bean.ClassInfo;
import com.yijian.staff.mvp.coach.viperlist.filter.CoachFilterViperDialog;
import com.yijian.staff.mvp.coach.viperlist.filter.CoachViperFilterBean;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * （教练）产品报价
 */
@Route(path = "/test/20")
public class CoachClassBaoJiaActivity extends AppCompatActivity {


    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;

    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;

    private List<ClassInfo> mClassInfoList = new ArrayList<>();
    private ClassListAdapter classListAdapter;
    private CoachClassFilterDialog coachClassFilterDialog;
    private ClassInfo selectedClassInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_goods_bao_jia);
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
        classListAdapter = new ClassListAdapter(this, mClassInfoList);
        goodsRcv.setAdapter(classListAdapter);

        initGoodsList();

        coachClassFilterDialog = new CoachClassFilterDialog(this);
        coachClassFilterDialog.setOnDismissListener(new CoachClassFilterDialog.OnDismissListener() {
            @Override
            public void onDismiss(CoachClassFilterBean coachClassFilterBean) {

                //TODO

            }
        });
        selectZongHe();

    }
    private void initGoodsList() {
        mClassInfoList.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("className", "瑜伽课");
            jsonObject.put("classNum", "10节");
            jsonObject.put("classLongTime", "120分钟");
            jsonObject.put("price", "1400元");
            for (int i = 0; i < 10; i++) {
                ClassInfo classInfo = new ClassInfo(jsonObject);
                mClassInfoList.add(classInfo);
            }
            classListAdapter.update(mClassInfoList);
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
        if (tvShaixuan.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
            showFilterDialog();

        } else {
            tvShaixuan.setTextColor(Color.parseColor("#1997f8"));
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
        if (tvPrice.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
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
            tvPrice.setTextColor(Color.parseColor("#1997f8"));
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
        if (tvZongHe.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {

        } else {
            tvZongHe.setTextColor(Color.parseColor("#1997f8"));
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
        coachClassFilterDialog.showFilterDialog();
    }
}
