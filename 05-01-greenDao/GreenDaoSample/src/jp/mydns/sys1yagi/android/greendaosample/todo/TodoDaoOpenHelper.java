package jp.mydns.sys1yagi.android.greendaosample.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import jp.mydns.sys1yagi.android.greendaosample.DaoMaster.OpenHelper;

public class TodoDaoOpenHelper extends OpenHelper {
    public static final int STATUS_NEW = 0;
    public static final int STATUS_END = 1;
    public static final int STATUS_ARCHIVE = 2;

    public TodoDaoOpenHelper(Context context, String name, CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int oldVersion,
            int newVersion) {
        // ここでマイグレーションを行う
    }
}
