package jp.mydns.sys1yagi.android.android_support_v4_sample.rss;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yagitoshihiro
 * 
 */
public class RssList {
    List<RssItem> mItems;

    public RssList() {
        mItems = new ArrayList<RssItem>();
    }

    public void addItem(RssItem item) {
        mItems.add(item);
    }

    public List<RssItem> getItems() {
        return mItems;
    }
}