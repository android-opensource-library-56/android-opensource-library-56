package jp.mydns.sys1yagi.android.android_support_v4_sample;

import java.util.ArrayList;
import java.util.List;

import jp.mydns.sys1agi.android.android_support_v4_sample.R;
import jp.mydns.sys1yagi.android.android_support_v4_sample.fragment.RssFragment;
import jp.mydns.sys1yagi.android.android_support_v4_sample.rss.RssFeed;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class LoaderActivity extends FragmentActivity {
    private final static String TAG = LoaderActivity.class.getSimpleName();

    private final static List<RssFeed> RSS_FEEDS = new ArrayList<RssFeed>() {
        {
            this.add(new RssFeed("Yahoo! 国内",
                    "http://rss.dailynews.yahoo.co.jp/fc/domestic/rss.xml"));
            this.add(new RssFeed("Yahoo! コンピュータ",
                    "http://rss.dailynews.yahoo.co.jp/fc/computer/rss.xml"));
            this.add(new RssFeed("Yahoo! サイエンス",
                    "http://rss.dailynews.yahoo.co.jp/fc/science/rss.xml"));
            this.add(new RssFeed("Yahoo! 海外",
                    "http://rss.dailynews.yahoo.co.jp/fc/world/rss.xml"));
            this.add(new RssFeed("Yahoo! エンターテインメント",
                    "http://rss.dailynews.yahoo.co.jp/fc/entertainment/rss.xml"));
            this.add(new RssFeed("Yahoo! 地域",
                    "http://rss.dailynews.yahoo.co.jp/fc/local/rss.xml"));
        }
    };

    private Fragment[] mFragments = new Fragment[RSS_FEEDS.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return RSS_FEEDS.size();
            }

            @Override
            public Fragment getItem(int position) {
                if (mFragments[position] == null) {
                    mFragments[position] = RssFragment.newInstance(RSS_FEEDS
                            .get(position));
                }
                return mFragments[position];
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return RSS_FEEDS.get(position).getTitle();
            }
        });
    }
}
