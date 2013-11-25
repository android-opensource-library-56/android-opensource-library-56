package jp.mydns.sys1yagi.android.butterknifesample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class ButterKnifeSampleActivity extends Activity {
    private ButterKnifeSampleActivity This() {
        return this;
    }

    @InjectView(R.id.text)
    TextView mText;

    @InjectView(R.id.button)
    Button mButton;

    @OnClick(R.id.button)
    void onClick(Button v) {
        Toast.makeText(This(), "button clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_sample);

        Views.inject(this);
    }
}
