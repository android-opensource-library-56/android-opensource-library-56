package jp.mydns.sys1yagi.android.android_support_v4_sample.rss;

import java.io.Serializable;

/**
 * 
 * @author yagitoshihiro
 *
 */
public class RssFeed implements Serializable {
    
    private static final long serialVersionUID = 7633069663409846350L;
    String mTitle;
    String mUrl;

    public RssFeed(String title, String url) {
        this.mTitle = title;
        this.mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}