package jp.mydns.sys1yagi.android.androidviewpagerindicatorsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.PageIndicator;

public class CirclePageIndicatorActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_circle_page_indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 5;
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
            public CharSequence getPageTitle(int position) {
                return "Page" + (position + 1);
            }
        });
        PageIndicator circleIndicator = (PageIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

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
