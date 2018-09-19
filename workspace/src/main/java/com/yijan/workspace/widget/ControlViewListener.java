package com.yijan.workspace.widget;

public interface ControlViewListener {

    //移动时的监听
    void invalidateMoveXY(float x, float y);
    //放开时的监听
    void invalidateUpXY();
    //监听是否选择了拖动点
    void checkDragPoint();

}
