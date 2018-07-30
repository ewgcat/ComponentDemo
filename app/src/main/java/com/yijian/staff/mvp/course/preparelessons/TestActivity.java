package com.yijian.staff.mvp.course.preparelessons;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.NumberTabLayout;

import java.util.ArrayList;

import static com.yijian.staff.widget.NumberTabLayout.ADD_TYPE;
import static com.yijian.staff.widget.NumberTabLayout.CHE_XIAO_TYPE;


public class TestActivity extends MvcBaseActivity {
    private NumberTabLayout numberTabLayout;
    private LineChart mChart;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        numberTabLayout = findViewById(R.id.my_point);
        numberTabLayout.setAddListener(new NumberTabLayout.AddListener() {
            @Override
            public void addClick(View view) {
                if (numberTabLayout.getAddType() == 1) {
                    numberTabLayout.addPoint();
                } else if (numberTabLayout.getAddType() == 2) {
                    numberTabLayout.resetPointNum();
                    numberTabLayout.changeAddView(ADD_TYPE);
                }
            }
        });
        numberTabLayout.setPointListener(new NumberTabLayout.PointListener() {
            @Override
            public void pointClick(View view) {
                TextView point = (TextView) view;
                if ("-".equals(point.getText().toString())) {
                    numberTabLayout.removePoint(view);
                } else {
                    point.setText("-");
                    point.setBackground(getDrawable(R.drawable.red_circle_background));
                    numberTabLayout.changeAddView(CHE_XIAO_TYPE);
                }
            }
        });

        mChart = findViewById(R.id.chart1);
        mChart.setViewPortOffsets(CommonUtil.dp2px(this,20), CommonUtil.dp2px(this,20), CommonUtil.dp2px(this,20), CommonUtil.dp2px(this,20));
        mChart.setBackgroundColor(Color.WHITE);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(false);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setMaxHighlightDistance(300);

        XAxis x = mChart.getXAxis();
        x.setEnabled(false);

        YAxis y = mChart.getAxisLeft();
        y.setTypeface(mTfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.BLUE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.BLUE);

        mChart.getAxisRight().setEnabled(false);

        // add data
        setData(30, 100);

        mChart.getLegend().setEnabled(false);

        mChart.animateXY(2000, 2000);

        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) ;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            //set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.BLUE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.BLUE);
            set1.setFillColor(Color.BLUE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTypeface(mTfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(true);

            // set data
            mChart.setData(data);
        }
    }
}
