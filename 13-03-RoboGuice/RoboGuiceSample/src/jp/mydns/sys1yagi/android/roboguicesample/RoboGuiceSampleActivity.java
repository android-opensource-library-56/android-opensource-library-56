package jp.mydns.sys1yagi.android.roboguicesample;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;

@ContentView(R.layout.activity_robo_guice_sample)
public class RoboGuiceSampleActivity extends RoboActivity {

    TextView mName;

    @InjectView(R.id.container)
    LinearLayout mContainer;

    @Inject
    LayoutInflater mLayoutInflater;

    @Inject
    ViewFinder mFinder;

    @InjectResource(R.drawable.ic_launcher)
    Drawable mDrawable;

    @InjectResource(R.string.app_name)
    String mAppName;

    @InjectView(R.id.imageView1)
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mName = mFinder.find(R.id.text);
        mName.setText(mAppName);
        mImage.setImageDrawable(mDrawable);

    }
}
