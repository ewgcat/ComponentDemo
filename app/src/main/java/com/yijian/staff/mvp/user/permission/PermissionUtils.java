package com.yijian.staff.mvp.user.permission;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

public class PermissionUtils {

    private PermissionUtils() {
    }

    private static PermissionUtils permissionUtils;

    public static PermissionUtils getInstance() {
        if (permissionUtils == null) {
            permissionUtils = new PermissionUtils();
        }
        return permissionUtils;
    }

    /*public boolean isAllEditable(){

        return ;
    }*/

    public void savePermissionMenu(Context context, List<PermissionBean> permissionBeanList) {
        File file = new File(context.getCacheDir() + "permissionbean.adt");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            //将List转换成数组
            PermissionBean[] obj = new PermissionBean[permissionBeanList.size()];
            permissionBeanList.toArray(obj);
            //执行序列化存储
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PermissionBean> getPermissionMenu(Context context) {
        List<PermissionBean> listObject = null;
        File file = new File(context.getCacheDir() + "permissionbean.adt");
        try (ObjectInputStream out = new ObjectInputStream(new FileInputStream(file))) {
            //执行反序列化读取
            PermissionBean[] obj = (PermissionBean[]) out.readObject();
            //将数组转换成List
            listObject = Arrays.asList(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listObject;
    }

    public boolean isEdit(Context context, String menuKey, String subMenuKey) {
        boolean isEdit = false;
        List<PermissionBean> permissionBeanList = getPermissionMenu(context);
        for (PermissionBean permissionBean : permissionBeanList) {
            String menuKey1 = permissionBean.getMenuKey();
            if (menuKey.equals(menuKey1)) {
                List<PermissionBean.SubMeneModelListBean> subMeneModelListBeanList = permissionBean.getSubMeneModelList();
                for (PermissionBean.SubMeneModelListBean subMeneModelListBean : subMeneModelListBeanList) {
                    String subMenuKey1 = subMeneModelListBean.getMenuKey();
                    if (subMenuKey.equals(subMenuKey1)) {
                        List<PermissionBean.SubMeneModelListBean.MenuActionListBean> menuActionListBeanList = subMeneModelListBean.getMenuActionList();
                        for(PermissionBean.SubMeneModelListBean.MenuActionListBean menuActionListBean : menuActionListBeanList){
                            if("edit".equals(menuActionListBean.getTag())){
                                isEdit = menuActionListBean.isCheck();
                            }
                        }
                    }
                }
            }
        }
        return isEdit;
    }

}
