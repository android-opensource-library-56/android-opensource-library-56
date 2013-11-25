package jp.mydns.sys1yagi.android.jsoupsample;

import jp.mydns.sys1yagi.android.jsoupsample.RssList.Feed;
import jp.mydns.sys1yagi.android.jsoupsample.RssList.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

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

    private void parseDomTraverse(Document document) {
        Elements elements = document.getElementsByTag("item");
        for (Element element : elements) {
            Item item = new Item();
            Elements title = element.getElementsByTag("title");
            Elements link = element.getElementsByTag("link");
            if (!title.isEmpty()) {
                item.title = title.get(0).text();
            }
            if (!link.isEmpty()) {
                item.url = link.get(0).text();
            }
            if (mList == null) {
                mList = new RssList();
            }
            mList.addItem(item);
        }
    }

    private void parseCssSelector(Document document) {
        Elements elements = document.select("item");
        for (Element element : elements) {
            Item item = new Item();
            Elements title = element.select("title");
            Elements link = element.select("link");
            if (!title.isEmpty()) {
                item.title = title.get(0).text();
            }
            if (!link.isEmpty()) {
                item.url = link.get(0).text();
            }
            if (mList == null) {
                mList = new RssList();
            }
            mList.addItem(item);
        }
    }

    @Override
    public RssList loadInBackground() {
        try {

            Document document = Jsoup.connect(this.mFeed.url)
                    .parser(Parser.xmlParser()).get();
            parseCssSelector(document);
            // parseDomTraverse(document);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mList;
    }

}
