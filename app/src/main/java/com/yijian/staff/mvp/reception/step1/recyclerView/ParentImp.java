package com.yijian.staff.mvp.reception.step1.recyclerView;

import java.util.List;

/**
 * Interface for implementing required methods in a parent.
 */
public interface ParentImp<C> {

    /**
     * Getter for the list of this parent's child items.
     * <p>
     * If list is empty, the parent has no children.
     *
     * @return A {@link List} of the children of this {@link ParentImp}
     */
    List<C> getChildList();


}