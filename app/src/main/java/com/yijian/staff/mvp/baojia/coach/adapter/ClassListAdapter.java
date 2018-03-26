package com.yijian.staff.mvp.baojia.coach.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.baojia.coach.bean.ClassInfo;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/3 17:08:17
 */
public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {
    private List<ClassInfo> mClassInfoList;
    private Context context;
    private int clickIndex=-1;

    public ClassListAdapter(Context context, List<ClassInfo> mClassInfoList) {
        this.mClassInfoList = mClassInfoList;
        this.context = context;
    }

    @Override
    public ClassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClassListAdapter.ViewHolder holder, int position) {
        Logger.i("ClassListAdapter", "position: " + position);


        ClassInfo classInfo = mClassInfoList.get(position);
        holder.tvGoodsName.setText(classInfo.getGoodsName());
        holder.tvJianshenplace.setText(classInfo.getJianshenplace());
        holder.tvYuEr.setText(classInfo.getYuer());
        holder.tvChuzhiyouhui.setText(classInfo.getChuzhiyouhui());
        holder.tvPrice.setText(classInfo.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(v, classInfo);
                }
                clickIndex=position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClassInfoList.size();
    }

    public void update(List<ClassInfo> mClassInfoList) {
        this.mClassInfoList = mClassInfoList;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemView;
        TextView tvGoodsName;
        TextView tvJianshenplace;
        TextView tvYuEr;
        TextView tvChuzhiyouhui;
        TextView tvPrice;


        public ViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.item_view);
            tvGoodsName = view.findViewById(R.id.tv_goods_name);
            tvJianshenplace = view.findViewById(R.id.tv_jianshenplace);
            tvYuEr = view.findViewById(R.id.tv_yu_er);
            tvChuzhiyouhui = view.findViewById(R.id.tv_chuzhiyouhui);
            tvPrice = view.findViewById(R.id.tv_price);

        }
    }
  private   OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{

       void onItemClick(View v, ClassInfo classInfo);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
