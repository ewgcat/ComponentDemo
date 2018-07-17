package com.yijian.staff.mvp.workspace.commen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;


public class SearchFragment2 extends MvcBaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_fragment2;
    }

    @Override
    public void initView() {
        RecyclerView rv_search_all = rootView.findViewById(R.id.rv);
        rv_search_all.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加Android自带的分割线
        rv_search_all.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        SearchAllAdapter searchAllAdapter = new SearchAllAdapter();
        rv_search_all.setAdapter(searchAllAdapter);
    }

}
