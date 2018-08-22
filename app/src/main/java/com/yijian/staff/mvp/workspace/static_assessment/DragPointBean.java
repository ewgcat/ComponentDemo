package com.yijian.staff.mvp.workspace.static_assessment;

import android.graphics.PointF;

public class DragPointBean {

    private PointF pointF;
    private float firstX;
    private float firstY;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;
    private boolean isCheck = false;
    private int value; //部位标识

    public DragPointBean() {
        pointF = new PointF();
    }

    public DragPointBean(float x, float y, int value) {
        pointF = new PointF();
        pointF.x = x;
        pointF.y = y;
        this.value = value;
    }

    public PointF getPointF() {
        return pointF;
    }

    public PointF getPointF(float x, float y) {
        return pointF;
    }

    public void setPointF(PointF pointF) {
        this.pointF = pointF;
    }

    public float getMinX() {
        return minX;
    }

    public void setMinX(float minX) {
        this.minX = minX;
    }

    public float getMinY() {
        return minY;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }

    public float getFirstX() {
        return firstX;
    }

    public void setFirstX(float firstX) {
        this.firstX = firstX;
    }

    public float getFirstY() {
        return firstY;
    }

    public void setFirstY(float firstY) {
        this.firstY = firstY;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void reset(){
        this.pointF.x = firstX;
        this.pointF.y = firstY;
    }

    /**
     * 设置极端值
     */
    public void constraintExtreme(float minX, float minY, float maxX, float maxY){
        this.setMinX(minX);
        this.setMinY(minY);
        this.setMaxX(maxX);
        this.setMaxY(maxY);
    }

    public void setPointFXY(float x, float y){
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
