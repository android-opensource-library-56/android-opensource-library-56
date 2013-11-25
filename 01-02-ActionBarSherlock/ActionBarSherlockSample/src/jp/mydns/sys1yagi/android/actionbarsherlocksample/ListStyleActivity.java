package jp.mydns.sys1yagi.android.actionbarsherlocksample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;

public class ListStyleActivity extends SherlockActivity {
    public ListStyleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_style);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        for (int i = 0; i < 5; i++) {
            adapter.add("item" + i);
        }
        actionBar.setListNavigationCallbacks(adapter,
                new OnNavigationListener() {
                    @Override
                    public boolean onNavigationItemSelected(int itemPosition,
                            long itemId) {
                        Toast.makeText(This(), "pos" + itemPosition,
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
    }

}
