package com.example.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.commonlibrary.BaseFragment;
import com.example.commonlibrary.baseadapter.SuperRecyclerView;
import com.example.commonlibrary.baseadapter.adapter.ViewPagerAdapter;
import com.example.commonlibrary.baseadapter.listener.OnSimpleItemClickListener;
import com.example.commonlibrary.baseadapter.manager.WrappedGridLayoutManager;
import com.example.commonlibrary.bean.news.OtherNewsTypeBean;
import com.example.commonlibrary.bean.news.OtherNewsTypeBeanDao;
import com.example.commonlibrary.cusotomview.CustomPopWindow;
import com.example.commonlibrary.cusotomview.ToolBarOption;
import com.example.commonlibrary.rxbus.RxBus;
import com.example.news.adapter.PopWindowAdapter;
import com.example.news.event.TypeNewsEvent;
import com.example.news.mvp.news.NewsListFragment;
import com.example.news.mvp.news.college.CollegeNewsMainFragment;
import com.example.news.mvp.news.othernew.OtherNewsListFragment;
import com.example.news.mvp.news.othernew.photolist.PhotoListFragment;
import com.example.news.util.NewsUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 项目名称:    NewFastFrame
 * 创建人:        陈锦军
 * 创建时间:    2017/9/17      17:32
 * QQ:             1981367757
 */

public class IndexFragment extends BaseFragment implements View.OnClickListener {
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager display;
    private ImageView expend;
    private List<String> titleList;
    private List<BaseFragment> fragmentList;


    @Override
    public void updateData(Object o) {

    }

    @Override
    protected boolean isNeedHeadLayout() {
        return true;
    }

    @Override
    protected boolean isNeedEmptyLayout() {
        return false;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        display = (ViewPager) findViewById(R.id.vp_fragment_index_display);
        tabLayout = (TabLayout) findViewById(R.id.tl_fragment_index_tab);
        expend = (ImageView) findViewById(R.id.iv_fragment_index_expend_list);
        expend.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        titleList = new ArrayList<>();
        titleList.add("要闻");
        titleList.add("公告");
        titleList.add("学术");
        titleList.add("马院");
        titleList.add("艺媒");
        titleList.add("数理");
        titleList.add("海洋");
        titleList.add("机电");
        titleList.add("地空");
        titleList.add("环境");
        titleList.add("工程");
        titleList.add("材化");
        titleList.add("资源");
        titleList.add("自动化");
        titleList.add("信工");
        titleList.add("地院");
        titleList.add("经管");
        titleList.add("公管");
        titleList.add("计院 ");
        titleList.add("地科");
        titleList.add("外院");
        titleList.add("福利");
        fragmentList = new ArrayList<>();
        fragmentList.add(NewsListFragment.newInstance(NewsUtil.CUG_NEWS));
        fragmentList.add(NewsListFragment.newInstance(NewsUtil.CUG_NOTIFY));
        fragmentList.add(NewsListFragment.newInstance(NewsUtil.CUG_TECHNOLOGY));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_MY));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_YM));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_SL));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_HY));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_JD));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_DWK));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_HJ));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_GC));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_CH));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_ZY));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_ZDH));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_XY));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_DY));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_JG));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_GG));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_JSJ));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_DK));
        fragmentList.add(CollegeNewsMainFragment.newInstance(NewsUtil.COLLEGE_TYPE_WY));
        fragmentList.add(PhotoListFragment.newInstance());
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        List<OtherNewsTypeBean> result = NewsApplication
                .getNewsComponent().getDataManager()
                .getDaoSession()
                .getOtherNewsTypeBeanDao()
                .queryBuilder().where(OtherNewsTypeBeanDao.Properties.HasSelected.eq(Boolean.TRUE))
                .build().list();
        for (int i = 0; i < result.size(); i++) {
            OtherNewsTypeBean otherNewsTypeBean = result.get(i);
            OtherNewsListFragment otherNewsListFragment = OtherNewsListFragment.newInstance(otherNewsTypeBean);
            fragmentList.add(otherNewsListFragment);
            titleList.add(otherNewsTypeBean.getName());
        }
        viewPagerAdapter.setTitleAndFragments(titleList, fragmentList);
        tabLayout.setupWithViewPager(display);
        display.setAdapter(viewPagerAdapter);
        display.setCurrentItem(0);
        ToolBarOption toolBarOption = new ToolBarOption();
        toolBarOption.setBgColor(getResources().getColor(R.color.base_color_text_grey));
        toolBarOption.setTitle("地大新闻");
        toolBarOption.setNeedNavigation(true);
        setToolBar(toolBarOption);
        RxBus.getInstance().registerEvent(TypeNewsEvent.class, new Consumer<TypeNewsEvent>() {
            @Override
            public void accept(@NonNull TypeNewsEvent typeNewsEvent) throws Exception {
                OtherNewsTypeBean newsTypeBean = NewsApplication.getNewsComponent()
                        .getDataManager().getDaoSession().getOtherNewsTypeBeanDao()
                        .queryBuilder().where(OtherNewsTypeBeanDao.Properties.TypeId.eq(typeNewsEvent.getTypeId()))
                        .build().list().get(0);
                if (typeNewsEvent.getType() == TypeNewsEvent.ADD) {
                    OtherNewsListFragment otherNewsListFragment = OtherNewsListFragment.newInstance(newsTypeBean);
                    fragmentList.add(otherNewsListFragment);
                    titleList.add(newsTypeBean.getName());
                } else {
                    int index = titleList.indexOf(newsTypeBean.getName());
                    display.setCurrentItem(0);
                    fragmentList.remove(index);
                    titleList.remove(newsTypeBean.getName());
                }
                viewPagerAdapter.notifyDataSetChanged();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {

            }
        });
    }

    private CustomPopWindow customPopWindow;


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_fragment_index_expend_list) {
            if (customPopWindow == null) {
                customPopWindow = new CustomPopWindow.Builder().parentView(v).activity(getActivity()).contentView(getContentView())
                        .build();
            }
            if (!customPopWindow.isShowing()) {
                customPopWindow.showAsDropDown(v);
            } else {
                customPopWindow.dismiss();
            }
        } else if (id == R.id.btn_view_fragment_index_pop_adjust) {
            AdjustNewsTypeActivity.start(getActivity());
            customPopWindow.dismiss();
        }
    }


    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.view_fragment_index_pop_window, null);
        Button adjust = (Button) contentView.findViewById(R.id.btn_view_fragment_index_pop_adjust);
        final SuperRecyclerView display = (SuperRecyclerView) contentView.findViewById(R.id.srcv_view_fragment_index_pop_display);
        display.setLayoutManager(new WrappedGridLayoutManager(getContext(), 5));
        PopWindowAdapter popWindowAdapter = new PopWindowAdapter();
        display.setAdapter(popWindowAdapter);
        List<OtherNewsTypeBean> result = NewsApplication
                .getNewsComponent().getDataManager().getDaoSession()
                .getOtherNewsTypeBeanDao().queryBuilder().where(OtherNewsTypeBeanDao
                        .Properties.HasSelected.eq(Boolean.TRUE)).list();
        OtherNewsTypeBean item_1 = new OtherNewsTypeBean();
        item_1.setName("要闻");
        OtherNewsTypeBean item_2 = new OtherNewsTypeBean();
        item_2.setName("公告");
        OtherNewsTypeBean item_3 = new OtherNewsTypeBean();
        item_3.setName("学术");
        OtherNewsTypeBean item_4 = new OtherNewsTypeBean();
        item_4.setName("福利");
        result.add(0, item_4);
        result.add(0, item_3);
        result.add(0, item_2);
        result.add(0, item_1);
        popWindowAdapter.addData(result);
        popWindowAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                IndexFragment.this.display.setCurrentItem(position);
                customPopWindow.dismiss();
            }
        });
        adjust.setOnClickListener(this);
        return contentView;
    }


    @Override
    protected void updateView() {

    }

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }
}
