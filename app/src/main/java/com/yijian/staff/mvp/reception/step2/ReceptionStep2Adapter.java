package com.yijian.staff.mvp.reception.step2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by The_P on 2018/3/13.
 */

public class ReceptionStep2Adapter extends ExpandableRecyclerAdapter<ParentQuestionBean, ChildOptBean, Parent_Step2ViewHolder, ChildViewHolder> implements Child_Step2ViewHolder_normal.ChildNormalListener, Child_Step2ViewHolder_write.ChildWriteListener, Child_Step2ViewHolder_edit.ChildEditListener, Child_Step2ViewHolder_dispalyMulti.ItemMultiListener {

    private LayoutInflater mInflater;
    private List<ParentQuestionBean> parentList;
    private Activity mContext;
    private static final String TAG = "DemoAdapter";
    private MultiAdapter multiAdapter;

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
    public ReceptionStep2Adapter(@NonNull List<ParentQuestionBean> parentList, Activity context) {
        super(parentList);
        this.parentList = parentList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public Parent_Step2ViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_parent_demo, parentViewGroup, false);
        Parent_Step2ViewHolder parent_demoViewHolder = new Parent_Step2ViewHolder(inflate,mContext);
        return parent_demoViewHolder;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
//        View inflate = mInflater.inflate(R.layout.item_parent_demo, parentViewGroup, false);
        ChildViewHolder childViewHolder;

        switch (viewType){
            case 3:
                View view0 = mInflater.inflate(R.layout.item_child_type_normal, childViewGroup, false);
                childViewHolder=new Child_Step2ViewHolder_normal(view0);
                ((Child_Step2ViewHolder_normal)childViewHolder).setChildNormalListener(this);
                break;

            case 5:
                View view2 = mInflater.inflate(R.layout.item_child_type_write, childViewGroup, false);
                childViewHolder=new Child_Step2ViewHolder_write(view2);
                ((Child_Step2ViewHolder_write)childViewHolder).setChildWriteListener(this);
                break;
            default:
            case 6:
                View view3 = mInflater.inflate(R.layout.item_child_type_display, childViewGroup, false);
                childViewHolder=new Child_Step2ViewHolder_dispaly(view3);
                break;

            case 8:
                View view8 = mInflater.inflate(R.layout.item_child_type_display_multi, childViewGroup, false);
                childViewHolder=new Child_Step2ViewHolder_dispalyMulti(view8,mContext);
                ((Child_Step2ViewHolder_dispalyMulti)childViewHolder).setChildMultiListener(this);
                break;

            case 9:
                View view9 = mInflater.inflate(R.layout.item_child_type_edit, childViewGroup, false);
                childViewHolder=new Child_Step2ViewHolder_edit(view9);
                ((Child_Step2ViewHolder_edit)childViewHolder).setChildEditListener(this);

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
        if (childViewHolder instanceof Child_Step2ViewHolder_normal){
            ((Child_Step2ViewHolder_normal) childViewHolder).bind(child,childPosition,parentPosition,parentList);
        }else if (childViewHolder instanceof Child_Step2ViewHolder_write){
            ((Child_Step2ViewHolder_write) childViewHolder).bind(child,childPosition,parentPosition,parentList);
        }else if (childViewHolder instanceof Child_Step2ViewHolder_dispaly){
            ((Child_Step2ViewHolder_dispaly) childViewHolder).bind(child,childPosition,parentPosition);
        }else if(childViewHolder instanceof Child_Step2ViewHolder_dispalyMulti){
            ((Child_Step2ViewHolder_dispalyMulti) childViewHolder).bind(child,childPosition,parentPosition,mContext);
            multiAdapter = ((Child_Step2ViewHolder_dispalyMulti) childViewHolder).getMultiAdapter();
        }else if (childViewHolder instanceof Child_Step2ViewHolder_edit){
            ((Child_Step2ViewHolder_edit) childViewHolder).bind(child,childPosition,parentPosition);
        }
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {

        int type;

        ChildOptBean childObjBean = parentList.get(parentPosition).getChildList().get(childPosition);
        switch (childObjBean.getQusType()){
            case "normal"://默认的单选
                type=3;
                break;
//            case "multi":
//                type=4;
//                break;
            case "write"://填空
                type=5;
                break;
            default:
            case "dispaly"://展示
                type=6;
                break;

//            case "multiAndEdit":
//                type=7;
//                break;
            case "display_multi_opt"://多选
                type=8;
                break;
            case "edit"://编辑类
                type=9;
                break;
        }

        return type;
    }


    /**
     * 单选
     * @param childPosition
     * @param parentPosition
     */
    @Override
    public void onChildNormalClick(int childPosition, int parentPosition) {
        ((ReceptionStepTwoActivity_ycm) mContext).showBottomView(childPosition,parentPosition);

    }

    private Map<String,HashMap<String,Boolean>> multiCheck=new HashMap<>();//多选的结果集合(数据结构为：String（大类——人体测量/基础测量），String（ 选项名称）,Boolean(是否被选中))

//    @Override
//    public void onChildMultiClick(ChildObjBean child_demo, int childPosition, int parentPosition) {
//        child_demo.setSelected(child_demo.isSelected()?false:true);
//        notifyChildChanged(parentPosition,childPosition);
//
////        getMultiCheckQuestionName();
//
//        String questionName=null;
//
//        ParentObjBean parentObjBean = parentList.get(parentPosition);
//        List<ChildObjBean> childList = parentObjBean.getChildList();
//        for (int i = (childPosition-1); i >=0 ; i--) {
//            String qusType = childList.get(i).getQusType();
//            if ("display_multi_opt".equals(qusType)){
//                questionName=childList.get(i).getQustion();
//                break;
//            }
//        }
//
//
//
////        int getNearestParentPosition(int flatPosition) {
////            if (flatPosition == 0) {
////                return 0;
////            }
////
////            int parentCount = -1;
////            for (int i = 0; i <= flatPosition; i++) {
////                ExpandableWrapper<P, C> listItem = mFlatItemList.get(i);
////                if (listItem.isParent()) {
////                    parentCount++;
////                }
////            }
////            return parentCount;
////        }
//
//
//
//
//
//
////        HashMap<String, Boolean> stringBooleanHashMap = multiCheck.get(parentList.get(parentPosition).getTitle());
////
////        if (child_demo.isSelected()){
////            if (stringBooleanHashMap==null)stringBooleanHashMap =new HashMap<>();
////            stringBooleanHashMap.put(child_demo.getQustion(),true);
////        }else {
////            if (stringBooleanHashMap!=null){
////                stringBooleanHashMap.remove(child_demo.getQustion());
////            }
////        }
////        multiCheck.put(parentList.get(parentPosition).getTitle(),stringBooleanHashMap);
////
////        for (int i = 0; i < multiCheck.size(); i++) {
////
////        }
//
//        Log.e(TAG, "onChildMultiClick: "+multiCheck );
//    }


    /**
     * 填空
     * @param childPosition
     * @param parentPosition
     * @param s
     */
    @Override
    public void onChildWrited(int childPosition, int parentPosition, Editable s) {

    }


    /**
     * 这里指 基础代谢
     * @param child
     * @param childPosition
     * @param parentPosition
     * @param s
     */
    @Override
    public void onChildEdited(ChildOptBean child, int childPosition, int parentPosition, Editable s) {

    }

    /**
     * 多选点击
     * @param child_demo
     * @param multiItemPosition 多选的选项在选项中的位置
     * @param childPosition     在reyclerview中parent的子列表中的位置
     * @param parentPosition       在reyclerview中parent的位置
     */
    @Override
    public void onItemMultiClick(MultiOptBean child_demo, int multiItemPosition, int childPosition, int parentPosition) {
        child_demo.setIsSelected(child_demo.isIsSelected()?false:true);
        multiAdapter.notifyDataSetChanged();
//        notifyChildChanged(parentPosition,childPosition);

    }

    /**
     * 多选编辑
     * @param child_demo
     * @param multiItemPosition 多选的选项在选项中的位置
     * @param childPosition 在reyclerview中parent的子列表中的位置
     * @param parentPosition  在reyclerview中parent的位置
     * @param s
     */
    @Override
    public void onItemMultiWrited(MultiOptBean child_demo, int multiItemPosition, int childPosition, int parentPosition, Editable s) {

    }


}
