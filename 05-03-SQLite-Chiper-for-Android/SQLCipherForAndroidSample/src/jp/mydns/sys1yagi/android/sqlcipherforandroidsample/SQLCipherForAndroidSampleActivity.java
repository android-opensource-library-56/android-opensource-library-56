package jp.mydns.sys1yagi.android.sqlcipherforandroidsample;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import net.sqlcipher.database.SQLiteDatabase;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class SQLCipherForAndroidSampleActivity extends Activity {

    private SampleOpenHelper mOpenHelper;
    private SampleRawOpenHelper mRawOpenHelper;

    private String mPassword = "Yl4jE46Pw3#sS]wegksW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlcipher_for_android_sample);
        SQLiteDatabase.loadLibs(this);

        mOpenHelper = new SampleOpenHelper(this);
        mRawOpenHelper = new SampleRawOpenHelper(this);

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase(mPassword);
        final android.database.sqlite.SQLiteDatabase rawDb = mRawOpenHelper
                .getWritableDatabase();

        final EditText dataEdit = (EditText) findViewById(R.id.data);

        ((TextView) findViewById(R.id.raw_data))
                .setMovementMethod(new ScrollingMovementMethod());
        ((TextView) findViewById(R.id.unencrypted_data))
                .setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.button_save).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String data = dataEdit.getText().toString();
                        ContentValues values = new ContentValues();
                        values.put(BaseColumns._ID, "1000");
                        values.put("no", 1);
                        values.put("name", data);
                        db.replace(SampleOpenHelper.TABLE_NAME, null, values);
                        rawDb.replace(SampleRawOpenHelper.TABLE_NAME, null,
                                values);
                        load(db);
                    }
                });

        load(db);
    }

    private void load(SQLiteDatabase db) {
        loadCipher(db);
        loadRaw(new File(getExternalFilesDir(null), "cipher_db.db"),
                (TextView) findViewById(R.id.raw_data));
        loadRaw(new File(getExternalFilesDir(null), "cipher_db_raw.db"),
                (TextView) findViewById(R.id.unencrypted_data));

    }

    private void loadCipher(SQLiteDatabase db) {
        TextView ciper = (TextView) findViewById(R.id.cipher_data);

        Cursor cursor = db.query(SampleOpenHelper.TABLE_NAME, null, null, null,
                null, null, null);
        if (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(BaseColumns._ID);
            int noIndex = cursor.getColumnIndex("no");
            int nameIndex = cursor.getColumnIndex("name");
            int id = cursor.getInt(idIndex);
            int no = cursor.getInt(noIndex);
            String name = cursor.getString(nameIndex);
            ciper.setText("id=" + id + "\nno=" + no + "\nname=" + name);
        }
        cursor.close();
    }

    public static byte[] concat(byte[] first, byte[] second, int size) {
        byte[] result = Arrays.copyOf(first, first.length + size);
        System.arraycopy(second, 0, result, first.length, size);
        return result;
    }

    private void loadRaw(File dbFile, TextView raw) {
        try {
            byte[] buf = new byte[1024];
            int readBytes = 0;
            byte[] byteString = null;
            FileInputStream fis = new FileInputStream(dbFile);
            while ((readBytes = fis.read(buf)) > 0) {
                if (byteString == null) {
                    byteString = Arrays.copyOf(buf, readBytes);
                } else {
                    byteString = concat(byteString, buf, readBytes);
                }
            }
            raw.setText(new String(byteString));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sqlcipher_for_android_sample, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        // DBクリア
        mOpenHelper.clear(mPassword);
        mRawOpenHelper.clear();
        return true;
    }

}
