package jp.mydns.sys1yagi.android.multichoiceadaptersample;

import java.util.List;
import java.util.Set;

import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.manuelpeinado.multichoiceadapter.extras.actionbarsherlock.MultiChoiceArrayAdapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

class MyArrayAdapter extends MultiChoiceArrayAdapter<String>{
    private final static String TAG = MyArrayAdapter.class.getSimpleName();
    public MyArrayAdapter(Bundle bundle, Context context, List<String> items) {
        super(bundle, context, 
                R.layout.mca__simple_list_item_checkable_1,
                android.R.id.text1, items);
    }
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        Log.d(TAG, "onActionItemClicked");
        Set<Long> checkedSet = getCheckedItems();
        for(Long checked : checkedSet){
            Log.d(TAG, "checked:" + checked);
        }
        return false;
    }
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        Log.d(TAG, "onPrepareActionMode");
        return false;
    }
}