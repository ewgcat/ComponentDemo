package com.yijian.staff.mvp.reception.step2;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;

import java.util.List;


/**
 * Created by The_P on 2018/3/13.
 */

public class Child_Step2ViewHolder_write extends ChildViewHolder {

    private final EditText etContent;
    private final View seatView;
    private final View itemView;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Child_Step2ViewHolder_write(@NonNull View itemView) {
        super(itemView);
        etContent = (EditText) itemView.findViewById(R.id.et_content);
        seatView = itemView.findViewById(R.id.seat_view);
        this.itemView =itemView;
    }

    public void bind(ChildOptBean child, int childPosition, int parentPosition, List<ParentQuestionBean> parentList) {
        SpannableString s = new SpannableString(child.getQustion());//这里输入自己想要的提示文字
        etContent.setHint(s);


        int size = parentList.get(parentPosition).getChildList().size();
        if (childPosition==size-1){
            seatView.setVisibility(View.VISIBLE);
            itemView.setBackgroundResource(R.drawable.shape_fillet_white_down_8);
        }else {
            seatView.setVisibility(View.GONE);
            itemView.setBackgroundResource(R.color.white);
        }
    }



    public interface ChildWriteListener{
        void onChildWrited(int childPosition, int parentPosition, Editable s);
    }

    private ChildWriteListener listener;

    public void setChildWriteListener(ChildWriteListener childWriteListener){
        listener=childWriteListener;
    }
}
