package jp.mydns.sys1yagi.android.listviewanimationssample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;

import jp.mydns.sys1yagi.android.listviewanimationssample.RssList.Feed;
import jp.mydns.sys1yagi.android.listviewanimationssample.RssList.Item;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

class RssLoader extends AsyncTaskLoader<RssList> {
    private final static String TAG = RssLoader.class.getSimpleName();
    private Feed mFeed;
    private RssList mList;
    private Pattern mPattern = Pattern.compile("(http://cdn-ak.b.st-hatena.com/entryimage/.*?)\"");
    
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

    public void buildRssList(Node node) {
        if (node == null) {
            return;
        }
        if ("item".equals(node.getNodeName())) {
            if (node.hasChildNodes()) {
                Item item = new Item();
                NodeList list = node.getChildNodes();
                for (int i = 0; i < list.getLength(); i++) {
                    Node n = list.item(i);
                    String name = n.getNodeName();
                    if ("title".equals(name)) {
                        item.title = n.getTextContent();
                    } else if ("link".equals(name)) {
                        item.url = n.getTextContent();
                    } else if("description".equals(name)){
                        item.description = n.getTextContent();
                    } else if("content:encoded".equals(name)){
                        Matcher matcher = mPattern.matcher(n.getTextContent());
                        if(matcher.find()){
                            item.imageUrl = matcher.group(1);
                        }
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
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mFeed.url);
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
