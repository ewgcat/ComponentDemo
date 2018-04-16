package com.yijian.staff.mvp.huiji.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.mvp.contract.ContractActivity;
import com.yijian.staff.mvp.huiji.bean.HuiJiVipeCardAdapter;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/29.
 */

public class SearchKeyAdapter extends RecyclerView.Adapter<SearchKeyAdapter.ViewHolder> {

    private Context context;
    private List<SearchKey> dataList = new ArrayList<SearchKey>();


    public SearchKeyAdapter(Context context, List<SearchKey> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void update(List<SearchKey> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public SearchKeyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_key, parent, false);
        SearchKeyAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchKeyAdapter.ViewHolder holder, int position) {

        int i = dataList == null ? 0 : dataList.size();
        SearchKey searchKey = dataList.get(position);
        holder.tv_key.setText(searchKey.getKey());
        if (i < 4) {
            if (position == i - 1) {
                holder.tv_clean_history.setVisibility(View.VISIBLE);
            } else {
                holder.tv_clean_history.setVisibility(View.GONE);
            }
        } else {
            if (position == 2) {
                holder.tv_clean_history.setVisibility(View.VISIBLE);
            } else {
                holder.tv_clean_history.setVisibility(View.GONE);
            }
        }

        holder.tv_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickKeyListener != null) {
                    clickKeyListener.onClick(searchKey);
                }
            }
        });
        holder.tv_clean_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clearKeyListener != null) {
                    clearKeyListener.onClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        int i = dataList == null ? 0 : dataList.size();
        if (i < 4) {
            return i;
        } else {
            return 3;
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_key;
        TextView tv_clean_history;

        public ViewHolder(View view) {
            super(view);
            tv_key = view.findViewById(R.id.tv_key);
            tv_clean_history = view.findViewById(R.id.tv_clean_history);

        }
    }

    private ClickKeyListener clickKeyListener;

    public  interface ClickKeyListener {
        void onClick(SearchKey searchKey);
    }

    public void setClickKeyListener(ClickKeyListener clickKeyListener) {
        this.clickKeyListener = clickKeyListener;
    }

    private ClearKeyListener clearKeyListener;

    public  interface ClearKeyListener {
        void onClick();
    }

    public void setClearKeyListener(ClearKeyListener clearKeyListener) {
        this.clearKeyListener = clearKeyListener;
    }

}
