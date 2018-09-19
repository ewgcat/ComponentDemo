package com.yijian.workspace.workspace.static_assessment;

import android.util.Log;

public class DragPointUtil {

    /**
     * 求斜率
     *
     * @return
     */
    public static double returnSlope(double x1, double y1, double x2, double y2) {
        double k = (double) (y2 - y1) / (x2 - x1);
        return k;
    }

    /**
     * 求两点连线与Y轴 右上方形成的夹角
     *
     * @return
     */
    public static double returnRange(double x1, double y1, double x2, double y2) {
        // x2 > x1 取range绝对值， x2 < x1  (180 - \range\)
        double k1 = returnSlope(x1, y1, x2, y2);
        double arc = Math.atan2(Math.abs(x2 - x1), Math.abs(y2 - y1));
        double range = arc * 180 / Math.PI;//转换为角度
        if (k1 > 0) {
            range = 180 - range;
        }
        /*Log.e("Test","k1===" + k1);
        Log.e("Test","range===" + range);*/
        return range;
    }

    /**
     * 求两点连线与Y轴 右边夹角
     *
     * @return
     */
    public static double returnRange2(double x1, double y1, double x2, double y2) {
        // x2 > x1 取range绝对值， x2 < x1  (180 - \range\)
        double arc = Math.atan2(Math.abs(x2 - x1), Math.abs(y2 - y1));
        double range = arc * 180 / Math.PI;//转换为角度
//        Log.e("Test","range===" + range);
        return range;
    }

    /**
     * 求 3点连线形成的夹角
     *
     * @return
     */
    public static double return3Range(double x1, double y1, double x2, double y2, double centerX, double centerY) {
        double range = 0;
        if (centerX == x1 && centerX == x2) { // 3个坐标点在同一条垂直直线上
            if ((centerY < y1 && centerY > y2) || (centerY > y1 && centerY < y2)) {
                range = 180;
            } else {
                range = 0;
            }
        }else if (centerY == y1 && centerY == y2) { // 3个坐标点在同一条水平直线上
            if ((centerX < x1 && centerX > x2) || (centerX > x1 && centerX < x2)) {
                range = 180;
            } else {
                range = 0;
            }
        }else if (centerY == y1 || centerY == y2) { //其中一条为垂直Y轴的直线
            if ((centerX > x1 && centerX > x2) || (centerX < x1 && centerX < x2)) {
                double arc1 = returnRange(x1, y1, centerX, centerY);
                double arc2 = returnRange(x2, y2, centerX, centerY);
                range = Math.abs(Math.abs(arc1) - Math.abs(arc2));
            }else{
                if(centerY == y1){
                    double arc2 = returnRange2(x2, y2, centerX, centerY);
                    range = 90 + Math.abs(arc2);
                }else{
                    double arc1 = returnRange2(x1, y1, centerX, centerY);
                    range = 90 + Math.abs(arc1);
                }
            }
        }else if(centerX == x1 || centerX == x2){ //其中一条为垂直X轴的直线

            if((centerY > y1 && centerY > y2) || (centerY < y1 && centerY < y2)){
                if (centerX == x1) {
                    range = Math.abs(returnRange2(x2, y2, centerX, centerY));
                } else {
                    range = Math.abs(returnRange2(x1, y1, centerX, centerY));
                }
            }else{
                if(centerX == x1){
                    double arc2 = returnRange2(x2, y2, centerX, centerY);
                    range = 180 - Math.abs(arc2);
                }else{
                    double arc1 = returnRange2(x1, y1, centerX, centerY);
                    range = 180 - Math.abs(arc1);
                }
            }

        }else if ((centerY > y1 && centerY > y2) || (centerY < y1 && centerY < y2)) { //公共点在上下两侧

            double arc1 = returnRange2(x1, y1, centerX, centerY);
            double arc2 = returnRange2(x2, y2, centerX, centerY);
            if ((centerX > x1 && centerX < x2) || (centerX > x2 && centerX < x1)) { //公共点在左右中间
                range = Math.abs(arc1) + Math.abs(arc2);
            } else if ((centerX > x1 && centerX > x2) || (centerX < x1 && centerX < x2)) { //公共点在左右两侧
                range = Math.abs(Math.abs(arc1) - Math.abs(arc2));
            }

        } else if ((centerY < y1 && centerY > y2) || (centerY > y1 && centerY < y2)) { //公共点在上下之间

            if ((centerX > x1 && centerX > x2) || (centerX < x1 && centerX < x2)) { // 公共点在左右两侧
                double arc1 = returnRange(x1, y1, centerX, centerY);
                double arc2 = returnRange(x2, y2, centerX, centerY);
                range = Math.abs(Math.abs(arc1) - Math.abs(arc2));
            } else if ((centerX > x1 && centerX < x2) || (centerX < x1 && centerX > x2)) { //公共点在左右之间
                double arc1 = 0, arc2 = 0;
                if (centerX > x1) {
                    arc1 = returnRange2(x1, y1, centerX, centerY);
                    arc2 = returnRange(x2, y2, centerX, centerY);
                } else if (centerX > x2) {
                    arc1 = returnRange(x1, y1, centerX, centerY);
                    arc2 = returnRange2(x2, y2, centerX, centerY);
                }
                range = Math.abs(Math.abs(arc1) + Math.abs(arc2));
            }

        }
        Log.e("Test", "range===" + range);
        return range;
    }



}
