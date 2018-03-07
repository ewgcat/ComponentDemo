package com.yijian.staff.tab.tools;

import com.yijian.staff.R;

import java.lang.reflect.Field;


/**
 * 描述:
 * <br>作者:陈俊森
 * <br>创建时间:2017年05月08日 15:29
 * <br>邮箱:chenjunsen@outlook.com
 */

public class ImageKit {
    /**
     * 根据图片名字获取图片的资源id
     * @param imageName 图片名字
     * @param defType 资源id类型,如，"mipmap"
     * @return
     */
    public static int getSrcIdByName(String imageName, String defType){
        return ContextUtil.getContext().getResources().getIdentifier(imageName,defType,ContextUtil.getContext().getPackageName());
    }

    public static int getMipmapImageSrcIdByName(String imageName){
        return getSrcIdByName(imageName,"mipmap");
    }

    public static int getDrawableImageSrcIdByName(String imageName){
        return getSrcIdByName(imageName,"drawable");
    }

    /**
     * @deprecated
     * 通过反射来根据图片名字获取图片的资源id
     * @param imageName 图片名字
     * @param declaredClazz 要获取的图片资源id类型，如R.mipmap.class
     * @return
     */
    public static int getImageSrcIdWithReflectByName(String imageName, Class declaredClazz){
        int id=0;
        try {
            Field field=declaredClazz.getField(imageName);
            field.setAccessible(true);
            id=field.getInt(field.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 根据图片名字来获取mipmap图片的资源id
     * @param imageName
     * @return
     */
    public static int getMipMapImageSrcIdWithReflectByName(String imageName){
        int id=0;
        try {
            Field field = R.mipmap.class.getDeclaredField(imageName);
            field.setAccessible(true);
            R.mipmap mipmap = new R.mipmap();
            Object oId = field.get(mipmap);
            id =  (Integer) oId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
