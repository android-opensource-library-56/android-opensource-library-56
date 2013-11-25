package jp.mydns.sys1yagi.android.unifiedpreferencesample;

import net.saik0.android.unifiedpreference.UnifiedPreferenceActivity;
import net.saik0.android.unifiedpreference.UnifiedPreferenceFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.util.Log;

public class UnifiedPreferenceSampleActivity extends UnifiedPreferenceActivity
        implements OnSharedPreferenceChangeListener {
    private final static String TAG = UnifiedPreferenceSampleActivity.class
            .getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setHeaderRes(R.xml.preference_headers);

        setSharedPreferencesName("unified_preference");
        setSharedPreferencesMode(Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);

    }

    // タブレット用
    public static class NotificationFragment extends UnifiedPreferenceFragment {
    }

    public static class CacheFragment extends UnifiedPreferenceFragment {
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSharedPreferences("unified_preference", Context.MODE_PRIVATE)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getSharedPreferences("unified_preference", Context.MODE_PRIVATE)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
            String key) {
        // 設定の変更が通知される
        Log.d(TAG, key + " changed");
    }

}
