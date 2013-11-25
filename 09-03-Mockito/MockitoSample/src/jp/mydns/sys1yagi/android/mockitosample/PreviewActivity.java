package jp.mydns.sys1yagi.android.mockitosample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class PreviewActivity extends Activity {
    public static final String EXTRA_URL = "url";

    private ImageView mImageView;
    private ImageLoader mImageLoader = new ImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        mImageView = (ImageView) findViewById(R.id.image);
        String url = getIntent().getStringExtra(EXTRA_URL);
        mImageLoader.loadImage(mImageView, url);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mImageLoader.cancel(mImageView);
    }
}
