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
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildObjBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.JsonStringData;
import com.yijian.staff.mvp.reception.step2.step2Bean.OptionItemData;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentObjBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.Parent_Demo;
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

    private List<Parent_Demo> recipes;
    private OptionItemData optionItemData;
    private static final String TAG = "DemoActivity";
    private List<ParentObjBean> parentObj;
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
        return new Intent(context, ReceptionStepTwoActivity_ycm.class);
    }


    public void showBottomView(int childPosition, int parentPosition) {
        ChildObjBean childObjBean = parentObj.get(parentPosition).getChildList().get(childPosition);
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

    @Nullable
    private ArrayList<String> getIntegerRange(ChildObjBean childObjBean) {
        String minValue = childObjBean.getMinValue();
        String maxValue = childObjBean.getMaxValue();
        ArrayList<String> integerRange = optionItemData.getOptionDataFactory().initIntegerRange(Integer.valueOf(minValue), Integer.valueOf(maxValue));
        return integerRange;
    }

    /**
     * @param optArrays    选项数组——String
     * @param integerRange 选项数组——Integer
     * @param decimal      选项数组——小数
     * @param symbol       选项数组——符号(+/-)
     */
    private void showPickerView(final ArrayList<String> optArrays, final ArrayList<String> integerRange, final ArrayList<String> decimal
            , final ArrayList<String> symbol, final ChildObjBean childObjBean, final int childPosition, final int parentPosition) {
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
                        childObjBean.setDefaultValue(""+symbol.get(options1)+integerRange.get(options2)+decimal.get(options3));
                        break;
                    case 2:
                        str = "integerRange:" + integerRange.get(options1) + "decimal:" + decimal.get(options2);
                        childObjBean.setDefaultValue(""+integerRange.get(options1)+decimal.get(options2));
                        break;
                    case 3:
                        str = "integerRange:" + integerRange.get(options1);
                        childObjBean.setDefaultValue(""+integerRange.get(options1));
                        break;
                    case 4:
                        str = "integerRange:" + optArrays.get(options1);
                        childObjBean.setDefaultValue(""+optArrays.get(options1));
                        break;
                }

                Toast.makeText(ReceptionStepTwoActivity_ycm.this, str, Toast.LENGTH_SHORT).show();
                demoAdapter.notifyChildChanged(parentPosition,childPosition);

            }
        }).build();

        switch (type) {
            case 1:
                pvNoLinkOptions.setNPicker(symbol, integerRange, decimal);
                break;
            case 2:
                pvNoLinkOptions.setNPicker(integerRange, decimal, null);
                break;
            case 3:
                pvNoLinkOptions.setNPicker(integerRange, null, null);
                break;
            case 4:
                pvNoLinkOptions.setNPicker(optArrays, null, null);
                break;
        }


//        pvNoLinkOptions.setSelectOptions(2,3);
        pvNoLinkOptions.show();
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
                Intent intent = new Intent(ReceptionStepTwoActivity_ycm.this, ReceptionStepThreeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }


//    private void initData() {
//        Bean bean = new Gson().fromJson(JsonStringData.metaData, Bean.class);
//        parentObj = bean.getParentObj();
//

//            Child_Demo child_demoTYPE_NORMAL00 = new Child_Demo("key__TYPE_NORMAL00" , "value_TYPE_NORMAL00" , Child_Demo.TYPE_NORMAL,false);
//            Child_Demo child_demoTYPE_NORMAL11 = new Child_Demo("key__TYPE_NORMAL11" , "value_TYPE_NORMAL11" , Child_Demo.TYPE_NORMAL,false);
//            Child_Demo child_demoTYPE_NORMAL22 = new Child_Demo("key__TYPE_NORMAL22" , "value_TYPE_NORMAL22" , Child_Demo.TYPE_NORMAL,false);
//            Child_Demo child_demoTYPE_NORMAL33 = new Child_Demo("key__TYPE_NORMAL33" , "value_TYPE_NORMAL33" , Child_Demo.TYPE_NORMAL,false);
//
//            Child_Demo child_demoTYPE_MULTIOPTION00 = new Child_Demo("key__MULTIOPTION00" , "value_MULTIOPTION00" , Child_Demo.TYPE_MULTIOPTION,false);
//            Child_Demo child_demoTYPE_MULTIOPTION11 = new Child_Demo("key__MULTIOPTION11" , "value_MULTIOPTION11" , Child_Demo.TYPE_MULTIOPTION,false);
//            Child_Demo child_demoTYPE_MULTIOPTION22 = new Child_Demo("key__MULTIOPTION22" , "value_MULTIOPTION22" , Child_Demo.TYPE_MULTIOPTION,false);
//            Child_Demo child_demoTYPE_MULTIOPTION33 = new Child_Demo("key__MULTIOPTION33" , "value_MULTIOPTION33" , Child_Demo.TYPE_MULTIOPTION,false);
//
//            Child_Demo child_demoTYPE_WRITE0 = new Child_Demo("key__WRITE0" , "value_WRITE0" , Child_Demo.TYPE_WRITE,false);
//            Child_Demo child_demoTYPE_WRITE1 = new Child_Demo("key__WRITE1" , "value_WRITE1" , Child_Demo.TYPE_WRITE,false);
//            Child_Demo child_demoTYPE_WRITE2 = new Child_Demo("key__WRITE2" , "value_WRITE2" , Child_Demo.TYPE_WRITE,false);
//
//
//
//        Parent_Demo parent_demo = new Parent_Demo("this is a normal", Arrays.asList(child_demoTYPE_NORMAL00,child_demoTYPE_NORMAL11
//                ,child_demoTYPE_NORMAL22,child_demoTYPE_NORMAL33),1);
//
//        Parent_Demo parent_demo1 = new Parent_Demo("this is a MULTIOPTION11", Arrays.asList(child_demoTYPE_MULTIOPTION00,child_demoTYPE_MULTIOPTION11
//                ,child_demoTYPE_MULTIOPTION22,child_demoTYPE_MULTIOPTION33),2);
//
//        Parent_Demo parent_demo2 = new Parent_Demo("this is a MIX", Arrays.asList(child_demoTYPE_NORMAL00,child_demoTYPE_MULTIOPTION11
//                ,child_demoTYPE_WRITE0,child_demoTYPE_WRITE1),3);
//        recipes = Arrays.asList(parent_demo, parent_demo1, parent_demo2);

////        人体成分分析
//        Child_Demo weight = new Child_Demo(FieldTest.WEIGHT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo skeletalmuscle = new Child_Demo(FieldTest.SKELETALMUSCLE , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo fat = new Child_Demo(FieldTest.FAT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo water = new Child_Demo(FieldTest.WATER , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo heightnofat = new Child_Demo(FieldTest.HEIGHTNOFAT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//
//        Child_Demo bmi = new Child_Demo(FieldTest.BMI , FieldTest.NORMAL , Child_Demo.TYPE_DISPLAY,false);
//        Child_Demo pbf = new Child_Demo(FieldTest.PBF , FieldTest.NORMAL , Child_Demo.TYPE_DISPLAY,false);
//        Child_Demo whr = new Child_Demo(FieldTest.WHR , FieldTest.NORMAL , Child_Demo.TYPE_DISPLAY,false);
//        Child_Demo basalmetabolism = new Child_Demo(FieldTest.BASALMETABOLISM , FieldTest.NORMAL , Child_Demo.TYPE_DISPLAY,false);
//
//        Child_Demo musclecontroller = new Child_Demo(FieldTest.MUSCLECONTROLLER , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo fatcontroller = new Child_Demo(FieldTest.FATCONTROLLER , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//
//
////        基础测量数据
//        Child_Demo waist = new Child_Demo(FieldTest.WAIST , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo skinfatland = new Child_Demo(FieldTest.SKINFATLAND , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo lefthand = new Child_Demo(FieldTest.LEFTHAND , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo righthand = new Child_Demo(FieldTest.RIGHTHAND , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo lefthhigh = new Child_Demo(FieldTest.LEFTHHIGH , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo righthhigh = new Child_Demo(FieldTest.RIGHTHHIGH , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo leftshins = new Child_Demo(FieldTest.LEFTSHINS , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo rightshins = new Child_Demo(FieldTest.RIGHTSHINS , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo shrinkbloodpressure = new Child_Demo(FieldTest.SHRINKBLOODPRESSURE , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo diastolebloodpressure = new Child_Demo(FieldTest.DIASTOLEBLOODPRESSURE , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo heartbeat = new Child_Demo(FieldTest.HEARTBEAT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//
//
////        体态评估-侧面
//        Child_Demo shead = new Child_Demo(FieldTest.SHEAD , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo scervicalv = new Child_Demo(FieldTest.SCERVICALV , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo sscapula = new Child_Demo(FieldTest.SSCAPULA , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo sthoracicv = new Child_Demo(FieldTest.STHORACICV , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo spelvis = new Child_Demo(FieldTest.SPELVIS , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo shipj = new Child_Demo(FieldTest.SHIPJ , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo sknee = new Child_Demo(FieldTest.SKNEE , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo sankle = new Child_Demo(FieldTest.SANKLE , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo sfibula = new Child_Demo(FieldTest.SFIBULA , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//
//
////        体态评估-正面
//        Child_Demo bhead = new Child_Demo(FieldTest.BHEAD , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo bshoulders = new Child_Demo(FieldTest.BSHOULDERS , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo bscapula = new Child_Demo(FieldTest.BSCAPULA , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo btlv = new Child_Demo(FieldTest.BTLV , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo bpelvis = new Child_Demo(FieldTest.BPELVIS , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo bhipj = new Child_Demo(FieldTest.BHIPJ , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo bknee = new Child_Demo(FieldTest.BKNEE , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo bfoot = new Child_Demo(FieldTest.BFOOT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);


//        健身处方
//        Child_Demo weight = new Child_Demo(FieldTest.WEIGHT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo weight = new Child_Demo(FieldTest.WEIGHT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//        Child_Demo weight = new Child_Demo(FieldTest.WEIGHT , FieldTest.NORMAL , Child_Demo.TYPE_NORMAL,false);
//    }
}
