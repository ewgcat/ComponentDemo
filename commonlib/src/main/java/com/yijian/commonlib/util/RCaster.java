package com.yijian.commonlib.util;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/19 15:16:03
 */
public class RCaster {
    //id取得属性名字
    private HashMap<Integer, String> r1Map = new HashMap<>();
    //属性名字回取id
    private HashMap<String, Integer> r2Map = new HashMap<>();


    private Class R;
    private Class R2;

    public RCaster(Class r, Class r2) {
        R = r;
        R2 = r2;
        initMap1();
        initMap2();
    }

    /**
     * R1 id cast to R2
     *
     * @param rid
     * @return
     */
    public int cast(int rid) {
        String name = r1Map.get(rid);
        int id2 = r2Map.get(name);
        return id2;
    }

    /**
     * 初始化r1Map
     */
    @SuppressWarnings("Duplicates")
    private void initMap1() {

        long time = System.currentTimeMillis();

        Class[] classes = R.getClasses();
        Object r = null;
        try {
            r = R.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Class aClass : classes) {
            if (aClass.getSimpleName().equals("id")) {
                Field[] fields = aClass.getFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        int x = (int) field.get(r);
                        r1Map.put(x, field.getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        long time2 = System.currentTimeMillis();
        long timeCost = time2 - time;

        System.out.println("\nTimecost:" + timeCost + "ms");

    }

    /**
     * 初始化r2Map
     */
    @SuppressWarnings("Duplicates")
    private void initMap2() {

        long time = System.currentTimeMillis();

        Class[] classes = R2.getClasses();
        Object r = null;
        try {
            r = R2.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Class aClass : classes) {
            if (aClass.getSimpleName().equals("id")) {
                Field[] fields = aClass.getFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        int x = (int) field.get(r);
//                        System.out.println(field.getName() + " ----> " + x);
//                        r2Map.put(x, field.getName());
                        r2Map.put(field.getName(),x);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        long time2 = System.currentTimeMillis();
        long timeCost = time2 - time;

        System.out.println("\nTimecost:" + timeCost + "ms");

    }


}

