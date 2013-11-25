package jp.mydns.sys1yagi.android.listviewanimationssample;

import jp.mydns.sys1yagi.android.listviewanimationssample.RssList.Feed;
import jp.mydns.sys1yagi.android.listviewanimationssample.RssList.Item;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.haarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.haarman.listviewanimations.itemmanipulation.SwipeDismissAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;

public class ListViewAnimationsSampleActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_animations_sample);
    }

    public static class CardFragment extends ListFragment implements
            LoaderCallbacks<RssList> {
        public static final String RSS_FEED = "rss_feed";
        private RssList mRssList;

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            getListView().setDivider(null);
            LoaderManager manager = getActivity().getSupportLoaderManager();
            Bundle args = new Bundle();
            Feed feed = new Feed("はてなブックマーク - 新着エントリー",
                    "http://b.hatena.ne.jp/entrylist?sort=hot&threshold=500&mode=rss");
            args.putSerializable(RSS_FEED, feed);
            manager.initLoader(0, args, this);
        }

        @Override
        public Loader<RssList> onCreateLoader(int id, Bundle bundle) {
            return new RssLoader(getActivity(),
                    (Feed) bundle.getSerializable(RSS_FEED));
        }

        @Override
        public void onLoaderReset(Loader<RssList> arg0) {
        }

        @Override
        public void onLoadFinished(Loader<RssList> loader, RssList result) {
            mRssList = result;
            ListView listView = getListView();
            final RssListAdapter adapter = new RssListAdapter(getActivity(),
                    result.mItems);
            SwipeDismissAdapter swipeDismissAdaper = new SwipeDismissAdapter(
                    adapter, new OnDismissCallback() {
                        @Override
                        public void onDismiss(AbsListView listView,
                                int[] reverseSortedPositions) {
                            for (int position : reverseSortedPositions) {
                                adapter.remove(position);
                            }
                        }
                    });

            AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(
                    swipeDismissAdaper);
            alphaInAnimationAdapter.setAbsListView(listView);
            setListAdapter(alphaInAnimationAdapter);

            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view,
                        int pos, long id) {
                    Item item = (Item) listView.getAdapter().getItem(pos);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(item.url));
                    startActivity(intent);
                }
            });
        }
    }
}
