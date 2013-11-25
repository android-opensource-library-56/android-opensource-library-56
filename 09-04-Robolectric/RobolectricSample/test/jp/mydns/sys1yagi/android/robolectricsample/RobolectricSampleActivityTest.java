package jp.mydns.sys1yagi.android.robolectricsample;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowWebView;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
public class RobolectricSampleActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void 画面に表示するTextViewの文字列を取得してチェックする() {
        Activity activity = Robolectric
                .buildActivity(RobolectricSampleActivity.class).create().get();
        TextView textView = (TextView) activity.findViewById(R.id.text);
        assertThat(textView, notNullValue());
        assertThat(textView.getText().toString(),
                is(activity.getString(R.string.hello_world)));
    }

    @Test
    public void WebViewで読み込んだURLのテスト() {
        Activity activity = Robolectric
                .buildActivity(RobolectricSampleActivity.class).create().get();
        WebView webView = (WebView) activity.findViewById(R.id.webview);

        ShadowWebView shadowWebView = Robolectric.shadowOf((WebView) webView);

        assertThat("http://robolectric.org/index.html",
                is(shadowWebView.getLastLoadedUrl()));
    }
    
    @Test
    public void lifecycle(){
        RobolectricSampleActivity activity = Robolectric
                .buildActivity(RobolectricSampleActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }
}
