package jp.mydns.sys1yagi.android.acrasample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class AcraSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acra_sample);

        findViewById(R.id.crash_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 意図的にNullPointerExceptionを発生させる
                        String a = null;
                        a.toString();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acra_sample, menu);
        return true;
    }

}
