package jp.mydns.sys1yagi.android.android_support_v4_sample;

import jp.mydns.sys1agi.android.android_support_v4_sample.R;
import jp.mydns.sys1yagi.android.android_support_v4_sample.fragment.SimpleFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

public class ViewPagerActivity extends FragmentActivity {
    private final static String TAG = ViewPagerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = SimpleFragment.newInstance();
                Bundle args = new Bundle();
                args.putInt(SimpleFragment.PAGE_NUMBER, position + 1);
                fragment.setArguments(args);
                return fragment;
            }
        });

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                Log.d(TAG, "onPageSelected:" + pos);
            }

            @Override
            public void onPageScrolled(int pos, float positionOffset,
                    int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled:" + pos);
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
                Log.d(TAG, "onPageScrollStateChanged:" + pos);
            }
        });
    }
}
