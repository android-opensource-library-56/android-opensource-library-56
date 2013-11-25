package jp.mydns.sys1yagi.android.dom4jsample;

import java.util.List;

import jp.mydns.sys1yagi.android.dom4jsample.RssList.Feed;
import jp.mydns.sys1yagi.android.dom4jsample.RssList.Item;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

class RssLoader extends AsyncTaskLoader<RssList> {
    private final static String TAG = RssLoader.class.getSimpleName();
    private Feed mFeed;
    private RssList mList;

    public RssLoader(Context context, Feed feed) {
        super(context);
        mFeed = feed;
        mList = null;
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG, "onStartLoading");
        if (mList != null) {
            Log.d(TAG, "cahced.");
            super.deliverResult(mList);
        } else {
            Log.d(TAG, "load.");
            forceLoad();
        }
    };

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    @Override
    public RssList loadInBackground() {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(this.mFeed.url);
            List<Element> list = document.selectNodes("/rss/channel/item");
            if (list != null) {
                for (Element element : list) {
                    Item item = new Item();
                    Element title = element.element("title");
                    if (title != null) {
                        item.title = title.getStringValue();
                    }
                    Element link = element.element("link");
                    if (link != null) {
                        item.url = link.getStringValue();
                    }
                    if (mList == null) {
                        mList = new RssList();
                    }
                    mList.addItem(item);
                }
            } else {
                Log.d(TAG, "not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mList;
    }

}
