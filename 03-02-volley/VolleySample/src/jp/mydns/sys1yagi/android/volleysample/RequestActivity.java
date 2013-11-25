package jp.mydns.sys1yagi.android.volleysample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestActivity extends Activity {
    static RequestQueue sQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (sQueue == null) {
            sQueue = Volley.newRequestQueue(getApplicationContext());
        }
        setContentView(R.layout.activity_request);
        RequestQueue queue = Volley.newRequestQueue(this);
        final TextView textView = (TextView) findViewById(R.id.text);
        StringRequest request = new StringRequest(
                "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=500&mode=rss",
                new Listener<String>() {
                    @Override
                    public void onResponse(String text) {
                        textView.setText(text);
                    }
                }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage());
                    }
                });
        queue.add(request);
    }

}
