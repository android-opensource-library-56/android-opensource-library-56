package jp.mydns.sys1yagi.android.butterknifesample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;

public class ListViewActivity extends Activity {

    public static Intent createIntent(Context context) {
        return new Intent(context, ListViewActivity.class);
    }

    // 4.0.1では生成されるコードにバグがあり、そのままでは動作しません。
    // @OnItemClick(R.id.listview)
    void itemClick(AdapterView<?> adapter, View view, int pos, long id) {
        Toast.makeText(getApplicationContext(), "click:" + pos,
                Toast.LENGTH_SHORT).show();
    }
    //@OnItemLongClick(R.id.listview)
    boolean itemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
        Toast.makeText(getApplicationContext(), "long click:" + pos,
                Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ButterKnife.inject(this);

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        for (int i = 0; i < 30; i++) {
            adapter.add("item" + i);
        }
        listView.setAdapter(adapter);
    }
}