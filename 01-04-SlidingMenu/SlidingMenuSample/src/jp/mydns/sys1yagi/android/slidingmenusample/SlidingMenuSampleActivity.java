package jp.mydns.sys1yagi.android.slidingmenusample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;

public class SlidingMenuSampleActivity extends SherlockActivity {
    private final static String TAG = SlidingMenuSampleActivity.class
            .getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu_sample);

        final SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindWidth(getResources().getDimensionPixelSize(
                R.dimen.menu_width));
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding_menu);

        menu.setOnOpenedListener(new OnOpenedListener() {
            @Override
            public void onOpened() {
                // メニューが開かれた時に呼び出される

            }
        });
        menu.setOnClosedListener(new OnClosedListener() {
            @Override
            public void onClosed() {
                // メニューが閉じた時に呼び出される
            }
        });
        initializeMenuLayout(menu);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                menu.showMenu(true);
            }
        }, 4000);

    }

    class MenuItem {
        String title;
        int icon;
    }

    private final static String ICON = "icon";
    private final static String TITLE = "text";

    void initializeMenuLayout(SlidingMenu menu) {
        ListView listView = (ListView) menu.findViewById(R.id.menu_list);

        final List<Integer> itemsIcon = new ArrayList<Integer>();
        itemsIcon.add(R.drawable.icon_refresh);
        itemsIcon.add(R.drawable.icon_account);
        itemsIcon.add(R.drawable.icon_home);
        itemsIcon.add(R.drawable.icon_group);

        final List<String> itemsTitle = new ArrayList<String>();
        itemsTitle.add("Refresh");
        itemsTitle.add("Account");
        itemsTitle.add("Home");
        itemsTitle.add("Group");

        List<Map<String, MenuItem>> items = new ArrayList<Map<String, MenuItem>>();
        for (int i = 0; i < itemsTitle.size(); i++) {
            Map<String, MenuItem> map = new HashMap<String, MenuItem>();
            MenuItem menuItem = new MenuItem();
            menuItem.icon = itemsIcon.get(i);
            menuItem.title = itemsTitle.get(i);

            map.put(ICON, menuItem);
            map.put(TITLE, menuItem);
            items.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, items,
                R.layout.sliding_menu_item, new String[] { ICON, TITLE },
                new int[] { R.id.icon, R.id.title });

        adapter.setViewBinder(new ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                    String textRepresentation) {
                MenuItem menuItem = (MenuItem) data;
                switch (view.getId()) {
                case R.id.icon:
                    ((ImageView) view).setImageResource(menuItem.icon);
                    break;
                case R.id.title:
                    ((TextView) view).setText(menuItem.title);
                    break;
                }
                return true;
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Log.d(TAG, "onItemClick");
            }
        });
    }
}
