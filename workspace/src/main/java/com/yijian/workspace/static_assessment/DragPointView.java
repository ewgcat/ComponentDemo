package com.yijian.workspace.static_assessment;

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
import android.widget.Toast;

import com.yijian.workspace.widget.ControlViewListener;

import java.util.ArrayList;
import java.util.List;

public class DragPointView extends View implements ControlViewListener {

    private Context mContext;
    private int mWidth, mHeight;
    private float centerX, centerY;
    private Paint mPaint, linePaint;
    private DragPointBean point_ear_left, point_ear_right, point_jian_left, point_jian_right,
            point_navel, point_hipbone_left, point_hipbone_right, point_knee_left, point_knee_right,
            point_ankle_left, point_ankle_right, point_tiptoe_left, point_tiptoe_right;
    private List<DragPointBean> pointFList = new ArrayList<>();
    private float allowRange = 36; //允许触摸响应超过点本身大小的多少像素
    private int radius = 10;
    private int position = 0;
    private DragPointBean moveDragPointBean = null; //拖动时的点
    private DragPointBean controlDragPointBean = null; //滚轮控制时的点
    private IphotoCrop listener;
    private int circleNorColor = 0;
    private int circleSelColor = 0;
    private int lineColor = 0;
    private int circleStrokeWidth = 5;
    private int lineStrokeWidth = 5;

    public void setListener(IphotoCrop listener) {
        this.listener = listener;
    }

    public DragPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
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
        //绘制耳朵和肚脐连线
        path.moveTo(point_ear_left.getPointF().x, point_ear_left.getPointF().y);
        path.lineTo(point_navel.getPointF().x, point_navel.getPointF().y);
        path.lineTo(point_ear_right.getPointF().x, point_ear_right.getPointF().y);
        //肩之间连线
        path.moveTo(point_jian_left.getPointF().x, point_jian_left.getPointF().y);
        path.lineTo(point_jian_right.getPointF().x, point_jian_right.getPointF().y);
        //肚脐和胯骨中部连线
        path.moveTo(point_navel.getPointF().x, point_navel.getPointF().y);
        PointF centerPointF = returnCenterPointF(point_hipbone_left.getPointF(), point_hipbone_right.getPointF());
        float navel_center_x = centerPointF.x;
        float navel_center_y = centerPointF.y;

        path.lineTo(navel_center_x, navel_center_y);
        //胯骨间连线
        path.moveTo(point_hipbone_left.getPointF().x, point_hipbone_left.getPointF().y);
        path.lineTo(point_hipbone_right.getPointF().x, point_hipbone_right.getPointF().y);
        //左胯骨和左膝盖间连线
        path.moveTo(point_hipbone_left.getPointF().x, point_hipbone_left.getPointF().y);
        path.lineTo(point_knee_left.getPointF().x, point_knee_left.getPointF().y);
        //左膝盖和左脚踝连线
        path.lineTo(point_ankle_left.getPointF().x, point_ankle_left.getPointF().y);
        path.lineTo(point_tiptoe_left.getPointF().x, point_tiptoe_left.getPointF().y);
        //右胯骨和右膝盖间连线
        path.moveTo(point_hipbone_right.getPointF().x, point_hipbone_right.getPointF().y);
        path.lineTo(point_knee_right.getPointF().x, point_knee_right.getPointF().y);
        //右膝盖和右脚踝连线
        path.lineTo(point_ankle_right.getPointF().x, point_ankle_right.getPointF().y);
        path.lineTo(point_tiptoe_right.getPointF().x, point_tiptoe_right.getPointF().y);
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
                    listener.onClickCircle(moveDragPointBean.getPointEnum().getValue());
                    setSelCircle(moveDragPointBean);
                    listener.onTouchCrop(moveX, moveY);
                    controlDragPointBean = moveDragPointBean;// 此处用于记录滚轮控制的点对象
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (moveX > 0 && moveX < mWidth && moveY < mHeight && moveY > 0) {
                    if (moveDragPointBean != null) {
                        moveDragPointBean.getPointF().x = moveX;
                        moveDragPointBean.getPointF().y = moveY;
                        pointFList.set(position, moveDragPointBean);
                        controlDragPointBean = moveDragPointBean;// 此处用于记录滚轮控制的点对象
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
                        controlDragPointBean = moveDragPointBean;// 此处用于记录滚轮控制的点对象
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
        mWidth = w;
        mHeight = h;
        centerX = mWidth / 2;
        centerY = mHeight / 2;
        initBodyPoint();
    }

    /**
     * 初始化身体部位点
     */
    private void initBodyPoint() {
        //耳朵
        point_ear_left = new DragPointBean((float) ((6.0 / 7.0) * centerX), (float) ((1.0 / 3.0) * centerY), PointEnum.POINT_EAR_LEFT);
        point_ear_right = new DragPointBean((centerX) + (centerX - point_ear_left.getPointF().x), point_ear_left.getPointF().y, PointEnum.POINT_EAR_RIGHT);

        //肩
        point_jian_left = new DragPointBean(centerX - 2 * (centerX - point_ear_left.getPointF().x), (float) ((1.0 / 2.0) * centerY), PointEnum.POINT_JIAN_LEFT);
        point_jian_right = new DragPointBean(centerX + (centerX - point_jian_left.getPointF().x), (float) ((1.0 / 2.0) * centerY), PointEnum.POINT_JIAN_RIGHT);

        //肚脐
        point_navel = new DragPointBean(centerX, (float) (7.0 / 8.0) * centerY, PointEnum.POINT_NAVEL);

        //胯骨
        point_hipbone_left = new DragPointBean(point_ear_left.getPointF().x, centerY + ((float) ((1.0 / 8.0) * centerY)), PointEnum.POINT_HIPBONE_LEFT);
        point_hipbone_right = new DragPointBean(point_ear_right.getPointF().x, centerY + ((float) ((1.0 / 8.0) * centerY)), PointEnum.POINT_HIPBONE_RIGHT);

        //膝盖
        point_knee_left = new DragPointBean(point_hipbone_left.getPointF().x - 20, centerY + ((float) ((4.0 / 8.0) * centerY)), PointEnum.POINT_KNEE_LEFT);
        point_knee_right = new DragPointBean(centerX + (centerX - point_knee_left.getPointF().x), centerY + ((float) ((4.0 / 8.0) * centerY)), PointEnum.POINT_KNEE_RIGHT);

        //脚踝
        point_ankle_left = new DragPointBean(point_knee_left.getPointF().x + 30, centerY + ((float) ((6.0 / 8.0) * centerY)), PointEnum.POINT_ANKLE_LEFT);
        point_ankle_right = new DragPointBean(centerX + (centerX - point_ankle_left.getPointF().x), centerY + ((float) ((6.0 / 8.0) * centerY)), PointEnum.POINT_ANKLE_RIGHT);

        //脚尖
        point_tiptoe_left = new DragPointBean(point_knee_left.getPointF().x, centerY + ((float) ((9.0 / 10.0) * centerY)), PointEnum.POINT_TIPTOE_LEFT);
        point_tiptoe_right = new DragPointBean(centerX + (centerX - point_tiptoe_left.getPointF().x), point_tiptoe_left.getPointF().y, PointEnum.POINT_TIPTOE_RIGHT);

        pointFList.add(point_ear_left);
        pointFList.add(point_ear_right);
        pointFList.add(point_jian_left);
        pointFList.add(point_jian_right);
        pointFList.add(point_navel);
        pointFList.add(point_hipbone_left);
        pointFList.add(point_hipbone_right);
        pointFList.add(point_knee_left);
        pointFList.add(point_knee_right);
        pointFList.add(point_ankle_left);
        pointFList.add(point_ankle_right);
        pointFList.add(point_tiptoe_left);
        pointFList.add(point_tiptoe_right);
        setCriticalValue();
    }

    /**
     * 设置红点
     *
     * @param dragPointBean
     */
    private void setSelCircle(DragPointBean dragPointBean) {
        for (DragPointBean dBean : pointFList) {
            dBean.setCheck(false);
        }
        dragPointBean.setCheck(true);
    }

    /**
     * 清除所有选中点
     */
    public void clearSelCircle() {
        for (DragPointBean dBean : pointFList) {
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
        point_ear_left.constraintExtreme(0, 0, point_ear_right.getPointF().x, point_jian_left.getPointF().y);
        point_ear_right.constraintExtreme(point_ear_left.getPointF().x, 0, 2 * centerX, point_jian_right.getPointF().y);

        //肩
        point_jian_left.constraintExtreme(0, point_ear_left.getPointF().y, point_jian_right.getPointF().x, point_navel.getPointF().y);
        point_jian_right.constraintExtreme(point_jian_left.getPointF().x, point_ear_right.getPointF().y, 2 * centerX, point_navel.getPointF().y);


        //肚脐眼
        point_navel.constraintExtreme(0, Math.max(point_jian_left.getPointF().y, point_jian_right.getPointF().y),
                2 * centerX, Math.min(point_hipbone_left.getPointF().y, point_hipbone_right.getPointF().y));

        //胯骨
        point_hipbone_left.constraintExtreme(0, point_navel.getPointF().y, point_hipbone_right.getPointF().x, point_knee_left.getPointF().y);
        point_hipbone_right.constraintExtreme(point_hipbone_left.getPointF().x, point_navel.getPointF().y, 2 * centerX, point_knee_right.getPointF().y);


        //膝盖
        point_knee_left.constraintExtreme(0, point_hipbone_left.getPointF().y, point_ankle_right.getPointF().x, point_ankle_left.getPointF().y);
        point_knee_right.constraintExtreme(point_knee_left.getPointF().x, point_hipbone_right.getPointF().y, 2 * centerX, point_ankle_right.getPointF().y);


        //脚踝
        point_ankle_left.constraintExtreme(0, point_knee_left.getPointF().y, point_ankle_right.getPointF().x, point_tiptoe_left.getPointF().y);
        point_ankle_right.constraintExtreme(point_ankle_left.getPointF().x, point_knee_right.getPointF().y, 2 * centerX, point_tiptoe_right.getPointF().y);


        //脚尖
        point_tiptoe_left.constraintExtreme(0, point_ankle_left.getPointF().y, point_tiptoe_right.getPointF().x, 2 * centerY);
        point_tiptoe_right.constraintExtreme(point_tiptoe_left.getPointF().x, point_ankle_right.getPointF().y, 2 * centerX, 2 * centerY);
    }

    /**
     * 求两点间的中点坐标
     *
     * @param pointF1
     * @param pointF2
     * @return
     */
    private PointF returnCenterPointF(PointF pointF1, PointF pointF2) {
        PointF pointF = new PointF();
        pointF.x = Math.abs(pointF1.x + pointF2.x) / 2;
        pointF.y = (pointF1.y + pointF2.y) / 2;
        return pointF;
    }

    /**
     * 获取服务器所需角度
     *
     * @return
     */
    public List<Double> returnOrientation() {
        List<Double> orientationList = new ArrayList<>();

        //耳朵
        double orientation1 = Math.abs(
                DragPointUtil.return3Range(point_ear_left.getPointF().x, point_ear_left.getPointF().y,
                        point_navel.getPointF().x, point_ear_left.getPointF().y,
                        point_navel.getPointF().x, point_navel.getPointF().y)
                        -
                        DragPointUtil.return3Range(point_ear_right.getPointF().x, point_ear_right.getPointF().y,
                                point_navel.getPointF().x, point_ear_right.getPointF().y,
                                point_navel.getPointF().x, point_navel.getPointF().y)
        );

        //肩
        double orientation2 = DragPointUtil.return3Range(point_jian_left.getPointF().x, point_jian_left.getPointF().y,
                point_jian_left.getPointF().x, point_jian_right.getPointF().y,
                point_jian_right.getPointF().x, point_jian_right.getPointF().y);

        //膝盖 左
        double orientation3 = DragPointUtil.return3Range(point_hipbone_left.getPointF().x, point_hipbone_left.getPointF().y,
                point_ankle_left.getPointF().x, point_ankle_left.getPointF().y,
                point_knee_left.getPointF().x, point_knee_left.getPointF().y);
        if (point_knee_left.getPointF().x < point_ankle_left.getPointF().x && point_knee_left.getPointF().x < point_hipbone_left.getPointF().x) { // 公共点在两点左边
            orientation3 = 360 - orientation3;
        } else { //公共点在两点之间的时候, 根据斜率去判断是否取外侧角度
            double slope = DragPointUtil.returnSlope(point_hipbone_left.getPointF().x, point_hipbone_left.getPointF().y, point_ankle_left.getPointF().x, point_ankle_left.getPointF().y);
            if (point_knee_left.getPointF().x > point_ankle_left.getPointF().x && point_knee_left.getPointF().x < point_hipbone_left.getPointF().x) {
                double slope1 = DragPointUtil.returnSlope(point_hipbone_left.getPointF().x, point_hipbone_left.getPointF().y,
                        point_knee_left.getPointF().x, point_knee_left.getPointF().y);
                if (Math.abs(slope1) < Math.abs(slope)) {
                    orientation3 = 360 - orientation3;
                }
            } else if (point_knee_left.getPointF().x < point_ankle_left.getPointF().x && point_knee_left.getPointF().x > point_hipbone_left.getPointF().x) {
                double slope2 = DragPointUtil.returnSlope(point_hipbone_left.getPointF().x, point_hipbone_left.getPointF().y,
                        point_knee_left.getPointF().x, point_knee_left.getPointF().y);
                if (Math.abs(slope2) > Math.abs(slope)) {
                    orientation3 = 360 - orientation3;
                }
            }
        }

        //膝盖 右
        double orientationRight = DragPointUtil.return3Range(point_hipbone_right.getPointF().x, point_hipbone_right.getPointF().y,
                point_ankle_right.getPointF().x, point_ankle_right.getPointF().y,
                point_knee_right.getPointF().x, point_knee_right.getPointF().y);

        if (point_knee_right.getPointF().x > point_ankle_right.getPointF().x && point_knee_right.getPointF().x > point_hipbone_right.getPointF().x) { // 公共点在两点右边
            orientationRight = 360 - orientationRight;
        } else { //公共点在两点之间的时候, 根据斜率去判断是否取外侧角度
            double slope = DragPointUtil.returnSlope(point_hipbone_right.getPointF().x, point_hipbone_right.getPointF().y,
                    point_ankle_right.getPointF().x, point_ankle_right.getPointF().y);
            if (point_knee_right.getPointF().x < point_ankle_right.getPointF().x && point_knee_right.getPointF().x > point_hipbone_right.getPointF().x) {
                double slope1 = DragPointUtil.returnSlope(point_hipbone_right.getPointF().x, point_hipbone_right.getPointF().y,
                        point_knee_right.getPointF().x, point_knee_right.getPointF().y);
                if (Math.abs(slope1) > Math.abs(slope)) {
                    orientationRight = 360 - orientationRight;
                }
            } else if (point_knee_right.getPointF().x < point_ankle_right.getPointF().x && point_knee_right.getPointF().x > point_hipbone_right.getPointF().x) {
                double slope2 = DragPointUtil.returnSlope(point_hipbone_right.getPointF().x, point_hipbone_right.getPointF().y,
                        point_knee_left.getPointF().x, point_knee_left.getPointF().y);
                if (Math.abs(slope2) < Math.abs(slope)) {
                    orientationRight = 360 - orientationRight;
                }
            }
        }

        //脚踝 左
        double orientation4 = DragPointUtil.return3Range(point_tiptoe_left.getPointF().x, point_tiptoe_left.getPointF().y,
                point_ankle_left.getPointF().x, point_tiptoe_left.getPointF().y,
                point_ankle_left.getPointF().x, point_ankle_left.getPointF().y);

        //脚踝 右
        double orientation5 = DragPointUtil.return3Range(point_tiptoe_right.getPointF().x, point_tiptoe_right.getPointF().y,
                point_ankle_right.getPointF().x, point_tiptoe_right.getPointF().y,
                point_ankle_right.getPointF().x, point_ankle_right.getPointF().y);

        orientationList.add(orientation1);
        orientationList.add(orientation2);
        orientationList.add(orientation3);
        orientationList.add(orientation4);
        orientationList.add(orientationRight);
        orientationList.add(orientation5);
        for (Double orientation : orientationList) {
            Log.e("Test", "orientation====" + orientation);
        }
        return orientationList;
    }


    @Override
    public void invalidateMoveXY(float x, float y) {
        if (controlDragPointBean != null) {
            controlDragPointBean.getPointF().x = controlDragPointBean.getPointF().x + x;
            controlDragPointBean.getPointF().y = controlDragPointBean.getPointF().y + y;
            pointFList.set(position, controlDragPointBean);
            invalidate();
            listener.onTouchCrop(controlDragPointBean.getPointF().x, controlDragPointBean.getPointF().y);
        }
    }

    @Override
    public void invalidateUpXY() {
        if (controlDragPointBean != null) {
            boolean xFlag = controlDragPointBean.getPointF().x > controlDragPointBean.getMinX() && controlDragPointBean.getPointF().x < controlDragPointBean.getMaxX();
            boolean yFlag = controlDragPointBean.getPointF().y > controlDragPointBean.getMinY() && controlDragPointBean.getPointF().y < controlDragPointBean.getMaxY();
            if (!(xFlag && yFlag)) {
                controlDragPointBean.reset();
                pointFList.set(position, controlDragPointBean);
                invalidate();
            }else{
                controlDragPointBean.setFirstX(controlDragPointBean.getPointF().x);
                controlDragPointBean.setFirstY(controlDragPointBean.getPointF().y);
            }
            setCriticalValue();
        }
    }

    @Override
    public void checkDragPoint() {
        if(controlDragPointBean == null){
            Toast.makeText(mContext, "请选择可拖动的点", Toast.LENGTH_SHORT).show();
        }
    }

}