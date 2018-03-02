package com.yijian.staff.mvp.goodsdetail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.ArrayColumn;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.utils.DensityUtils;
import com.yijian.staff.R;
import com.yijian.staff.bean.KeCheng;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetaiActivity extends AppCompatActivity {

    @BindView(R.id.table)
    SmartTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);

        setContentView(R.layout.activity_goods_detai);
        ButterKnife.bind(this);

        initView();


    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.goods_detail_navigation_bar);
        navigationBar.setTitle("产品详情", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));


        FontStyle.setDefaultTextSize(DensityUtils.sp2px(this,5));
        SmartTable    table = (SmartTable<KeCheng>) findViewById(R.id.table);
        List<KeCheng> keChengList=new ArrayList<KeCheng>();
        KeCheng keCheng1 = new KeCheng("12:00-18:00", "30", "30", "30", "30", "30", "30", "30");
        keChengList.add(keCheng1);
        KeCheng keCheng2 = new KeCheng("20:00-24:00", "30", "30", "30", "30", "30", "30", "30");
        keChengList.add(keCheng2);
        table.setData(keChengList);
        table.getConfig().setShowTableTitle(false);
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);


    }



}
