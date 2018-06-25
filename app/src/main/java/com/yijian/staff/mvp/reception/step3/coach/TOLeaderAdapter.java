package com.yijian.staff.mvp.reception.step3.coach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step3.coach.bean.LeaderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/19.
 */

public class TOLeaderAdapter extends RecyclerView.Adapter<TOLeaderAdapter.Holder> {
    private Context context;
    private List<LeaderBean> listBeans = new ArrayList<>();

    public TOLeaderAdapter(Context context) {
        this.context = context;
    }

    public void resetData(List<LeaderBean> list) {
        listBeans.clear();
        listBeans.addAll(list);
        notifyDataSetChanged();

    }


    @Override
    public TOLeaderAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_toleader, parent, false);
        Holder holder = new Holder(view);
        int itemWidth = parent.getWidth() / 3;
        holder.itemView.getLayoutParams().width = itemWidth;
        // 计算Item的宽度
        return holder;
    }

    @Override
    public void onBindViewHolder(TOLeaderAdapter.Holder holder, int position) {
        holder.bindView(listBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView title;
        private final TextView select;

        public Holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            title = itemView.findViewById(R.id.tv_title);
            select = itemView.findViewById(R.id.tv_select);

        }

        public void bindView(LeaderBean dataListBean) {
//            select.setBackgroundResource(dataListBean.isSelect?R.drawable.blue_solid_select_bg_13:R.drawable.blue_solid_unselect_bg_13);
            select.setSelected(dataListBean.isSelect);
            title.setText("" + dataListBean.getPostName());
            name.setText("" + dataListBean.getPost());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (LeaderBean bean : listBeans) {
                        bean.setSelect(false);
                    }
                    dataListBean.setSelect(true);
                    notifyDataSetChanged();

                    if (itemSelectLisenter != null) itemSelectLisenter.onclick(dataListBean);
                }
            });
        }
    }

    public interface ItemSelectLisenter {
        void onclick(LeaderBean bean);
    }

    private ItemSelectLisenter itemSelectLisenter;

    public void setItemSelectLisenter(ItemSelectLisenter itemSelectLisenter) {
        this.itemSelectLisenter = itemSelectLisenter;
    }
}
