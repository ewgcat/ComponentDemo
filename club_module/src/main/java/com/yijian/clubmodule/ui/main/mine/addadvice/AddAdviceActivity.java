package com.yijian.clubmodule.ui.main.mine.addadvice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.requestbody.advice.AddAdviceBody;
import com.yijian.clubmodule.net.requestbody.advice.Advice;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.CommonUtil;

import org.json.JSONObject;



public class AddAdviceActivity extends MvcBaseActivity {

    EditText etAdvice;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_advice;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        etAdvice= findView(R. id.et_advice);

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.advice_view_navigation_bar);
        navigationBar.setTitle("系统意见");
        navigationBar .hideLeftSecondIv();
        navigationBar.getFirstLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(etAdvice);
                finish();
            }
        });
        navigationBar.setmRightTvText("发送");
        TextView textView =  navigationBar.getmRightTv();
        textView.setTextColor(getResources().getColor(R.color.blue));
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
        CommonUtil.showSoftInputFromWindow(this,etAdvice);

    }


    private void postData() {
        String advice = etAdvice.getText().toString();
        if (TextUtils.isEmpty(advice)) {
            showToast("请先填写反馈意见！");
        } else {
            String userId = DBManager.getInstance().queryUser().getUserId();
            Advice advicebody = new Advice(advice, userId);

            AddAdviceBody addAdviceBody = new AddAdviceBody(advicebody);
            HttpManager.postAddAdvice(HttpManager.ADD_FEEDBACK_URL, addAdviceBody, new ResultJSONObjectObserver(getLifecycle()) {
                @Override
                public void onSuccess(JSONObject result) {
                    hideKeyBoard(etAdvice);
                    showToast("提交建议成功");
                    finish();
                }

                @Override
                public void onFail(String msg) {
                    showToast(msg);
                }
            });

        }
    }
}
