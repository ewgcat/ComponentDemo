package com.yijan.workspace.static_assessment;

import android.graphics.Bitmap;
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
    private PointEnum pointEnum;  //部位标识
    private Bitmap pointBitmap;


    public DragPointBean() {
        pointF = new PointF();
    }

    public DragPointBean(float x, float y, PointEnum pointEnum) {
        pointF = new PointF();
        pointF.x = x;
        pointF.y = y;
        this.pointEnum = pointEnum;
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

    public PointEnum getPointEnum() {
        return pointEnum;
    }

    public void setPointEnum(PointEnum pointEnum) {
        this.pointEnum = pointEnum;
    }

    public Bitmap getPointBitmap() {
        return pointBitmap;
    }

    public void setPointBitmap(Bitmap pointBitmap) {
        this.pointBitmap = pointBitmap;
    }

}
