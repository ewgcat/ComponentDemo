package com.yijian.staff.mvp.course.preparelessons.index;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.TemplateBean;
import com.yijian.staff.mvp.course.preparelessons.createlession.PrepareLessonDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/17 11:24:37
 */
public class TemplatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<TemplateBean> templateBeanList = new ArrayList<>();
    private Context context;
    private int selectTemplatePosition=0;

    public TemplatesAdapter(Context context, List<TemplateBean> templateBeanList) {
        this.context = context;
        this.templateBeanList = templateBeanList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TemplateBean templateBean = templateBeanList.get(position);
        ((ViewHolder) holder).bind(templateBean, position);
    }

    @Override
    public int getItemCount() {
        return templateBeanList == null ? 0 : templateBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout llTemplate;
        private TextView tvTemplateName;
        private View line;
        private TextView tvDetail;


        public ViewHolder(View itemView) {
            super(itemView);
            llTemplate = itemView.findViewById(R.id.ll_template);
            tvTemplateName = itemView.findViewById(R.id.tv_template_name);
            line = itemView.findViewById(R.id.view_line_cursor);
            tvDetail = itemView.findViewById(R.id.tv_detail);
        }

        private void bind(TemplateBean templateBean, int position) {

            String templateName = templateBean.getTemplateName();
            if (TextUtils.isEmpty(templateName)){
                tvTemplateName.setText(templateName);
            }

            if (selectTemplatePosition==position){
                line.setVisibility(View.VISIBLE);
            }else {
                line.setVisibility(View.INVISIBLE);
            }

            llTemplate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectTemplatePosition=position;
                    if (selectTemplateListener!=null){
                        selectTemplateListener.onSelectTemplate(templateBean,position);
                    }
                    notifyDataSetChanged();
                }
            });

            tvDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,PrepareLessonDetailActivity.class);
                    intent.putExtra("TemplateBean",templateBean);
                    context.startActivity(intent);
                }
            });


        }

    }

    public interface SelectTemplateListener{

        void onSelectTemplate(TemplateBean templateBean, int position);
    }

    private SelectTemplateListener selectTemplateListener;
    public void setSelectTemplateListener(SelectTemplateListener selectTemplateListener){
        this.selectTemplateListener=selectTemplateListener;
    }


}
