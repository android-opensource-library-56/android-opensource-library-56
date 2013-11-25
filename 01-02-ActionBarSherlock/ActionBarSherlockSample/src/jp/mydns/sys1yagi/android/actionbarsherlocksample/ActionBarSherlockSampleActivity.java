package jp.mydns.sys1yagi.android.actionbarsherlocksample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class ActionBarSherlockSampleActivity extends SherlockFragmentActivity {
    
    private Activity This() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_sherlock_sample);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.layout, new ItemList());
        transaction.commit();
    }

    class ItemList extends ListFragment {
        @Override
        public void onResume() {
            super.onResume();
            ArrayAdapter<String> adapter = 
                    new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1
            );
            adapter.add("Default Style");
            adapter.add("Tab Style");
            adapter.add("List Style");
            setListAdapter(adapter);

            ListView listView = getListView();
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                    Intent intent = null;
                    switch(pos){
                    case 0:
                        intent = new Intent(This(), DefaultStyleActivity.class);
                        break;
                    case 1:
                        intent = new Intent(This(), TabStyleActivity.class);
                        break;
                    case 2:
                        intent = new Intent(This(), ListStyleActivity.class);
                        break;
                    }
                    if(intent != null){
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
