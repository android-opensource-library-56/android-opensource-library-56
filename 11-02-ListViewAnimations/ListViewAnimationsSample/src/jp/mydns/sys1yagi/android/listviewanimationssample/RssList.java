package jp.mydns.sys1yagi.android.listviewanimationssample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RssList {

    List<Item> mItems;

    public RssList() {
        mItems = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        mItems.add(item);
    }

    static class Feed implements Serializable{
        String title;
        String url;

        public Feed(String title, String url) {
            this.title = title;
            this.url = url;
        }
    }

    /**
     * フィード内の各エントリを表す
     * @author yagitoshihiro
     *
     */
    static class Item {
        String title;
        String url;
        String description;
        String imageUrl;

        public Item() {
            this.title = null;
            this.url = null;
            this.description = null;
            this.imageUrl = null;
        }
    }
}
