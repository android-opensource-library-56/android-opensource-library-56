package jp.mydns.sys1yagi.android.multichoiceadaptersample;

import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;

public class MultiChoiceAdapterSampleActivity extends SherlockActivity {
    private final static String TAG = MultiChoiceAdapterSampleActivity.class.getSimpleName();
    public Activity This(){
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.list);
        
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "onItemClick2");
            }
        });
        
        List<String> items = Arrays.asList(
                "Hello World!","Tokyo","Nagoya","Osaka","Fukuoka"
                ,"Hello World!","Tokyo","Nagoya","Osaka","Fukuoka");
        final MyArrayAdapter adapter = new MyArrayAdapter(savedInstanceState, this, items);
        adapter.setAdapterView(listView);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //リストの要素がタップされた時に呼び出される
                Log.d(TAG, "onItemClick");
            }
        });
    }
}
