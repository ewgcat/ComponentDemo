package com.yijian.staff.mvp.huiji.intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.vip.bean.VipDetailBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 潜在会员或意向会员 基本信息
 */
public class HuijiIntentViperDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        setContentView(R.layout.activity_potential_and_intent_viper_detail);

        /*findViewById(R.id.ll_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        initTitle();
        initData();
    }

    private void initData() {
        String id = getIntent().getStringExtra("id");
        loadData(id);
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_intent_navigation_bar);
        navigationBar2.hideBottomLine();
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("意向会员");
    }

    public void loadData(String id) {
        HashMap<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        header.put("token", user.getToken());

        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_HUIJI_VIPER_DETAIL_URL,header, map, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                VipDetailBean vipDetailBean = com.alibaba.fastjson.JSONObject.parseObject(result.toString(),VipDetailBean.class);
                updateUi(vipDetailBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HuijiIntentViperDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUi(VipDetailBean vipDetailBean){

    }

}
