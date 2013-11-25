package jp.mydns.sys1yagi.android.androidviewpagerindicatorsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;

public class IconPageIndicatorActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_icon_page_indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new IconPagerAdapterImpl(
                getSupportFragmentManager()));
        IconPageIndicator iconIndicator = (IconPageIndicator) findViewById(R.id.indicator);
        iconIndicator.setViewPager(viewPager);

    }

    static class IconPagerAdapterImpl extends FragmentStatePagerAdapter
            implements IconPagerAdapter {
        protected static final String[] CONTENT = new String[] { "1", "2", "3",
                "4", };
        protected static final int[] ICONS = new int[] { R.drawable.facebook,
                R.drawable.google, R.drawable.twitter, R.drawable.share };

        private int mCount = CONTENT.length;

        public IconPagerAdapterImpl(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new SampleFragment();
            Bundle args = new Bundle();
            args.putInt(SampleFragment.PAGE_NUMBER, position + 1);
            f.setArguments(args);
            return f;
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public int getIconResId(int index) {
            return ICONS[index % ICONS.length];
        }

    }

    public static class SampleFragment extends Fragment {
        public static final String PAGE_NUMBER = "page_number";

        public SampleFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText("Page" + getArguments().getInt(PAGE_NUMBER));
            return view;
        }
    }
}
