package com.yijian.staff.mvp.coach.experienceclass.step4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.step4.bean.ExperienceClassRecordTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/25.
 */

public class ExperienceStep4Adapter extends RecyclerView.Adapter<ExperienceStep4Adapter.Holder> {
    private Context context;
    private List<ExperienceClassRecordTable> list = new ArrayList<>();

    public ExperienceStep4Adapter(Context context) {
        this.context = context;
    }

    @Override
    public ExperienceStep4Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_experience_step4, parent, false);

        return new Holder(view);
    }

    public void resetData(List<ExperienceClassRecordTable> list1) {
        list.clear();
        list.addAll(list1);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ExperienceStep4Adapter.Holder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView tvName;

        public Holder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }


        public void bindView(ExperienceClassRecordTable experienceClassRecordTable) {
            if (experienceClassRecordTable == null) return;
            String templateName = experienceClassRecordTable.getTemplateName();
            tvName.setText(TextUtils.isEmpty(templateName) ? "无名" : templateName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickLisenter != null) itemClickLisenter.onClick(experienceClassRecordTable);
                }
            });
        }
    }

    public interface ItemClickLisenter {
        void onClick(ExperienceClassRecordTable bean);
    }

    private ItemClickLisenter itemClickLisenter;

    public void setItemClickLisenter(ItemClickLisenter itemClickLisenter) {
        this.itemClickLisenter = itemClickLisenter;
    }
}
