package jp.mydns.sys1yagi.android.mockitosample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MockitoSampleActivity extends FragmentActivity {
    MockitoSampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mockito_sample);

        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(), PreviewActivity.class);
                intent.putExtra(PreviewActivity.EXTRA_URL,
                        "http://farm5.staticflickr.com/4118/4754588349_8d3386450b_z.jpg");
                startActivity(intent);
            }
        });
    }
}
