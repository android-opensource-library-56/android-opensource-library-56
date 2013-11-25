package jp.mydns.sys1yagi.android.jsoupsample;

import jp.mydns.sys1yagi.android.jsoupsample.RssList.Feed;
import jp.mydns.sys1yagi.android.jsoupsample.RssList.Item;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class RssFragment extends ListFragment implements
        LoaderCallbacks<RssList> {
    private final static String TAG = RssFragment.class.getSimpleName();
    public static final String RSS_FEED = "rss_feed";
    private RssList mRssList;

    public static RssFragment newInstance(Feed feed) {
        RssFragment fragment = new RssFragment();
        Bundle args = new Bundle();
        args.putSerializable(RssFragment.RSS_FEED, feed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoaderManager manager = this.getLoaderManager();
        manager.initLoader(0, getArguments(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public Loader<RssList> onCreateLoader(int id, Bundle bundle) {
        Log.d(TAG, "onCreateLoader");
        return new RssLoader(getActivity(),
                (Feed) bundle.getSerializable(RSS_FEED));
    }

    @Override
    public void onLoaderReset(Loader<RssList> loader) {

    }

    @Override
    public void onLoadFinished(Loader<RssList> loader, RssList result) {
        mRssList = result;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
        for (Item item : result.mItems) {
            adapter.add(item.title);
        }
        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                if (mRssList != null && mRssList.mItems.size() > pos) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mRssList.mItems.get(pos).url));
                    startActivity(intent);
                }
            }
        });
        setListAdapter(adapter);
    }
}