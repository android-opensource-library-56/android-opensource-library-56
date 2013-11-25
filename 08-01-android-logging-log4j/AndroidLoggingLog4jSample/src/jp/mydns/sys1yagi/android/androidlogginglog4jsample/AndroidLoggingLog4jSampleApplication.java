package jp.mydns.sys1yagi.android.androidlogginglog4jsample;

import android.app.Application;

public class AndroidLoggingLog4jSampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ConfigureLog4j.configure(this);
    }
}
