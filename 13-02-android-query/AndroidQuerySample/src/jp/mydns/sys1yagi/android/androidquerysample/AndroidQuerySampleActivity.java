package jp.mydns.sys1yagi.android.androidquerysample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class AndroidQuerySampleActivity extends Activity {
    private AndroidQuerySampleActivity This() {
        return this;
    }

    private AQuery mAQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_query_sample);
        mAQuery = new AQuery(this);

        mAQuery.id(R.id.text).text("Hello AQuery").id(R.id.button)
                .clicked(this, "onClick");
    }

    public void onClick(View v) {
        Toast.makeText(This(), "Hello AQuery", Toast.LENGTH_SHORT).show();
        loadData("https://github.com/androidquery/androidquery");
    }

    private void loadData(String url) {
        mAQuery.ajax(url, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String html, AjaxStatus status) {

                if (status.getCode() == 200) {
                    // success
                    mAQuery.id(R.id.text).text(html);
                } else {
                    // error
                    mAQuery.id(R.id.text).text(status.getError());
                }
            }
        });
    }
}
