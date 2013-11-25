package jp.mydns.sys1yagi.android.androidlogginglog4jsample;

import org.apache.log4j.Logger;

import android.app.Activity;
import android.os.Bundle;

public class AndroidLoggingLog4jSampleActivity extends Activity {
    private final static Logger log = Logger
            .getLogger(AndroidLoggingLog4jSampleActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_logging_log4j_sample);
        log.debug("onCreate");
        new SampleClass().doSomething();
    }
}
