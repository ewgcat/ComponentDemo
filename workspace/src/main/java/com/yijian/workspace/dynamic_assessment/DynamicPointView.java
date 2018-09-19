package com.yijian.workspace.dynamic_assessment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yijian.workspace.face.BitmapFaceUtils;
import com.yijian.workspace.static_assessment.DragPointBean;
import com.yijian.workspace.static_assessment.DragPointUtil;
import com.yijian.workspace.static_assessment.PointEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicPointView extends View {

    private int mWidth, mHeight;
    private float centerX, centerY;
    private Paint mPaint, linePaint, txtPaint;
    private DragPointBean point_sciaticbone_left, point_sciaticbone_right, point_caudabone, point_pubicbone;
    private List<DragPointBean> pointFList = new ArrayList<>();
    private int circleNorColor = 0;
    private int circleSelColor = 0;
    private int lineColor = 0;
    private int circleStrokeWidth = 5;
    private int lineStrokeWidth = 5;
    private int radius = 10;
    private DragPointBean moveDragPointBean = null;
    private float allowRange = 36; //允许触摸响应超过点本身大小的多少像素
    private int position = 0;
    private Rect mTextBounds;
    private int transDiff = 50; // 文字描述偏移量

    public DynamicPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        circleNorColor = Color.parseColor("#1997f8");
        circleSelColor = Color.RED;
        lineColor = Color.parseColor("#1997f8");
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(circleNorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(circleStrokeWidth);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(lineStrokeWidth);
        txtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        txtPaint.setColor(Color.WHITE);
        txtPaint.setTextSize(35);
        mTextBounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        //左坐骨(左点) 和 尾骨的连线
        path.moveTo(point_sciaticbone_left.getPointF().x, point_sciaticbone_left.getPointF().y);
        path.lineTo(point_caudabone.getPointF().x, point_caudabone.getPointF().y);

        //尾骨 和 左坐骨(右点)的连线
        path.lineTo(point_sciaticbone_right.getPointF().x, point_sciaticbone_right.getPointF().y);

        //左坐骨(右点) 和 耻骨 的连线
        path.lineTo(point_pubicbone.getPointF().x, point_pubicbone.getPointF().y);

        //左坐骨(左点) 和 耻骨 的连线
        path.lineTo(point_sciaticbone_left.getPointF().x, point_sciaticbone_left.getPointF().y);
        path.close();
        canvas.drawPath(path, linePaint);
        for (DragPointBean dragPointBean : pointFList) {
            PointF pointF = dragPointBean.getPointF();
            mPaint.setColor(dragPointBean.isCheck() ? circleSelColor : circleNorColor);
            canvas.drawCircle(pointF.x, pointF.y, radius, mPaint);
            Bitmap bitmap = dragPointBean.getPointBitmap();
            canvas.drawBitmap(bitmap, dragPointBean.getPointF().x - bitmap.getWidth() - transDiff, dragPointBean.getPointF().y - bitmap.getHeight() / 2, txtPaint);
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
                    setSelCircle(moveDragPointBean);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (moveX > 0 && moveX < mWidth && moveY < mHeight && moveY > 0) {
                    if (moveDragPointBean != null) {
                        moveDragPointBean.getPointF().x = moveX;
                        moveDragPointBean.getPointF().y = moveY;
                        pointFList.set(position, moveDragPointBean);
                        invalidate();
                    }
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


    /**
     * 生成文字图片
     *
     * @param name
     * @return
     */
    private Bitmap returnBitmap(String name) {
        String noBracketsName = name.substring(0, name.indexOf("(")); // 没有括号部分
        String bracketsName = name.substring(name.indexOf("("), name.length()); //括号部分
        //绘制没有括号部分
        txtPaint.getTextBounds(noBracketsName, 0, noBracketsName.length(), mTextBounds);
        int noBracketsDiff = Math.abs(mTextBounds.top) - (mTextBounds.bottom - mTextBounds.top) / 2;
        int noBracketsBaseLine = (mTextBounds.bottom - mTextBounds.top) / 2 + noBracketsDiff;
        int noBracketsWidth = mTextBounds.width();
        int noBracketsHeight = mTextBounds.bottom - mTextBounds.top;

        //绘制括号部分
        txtPaint.getTextBounds(bracketsName, 0, bracketsName.length(), mTextBounds);
        int bracketsDiff = Math.abs(mTextBounds.top) - (mTextBounds.bottom - mTextBounds.top) / 2;
        int bracketsBaseLine = (mTextBounds.bottom - mTextBounds.top) / 2 + bracketsDiff;
        int bracketsWidth = mTextBounds.width();
        int bracketsHeight = mTextBounds.bottom - mTextBounds.top;
        int totalWidth = Math.max(noBracketsWidth, bracketsWidth);
        int totalHeight = noBracketsHeight + bracketsHeight;


        Bitmap bitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawText(noBracketsName, (totalWidth - noBracketsWidth) / 2, noBracketsBaseLine, txtPaint);
        bitmapCanvas.drawText(bracketsName, (totalWidth - bracketsWidth) / 2, noBracketsHeight + bracketsBaseLine, txtPaint);
        bitmap = BitmapFaceUtils.rotateBitmap(bitmap, 90);
        return bitmap;
    }

    /**
     * 初始化身体部位点
     */
    private void initBodyPoint() {

        //左坐骨 （左点）
        point_sciaticbone_left = new DragPointBean((float) (centerX), (float) (centerY / 4), PointEnum.POINT_SCIATICBONE_LEFT);
        point_sciaticbone_left.setPointBitmap(returnBitmap(point_sciaticbone_left.getPointEnum().getName()));

        //右坐骨 （右点）
        point_sciaticbone_right = new DragPointBean((float) (centerX), (float) (2 * centerY - centerY / 4), PointEnum.POINT_SCIATICBONE_RIGHT);
        point_sciaticbone_right.setPointBitmap(returnBitmap(point_sciaticbone_right.getPointEnum().getName()));

        //尾骨（后点）
        point_caudabone = new DragPointBean((float) (centerX / 2), centerY, PointEnum.POINT_CAUDABONE);
        point_caudabone.setPointBitmap(returnBitmap(point_caudabone.getPointEnum().getName()));


        //耻骨联合（前点）
        point_pubicbone = new DragPointBean((float) (3 * centerX / 2), centerY, PointEnum.POINT_PUBICBONE);
        point_pubicbone.setPointBitmap(returnBitmap(point_pubicbone.getPointEnum().getName()));

        pointFList.add(point_sciaticbone_left);
        pointFList.add(point_sciaticbone_right);
        pointFList.add(point_caudabone);
        pointFList.add(point_pubicbone);
        setCriticalValue();
    }


    /**
     * 重置触摸极端值
     */
    private void setCriticalValue() {
        //左坐骨 （左点）
        point_sciaticbone_left.constraintExtreme(point_caudabone.getPointF().x, 0, point_pubicbone.getPointF().x, Math.min(point_caudabone.getPointF().y, point_pubicbone.getPointF().y));

        //右坐骨 （右点）
        point_sciaticbone_right.constraintExtreme(point_caudabone.getPointF().x, Math.max(point_caudabone.getPointF().y, point_pubicbone.getPointF().y), point_pubicbone.getPointF().x, 2 * centerY);

        //尾骨（后点）
        point_caudabone.constraintExtreme(0, point_sciaticbone_left.getPointF().y, Math.min(point_sciaticbone_left.getPointF().x, point_sciaticbone_right.getPointF().x), point_sciaticbone_right.getPointF().y);

        //耻骨联合（前点）
        point_pubicbone.constraintExtreme(Math.max(point_sciaticbone_left.getPointF().x, point_sciaticbone_right.getPointF().x), point_sciaticbone_left.getPointF().y, 2 * centerX, point_sciaticbone_right.getPointF().y);
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
     * 求出2点连线的直线长度
     *
     * @param point1
     * @param point2
     * @return
     */
    private double getLenth(PointF point1, PointF point2) {
        double length = 0;
        if (point1.y == point2.y) {
            length = Math.abs(point1.x - point2.x);
        } else if (point1.x == point2.x) {
            length = Math.abs(point1.y - point2.y);
        } else {
            length = Math.sqrt(Math.pow(Math.abs(point1.x - point2.x), 2) + Math.pow(Math.abs(point1.y - point2.y), 2));
        }
        return length;
    }

    /**
     * 求出3点坐标形成的三角形面积
     *
     * @param point1
     * @param point2
     * @param centerPoint
     * @return
     */
    private double returnArea(PointF centerPoint, PointF point1, PointF point2) {
        double length1 = getLenth(point1, point2);
        double length2 = getLenth(point1, centerPoint);
        double length3 = getLenth(point2, centerPoint);
        double p = (length1 + length2 + length3) / 2;
        double area = Math.sqrt(Math.abs(p * (p - length1) * (p - length2) * (p - length3)));
        return area;
    }

    /**
     * 求出中心点
     * <p>
     * line1( point1 和 point2 形成的直线) 与  line2( point3 和 point4 形成的直线) 的交点
     * 下面就用 line1 line2 简称2条直线
     * 使用时，记得依次 按2条直线的2点坐标 传入
     *
     * @param point1
     * @param point2
     * @param point3
     * @param point4
     * @return
     */
    private PointF returnCenterPoint(PointF point1, PointF point2, PointF point3, PointF point4) {
        PointF pointF = new PointF();
        if ((point1.x == point2.x && point3.x == point4.x)) { // 两条直线斜率不存在的时候

            return null;
        } else if (point1.x == point2.x || point3.x == point4.x) { //  2条直线的斜率其中一条不存在，另一条是存在的

            if (point1.x == point2.x) {  //line1 不存在斜率 , line2 存在斜率

                pointF.x = point1.x;
                double k2 = DragPointUtil.returnSlope(point3.x, point3.y, point4.x, point4.y);
                double b2 = point3.y - k2 * point3.x;
                pointF.y = (float) (k2 * pointF.x + b2);
                return pointF;
            } else { //line2 不存在斜率 ， line1 存在斜率
                pointF.x = point3.x;
                double k1 = DragPointUtil.returnSlope(point1.x, point1.y, point2.x, point2.y);
                double b1 = point3.y - k1 * point3.x;
                pointF.y = (float) (k1 * pointF.x + b1);
                return pointF;
            }

        } else { // 两条直线都存在斜率
            double k1 = DragPointUtil.returnSlope(point1.x, point1.y, point2.x, point2.y);
            double b1 = point1.y - k1 * point1.x;
            double k2 = DragPointUtil.returnSlope(point3.x, point3.y, point4.x, point4.y);
            double b2 = point3.y - k2 * point3.x;
            pointF.x = (float) ((b2 - b1) / (k1 - k2));
            if(point1.y == point2.y && point3.y == point4.y){
                return null;
            }else if(point1.y == point2.y || point3.y == point4.y){
                if (point1.y == point2.y) {
                    pointF.y = (float) (k2 * pointF.x + b2);
                }else if(point3.y == point4.y) {
                    pointF.y = (float) (k1 * pointF.x + b1);
                }
            }else{
                pointF.y = (float) (k1 * pointF.x + b1);
            }
        }
        return pointF;
    }

    /**
     * 获取面积集合
     *
     * @return
     */
    public List<Double> getArea() {
        List<Double> areas = new ArrayList<>();
        //求出中心点
        PointF centerPoint = returnCenterPoint(point_sciaticbone_left.getPointF(), point_sciaticbone_right.getPointF(), point_caudabone.getPointF(), point_pubicbone.getPointF());
        //左坐骨(左点) 耻骨 中心点
        double area2 = returnArea(centerPoint, point_pubicbone.getPointF(), point_sciaticbone_left.getPointF());
        //右坐骨(右点) 耻骨 中心点
        double area3 = returnArea(centerPoint, point_sciaticbone_right.getPointF(), point_pubicbone.getPointF());
        //左坐骨(左点) 尾骨 中心点
        double area1 = returnArea(centerPoint, point_sciaticbone_left.getPointF(), point_caudabone.getPointF());
        //右坐骨(右点) 尾骨 中心点
        double area4 = returnArea(centerPoint, point_caudabone.getPointF(), point_sciaticbone_right.getPointF());
        areas.add(area1);
        areas.add(area2);
        areas.add(area3);
        areas.add(area4);
        return areas;
    }

    public String getMinArea() {
        StringBuffer result = new StringBuffer();
        List<Double> areas = getArea();
        Collections.sort(areas);
        double minArea = areas.get(0);
        List<Double> areas2 = getArea();
        int count = 0; //当前最小面积4个都一样，则结果为“”
        for (int i = 0; i < areas2.size(); i++) {
            if (minArea == areas2.get(i)) {
                count++;
                if (i == 0) {
                    result.append("左前侧面面积最小,");
                } else if (i == 1) {
                    result.append("右前侧面面积最小,");

                } else if (i == 2) {
                    result.append("左后侧面面积最小,");

                } else if (i == 3) {
                    result.append("右后侧面面积最小,");
                }
            }
        }
        if (count < 4) {
            int index = result.lastIndexOf(",");
            if (index > 0) {
                return result.substring(0, index);
            }
        }
        return "";
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

}
