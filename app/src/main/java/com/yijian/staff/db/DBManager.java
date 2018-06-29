package com.yijian.staff.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.yijian.staff.db.bean.OthermodelVo;
import com.yijian.staff.db.bean.RoleVoBean;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.greendao.gen.CoachHuiFangTypeBeanDao;
import com.yijian.staff.greendao.gen.DaoMaster;
import com.yijian.staff.greendao.gen.DaoSession;
import com.yijian.staff.greendao.gen.HuiFangTypeBeanDao;
import com.yijian.staff.greendao.gen.OthermodelVoDao;
import com.yijian.staff.greendao.gen.RoleVoBeanDao;
import com.yijian.staff.greendao.gen.SearchKeyDao;
import com.yijian.staff.greendao.gen.UserDao;
import com.yijian.staff.db.bean.CoachHuiFangTypeBean;
import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangTypeBean;
import com.yijian.staff.prefs.SharePreferenceUtil;

import java.util.List;


public class DBManager {
    private final static String dbName = "house_db";
    private static DBManager mInstance;
    private MySQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    public DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DBManager(Context context) {
        openHelper = new MySQLiteOpenHelper(context, dbName, null);
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
    public User queryUser() {
        return mDaoSession.getUserDao().queryBuilder().build().unique();
    }

    //插入账号信息
    public void insertOrReplaceUser(User user) {
        UserDao userDao = mDaoSession.getUserDao();
        userDao.deleteAll();
        userDao.insertOrReplace(user);
    }


    public void clearUser() {
        UserDao userDao = mDaoSession.getUserDao();
        userDao.deleteAll();
    }

    //查询RoleVoBean信息
    public RoleVoBean queryRoleVoBean() {
        return mDaoSession.getRoleVoBeanDao().queryBuilder().build().unique();
    }

    //插入RoleVoBean信息
    public void insertOrReplaceRoleVoBean(RoleVoBean roleVoBean) {
        RoleVoBeanDao roleVoBeanDao = mDaoSession.getRoleVoBeanDao();
        roleVoBeanDao.deleteAll();
        roleVoBeanDao.insertOrReplace(roleVoBean);
    }


    public void clearRoleVoBean() {
        RoleVoBeanDao roleVoBeanDao = mDaoSession.getRoleVoBeanDao();
        roleVoBeanDao.deleteAll();
    }


    //查询OthermodelVo信息
    public OthermodelVo queryOthermodelVo() {
        return mDaoSession.getOthermodelVoDao().queryBuilder().build().unique();
    }

    //插入OthermodelVo信息
    public void insertOrReplaceOthermodelVo(OthermodelVo othermodelVo) {
        OthermodelVoDao othermodelVoDao = mDaoSession.getOthermodelVoDao();
        othermodelVoDao.deleteAll();
        othermodelVoDao.insertOrReplace(othermodelVo);
    }


    public void clearOthermodelVo() {
        OthermodelVoDao othermodelVo = mDaoSession.getOthermodelVoDao();
        othermodelVo.deleteAll();
    }


    public void insertOrReplaceSearch(SearchKey searchKey) {
        String key = searchKey.getKey();
        if (TextUtils.isEmpty(key)) {
            return;
        }
        SearchKeyDao searchKeyDao = mDaoSession.getSearchKeyDao();
        List<SearchKey> searchKeys = querySearchList();
        Long id = 0L;
        for (int i = 0; i < searchKeys.size(); i++) {
            SearchKey searchKey1 = searchKeys.get(i);
            if (searchKey.getKey().equals(searchKey1.getKey())) {
                id = searchKey1.getId();
            }
        }
        searchKeyDao.deleteByKey(id);
        searchKeyDao.insertOrReplace(searchKey);
    }

    public List<SearchKey> querySearchList() {
        SearchKeyDao searchKeyDao = mDaoSession.getSearchKeyDao();
        String userId = SharePreferenceUtil.getUserId() + "";
        List<SearchKey> list = searchKeyDao.queryBuilder()
                .where(SearchKeyDao.Properties.UserId.eq(userId))
                .orderDesc(SearchKeyDao.Properties.Id)
                .list();
        return list;
    }

    public void clearSearchList() {
        SearchKeyDao searchKeyDao = mDaoSession.getSearchKeyDao();
        searchKeyDao.deleteAll();
    }

    public void insertCoachHuiFangTypeBeanList(List<CoachHuiFangTypeBean> coachHuiFangTypeBeanList) {
        mDaoSession.getCoachHuiFangTypeBeanDao().deleteAll();
        if (coachHuiFangTypeBeanList != null) {
            for (int i = 0; i < coachHuiFangTypeBeanList.size(); i++) {
                CoachHuiFangTypeBean coachHuiFangTypeBean = coachHuiFangTypeBeanList.get(i);
                mDaoSession.getCoachHuiFangTypeBeanDao().insert(coachHuiFangTypeBean);
            }
        }
    }

    public CoachHuiFangTypeBean queryCoachHuiFangTypeBean(String configType) {
        CoachHuiFangTypeBeanDao coachHuiFangTypeBeanDao = mDaoSession.getCoachHuiFangTypeBeanDao();
        CoachHuiFangTypeBean coachHuiFangTypeBean = coachHuiFangTypeBeanDao.queryBuilder()
                .where(CoachHuiFangTypeBeanDao.Properties.ConfigType.eq(configType)).unique();
        return coachHuiFangTypeBean;
    }

    public void insertHuiFangTypeBeanList(List<HuiFangTypeBean> huiFangTypeBeanList) {
        mDaoSession.getHuiFangTypeBeanDao().deleteAll();
        if (huiFangTypeBeanList != null) {
            for (int i = 0; i < huiFangTypeBeanList.size(); i++) {
                HuiFangTypeBean huiFangTypeBean = huiFangTypeBeanList.get(i);
                mDaoSession.getHuiFangTypeBeanDao().insert(huiFangTypeBean);
            }
        }
    }

    public HuiFangTypeBean queryHuiFangTypeBean(String configType) {
        HuiFangTypeBeanDao huiFangTypeBeanDao = mDaoSession.getHuiFangTypeBeanDao();
        HuiFangTypeBean huiFangTypeBean = huiFangTypeBeanDao.queryBuilder()
                .where(HuiFangTypeBeanDao.Properties.ConfigType.eq(configType)).unique();
        return huiFangTypeBean;
    }

}
