package com.yijian.staff.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.yijian.staff.mvp.huiji.huifang.bean.HuiFangTypeBean;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.db.bean.CoachHuiFangTypeBean;

import com.yijian.staff.greendao.gen.HuiFangTypeBeanDao;
import com.yijian.staff.greendao.gen.UserDao;
import com.yijian.staff.greendao.gen.SearchKeyDao;
import com.yijian.staff.greendao.gen.CoachHuiFangTypeBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig huiFangTypeBeanDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig searchKeyDaoConfig;
    private final DaoConfig coachHuiFangTypeBeanDaoConfig;

    private final HuiFangTypeBeanDao huiFangTypeBeanDao;
    private final UserDao userDao;
    private final SearchKeyDao searchKeyDao;
    private final CoachHuiFangTypeBeanDao coachHuiFangTypeBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        huiFangTypeBeanDaoConfig = daoConfigMap.get(HuiFangTypeBeanDao.class).clone();
        huiFangTypeBeanDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        searchKeyDaoConfig = daoConfigMap.get(SearchKeyDao.class).clone();
        searchKeyDaoConfig.initIdentityScope(type);

        coachHuiFangTypeBeanDaoConfig = daoConfigMap.get(CoachHuiFangTypeBeanDao.class).clone();
        coachHuiFangTypeBeanDaoConfig.initIdentityScope(type);

        huiFangTypeBeanDao = new HuiFangTypeBeanDao(huiFangTypeBeanDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        searchKeyDao = new SearchKeyDao(searchKeyDaoConfig, this);
        coachHuiFangTypeBeanDao = new CoachHuiFangTypeBeanDao(coachHuiFangTypeBeanDaoConfig, this);

        registerDao(HuiFangTypeBean.class, huiFangTypeBeanDao);
        registerDao(User.class, userDao);
        registerDao(SearchKey.class, searchKeyDao);
        registerDao(CoachHuiFangTypeBean.class, coachHuiFangTypeBeanDao);
    }
    
    public void clear() {
        huiFangTypeBeanDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        searchKeyDaoConfig.clearIdentityScope();
        coachHuiFangTypeBeanDaoConfig.clearIdentityScope();
    }

    public HuiFangTypeBeanDao getHuiFangTypeBeanDao() {
        return huiFangTypeBeanDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public SearchKeyDao getSearchKeyDao() {
        return searchKeyDao;
    }

    public CoachHuiFangTypeBeanDao getCoachHuiFangTypeBeanDao() {
        return coachHuiFangTypeBeanDao;
    }

}
