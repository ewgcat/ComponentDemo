package com.yijian.clubmodule.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.yijian.clubmodule.greendao.gen.CourseStudentModelDao;
import com.yijian.clubmodule.greendao.gen.DaoMaster;
import com.yijian.clubmodule.greendao.gen.HuiFangTypeBeanDao;
import com.yijian.clubmodule.greendao.gen.OthermodelVoDao;
import com.yijian.clubmodule.greendao.gen.PrivateCoachCourseModelDao;
import com.yijian.clubmodule.greendao.gen.PrivateCoachCurriculumArrangementPlanModelDao;
import com.yijian.clubmodule.greendao.gen.PrivateCourseMemberModelDao;
import com.yijian.clubmodule.greendao.gen.RoleVoBeanDao;
import com.yijian.clubmodule.greendao.gen.SearchKeyDao;

import org.greenrobot.greendao.database.Database;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/16 20:26:49
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                }, SearchKeyDao.class,
                HuiFangTypeBeanDao.class,
                OthermodelVoDao.class, RoleVoBeanDao.class,
                CourseStudentModelDao.class, PrivateCoachCourseModelDao.class,
                PrivateCoachCurriculumArrangementPlanModelDao.class, PrivateCourseMemberModelDao.class
        );
    }

}


