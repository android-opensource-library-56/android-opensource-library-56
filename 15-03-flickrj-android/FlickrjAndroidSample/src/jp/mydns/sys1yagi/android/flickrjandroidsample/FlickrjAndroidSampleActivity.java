package jp.mydns.sys1yagi.android.flickrjandroidsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FlickrjAndroidSampleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickrj_android_sample);

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.add("Photo Search");
        adapter.add("OAuth");

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                switch (pos) {
                case 0:
                    startPhotoSearch();
                    break;
                case 1:
                    startOAuth();
                    break;
                }
            }
        });
    }

    private void startPhotoSearch() {
        Intent intent = new Intent(this, PhotoSearchActivity.class);
        startActivity(intent);
    }

    private void startOAuth() {
        Intent intent = new Intent(this, OAuthActivity.class);
        startActivity(intent);
    }
}