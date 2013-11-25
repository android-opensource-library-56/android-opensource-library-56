package jp.mydns.sys1yagi.android.volleysample.data;

/**
 * フィード内の各エントリを表す
 * @author yagitoshihiro
 *
 */
public class Item {
    public String title;
    public String url;
    public String description;
    public String imageUrl;

    public Item() {
        this.title = null;
        this.url = null;
        this.description = null;
        this.imageUrl = null;
    }
}