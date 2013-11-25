package jp.mydns.sys1yagi.android.stickylistheaderssample;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class StickyListHeadersSampleActivity extends Activity {
    private StickyListHeadersSampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_list_headers_sample);

        StickyListHeadersListView listView = (StickyListHeadersListView) findViewById(R.id.list);

        listView.setDrawingListUnderStickyHeader(true);
        listView.setAreHeadersSticky(true);

        MyAdapter adapter = new MyAdapter(this);
        for (int i = 0; i < 100; i++) {
            adapter.add("item:" + i);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                Toast.makeText(This(), "pos:" + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
