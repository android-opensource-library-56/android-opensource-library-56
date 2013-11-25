package jp.mydns.sys1yagi.android_pulltorefreshsample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class AndroidPullToRefreshSampleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_pull_to_refresh_sample);
        final PullToRefreshListView listView = (PullToRefreshListView) findViewById(R.id.list_view);
        listView.setMode(Mode.PULL_FROM_START);
        final List<String> items = new ArrayList<String>() {
            private static final long serialVersionUID = 1L;
            {
                this.add("red");
                this.add("yellow");
                this.add("green");
            }
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items);
        listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(final PullToRefreshBase<ListView> refreshView) {
                // リスト更新処理。一般的には通信処理や、データベースアクセスをする為別スレッドで実行する。
                // ここではHandlerを使って1秒後にリストを更新する様にしている。
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        items.add("No." + (items.size() + 1));
                        refreshView.onRefreshComplete();
                    }
                }, 1000);
            }
        });
        listView.setAdapter(adapter);
    }
}
