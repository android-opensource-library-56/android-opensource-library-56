package jp.mydns.sys1yagi.android.actionbarsherlocksample;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;

public class TabStyleActivity extends SherlockActivity implements TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_style);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0; i < 3; i++) {
            addTab(actionBar, i + 1);
        }

    }

    void addTab(ActionBar actionBar, int index) {
        Tab tab = actionBar.newTab();
        tab.setText("Tab" + index);
        tab.setTabListener(this);
        actionBar.addTab(tab);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Toast.makeText(this, "change:" + tab.getPosition(), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }
}
