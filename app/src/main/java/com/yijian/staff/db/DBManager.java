package com.yijian.staff.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.yijian.staff.bean.CourseStudentBean;
import com.yijian.staff.bean.HuiFangTypeBean;
import com.yijian.staff.db.bean.CourseStudentModel;
import com.yijian.staff.db.bean.OthermodelVo;
import com.yijian.staff.db.bean.PrivateCoachCourseModel;
import com.yijian.staff.db.bean.PrivateCoachCurriculumArrangementPlanModel;
import com.yijian.staff.db.bean.PrivateCourseMemberModel;
import com.yijian.staff.db.bean.RoleVoBean;
import com.yijian.staff.db.bean.SearchKey;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.greendao.gen.CourseStudentModelDao;
import com.yijian.staff.greendao.gen.DaoMaster;
import com.yijian.staff.greendao.gen.DaoSession;
import com.yijian.staff.greendao.gen.HuiFangTypeBeanDao;
import com.yijian.staff.greendao.gen.OthermodelVoDao;
import com.yijian.staff.greendao.gen.PrivateCoachCourseModelDao;
import com.yijian.staff.greendao.gen.PrivateCoachCurriculumArrangementPlanModelDao;
import com.yijian.staff.greendao.gen.PrivateCourseMemberModelDao;
import com.yijian.staff.greendao.gen.RoleVoBeanDao;
import com.yijian.staff.greendao.gen.SearchKeyDao;
import com.yijian.staff.greendao.gen.UserDao;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.Logger;

import java.util.ArrayList;
import java.util.List;


public class DBManager {
    private final static String TAG = "DBManager";
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

    public void deleteSearch(SearchKey searchKey) {
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

    public void insertOrReplaceHuiFangTypeBeans(List<HuiFangTypeBean> huiFangTypeBeans) {

        HuiFangTypeBeanDao huiFangTypeBeanDao = mDaoSession.getHuiFangTypeBeanDao();
        huiFangTypeBeanDao.deleteAll();
        huiFangTypeBeanDao.insertInTx(huiFangTypeBeans);
    }

    public HuiFangTypeBean queryHuiFangTypeBean(int menu) {
        HuiFangTypeBean huiFangTypeBean = null;
        HuiFangTypeBeanDao huiFangTypeBeanDao = mDaoSession.getHuiFangTypeBeanDao();
        List<HuiFangTypeBean> list = huiFangTypeBeanDao.queryBuilder()
                .where(HuiFangTypeBeanDao.Properties.Menu.eq(menu))
                .list();
        if (list != null && list.size() > 0) {
            huiFangTypeBean = list.get(0);
        }
        return huiFangTypeBean;
    }


    //查询排课
    public List<CourseStudentBean> queryCourseStudentBeans() {
        List<CourseStudentBean> courseStudentBeans = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            CourseStudentBean courseStudentBean = queryCourseStudentBean(i);
            if (courseStudentBean != null) {
                courseStudentBeans.add(courseStudentBean);
            }
        }
        return courseStudentBeans;
    }

    public CourseStudentBean queryCourseStudentBean(int week) {

        CourseStudentModelDao courseStudentModelDao = mDaoSession.getCourseStudentModelDao();
        CourseStudentModel courseStudentModel = courseStudentModelDao.queryBuilder().where(CourseStudentModelDao.Properties.WeekCode.eq(week)).unique();
        if (courseStudentModel != null) {


            List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOSBeans = queryPrivateCoachCurriculumArrangementPlanVOSBeansByWeek(week);
            CourseStudentBean courseStudentBean = new CourseStudentBean();
            courseStudentBean.setPrivateCoachCurriculumArrangementPlanVOS(privateCoachCurriculumArrangementPlanVOSBeans);
            courseStudentBean.setDay(courseStudentModel.getDay());
            courseStudentBean.setDayAlias(courseStudentModel.getDayAlias());
            courseStudentBean.setWeekCode(courseStudentModel.getWeekCode());
            courseStudentBean.setWeekName(courseStudentModel.getWeekName());
            courseStudentBean.setLocalDate(courseStudentModel.getLocalDate());
            return courseStudentBean;
        }
        return null;
    }

    public List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> queryPrivateCoachCurriculumArrangementPlanVOSBeansByWeek(int week) {
        List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOSBeans = new ArrayList<>();
        PrivateCoachCurriculumArrangementPlanModelDao privateCoachCurriculumArrangementPlanModelDao = mDaoSession.getPrivateCoachCurriculumArrangementPlanModelDao();
        List<PrivateCoachCurriculumArrangementPlanModel> list = privateCoachCurriculumArrangementPlanModelDao.queryBuilder().where(PrivateCoachCurriculumArrangementPlanModelDao.Properties.Week.eq(week)).list();
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            PrivateCoachCurriculumArrangementPlanModel privateCoachCurriculumArrangementPlanModel = list.get(i);
            String id = privateCoachCurriculumArrangementPlanModel.getId();

            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVOBean = queryPrivateCoachCourseVOBean(id);
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVOBean = queryPrivateCourseMemberVOBean(id);
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = new CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean();
            privateCoachCurriculumArrangementPlanVOSBean.setPrivateCoachCourseVO(privateCoachCourseVOBean);
            privateCoachCurriculumArrangementPlanVOSBean.setPrivateCourseMemberVO(privateCourseMemberVOBean);
            privateCoachCurriculumArrangementPlanVOSBean.setId(id);
            privateCoachCurriculumArrangementPlanVOSBean.setWeek(week);
            privateCoachCurriculumArrangementPlanVOSBean.setSTime(privateCoachCurriculumArrangementPlanModel.getSTime());
            privateCoachCurriculumArrangementPlanVOSBean.setETime(privateCoachCurriculumArrangementPlanModel.getETime());
            privateCoachCurriculumArrangementPlanVOSBean.setColour(privateCoachCurriculumArrangementPlanModel.getColour());
            privateCoachCurriculumArrangementPlanVOSBean.setCoachId(privateCoachCurriculumArrangementPlanModel.getCoachId());
            privateCoachCurriculumArrangementPlanVOSBean.setDuration(privateCoachCurriculumArrangementPlanModel.getDuration());
            privateCoachCurriculumArrangementPlanVOSBean.setDataType(privateCoachCurriculumArrangementPlanModel.getDataType());
            privateCoachCurriculumArrangementPlanVOSBeans.add(privateCoachCurriculumArrangementPlanVOSBean);
        }
        return privateCoachCurriculumArrangementPlanVOSBeans;
    }

    public CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean queryPrivateCoachCourseVOBean(String id) {
        PrivateCoachCourseModelDao privateCoachCourseModelDao = mDaoSession.getPrivateCoachCourseModelDao();
        PrivateCoachCourseModel privateCoachCourseModel = privateCoachCourseModelDao.queryBuilder().where(PrivateCoachCourseModelDao.Properties.Id.eq(id)).unique();
        if (privateCoachCourseModel != null) {
            CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVOBean = new CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean();
            privateCoachCourseVOBean.setConsumingMinute(privateCoachCourseModel.getConsumingMinute());
            privateCoachCourseVOBean.setMemberCourseId(privateCoachCourseModel.getMemberCourseId());
            privateCoachCourseVOBean.setMemberCourseName(privateCoachCourseModel.getMemberCourseName());
            return privateCoachCourseVOBean;
        }
        return null;

    }

    public CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean queryPrivateCourseMemberVOBean(String id) {
        PrivateCourseMemberModelDao privateCourseMemberModelDao = mDaoSession.getPrivateCourseMemberModelDao();
        PrivateCourseMemberModel privateCourseMemberModel = privateCourseMemberModelDao.queryBuilder().where(PrivateCourseMemberModelDao.Properties.Id.eq(id)).unique();

        CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVOBean = new CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean();
        if (privateCourseMemberModel != null) {
            privateCourseMemberVOBean.setHeadPath(privateCourseMemberModel.getHeadPath());
            privateCourseMemberVOBean.setMemberId(privateCourseMemberModel.getMemberId());
            privateCourseMemberVOBean.setMemberName(privateCourseMemberModel.getMemberName());
            privateCourseMemberVOBean.setMemberSex(privateCourseMemberModel.getMemberSex());
            return privateCourseMemberVOBean;
        }
        return null;

    }

    //插入排课
    public Long insertPrivateCourseMemberVOBean(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCourseMemberVOBean privateCourseMemberVOBean, String id) {
        long l = 0;
        PrivateCourseMemberModelDao privateCourseMemberModelDao = mDaoSession.getPrivateCourseMemberModelDao();
        PrivateCourseMemberModel privateCourseMemberModel = new PrivateCourseMemberModel();
        privateCourseMemberModel.setId(id);
        if (privateCourseMemberVOBean != null) {
            privateCourseMemberModel.setHeadPath(privateCourseMemberVOBean.getHeadPath());
            privateCourseMemberModel.setMemberId(privateCourseMemberVOBean.getMemberId());
            privateCourseMemberModel.setMemberName(privateCourseMemberVOBean.getMemberName());
            privateCourseMemberModel.setMemberSex(privateCourseMemberVOBean.getMemberSex());
            l = privateCourseMemberModelDao.insertOrReplace(privateCourseMemberModel);
        }
        return l;
    }

    public Long insertPrivateCoachCourseVOBean(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean.PrivateCoachCourseVOBean privateCoachCourseVOBean, String id) {
        long l = 0;
        PrivateCoachCourseModelDao privateCoachCourseModelDao = mDaoSession.getPrivateCoachCourseModelDao();

        PrivateCoachCourseModel privateCoachCourseModel = new PrivateCoachCourseModel();
        privateCoachCourseModel.setId(id);
        if (privateCoachCourseVOBean != null) {
            privateCoachCourseModel.setConsumingMinute(privateCoachCourseVOBean.getConsumingMinute());
            privateCoachCourseModel.setMemberCourseId(privateCoachCourseVOBean.getMemberCourseId());
            privateCoachCourseModel.setMemberCourseName(privateCoachCourseVOBean.getMemberCourseName());
            l = privateCoachCourseModelDao.insertOrReplace(privateCoachCourseModel);
        }

        return l;
    }


    public Long insertPrivateCoachCurriculumArrangementPlanVOSBean(CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean) {
        long l = 0;
        if (privateCoachCurriculumArrangementPlanVOSBean != null) {
            insertPrivateCourseMemberVOBean(privateCoachCurriculumArrangementPlanVOSBean.getPrivateCourseMemberVO(), privateCoachCurriculumArrangementPlanVOSBean.getId());
            insertPrivateCoachCourseVOBean(privateCoachCurriculumArrangementPlanVOSBean.getPrivateCoachCourseVO(), privateCoachCurriculumArrangementPlanVOSBean.getId());
            PrivateCoachCurriculumArrangementPlanModelDao privateCoachCurriculumArrangementPlanModelDao = mDaoSession.getPrivateCoachCurriculumArrangementPlanModelDao();
            PrivateCoachCurriculumArrangementPlanModel privateCoachCurriculumArrangementPlanModel = new PrivateCoachCurriculumArrangementPlanModel();
            privateCoachCurriculumArrangementPlanModel.setId(privateCoachCurriculumArrangementPlanVOSBean.getId());
            privateCoachCurriculumArrangementPlanModel.setWeek(privateCoachCurriculumArrangementPlanVOSBean.getWeek());
            privateCoachCurriculumArrangementPlanModel.setSTime(privateCoachCurriculumArrangementPlanVOSBean.getSTime());
            privateCoachCurriculumArrangementPlanModel.setETime(privateCoachCurriculumArrangementPlanVOSBean.getETime());
            privateCoachCurriculumArrangementPlanModel.setColour(privateCoachCurriculumArrangementPlanVOSBean.getColour());
            privateCoachCurriculumArrangementPlanModel.setCoachId(privateCoachCurriculumArrangementPlanVOSBean.getCoachId());
            privateCoachCurriculumArrangementPlanModel.setDuration(privateCoachCurriculumArrangementPlanVOSBean.getDuration());
            privateCoachCurriculumArrangementPlanModel.setDataType(privateCoachCurriculumArrangementPlanVOSBean.getDataType());
            l = privateCoachCurriculumArrangementPlanModelDao.insertOrReplace(privateCoachCurriculumArrangementPlanModel);
        }

        return l;
    }

    public Long insertCourseStudentBean(CourseStudentBean courseStudentBean) {
        long l = 0;
        if (courseStudentBean != null) {
            List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOS = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
            for (int i = 0; i < privateCoachCurriculumArrangementPlanVOS.size(); i++) {
                CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean privateCoachCurriculumArrangementPlanVOSBean = privateCoachCurriculumArrangementPlanVOS.get(i);
                insertPrivateCoachCurriculumArrangementPlanVOSBean(privateCoachCurriculumArrangementPlanVOSBean);
            }
            CourseStudentModelDao courseStudentModelDao = mDaoSession.getCourseStudentModelDao();
            CourseStudentModel courseStudentModel = new CourseStudentModel();
            courseStudentModel.setDay(courseStudentBean.getDay());
            courseStudentModel.setDayAlias(courseStudentBean.getDayAlias());
            courseStudentModel.setWeekCode(courseStudentBean.getWeekCode());
            courseStudentModel.setWeekName(courseStudentBean.getWeekName());
            courseStudentModel.setLocalDate(courseStudentBean.getLocalDate());
            l = courseStudentModelDao.insertOrReplace(courseStudentModel);
        }

        return l;
    }

    public Long insertCourseStudentBeans(List<CourseStudentBean> courseStudentBeans) {
        Long count = 0L;
        if (courseStudentBeans == null) {
            return 0L;
        }
        clearCourseStudentData();
        for (int i = 0; i < courseStudentBeans.size(); i++) {
            CourseStudentBean courseStudentBean = courseStudentBeans.get(i);
            count += insertCourseStudentBean(courseStudentBean);
        }
        return count;
    }

    //删除
    public void deletePrivateCourseMemberVOBeanById(String id) {

        PrivateCourseMemberModelDao privateCourseMemberModelDao = mDaoSession.getPrivateCourseMemberModelDao();
        PrivateCourseMemberModel privateCourseMemberModel = privateCourseMemberModelDao.queryBuilder().where(PrivateCourseMemberModelDao.Properties.Id.eq(id)).unique();
        if (privateCourseMemberModel != null) {
            privateCourseMemberModelDao.delete(privateCourseMemberModel);
        }
    }

    public void deletePrivateCoachCourseVOBeanById(String id) {
        PrivateCoachCourseModelDao privateCoachCourseModelDao = mDaoSession.getPrivateCoachCourseModelDao();
        PrivateCoachCourseModel privateCoachCourseModel = privateCoachCourseModelDao.queryBuilder().where(PrivateCoachCourseModelDao.Properties.Id.eq(id)).unique();
        if (privateCoachCourseModel != null) {
            privateCoachCourseModelDao.deleteByKey(privateCoachCourseModel.getIdx());
        }
    }

    public void deletePrivateCoachCurriculumArrangementPlanVOSBeanById(String id) {
        deletePrivateCourseMemberVOBeanById(id);
        deletePrivateCoachCourseVOBeanById(id);
        PrivateCoachCurriculumArrangementPlanModelDao privateCoachCurriculumArrangementPlanModelDao = mDaoSession.getPrivateCoachCurriculumArrangementPlanModelDao();
        PrivateCoachCurriculumArrangementPlanModel privateCoachCurriculumArrangementPlanModel = privateCoachCurriculumArrangementPlanModelDao.queryBuilder().where(PrivateCoachCurriculumArrangementPlanModelDao.Properties.Id.eq(id)).unique();
        if (privateCoachCurriculumArrangementPlanModel != null) {
            privateCoachCurriculumArrangementPlanModelDao.deleteByKey(privateCoachCurriculumArrangementPlanModel.getIdx());
        }
    }

    public void deleteCourseStudentBean(CourseStudentBean courseStudentBean) {
        List<CourseStudentBean.PrivateCoachCurriculumArrangementPlanVOSBean> privateCoachCurriculumArrangementPlanVOS = courseStudentBean.getPrivateCoachCurriculumArrangementPlanVOS();
        if (privateCoachCurriculumArrangementPlanVOS == null) {
            return;
        }
        for (int i = 0; i < privateCoachCurriculumArrangementPlanVOS.size(); i++) {
            deletePrivateCoachCurriculumArrangementPlanVOSBeanById(privateCoachCurriculumArrangementPlanVOS.get(i).getId());
        }
        CourseStudentModelDao courseStudentModelDao = mDaoSession.getCourseStudentModelDao();
        CourseStudentModel courseStudentModel = courseStudentModelDao.queryBuilder().where(CourseStudentModelDao.Properties.WeekCode.eq(courseStudentBean.getWeekCode())).unique();
        if (courseStudentModel != null) {
            courseStudentModelDao.deleteByKey(courseStudentModel.getIdx());
        }
    }

    public void clearCourseStudentData() {
        PrivateCourseMemberModelDao privateCourseMemberModelDao = mDaoSession.getPrivateCourseMemberModelDao();
        PrivateCoachCourseModelDao privateCoachCourseModelDao = mDaoSession.getPrivateCoachCourseModelDao();
        PrivateCoachCurriculumArrangementPlanModelDao privateCoachCurriculumArrangementPlanModelDao = mDaoSession.getPrivateCoachCurriculumArrangementPlanModelDao();
        CourseStudentModelDao courseStudentModelDao = mDaoSession.getCourseStudentModelDao();
        privateCourseMemberModelDao.deleteAll();
        privateCoachCourseModelDao.deleteAll();
        privateCoachCurriculumArrangementPlanModelDao.deleteAll();
        courseStudentModelDao.deleteAll();

    }


}
