package jp.mydns.sys1yagi.android.sqlcipherforandroidsample;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SampleRawOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "sample";

    public SampleRawOpenHelper(Context context) {
        super(context, new File(context.getExternalFilesDir(null),
                "cipher_db_raw.db").getAbsolutePath(), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ( " + BaseColumns._ID
                + " integer primary key autoincrement, " + "no integer, "
                + "name text not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public void clear() {
        getWritableDatabase().execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(getWritableDatabase());
    }
}
