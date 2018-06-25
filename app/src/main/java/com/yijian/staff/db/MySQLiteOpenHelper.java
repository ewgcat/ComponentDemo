package com.yijian.staff.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.yijian.staff.greendao.gen.CoachHuiFangTypeBeanDao;
import com.yijian.staff.greendao.gen.DaoMaster;
import com.yijian.staff.greendao.gen.HuiFangTypeBeanDao;
import com.yijian.staff.greendao.gen.SearchKeyDao;
import com.yijian.staff.greendao.gen.UserDao;

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
        }, SearchKeyDao.class, UserDao.class, CoachHuiFangTypeBeanDao.class, HuiFangTypeBeanDao.class);
    }

}


