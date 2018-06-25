package com.yijian.staff.mvp.reception.step1.recyclerView;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView.Adapter implementation that
 * adds the ability to expand and collapse list items.
 * <p>
 * Changes should be notified through:
 * <p>
 * methods and not the notify methods of RecyclerView.Adapter.
 */
public abstract class ExpandableRecyclerAdapterGroup<P extends ParentImp<C>, C, PVH extends ParentViewHolderGroup, CVH extends ChildViewHolderGroup>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ExpandableRecyclerAdapt";
    /**
     * Default ViewType for parent rows
     */
    public static final int TYPE_PARENT = 0;
    /**
     * Default ViewType for children rows
     */
    public static final int TYPE_CHILD = 1;
    /**
     * Start of user-defined view types
     */
    public static final int TYPE_FIRST_USER = 2;
    private static final int INVALID_FLAT_POSITION = -1;

    /**
     * A {@link List} of all currently expanded parents and their children, in order.
     * Changes to this list should be made through the add/remove methods
     * available in {@link ExpandableRecyclerAdapterGroup}.
     */
    @NonNull
    protected List<ExpandableWrapperGroup<P, C>> mFlatItemList;

    @NonNull
    private List<P> mParentList;


    @NonNull
    private List<RecyclerView> mAttachedRecyclerViewPool;


    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * <p>
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    public ExpandableRecyclerAdapterGroup(@NonNull List<P> parentList) {
        super();
        mParentList = parentList;
        mFlatItemList = generateFlattenedParentChildList(parentList);
        mAttachedRecyclerViewPool = new ArrayList<>();
    }


    @UiThread
    public void setParentList(@NonNull List<P> parentList) {
        mParentList = parentList;
        mFlatItemList = generateFlattenedParentChildList(mParentList);
        notifyDataSetChanged();
    }


    /**
     * Implementation of Adapter.onCreateViewHolder(ViewGroup, int)
     * that determines if the list item is a parent or a child and calls through
     * to the appropriate implementation of either {@link #onCreateParentViewHolder(ViewGroup, int)}
     * or {@link #onCreateChildViewHolder(ViewGroup, int)}.
     *
     * @param viewGroup The {@link ViewGroup} into which the new {@link android.view.View}
     *                  will be added after it is bound to an adapter position.
     * @param viewType  The view type of the new {@code android.view.View}.
     * @return A new RecyclerView.ViewHolder
     * that holds a {@code android.view.View} of the given view type.
     */
    @NonNull
    @Override
    @UiThread
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (isParentViewType(viewType)) {
            PVH pvh = onCreateParentViewHolder(viewGroup, viewType);
            pvh.mExpandableAdapter = this;
            return pvh;
        } else {
            CVH cvh = onCreateChildViewHolder(viewGroup, viewType);
            cvh.mExpandableAdapter = this;
            return cvh;
        }
    }

    /**
     * Implementation of Adapter.onBindViewHolder(RecyclerView.ViewHolder, int)
     * that determines if the list item is a parent or a child and calls through
     * to the appropriate implementation of either
     * {@link #onBindParentViewHolder(ParentViewHolderGroup, int, ParentImp)} or
     * {@link #onBindChildViewHolder(ChildViewHolderGroup, int, int, Object, int)}.
     *
     * @param holder       The RecyclerView.ViewHolder to bind data to
     * @param flatPosition The index in the merged list of children and parents at which to bind
     */
    @Override
    @SuppressWarnings("unchecked")
    @UiThread
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int flatPosition) {
        if (flatPosition > mFlatItemList.size()) {
            throw new IllegalStateException("Trying to bind item out of bounds, size " + mFlatItemList.size()
                    + " flatPosition " + flatPosition + ". Was the data changed without a call to notify...()?");
        }

        ExpandableWrapperGroup<P, C> listItem = mFlatItemList.get(flatPosition);
        if (listItem.isParent()) {
            PVH parentViewHolder = (PVH) holder;

            parentViewHolder.mParent = listItem.getParent();
            onBindParentViewHolder(parentViewHolder, getNearestParentPosition(flatPosition), listItem.getParent());
        } else {
            CVH childViewHolder = (CVH) holder;
            childViewHolder.mChild = listItem.getChild();
            onBindChildViewHolder(childViewHolder, getNearestParentPosition(flatPosition), getChildPosition(flatPosition), listItem.getChild(), flatPosition);
        }
    }

    /**
     * Callback called from {@link #onCreateViewHolder(ViewGroup, int)} when
     * the list item created is a parent.
     *
     * @param parentViewGroup The {@link ViewGroup} in the list for which a {@link PVH} is being
     *                        created
     * @return A {@code PVH} corresponding to the parent with the {@code ViewGroup} parentViewGroup
     */
    @NonNull
    @UiThread
    public abstract PVH onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType);

    /**
     * Callback called from {@link #onCreateViewHolder(ViewGroup, int)} when
     * the list item created is a child.
     *
     * @param childViewGroup The {@link ViewGroup} in the list for which a {@link CVH}
     *                       is being created
     * @return A {@code CVH} corresponding to the child with the {@code ViewGroup} childViewGroup
     */
    @NonNull
    @UiThread
    public abstract CVH onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType);

    /**
     * Callback called from onBindViewHolder(RecyclerView.ViewHolder, int)
     * when the list item bound to is a parent.
     * <p>
     * Bind data to the {@link PVH} here.
     *
     * @param parentViewHolder The {@code PVH} to bind data to
     * @param parentPosition   The position of the parent to bind
     * @param parent           The parent which holds the data to be bound to the {@code PVH}
     */
    @UiThread
    public abstract void onBindParentViewHolder(@NonNull PVH parentViewHolder, int parentPosition, @NonNull P parent);

    /**
     * Callback called from onBindViewHolder(RecyclerView.ViewHolder, int)
     * when the list item bound to is a child.
     * <p>
     * Bind data to the {@link CVH} here.
     *
     * @param childViewHolder The {@code CVH} to bind data to
     * @param parentPosition  The position of the parent that contains the child to bind
     * @param childPosition   The position of the child to bind
     * @param child           The child which holds that data to be bound to the {@code CVH}
     * @param flatPosition
     */
    @UiThread
    public abstract void onBindChildViewHolder(@NonNull CVH childViewHolder, int parentPosition, int childPosition, @NonNull C child, int flatPosition);

    /**
     * Gets the number of parents and children currently expanded.
     *
     * @return The size of {@link #mFlatItemList}
     */
    @Override
    @UiThread
    public int getItemCount() {
        return mFlatItemList.size();
    }

    /**
     * For multiple view type support look at overriding {@link #getParentViewType(int)} and
     * {@link #getChildViewType(int, int)}. Almost all cases should override those instead
     * of this method.
     *
     * @param flatPosition The index in the merged list of children and parents to get the view type of
     * @return Gets the view type of the item at the given flatPosition.
     */
    @Override
    @UiThread
    public int getItemViewType(int flatPosition) {
//        Log.e(TAG, "getItemViewType: "+ mFlatItemList.size());
        ExpandableWrapperGroup<P, C> listItem = mFlatItemList.get(flatPosition);
        if (listItem.isParent()) {
            return getParentViewType(getNearestParentPosition(flatPosition));
        } else {
            return getChildViewType(getNearestParentPosition(flatPosition), getChildPosition(flatPosition));
        }
    }

    /**
     * Return the view type of the parent at {@code parentPosition} for the purposes of view recycling.
     * <p>
     * The default implementation of this method returns {@link #TYPE_PARENT}, making the assumption of
     * a single view type for the parents in this adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     * <p>
     * If you are overriding this method make sure to override {@link #isParentViewType(int)} as well.
     * <p>
     * Start your defined viewtypes at {@link #TYPE_FIRST_USER}
     *
     * @param parentPosition The index of the parent to query
     * @return integer value identifying the type of the view needed to represent the parent at
     * {@code parentPosition}. Type codes need not be contiguous.
     */
    public int getParentViewType(int parentPosition) {
        return TYPE_PARENT;
    }


    /**
     * Return the view type of the child {@code parentPosition} contained within the parent
     * at {@code parentPosition} for the purposes of view recycling.
     * <p>
     * The default implementation of this method returns {@link #TYPE_CHILD}, making the assumption of
     * a single view type for the children in this adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     * <p>
     * Start your defined viewtypes at {@link #TYPE_FIRST_USER}
     *
     * @param parentPosition The index of the parent continaing the child to query
     * @param childPosition  The index of the child within the parent to query
     * @return integer value identifying the type of the view needed to represent the child at
     * {@code parentPosition}. Type codes need not be contiguous.
     */
    public int getChildViewType(int parentPosition, int childPosition) {
        return TYPE_CHILD;
    }

    /**
     * Used to determine whether a viewType is that of a parent or not, for ViewHolder creation purposes.
     * <p>
     * Only override if {@link #getParentViewType(int)} is being overriden
     *
     * @param viewType the viewType identifier in question
     * @return whether the given viewType belongs to a parent view
     */
    public boolean isParentViewType(int viewType) {
        return viewType == TYPE_PARENT;
    }

    /**
     * Gets the list of parents that is backing this adapter.
     * Changes can be made to the list and the adapter notified via the
     * <p>
     * methods.
     *
     * @return The list of parents that this adapter represents
     */
    @NonNull
    @UiThread
    public List<P> getParentList() {
        return mParentList;
    }


    /**
     * Implementation of Adapter#onAttachedToRecyclerView(RecyclerView).
     * <p>
     * Called when this {@link ExpandableRecyclerAdapterGroup} is attached to a RecyclerView.
     *
     * @param recyclerView The {@code RecyclerView} this {@code ExpandableRecyclerAdapter}
     *                     is being attached to
     */
    @Override
    @UiThread
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mAttachedRecyclerViewPool.add(recyclerView);
    }


    /**
     * Implementation of Adapter.onDetachedFromRecyclerView(RecyclerView)
     * <p>
     * Called when this ExpandableRecyclerAdapter is detached from a RecyclerView.
     *
     * @param recyclerView The {@code RecyclerView} this {@code ExpandableRecyclerAdapter}
     *                     is being detached from
     */
    @Override
    @UiThread
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mAttachedRecyclerViewPool.remove(recyclerView);
    }


    /**
     * Given the index relative to the entire RecyclerView, returns the nearest
     * ParentPosition without going past the given index.
     * <p>
     * If it is the index of a parent, will return the corresponding parent position.
     * If it is the index of a child within the RV, will return the position of that child's parent.
     */
    @UiThread
    int getNearestParentPosition(int flatPosition) {
        if (flatPosition == 0) {
            return 0;
        }

        int parentCount = -1;
        for (int i = 0; i <= flatPosition; i++) {
            ExpandableWrapperGroup<P, C> listItem = mFlatItemList.get(i);
            if (listItem.isParent()) {
                parentCount++;
            }
        }

//        Log.e(TAG, "getNearestParentPosition: "+parentCount );
        return parentCount;
    }

    /**
     * Given the index relative to the entire RecyclerView for a child item,
     * returns the child position within the child list of the parent.
     */
    @UiThread
    int getChildPosition(int flatPosition) {
        if (flatPosition == 0) {
            return 0;
        }

        int childCount = 0;
        for (int i = 0; i < flatPosition; i++) {
            ExpandableWrapperGroup<P, C> listItem = mFlatItemList.get(i);
            if (listItem.isParent()) {
                childCount = 0;
            } else {
                childCount++;
            }
        }
        return childCount;
    }


    // endregion

    /**
     * Generates a full list of all parents and their children, in order.
     *
     * @param parentList A list of the parents from
     *                   the {@link ExpandableRecyclerAdapterGroup}
     * @return A list of all parents and their children, expanded
     */
    private List<ExpandableWrapperGroup<P, C>> generateFlattenedParentChildList(List<P> parentList) {
        List<ExpandableWrapperGroup<P, C>> flatItemList = new ArrayList<>();
        if (parentList == null || parentList.size() == 0) return flatItemList;
        int parentCount = parentList.size();
        for (int i = 0; i < parentCount; i++) {
            P parent = parentList.get(i);
            generateParentWrapper(flatItemList, parent);
        }

        return flatItemList;
    }


    private void generateParentWrapper(List<ExpandableWrapperGroup<P, C>> flatItemList, P parent) {
        ExpandableWrapperGroup<P, C> parentWrapper = new ExpandableWrapperGroup<>(parent);
        flatItemList.add(parentWrapper);
        generateExpandedChildren(flatItemList, parentWrapper);
    }

    private void generateExpandedChildren(List<ExpandableWrapperGroup<P, C>> flatItemList, ExpandableWrapperGroup<P, C> parentWrapper) {
//        parentWrapper.setExpanded(true);

        List<ExpandableWrapperGroup<P, C>> wrappedChildList = parentWrapper.getWrappedChildList();
        int childCount = wrappedChildList.size();
        for (int j = 0; j < childCount; j++) {
            ExpandableWrapperGroup<P, C> childWrapper = wrappedChildList.get(j);
            flatItemList.add(childWrapper);
        }
    }


}
