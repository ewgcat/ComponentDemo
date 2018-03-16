package com.yijian.staff.mvp.reception.step1.recyclerView;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper used to link metadata with a list item.
 *
 * @param <P> Parent list item
 * @param <C> Child list item
 */
public class ExpandableWrapperGroup<P extends ParentImp<C>, C> {

    private P mParent;
    private C mChild;
    private boolean mWrappedParent;

    private List<ExpandableWrapperGroup<P, C>> mWrappedChildList;

    /**
     * Constructor to wrap a parent object of type {@link P}.
     *
     * @param parent The parent object to wrap
     */
    public ExpandableWrapperGroup(@NonNull P parent) {
        mParent = parent;
        mWrappedParent = true;

        mWrappedChildList = generateChildItemList(parent);
    }

    /**
     * Constructor to wrap a child object of type {@link C}
     *
     * @param child The child object to wrap
     */
    public ExpandableWrapperGroup(@NonNull C child) {
        mChild = child;
        mWrappedParent = false;
    }

    public P getParent() {
        return mParent;
    }


    public void setParent(@NonNull P parent) {
        mParent = parent;
        mWrappedChildList = generateChildItemList(parent);
    }



    public C getChild() {
        return mChild;
    }



    public boolean isParent() {
        return mWrappedParent;
    }



    /**
     * @return The list of children of a parent
     * @throws IllegalStateException If a parent isn't being wrapped
     */
    public List<ExpandableWrapperGroup<P, C>> getWrappedChildList() {
        if (!mWrappedParent) {
            throw new IllegalStateException("Parent not wrapped");
        }

        return mWrappedChildList;
    }

    private List<ExpandableWrapperGroup<P, C>> generateChildItemList(P parentListItem) {
        List<ExpandableWrapperGroup<P, C>> childItemList = new ArrayList<>();

        for (C child : parentListItem.getChildList()) {
            childItemList.add(new ExpandableWrapperGroup<P, C>(child));
        }

        return childItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ExpandableWrapperGroup<?, ?> that = (ExpandableWrapperGroup<?, ?>) o;

        if (mParent != null ? !mParent.equals(that.mParent) : that.mParent != null)
            return false;
        return mChild != null ? mChild.equals(that.mChild) : that.mChild == null;

    }

    @Override
    public int hashCode() {
        int result = mParent != null ? mParent.hashCode() : 0;
        result = 31 * result + (mChild != null ? mChild.hashCode() : 0);
        return result;
    }
}
