package jp.mydns.sys1yagi.android.volleysample.data;

import java.io.Serializable;

public class Feed implements Serializable {
    private static final long serialVersionUID = -2794426624016874973L;
    String title;
    String url;

    public Feed(String title, String url) {
        this.title = title;
        this.url = url;
    }
}