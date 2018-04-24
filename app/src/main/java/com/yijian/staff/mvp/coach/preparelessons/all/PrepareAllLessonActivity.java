package com.yijian.staff.mvp.coach.preparelessons.all;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionViewAdapter;
import com.yijian.staff.mvp.coach.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.coach.preparelessons.createlession.SubActionBean;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 所有私教课备课
 */
public class PrepareAllLessonActivity extends AppCompatActivity {

    @BindView(R.id.tv_depart)
    TextView tv_depart;
    @BindView(R.id.rv_action_content)
    RecyclerView rv_action_content;
    List<ActionBean> actionBeanList = new ArrayList<>(); //装载RecyclerView的集合
    PrepareAllActionAdapter prepareAllActionAdapter ;
    EditActionObservable editActionObservable = new EditActionObservable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_all_lesson);
        ButterKnife.bind(this);
        initTitle();
        intView();
        initData();
    }

    private void intView() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_action_content.setLayoutManager(layoutmanager);
        prepareAllActionAdapter = new PrepareAllActionAdapter(actionBeanList, editActionObservable, this);
        rv_action_content.setAdapter(prepareAllActionAdapter);
    }

    private void initData(){
        /****************** 初始化动作内容选项数据 **************************/
        ActionBean actionBean1 = new ActionBean("1", "简单", "部位","平板支撑", "1组/1次", "无");
        ActionBean actionBean2 = new ActionBean("2", "中等", "部位","平板支撑2", "2组2次", "有");
        ActionBean actionBean3 = new ActionBean("3", "困难", "部位","平板支撑3", "3组/3次", "无");
        actionBeanList.add(actionBean1);
        actionBeanList.add(actionBean2);
        actionBeanList.add(actionBean3);
        prepareAllActionAdapter.notifyDataSetChanged();


    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("所有课程安排");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    /**
     * 点击头部 监听各分组的隐藏和显示
     *
     * @param itemPosition
     */
    public void notifyClickHeader(int itemPosition, int eventType) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "6");
        map.put("itemPosition", itemPosition + "");
        editActionObservable.notifyObservers(map);
    }


}
