package com.yijian.staff.mvp.course.setclass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.TypeOfActionItem;
import com.yijian.staff.bean.TypeOfActionTitle;
import com.yijian.staff.util.PopupWindowUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by The_P on 2018/3/22.
 */

public class AdapterLesson extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "AdapterLesson";
    private Context context;
    private List<Object> actionList = new ArrayList<>();
    private final TypeOfActionTitle typeOfAction;

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    public int index_point = 1;//可以点击的最大位置（只能按顺序点击）

    public AdapterLesson(Context context) {
        this.context = context;
        //添加标题栏
        typeOfAction = new TypeOfActionTitle("动作", "动作形态", "强度", "时间(s)", "间隔(s)");

        actionList.add(typeOfAction);//添加头部

    }

    public void resetActionList(List<Object> list) {
        actionList.clear();
        actionList.add(typeOfAction);//添加头部
        actionList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case TYPE_TITLE:
                View title = LayoutInflater.from(context).inflate(R.layout.item_openlesson_action_title, parent, false);
                viewHolder = new TitleHolder(title);
                break;
            default:
            case TYPE_ITEM:
                View item = LayoutInflater.from(context).inflate(R.layout.item_openlesson_action_item, parent, false);
                viewHolder = new ItemHolder(item);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = actionList.get(position);
        if (holder instanceof TitleHolder && o instanceof TypeOfActionTitle) {
            ((TitleHolder) holder).bind((TypeOfActionTitle) o);
        } else if (holder instanceof ItemHolder && o instanceof TypeOfActionItem) {
            ((ItemHolder) holder).bind((TypeOfActionItem) o, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;
        Object object = actionList.get(position);
        if (object instanceof TypeOfActionTitle) {
//            Log.e(TAG, "getItemViewType: TypeOfActionTitle=="+position );
            type = TYPE_TITLE;
        } else if (object instanceof TypeOfActionItem) {
//            Log.e(TAG, "getItemViewType: TypeOfActionItem=="+position );
            type = TYPE_ITEM;
        }
//        Log.e(TAG, "getItemViewType: "+type );
        return type;
    }

    @Override
    public int getItemCount() {
        return actionList.size();
    }


    public class TitleHolder extends RecyclerView.ViewHolder {
        TextView tvActionName;
        TextView tvActionType;
        TextView tvActionIntensity;
        TextView tvActionTime;
        TextView tvActionInterval;

        public TitleHolder(View itemView) {
            super(itemView);
            tvActionName = itemView.findViewById(R.id.tv_action_name);
            tvActionType = itemView.findViewById(R.id.tv_action_type);
            tvActionIntensity = itemView.findViewById(R.id.tv_action_intensity);
            tvActionTime = itemView.findViewById(R.id.tv_action_time);
            tvActionInterval = itemView.findViewById(R.id.tv_action_interval);
        }

        public void bind(TypeOfActionTitle typeOfActionTitle) {
            tvActionName.setText(typeOfActionTitle.getActionName());
            tvActionType.setText(typeOfActionTitle.getActionForm());
            tvActionIntensity.setText(typeOfActionTitle.getActionIntensity());
            tvActionTime.setText(typeOfActionTitle.getActionTime());
            tvActionInterval.setText(typeOfActionTitle.getActionTimeInterval());
        }
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvActionName;
        TextView tvActionType;
        TextView tvActionIntensity;
        TextView tvActionTime;
        TextView tvActionInterval;
        private View itemView;

        public ItemHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvActionName = itemView.findViewById(R.id.tv_action_name);
            tvActionType = itemView.findViewById(R.id.tv_action_type);
            tvActionIntensity = itemView.findViewById(R.id.tv_action_intensity);
            tvActionTime = itemView.findViewById(R.id.tv_action_time);
            tvActionInterval = itemView.findViewById(R.id.tv_action_interval);
        }

        public void bind(TypeOfActionItem typeOfActionItem, int parentPosition) {
            tvActionName.setText(typeOfActionItem.getActionName());

            if (!TextUtils.isEmpty(typeOfActionItem.getActionForm())) {
                tvActionType.setText("" + typeOfActionItem.getActionForm());
//                index_point=parentPosition;//记录当前应该点击的位置
            }

            if (!TextUtils.isEmpty(typeOfActionItem.getActionIntensity())) {
                tvActionIntensity.setText("" + typeOfActionItem.getActionIntensity());
//                index_point=parentPosition;//记录当前点击的位置
            }

            if (!TextUtils.isEmpty(typeOfActionItem.getActionTime())) {
                tvActionTime.setText("" + typeOfActionItem.getActionTime());
                tvActionTime.setPadding(0, 0, 0, 0);
                tvActionTime.setCompoundDrawables(null, null, null, null);
                if (index_point < parentPosition + 1) index_point = parentPosition + 1;//记录当前可以点击的最大位置
            }
            tvActionType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (parentPosition > index_point) {
                        Toast.makeText(context, "请按顺序测试所有项目", Toast.LENGTH_SHORT).show();
                        return;
                    }


//                    showPopupWindows(tvActionType,typeOfActionItem);
                    PopupWindowUtil popupWindowUtil = new PopupWindowUtil(context, Arrays.asList("标准", "非标准"));
                    popupWindowUtil.showUp(v);
                    popupWindowUtil.setPopupWindowsItemListener(new PopupWindowUtil.PopupWindowsItemListener() {
                        @Override
                        public void onItemClick(String name, int position) {
                            popupWindowUtil.dissmiss();
                            tvActionType.setText(name + "position==" + position);
                            typeOfActionItem.setActionForm(name);
                        }
                    });

                }
            });

            tvActionIntensity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (parentPosition > index_point) {
                        Toast.makeText(context, "请按顺序测试所有项目", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    PopupWindowUtil popupWindowUtil = new PopupWindowUtil(context, Arrays.asList("强", "中", "弱"));
                    popupWindowUtil.showUp(v);
                    popupWindowUtil.setPopupWindowsItemListener(new PopupWindowUtil.PopupWindowsItemListener() {
                        @Override
                        public void onItemClick(String name, int position) {
                            popupWindowUtil.dissmiss();
                            tvActionIntensity.setText(name + "position==" + position);
                            typeOfActionItem.setActionIntensity(name);
                        }
                    });
                }
            });


            tvActionTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick: parentPosition--------" + parentPosition + "   index_point-----"
                            + index_point);
                    if (parentPosition > index_point) {
                        Toast.makeText(context, "请按顺序测试所有项目", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ((ActivityOpenLesson) context).showClockView(parentPosition - 1);//去除标题栏

                }
            });


            if (!TextUtils.isEmpty(typeOfActionItem.getActionTimeInterval())) {
                tvActionInterval.setText(typeOfActionItem.getActionTimeInterval());
            }


        }

//
    }


    public void setIndex_point(int index) {
        index_point = index;
    }

    public int getIndex_point() {
        return index_point;
    }
}
