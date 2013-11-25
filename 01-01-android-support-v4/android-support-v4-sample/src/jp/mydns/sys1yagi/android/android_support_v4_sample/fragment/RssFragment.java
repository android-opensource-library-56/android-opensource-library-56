package jp.mydns.sys1yagi.android.android_support_v4_sample.fragment;

import jp.mydns.sys1yagi.android.android_support_v4_sample.loader.RssLoader;
import jp.mydns.sys1yagi.android.android_support_v4_sample.rss.RssFeed;
import jp.mydns.sys1yagi.android.android_support_v4_sample.rss.RssItem;
import jp.mydns.sys1yagi.android.android_support_v4_sample.rss.RssList;
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

    private final static int LOADER_ID = 1;

    public static final String RSS_FEED = "rss_feed";
    private RssList mRssList;
    private RssLoader mLoader;

    public static RssFragment newInstance(RssFeed rssFeed) {
        RssFragment fragment = new RssFragment();
        Bundle args = new Bundle();
        args.putSerializable(RSS_FEED, rssFeed);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        LoaderManager manager = this.getLoaderManager();
        manager.initLoader(LOADER_ID, getArguments(), this);
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
        if (mLoader == null) {
            Log.d(TAG, "onCreateLoader init");
            mLoader = new RssLoader(getActivity(),
                    (RssFeed) bundle.getSerializable(RSS_FEED));
        }
        return mLoader;
    }

    @Override
    public void onLoaderReset(Loader<RssList> loader) {

    }

    @Override
    public void onLoadFinished(Loader<RssList> loader, RssList result) {
        Log.d(TAG, "onLoadFinished");
        if (getListAdapter() == null) {
            Log.d(TAG, "onLoadFinished init");
            mRssList = result;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1);
            for (RssItem item : result.getItems()) {
                adapter.add(item.getTitle());
            }
            getListView().setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View view,
                        int pos, long id) {
                    if (mRssList != null && mRssList.getItems().size() > pos) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(mRssList.getItems().get(pos)
                                .getUrl()));
                        startActivity(intent);
                    }
                }
            });
            setListAdapter(adapter);
        }
    }

}
