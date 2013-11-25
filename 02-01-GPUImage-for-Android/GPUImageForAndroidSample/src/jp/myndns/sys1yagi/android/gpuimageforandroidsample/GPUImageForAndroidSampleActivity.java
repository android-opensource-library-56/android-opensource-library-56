package jp.myndns.sys1yagi.android.gpuimageforandroidsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GPUImageForAndroidSampleActivity extends Activity {
    private Activity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpuimage_for_android_sample);
        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        adapter.add("Image Proccessing");
        adapter.add("Realtime Camera Preview");
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                    long id) {
                Intent intent = null;
                switch (pos) {
                case 0:
                    intent = new Intent(This(), ImageProccessingActivity.class);
                    break;
                case 1:
                    intent = new Intent(This(),
                            RealtimeCameraPreviewActivity.class);
                    break;
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

}
