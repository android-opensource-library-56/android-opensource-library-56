/**
 * 
 */
package jp.mydns.sys1yagi.android.greendaosample.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;

/**
 * @author yagitoshihiro
 *
 */
public class TodoLoader extends CursorLoader {
    private final static String TAG = TodoLoader.class.getSimpleName();
    private SQLiteDatabase mDb;
    private String mTableName;

    public TodoLoader(Context context, SQLiteDatabase db, String tableName, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        super(context, null, projection, selection, selectionArgs, sortOrder);
        mDb = db;
        mTableName = tableName;
    }
    @Override
    public Cursor loadInBackground() {
        return mDb.query(mTableName, getProjection(), getSelection(), getSelectionArgs(), null, null, getSortOrder());
    }
}
