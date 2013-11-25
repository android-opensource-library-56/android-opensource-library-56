package jp.mydns.sys1yagi.android.volleysample.data;

import java.util.ArrayList;
import java.util.List;

public class RssList {

    public List<Item> mItems;

    public RssList() {
        mItems = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        mItems.add(item);
    }
}
