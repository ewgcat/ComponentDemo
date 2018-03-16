package com.yijian.staff.mvp.reception.step1.recyclerView;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * ViewHolder for a {@link ParentImp}
 * Keeps track of expanded state and holds callbacks which can be used to
 * trigger expansion-based events.
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class ParentViewHolderGroup<P extends ParentImp<C>, C> extends RecyclerView.ViewHolder {


    P mParent;
    ExpandableRecyclerAdapterGroup mExpandableAdapter;



    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    @UiThread
    public ParentViewHolderGroup(@NonNull View itemView) {
        super(itemView);

    }

    /**
     * @return the Parent associated with this ViewHolder
     */
    @UiThread
    public P getParent() {
        return mParent;
    }

    /**
     * Returns the adapter position of the Parent associated with this ParentViewHolder
     *
     * @return The adapter position of the Parent if it still exists in the adapter.
     * RecyclerView.NO_POSITION if item has been removed from the adapter,
     * RecyclerView.Adapter.notifyDataSetChanged() has been called after the last
     * layout pass or the ViewHolder has already been recycled.
     */
    @UiThread
    public int getParentAdapterPosition() {
        int flatPosition = getAdapterPosition();
        if (flatPosition == RecyclerView.NO_POSITION) {
            return flatPosition;
        }

        return mExpandableAdapter.getNearestParentPosition(flatPosition);
    }



}
