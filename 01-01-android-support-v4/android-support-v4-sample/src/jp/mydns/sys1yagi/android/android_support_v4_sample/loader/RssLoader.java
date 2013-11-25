/**
 * 
 */
package jp.mydns.sys1yagi.android.android_support_v4_sample.loader;

import javax.xml.parsers.DocumentBuilderFactory;

import jp.mydns.sys1yagi.android.android_support_v4_sample.rss.RssFeed;
import jp.mydns.sys1yagi.android.android_support_v4_sample.rss.RssItem;
import jp.mydns.sys1yagi.android.android_support_v4_sample.rss.RssList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

/**
 * @author yagitoshihiro
 * 
 */
public class RssLoader extends AsyncTaskLoader<RssList> {

    private final static String TAG = RssLoader.class.getSimpleName();

    private RssFeed mFeed;
    private RssList mList;

    public RssLoader(Context context, RssFeed feed) {
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

    public void buildRssList(Node node) {
        if (node == null) {
            return;
        }
        if ("item".equals(node.getNodeName())) {
            if (node.hasChildNodes()) {
                RssItem item = new RssItem();
                NodeList list = node.getChildNodes();
                for (int i = 0; i < list.getLength(); i++) {
                    Node n = list.item(i);
                    if ("title".equals(n.getNodeName())) {
                        item.setTitle(n.getTextContent());
                    } else if ("link".equals(n.getNodeName())) {
                        item.setUrl(n.getTextContent());
                    }
                }
                if (mList == null) {
                    mList = new RssList();
                }
                mList.addItem(item);
            }
        } else {
            if (node.hasChildNodes()) {
                NodeList list = node.getChildNodes();
                for (int i = 0; i < list.getLength(); i++) {
                    buildRssList(list.item(i));
                }
            }
        }
    }

    @Override
    public RssList loadInBackground() {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(this.mFeed.getUrl());
            if (doc != null) {
                Element element = doc.getDocumentElement();
                buildRssList(element);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mList;
    }

}
