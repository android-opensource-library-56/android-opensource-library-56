package jp.mydns.sys1yagi.android.android_support_v4_sample;

import jp.mydns.sys1agi.android.android_support_v4_sample.R;
import jp.mydns.sys1yagi.android.android_support_v4_sample.fragment.ItemListFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class AndroidSupportV4SampleActivity extends FragmentActivity {
    public AndroidSupportV4SampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_support_v4_sample);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.layout, ItemListFragment.newInstance());
        transaction.commit();
    }
}
