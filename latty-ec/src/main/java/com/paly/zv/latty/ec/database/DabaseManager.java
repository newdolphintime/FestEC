package com.paly.zv.latty.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DabaseManager {
    private DaoSession daoSession = null;
    private UserProfileDao mDao = null;

    private DabaseManager() {
    }

    public DabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DabaseManager INSTANCE = new DabaseManager();

    }

    public static DabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fastec.db");
        final Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        mDao = daoSession.getUserProfileDao();
    }

    public final UserProfileDao getmDao() {
        return mDao;
    }
}
