package jp.mydns.sys1yagi.android.greendaosample;

import jp.mydns.sys1yagi.android.greendaosample.todo.TodoDaoOpenHelper;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class GreenDaoSampleApplication extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        TodoDaoOpenHelper helper = new TodoDaoOpenHelper(this, "todo-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
