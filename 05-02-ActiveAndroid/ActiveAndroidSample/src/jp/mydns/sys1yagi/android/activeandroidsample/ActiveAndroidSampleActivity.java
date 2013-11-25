package jp.mydns.sys1yagi.android.activeandroidsample;

import java.util.List;
import java.util.Locale;

import jp.mydns.sys1yagi.android.activeandroidsample.history.HistoryFragment;
import jp.mydns.sys1yagi.android.activeandroidsample.model.Todo;
import jp.mydns.sys1yagi.android.activeandroidsample.todo.TodoDaoHelper;
import jp.mydns.sys1yagi.android.activeandroidsample.todo.TodoFragment;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

public class ActiveAndroidSampleActivity extends FragmentActivity implements
        ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;
    private MenuItem mCleanup;
    private TodoFragment mTodoFragment;
    private HistoryFragment mHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_android_sample);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar.setSelectedNavigationItem(position);
                    }
                });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.active_android_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ActiveAndroid.beginTransaction();
        try {
            List<Todo> ended = new Select()
                    .from(Todo.class)
                    .where(Todo.COLUMN_STATUS + "=?",
                            Integer.toString(TodoDaoHelper.STATUS_END))
                    .execute();
            for (Todo todo : ended) {
                todo.setStatus(TodoDaoHelper.STATUS_ARCHIVE);
                todo.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        // 画面更新
        if (mTodoFragment != null) {
            mTodoFragment.reload();
        }
        if (mHistoryFragment != null) {
            mHistoryFragment.reload();
        }

        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab,
            FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab,
            FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab,
            FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
            case 0:
                if (mTodoFragment == null) {
                    mTodoFragment = new TodoFragment();
                }
                fragment = mTodoFragment;
                break;
            case 1:
                if (mHistoryFragment == null) {
                    mHistoryFragment = new HistoryFragment();
                }
                fragment = mHistoryFragment;
                break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
            case 0:
                return getString(R.string.title_todo).toUpperCase(l);
            case 1:
                return getString(R.string.title_history).toUpperCase(l);
            }
            return null;
        }
    }
}
