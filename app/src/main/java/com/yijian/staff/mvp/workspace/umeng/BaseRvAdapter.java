package com.yijian.staff.mvp.workspace.umeng;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter {
    private int pageSize = 10;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        int count = getItemCount();
        return (count % pageSize == 0) ? (count / pageSize + 1) : (count / pageSize + 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            return;
        int truePosition = position;
        if (isContainHeader()) {
            truePosition = position - 1;
        }
        BaseRvViewHolder baseRvViewHolder = (BaseRvViewHolder) holder;
        baseRvViewHolder.bindData(mData.get(truePosition), truePosition);
        holder.itemView.setTag(truePosition);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer p = (Integer) v.getTag();
                    onItemClickListener.onClick(v, mData.get(p), p);
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Integer p = (Integer) v.getTag();
                    onItemLongClickListener.onLongClick(v, mData.get(p), p);
                    return false;
                }
            });
        }
    }


    protected List<T> mData = new ArrayList<>();

    public void replaceAllData(List<T> ts) {
        mData.clear();
        if (ts != null && !ts.isEmpty()) {
            mData.addAll(ts);
        }
        notifyDataSetChanged();
    }

    public void addData(T t) {
        mData.add(t);
        notifyDataSetChanged();
    }

    public void addData(int position, T t) {
        mData.add(position, t);
        //notifyItemInserted(position);//点击事件更新不了
        notifyDataSetChanged();
    }

    public void addDataList(int position, List<T> ts) {
        mData.addAll(position, ts);
        //notifyItemInserted(position);//点击事件更新不了
        notifyDataSetChanged();
    }

    public void replaceDataListNotNotify(List<T> ts) {
        mData.clear();
        mData.addAll(ts);
    }

    public void addDataList(List<T> ts) {
        mData.addAll(ts);
        notifyDataSetChanged();
    }

    public void removeAllData() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        mData.remove(position);
        //notifyItemRemoved(position);//点击事件更新不了
        notifyDataSetChanged();
    }

    public void removeData(T t) {
        mData.remove(t);
        //notifyItemRemoved(position);//点击事件更新不了
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return mData;
    }


    protected View getRootView(ViewGroup parent, int layoutID) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
        return view;
    }

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onClick(View view, T t, int position);
    }

    protected OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.onItemLongClickListener = longClickListener;
    }

    public interface OnItemLongClickListener<T> {
        void onLongClick(View view, T t, int position);
    }


    protected final int TYPE_HEADER = 0;
    private final int TYPE_NORMAL = 1;
    private View headerView;

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }

    protected boolean isContainHeader() {
        return getHeaderView() != null;
    }

    @Override
    public int getItemViewType(int position) {
        if (isContainHeader()) {
            if (position == 0)
                return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return isContainHeader() ? mData.size() + 1 : mData.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }
}
