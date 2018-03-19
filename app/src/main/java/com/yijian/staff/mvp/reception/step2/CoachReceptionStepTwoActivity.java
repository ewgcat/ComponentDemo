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

public class CoachReceptionStepTwoActivity extends AppCompatActivity implements View.OnClickListener {

    private OptionItemData optionItemData;
    private static final String TAG = "DemoActivity";
    private List<ParentQuestionBean> parentObj;
    private ArrayList<String> symbol;
    private ArrayList<String> decimal;
    private ReceptionStep2Adapter demoAdapter;
    private RecyclerView recyclerView;

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
        return new Intent(context, CoachReceptionStepTwoActivity.class);
    }


    public void showBottomView(int childPosition, int parentPosition) {
        ChildOptBean childObjBean = parentObj.get(parentPosition).getChildList().get(childPosition);
        String qusType = childObjBean.getQusType();
        if (!"normal".equals(qusType)) return;
        boolean valueIsNum = childObjBean.isValueIsNum();
        boolean hasSymbol = childObjBean.isHasSymbol();
        boolean hasDecimal = childObjBean.isHasDecimal();
        if (valueIsNum && hasSymbol && hasDecimal) {//选项是数字，有符号，有小数
            ArrayList<String> integerRange = getIntegerRange(childObjBean);
            showPickerView(null, integerRange, decimal, symbol,childObjBean,childPosition,parentPosition);
        } else if (valueIsNum && hasDecimal) {////选项是数字，有小数
            ArrayList<String> integerRange = getIntegerRange(childObjBean);
            showPickerView(null, integerRange, decimal, null,childObjBean,childPosition,parentPosition);
        } else if (valueIsNum) {//选项是数字
            ArrayList<String> integerRange = getIntegerRange(childObjBean);
            showPickerView(null, integerRange, null, null,childObjBean,childPosition,parentPosition);
        } else {//选项是StringArray
            List<String> valueList = childObjBean.getValueArray();
            ArrayList<String> valueArray = new ArrayList<>();
            valueArray.addAll(valueList);
            showPickerView(valueArray, null, null, null,childObjBean,childPosition,parentPosition);
        }

    }



    /**
     * @param optArrays    选项数组——String
     * @param integerRange 选项数组——Integer
     * @param decimal      选项数组——小数
     * @param symbol       选项数组——符号(+/-)
     */
    private void showPickerView(final ArrayList<String> optArrays, final ArrayList<String> integerRange, final ArrayList<String> decimal
            , final ArrayList<String> symbol, final ChildOptBean childObjBean, final int childPosition, final int parentPosition) {
        int type = 0;
        if (integerRange != null && decimal != null && symbol != null) {
            type = 1;////选项是数字，有符号，有小数
        } else if (integerRange != null && decimal != null) {
            type = 2;//选项是数字，有小数
        } else if (integerRange != null) {
            type = 3;//选项是数字
        } else if (optArrays.size() != 0) {
            type = 4;
        } else {
            return;
        }


        final int finalType = type;
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = "";
                switch (finalType) {
                    case 1:
                        str = "symbol:" + symbol.get(options1) + "integerRange:" + integerRange.get(options2) + "decimal:" + decimal.get(options3);
                        childObjBean.setUserValue(""+symbol.get(options1)+integerRange.get(options2)+decimal.get(options3));
                        break;
                    case 2:
                        str = "integerRange:" + integerRange.get(options1) + "decimal:" + decimal.get(options2);
                        childObjBean.setUserValue(""+integerRange.get(options1)+decimal.get(options2));
                        break;
                    case 3:
                        str = "integerRange:" + integerRange.get(options1);
                        childObjBean.setUserValue(""+integerRange.get(options1));
                        break;
                    case 4:
                        str = "integerRange:" + optArrays.get(options1);
                        childObjBean.setUserValue(""+optArrays.get(options1));
                        break;
                }

                Toast.makeText(CoachReceptionStepTwoActivity.this, str, Toast.LENGTH_SHORT).show();
                demoAdapter.notifyChildChanged(parentPosition,childPosition);

            }
        }).build();

        String defaultValue = childObjBean.getDefaultValue();



        switch (type) {
            case 1:
                pvNoLinkOptions.setNPicker(symbol, integerRange, decimal);
                int i = integerRange.indexOf(defaultValue);
                if (i<0)i=0;
                pvNoLinkOptions.setSelectOptions(0,i,0);
                break;
            case 2:
                pvNoLinkOptions.setNPicker(integerRange, decimal, null);
                int i1 = integerRange.indexOf(defaultValue);
                if (i1<0)i1=0;
                pvNoLinkOptions.setSelectOptions(i1,0);
                break;
            case 3:
                pvNoLinkOptions.setNPicker(integerRange, null, null);
                int i2 = integerRange.indexOf(defaultValue);
                if (i2<0)i2=0;
                pvNoLinkOptions.setSelectOptions(i2);
                break;
            case 4:
                pvNoLinkOptions.setNPicker(optArrays, null, null);
                int i3 = optArrays.indexOf(defaultValue);
                if (i3<0)i3=0;
                pvNoLinkOptions.setSelectOptions(i3);
                break;
        }
        pvNoLinkOptions.show();
    }

    @Nullable
    private ArrayList<String> getIntegerRange(ChildOptBean childObjBean) {
        String minValue = childObjBean.getMinValue();
        String maxValue = childObjBean.getMaxValue();
        ArrayList<String> integerRange = optionItemData.getOptionDataFactory().initIntegerRange(Integer.valueOf(minValue), Integer.valueOf(maxValue));
        return integerRange;
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:


                Intent i = new Intent(this,ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.right_tv:
                Intent intent = new Intent(CoachReceptionStepTwoActivity.this, ReceptionStepThreeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }



}
