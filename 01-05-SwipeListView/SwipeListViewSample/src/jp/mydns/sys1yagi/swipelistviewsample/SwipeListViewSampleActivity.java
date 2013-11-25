package jp.mydns.sys1yagi.swipelistviewsample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import com.fortysevendeg.android.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.android.swipelistview.SwipeListView;

public class SwipeListViewSampleActivity extends Activity {
    private final static String TAG = SwipeListViewSampleActivity.class
            .getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_list_view_sample);
        final SwipeListView swipeListView = (SwipeListView) findViewById(R.id.list);
        final List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", "List Item No:" + i);
            map.put("remove", i);
            dataList.add(map);
        }
        final SimpleAdapter adapter = new SimpleAdapter(this, dataList,
                R.layout.list_item, new String[] { "text", "remove" },
                new int[] { R.id.text, R.id.remove });
        adapter.setViewBinder(new ViewBinder() {
            @Override
            public boolean setViewValue(View view, final Object data,
                    String textRepresentation) {
                switch (view.getId()) {
                case R.id.remove:
                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String, Object> removeItem = null;
                            for (Map<String, Object> map : dataList) {
                                if (map.get("remove").equals(data)) {
                                    removeItem = map;
                                    break;
                                }
                            }
                            dataList.remove(removeItem);
                            adapter.notifyDataSetChanged();
                            swipeListView.closeOpenedItems();
                        }
                    });
                    break;
                case R.id.text:
                    ((TextView) view).setText((String) data);
                    break;
                }
                return true;
            }
        });
        swipeListView.setAdapter(adapter);
        swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onChoiceChanged(int position, boolean selected) {
                Log.d(TAG, "onChoiceChanged:" + position + ", " + selected);
            }

            @Override
            public void onChoiceEnded() {
                Log.d(TAG, "onChoiceEnded");
            }

            @Override
            public void onChoiceStarted() {
                Log.d(TAG, "onChoiceStarted");
            }

            @Override
            public void onClickBackView(int position) {
                Log.d(TAG, "onClickBackView:" + position);
            }

            @Override
            public void onClickFrontView(int position) {
                Log.d(TAG, "onClickFrontView:" + position);
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
                Log.d(TAG, "onClosed:" + position + "," + fromRight);
            }

            @Override
            public void onDismiss(int[] arg0) {
                Log.d(TAG, "onDismiss");
            }

            @Override
            public void onFirstListItem() {
                Log.d(TAG, "onFirstListItem");
            }

            @Override
            public void onLastListItem() {
                Log.d(TAG, "onLastListItem");
            }

            @Override
            public void onListChanged() {
                Log.d(TAG, "onListChanged");
            }

            @Override
            public void onMove(int position, float x) {
                Log.d(TAG, "onMove:" + position + "," + x);
            }

            @Override
            public void onOpened(int position, boolean toRight) {
                Log.d(TAG, "onOpened:" + position + "," + toRight);
            }

            @Override
            public void onStartClose(int position, boolean right) {
                Log.d(TAG, "onStartClose:" + position + "," + right);
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.d(TAG, "onStartOpen:" + position + "," + action + ","
                        + right);
            }
        });
    }
}
