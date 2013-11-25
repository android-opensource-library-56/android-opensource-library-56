package jp.mydns.sys1yagi.android.android_support_v4_sample.fragment;

import jp.mydns.sys1yagi.android.android_support_v4_sample.LoaderActivity;
import jp.mydns.sys1yagi.android.android_support_v4_sample.ViewPagerActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemListFragment extends ListFragment {

    public static ItemListFragment newInstance() {
        return new ItemListFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
        adapter.add("ViewPager");
        adapter.add("Loader");
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                    long id) {
                Intent intent = null;
                switch (pos) {
                case 0:
                    intent = new Intent(getActivity(), ViewPagerActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), LoaderActivity.class);
                    break;
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }
}