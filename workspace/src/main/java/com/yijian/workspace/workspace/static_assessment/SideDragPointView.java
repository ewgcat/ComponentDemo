package com.yijian.workspace.workspace.static_assessment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SideDragPointView extends View {

    private int mWidth,mHeight;
    private DragPointBean point_ear, point_gonggu, point_jian, point_hipbone, point_knee;
    private float centerX, centerY;
    private Paint mPaint, linePaint;
    private List<DragPointBean> pointFList = new ArrayList<>();
    private float allowRange = 36; //允许触摸响应超过点本身大小的多少像素
    private int radius = 10;
    private int position = 0;
    private DragPointBean moveDragPointBean = null;
    private IphotoCrop listener;
    private int circleNorColor = 0;
    private int circleSelColor = 0;
    private int lineColor = 0;
    private int circleStrokeWidth = 5;
    private int lineStrokeWidth = 3;

    public void setListener(IphotoCrop listener) {
        this.listener = listener;
    }

    public SideDragPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        circleNorColor = Color.parseColor("#1997f8");
        circleSelColor = Color.RED;
        lineColor = Color.WHITE;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(circleNorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(circleStrokeWidth);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(lineStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(point_ear.getPointF().x, point_ear.getPointF().y);
        path.lineTo(point_gonggu.getPointF().x, point_gonggu.getPointF().y);
        path.lineTo(point_jian.getPointF().x, point_jian.getPointF().y);
        path.moveTo(point_gonggu.getPointF().x, point_gonggu.getPointF().y);
        path.lineTo(point_hipbone.getPointF().x, point_hipbone.getPointF().y);
        path.lineTo(point_knee.getPointF().x, point_knee.getPointF().y);
        canvas.drawPath(path, linePaint);
        for (DragPointBean dragPointBean : pointFList) {
            PointF pointF = dragPointBean.getPointF();
            mPaint.setColor(dragPointBean.isCheck() ? circleSelColor : circleNorColor);
            canvas.drawCircle(pointF.x, pointF.y, radius, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float moveX = event.getX();
        float moveY = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                moveDragPointBean = returnDragPointBean(event.getX(), event.getY());
                if (moveDragPointBean != null) {
                    moveDragPointBean.setFirstX(moveDragPointBean.getPointF().x);
                    moveDragPointBean.setFirstY(moveDragPointBean.getPointF().y);
                    listener.onClickCircle(moveDragPointBean.getValue());
                    setSelCircle(moveDragPointBean);
                    listener.onTouchCrop(moveX, moveY);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(moveX > 0 && moveX < mWidth && moveY < mHeight && moveY > 0){
                    if (moveDragPointBean != null) {
                        moveDragPointBean.getPointF().x = moveX;
                        moveDragPointBean.getPointF().y = moveY;
                        pointFList.set(position, moveDragPointBean);
                        invalidate();
                    }
                    listener.onTouchCrop(moveX, moveY);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (moveDragPointBean != null) {
                    boolean xFlag = moveX > moveDragPointBean.getMinX() && moveX < moveDragPointBean.getMaxX();
                    boolean yFlag = moveY > moveDragPointBean.getMinY() && moveY < moveDragPointBean.getMaxY();
                    if (!(xFlag && yFlag)) {
                        moveDragPointBean.reset();
                        pointFList.set(position, moveDragPointBean);
                        invalidate();
                    }
                    setCriticalValue();
                }
                break;
            default:
        }
        return true;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        mWidth = w;
        mHeight = h;
        initBodyPoint();
    }

    /**
     * 初始化身体部位点
     */
    private void initBodyPoint() {
        point_ear = new DragPointBean(centerX, (float) ((1.0 / 5.0) * centerY), PointEnum.POINT_EAR.getValue());
        point_gonggu = new DragPointBean(centerX, 2 * point_ear.getPointF().y, PointEnum.POINT_GONGGU.getValue());
        point_jian = new DragPointBean((float) ((3.0 / 4.0) * centerX), point_gonggu.getPointF().y + (point_gonggu.getPointF().y - point_ear.getPointF().y), PointEnum.POINT_JIAN.getValue());
        point_hipbone = new DragPointBean(centerX, centerY, PointEnum.POINT_HIPBONE.getValue());
        point_knee = new DragPointBean(centerX, centerY + (centerY / 2 - 100), PointEnum.POINT_KNEE.getValue());

        pointFList.add(point_ear);
        pointFList.add(point_gonggu);
        pointFList.add(point_jian);
        pointFList.add(point_hipbone);
        pointFList.add(point_knee);
        setCriticalValue();
    }

    /**
     * 设置红点
     * @param dragPointBean
     */
    private void setSelCircle(DragPointBean dragPointBean){
        for(DragPointBean dBean : pointFList){
            dBean.setCheck(false);
        }
        dragPointBean.setCheck(true);
    }

    /**
     * 清除所有选中点
     */
    public void clearSelCircle(){
        for(DragPointBean dBean : pointFList){
            dBean.setCheck(false);
        }
        invalidate();
    }

    /**
     * 根据触摸获取响应对象
     *
     * @param x
     * @param y
     * @return
     */
    private DragPointBean returnDragPointBean(float x, float y) {
        for (int i = 0; i < pointFList.size(); i++) {
            DragPointBean dragPointBean = pointFList.get(i);
            PointF pointF = dragPointBean.getPointF();
            boolean xFlag = x > (pointF.x - radius - allowRange) && x < (pointF.x + radius + allowRange);
            boolean yFlag = y > (pointF.y - radius - allowRange) && y < (pointF.y + radius + allowRange);
            if (xFlag && yFlag) {
                position = i;
                return dragPointBean;
            }
        }
        return null;
    }

    /**
     * 重置触摸极端值
     */
    private void setCriticalValue() {
        //耳朵
        point_ear.constraintExtreme(0, 0, 2 * centerX, point_gonggu.getPointF().y);

        //肱骨
        point_gonggu.constraintExtreme(0, point_ear.getPointF().y, 2 * centerX, point_jian.getPointF().y);

        //肩
        point_jian.constraintExtreme(0, point_gonggu.getPointF().y, 2 * centerX, point_hipbone.getPointF().y);

        //大转子
        point_hipbone.constraintExtreme(0, point_jian.getPointF().y, 2 * centerX, point_knee.getPointF().y);

        //髌骨
        point_knee.constraintExtreme(0, point_hipbone.getPointF().y, 2 * centerX, 2 * centerY);
    }

    /**
     * 获取服务器所需角度
     *
     * @return
     */
    public List<Double> returnOrientation() {
        List<Double> orientationList = new ArrayList<>();

        //耳朵
        double orientation1 = DragPointUtil.return3Range(point_gonggu.getPointF().x, point_ear.getPointF().y,
                point_ear.getPointF().x, point_ear.getPointF().y,
                point_gonggu.getPointF().x, point_gonggu.getPointF().y);

        //肱骨头
        double orientation2 = DragPointUtil.return3Range(point_jian.getPointF().x, point_jian.getPointF().y,
                point_gonggu.getPointF().x + 50, point_gonggu.getPointF().y,
                point_gonggu.getPointF().x, point_gonggu.getPointF().y);


        orientation2 = (orientation2 > 90 ? (180-90) : orientation2);

        //大转子
        double orientation3 = DragPointUtil.return3Range(point_gonggu.getPointF().x, point_gonggu.getPointF().y,
                point_knee.getPointF().x, point_knee.getPointF().y,
                point_hipbone.getPointF().x, point_hipbone.getPointF().y);
        if(point_hipbone.getPointF().x < point_knee.getPointF().x){ // 公共点在两点左边
            orientation3 = 360 - orientation3;
        }else { //公共点在两点之间的时候, 根据斜率去判断是否取外侧角度
            double slope = DragPointUtil.returnSlope(point_gonggu.getPointF().x, point_gonggu.getPointF().y, point_knee.getPointF().x, point_knee.getPointF().y);
            if(point_hipbone.getPointF().x > point_knee.getPointF().x &&
                    point_hipbone.getPointF().x < point_gonggu.getPointF().x){
                double slope1 = DragPointUtil.returnSlope(point_gonggu.getPointF().x, point_gonggu.getPointF().y,
                        point_hipbone.getPointF().x, point_hipbone.getPointF().y);
                if(Math.abs(slope1) < Math.abs(slope)){
                    orientation3 = 360 - orientation3;
                }
            }else if(point_hipbone.getPointF().x < point_knee.getPointF().x && point_hipbone.getPointF().x > point_gonggu.getPointF().x){
                double slope2 = DragPointUtil.returnSlope(point_gonggu.getPointF().x, point_gonggu.getPointF().y,
                        point_hipbone.getPointF().x, point_hipbone.getPointF().y);
                if(Math.abs(slope2) > Math.abs(slope)){
                    orientation3 = 360 - orientation3;
                }
            }
        }

        orientationList.add(orientation1);
        orientationList.add(orientation2);
        orientationList.add(orientation3);
        for (Double orientation : orientationList) {
            Log.e("Test", "orientation====" + orientation);
        }
        return orientationList;
    }


}
