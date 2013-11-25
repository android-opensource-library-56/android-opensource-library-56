package jp.mydns.sys1yagi.android.robolectricsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class RobolectricSampleActivity extends Activity {
    private final static String TAG = RobolectricSampleActivity.class
            .getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robolectric_sample);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://robolectric.org/index.html");

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }
}
