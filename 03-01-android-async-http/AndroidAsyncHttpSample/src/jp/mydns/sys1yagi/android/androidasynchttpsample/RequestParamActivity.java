package jp.mydns.sys1yagi.android.androidasynchttpsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RequestParamActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_param);
        startLoad();
    }

    private void startLoad() {
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams param = new RequestParams();
        param.put("sort", "hot");
        param.put("threshold", "200");
        param.put("mode", "rss");

        client.get("http://b.hatena.ne.jp/entrylist/social", param,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        TextView textView = ((TextView) findViewById(R.id.text));
                        textView.setText(content);
                        textView.setVisibility(View.VISIBLE);
                        findViewById(R.id.progress).setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
                        TextView textView = ((TextView) findViewById(R.id.text));
                        textView.setText(content);
                        findViewById(R.id.scroll).setVisibility(View.VISIBLE);
                        findViewById(R.id.progress).setVisibility(View.GONE);
                    }
                });
    }

}
