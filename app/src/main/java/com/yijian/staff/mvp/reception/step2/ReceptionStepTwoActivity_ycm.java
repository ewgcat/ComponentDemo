package com.yijian.staff.mvp.reception.step2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.JsonStringData;
import com.yijian.staff.mvp.reception.step2.step2Bean.OptionItemData;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.QustionBean;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/3/13.
 */

public class ReceptionStepTwoActivity_ycm extends AppCompatActivity implements View.OnClickListener {

    private OptionItemData optionItemData;
    private static final String TAG = "DemoActivity";
    private List<ParentQuestionBean> parentObj;
    private ArrayList<String> symbol;
    private ArrayList<String> decimal;
    private ReceptionStep2Adapter demoAdapter;
    private RecyclerView recyclerView;
    private TextView tvName;
    private TextView tvHeight;
    private TextView tvAge;
    public static final int TYPE_0 = 0;//选项是数字，有符号，有小数
    public static final int TYPE_1 = 1;//选项是数字，有小数
    public static final int TYPE_2 = 2;//选项是数字
    public static final int TYPE_3 = 3;//选项是StringArray

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionsteptwo_ycm);

        initView();
        initData();

    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.step_two_navigation_bar2);

        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.setTitle("体测录入(2/5)");
        navigationBar2.setmRightTvText("下一步");


        TimeBar timeBar = findViewById(R.id.step_two_timebar);
        timeBar.showTimeBar(2);

        RelativeLayout rlName = findViewById(R.id.rl_name);
        RelativeLayout rlHeight = findViewById(R.id.rl_height);
        RelativeLayout rlAge = findViewById(R.id.rl_age);
        tvName = findViewById(R.id.tv_name);
        tvHeight = findViewById(R.id.tv_height);
        tvAge = findViewById(R.id.tv_age);

        rlName.setOnClickListener(this);
        rlHeight.setOnClickListener(this);
        rlAge.setOnClickListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);


    }

    private void initData() {
        QustionBean bean = new Gson().fromJson(JsonStringData.metaData, QustionBean.class);
        parentObj = bean.getParentObj();
        optionItemData = new OptionItemData();
        symbol = optionItemData.getSymbol();
        decimal = optionItemData.getDecimal();


        demoAdapter = new ReceptionStep2Adapter(parentObj, this);
        recyclerView.setAdapter(demoAdapter);


    }

    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, ReceptionStepTwoActivity_ycm.class);
    }


    public void showBottomView(int childPosition, int parentPosition) {
        ChildOptBean childObjBean = parentObj.get(parentPosition).getChildList().get(childPosition);
        String qusType = childObjBean.getQusType();
        if (!"normal".equals(qusType)) return;
        boolean valueIsNum = childObjBean.isValueIsNum();
        boolean hasSymbol = childObjBean.isHasSymbol();
        boolean hasDecimal = childObjBean.isHasDecimal();
        if (valueIsNum && hasSymbol && hasDecimal) {//选项是数字，有符号，有小数
            showPickerView(TYPE_0, childObjBean, childPosition, parentPosition);
        } else if (valueIsNum && hasDecimal) {////选项是数字，有小数
            showPickerView(TYPE_1, childObjBean, childPosition, parentPosition);
        } else if (valueIsNum) {//选项是数字
            showPickerView(TYPE_2, childObjBean, childPosition, parentPosition);
        } else {//选项是StringArray
            showPickerView(TYPE_3, childObjBean, childPosition, parentPosition);
        }


    }

    private void showPickerView(int type, ChildOptBean childObjBean, int childPosition, int parentPosition) {

        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = "";
                switch (type) {
                    case TYPE_0:
                            str = "symbol:" + symbol.get(options1) + "integerRange:" + getOptRange(childObjBean).get(options2) + "decimal:" + decimal.get(options3);
                            childObjBean.setUserValue("" + symbol.get(options1) + getOptRange(childObjBean).get(options2) + decimal.get(options3));
                        break;
                    case TYPE_1:
                            str = "integerRange:" + getOptRange(childObjBean).get(options1) + "decimal:" + decimal.get(options2);
                            childObjBean.setUserValue("" + getOptRange(childObjBean).get(options1) + decimal.get(options2));
                        break;
                    case TYPE_2:
                    case TYPE_3:
                            str = "integerRange:" + getOptRange(childObjBean).get(options1);
                            childObjBean.setUserValue("" + getOptRange(childObjBean).get(options1));
                        break;

                }

                Toast.makeText(ReceptionStepTwoActivity_ycm.this, str, Toast.LENGTH_SHORT).show();
                    demoAdapter.notifyChildChanged(parentPosition, childPosition);
            }
        }).build();


        switch (type) {
            case TYPE_0:
                pvNoLinkOptions.setNPicker(symbol, getOptRange(childObjBean), decimal);
                int i = getOptRange(childObjBean).indexOf(childObjBean.getDefaultValue());
                if (i < 0) i = 0;
                pvNoLinkOptions.setSelectOptions(0, i, 0);
                break;
            case TYPE_1:
                pvNoLinkOptions.setNPicker(getOptRange(childObjBean), decimal, null);
                int i1 = getOptRange(childObjBean).indexOf(childObjBean.getDefaultValue());
                if (i1 < 0) i1 = 0;
                pvNoLinkOptions.setSelectOptions(i1, 0);
                break;
            case TYPE_2:
            case TYPE_3:
                pvNoLinkOptions.setNPicker(getOptRange(childObjBean), null, null);
                int i2 = getOptRange(childObjBean).indexOf(childObjBean.getDefaultValue());
                if (i2 < 0) i2 = 0;
                pvNoLinkOptions.setSelectOptions(i2);
                break;
        }
        pvNoLinkOptions.show();
    }


    @Nullable
    private ArrayList<String> getOptRange(ChildOptBean childObjBean) {

        if (childObjBean.isValueIsNum()) {
            ArrayList<String> integerRange = optionItemData.getOptionDataFactory().initIntegerRange(Integer.valueOf(childObjBean.getMinValue()), Integer.valueOf(childObjBean.getMaxValue()));
            return integerRange;
        } else {
            ArrayList<String> optRange = new ArrayList<>();
            List<String> valueList = childObjBean.getValueArray();
            optRange.addAll(valueList);
            return optRange;
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:


                Intent i = new Intent(this, ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.right_tv:
                Intent intent = new Intent(ReceptionStepTwoActivity_ycm.this, ReceptionStepThreeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;


            case R.id.rl_height:
                ArrayList<String> heightRange = optionItemData.getOptionDataFactory().initIntegerRange(120, 300);
                manualPickedView(heightRange,"170",tvHeight);
                break;

            case R.id.rl_age:
                ArrayList<String> ageRange = optionItemData.getOptionDataFactory().initIntegerRange(18, 100);
                manualPickedView(ageRange,"20",tvAge);
                break;

        }
    }

    private void manualPickedView(ArrayList<String> opts, String defaultValue, TextView name) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                name.setText(opts.get(options1));
            }
        }).build();

        pvNoLinkOptions.setNPicker(opts, null, null);
        pvNoLinkOptions.setSelectOptions(opts.indexOf(defaultValue));
        pvNoLinkOptions.show();
    }
}
