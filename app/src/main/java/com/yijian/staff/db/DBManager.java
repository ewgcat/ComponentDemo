package com.yijian.staff.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yijian.staff.db.bean.User;
import com.yijian.staff.greendao.gen.DaoMaster;
import com.yijian.staff.greendao.gen.DaoSession;
import com.yijian.staff.greendao.gen.UserDao;


public class DBManager  {
    private final static String dbName = "house_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private SQLiteDatabase db;
    public DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DBManager(Context context) {
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        db = openHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }


    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
    }

    /**
     * 获取单例引用
     */
    public static DBManager getInstance() {
        if (mInstance == null) {
            try {
                throw new Exception("please init dbmanager before use");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mInstance;
    }


    //查询账号信息
    public  User queryUser(){
        return  mDaoSession.getUserDao().queryBuilder().build().unique();
    }

    //插入账号信息
    public void insertOrReplaceUser(User user){
        UserDao userDao = mDaoSession.getUserDao();
        userDao.deleteAll();
        userDao.insertOrReplace(user);
    }


    public void clearUser() {
        UserDao userDao = mDaoSession.getUserDao();
        userDao.deleteAll();
    }
}
