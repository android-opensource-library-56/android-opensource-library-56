package jp.mydns.sys1yagi.android.volleysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VolleySampleActivity extends Activity {

    private VolleySampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_sample);

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.add("Request");
        adapter.add("NetworkImageView");
        adapter.add("ImageLoader");
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                switch (pos) {
                case 0: {
                    Intent intent = new Intent(This(), RequestActivity.class);
                    startActivity(intent);
                    return;
                }
                case 1: {
                    Intent intent = new Intent(This(),
                            NetworkImageViewActivity.class);
                    startActivity(intent);
                    return;
                }
                case 2: {
                    Intent intent = new Intent(This(),
                            ImageLoaderSampleActivity.class);
                    startActivity(intent);
                    return;
                }

                }
            }
        });
    }

}
