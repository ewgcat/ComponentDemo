package com.yijian.staff.mvp.reception.physical;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.Parent_Step2ViewHolder;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;

import java.util.List;

/**
 * Created by The_P on 2018/3/13.
 */

public class PhysicalReportAdapter extends ExpandableRecyclerAdapter<ParentQuestionBean, ChildOptBean, Parent_Step2ViewHolder, ChildViewHolder> {

    private LayoutInflater mInflater;
    private List<ParentQuestionBean> parentList;
    private Activity mContext;
    private static final String TAG = "DemoAdapter";

    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    public PhysicalReportAdapter(@NonNull List<ParentQuestionBean> parentList, Activity context) {
        super(parentList);
        this.parentList = parentList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void resetData(List<ParentQuestionBean> list) {
        parentList = list;
        setParentList(list, true);
    }

    @NonNull
    @Override
    public Parent_Step2ViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_parent_demo, parentViewGroup, false);
        Parent_Step2ViewHolder parent_demoViewHolder = new Parent_Step2ViewHolder(inflate, mContext);
        return parent_demoViewHolder;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
//        View inflate = mInflater.inflate(R.layout.item_parent_demo, parentViewGroup, false);
        ChildViewHolder childViewHolder;

        switch (viewType) {
            case 3:
                View view0 = mInflater.inflate(R.layout.item_child_type_normal, childViewGroup, false);
                childViewHolder = new Child_physical_normal(view0);
                break;

            case 5:
                View view2 = mInflater.inflate(R.layout.item_child_type_write_physical, childViewGroup, false);
                childViewHolder = new Child_physical_write(view2);
                break;
            default:
            case 6:
                View view3 = mInflater.inflate(R.layout.item_child_type_display, childViewGroup, false);
                childViewHolder = new Child_physical_dispaly(view3);
                break;

            case 8:
                View view8 = mInflater.inflate(R.layout.item_child_type_display_multi, childViewGroup, false);
                childViewHolder = new Child_physical_dispalyMulti(view8, mContext);
                break;

            case 9:
                View view9 = mInflater.inflate(R.layout.item_child_type_edit_physical, childViewGroup, false);
                childViewHolder = new Child_physical_edit(view9);

                break;
        }

        return childViewHolder;
    }

    @Override
    public void onBindParentViewHolder(@NonNull Parent_Step2ViewHolder parentViewHolder, int parentPosition, @NonNull ParentQuestionBean parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull ChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull ChildOptBean child) {
        if (childViewHolder instanceof Child_physical_normal) {
            ((Child_physical_normal) childViewHolder).bind(child, childPosition, parentPosition, parentList);
        } else if (childViewHolder instanceof Child_physical_write) {
            ((Child_physical_write) childViewHolder).bind(child, childPosition, parentPosition, parentList);
        } else if (childViewHolder instanceof Child_physical_dispaly) {
            ((Child_physical_dispaly) childViewHolder).bind(child, childPosition, parentPosition);
        } else if (childViewHolder instanceof Child_physical_dispalyMulti) {
            ((Child_physical_dispalyMulti) childViewHolder).bind(child, childPosition, parentPosition, mContext);
        } else if (childViewHolder instanceof Child_physical_edit) {
            ((Child_physical_edit) childViewHolder).bind(child, childPosition, parentPosition);
        }
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {

        int type;

        ChildOptBean childObjBean = parentList.get(parentPosition).getChildList().get(childPosition);
        switch (childObjBean.getQusType()) {
            case "normal"://默认的单选
                type = 3;
                break;
            case "write"://填空
                type = 5;
                break;
            default:
            case "dispaly"://展示
                type = 6;
                break;

            case "display_multi_opt"://多选
                type = 8;
                break;
            case "edit"://编辑类
                type = 9;
                break;
        }

        return type;
    }


}
