package jp.mydns.sys1yagi.android.dex2jarsample;

import android.app.Activity;
import android.os.Bundle;

public class Dex2jarSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dex2jar_sample);
        consolidate("hello", "world");
    }

    public String consolidate(String a, String b){
        return a + b;
    }
}
