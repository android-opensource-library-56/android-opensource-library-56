package jp.mydns.sys1yagi.android.greendaosample;

import java.util.List;
import java.util.Locale;

import jp.mydns.sys1yagi.android.greendaosample.TodoDao.Properties;
import jp.mydns.sys1yagi.android.greendaosample.history.HistoryFragment;
import jp.mydns.sys1yagi.android.greendaosample.todo.TodoDaoOpenHelper;
import jp.mydns.sys1yagi.android.greendaosample.todo.TodoFragment;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class GreenDaoSampleActivity extends FragmentActivity implements
        ActionBar.TabListener {
    private final static String TAG = GreenDaoSampleActivity.class
            .getSimpleName();
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private DaoSession mDaoSession;
    private MenuItem mCleanup;
    private TodoFragment mTodoFragment;
    private HistoryFragment mHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_sample);
        // appcompatに対応すると2.1から利用出来る
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mDaoSession = ((GreenDaoSampleApplication) getApplication())
                .getDaoSession();

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
        getMenuInflater().inflate(R.menu.green_dao_sample, menu);
        mCleanup = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        List<Todo> ended = mDaoSession.getTodoDao().queryBuilder()
                .where(Properties.Status.eq(TodoDaoOpenHelper.STATUS_END))
                .list();

        for (Todo todo : ended) {
            todo.setStatus(TodoDaoOpenHelper.STATUS_ARCHIVE);
        }
        mDaoSession.getTodoDao().updateInTx(ended);
        mDaoSession.getTodoDao().delete(null);
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
        if (mCleanup != null) {
            if (tab.getPosition() == 0) {
                mCleanup.setVisible(true);
            } else {
                mCleanup.setVisible(false);
            }
        }
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
                    mTodoFragment = new TodoFragment(mDaoSession);
                }
                fragment = mTodoFragment;
                break;
            case 1:
                if (mHistoryFragment == null) {
                    mHistoryFragment = new HistoryFragment(mDaoSession);
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
