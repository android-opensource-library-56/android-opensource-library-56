package jp.mydns.sys1yagi.android.androidviewpagerindicatorsample.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>s
 */
public class Items {
    public final static int TITLE_PAGEINDICATOR = 0;
    public final static int CIRCLE_PAGEINDICATOR = 1;
    public final static int ICON_PAGEINDICATOR = 2;
    public final static int TAB_PAGEINDICATOR = 3;
    public final static int LINE_PAGEINDICATOR = 4;
    public final static int UNDERLINE_PAGEINDICATOR = 5;
    
    /**
     * An array of sample (dummy) items.
     */
    public static List<Item> ITEMS = new ArrayList<Item>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, Item> ITEM_MAP = new HashMap<Integer, Item>();

    static {
        // Add 3 sample items.
        addItem(new Item(TITLE_PAGEINDICATOR, "Title PageIndicator"));
        addItem(new Item(CIRCLE_PAGEINDICATOR, "Circle PageIndicator"));
        addItem(new Item(ICON_PAGEINDICATOR, "Icon PageIndicator"));
        addItem(new Item(TAB_PAGEINDICATOR, "Tab PageIndicator"));
        addItem(new Item(LINE_PAGEINDICATOR, "Line PageIndicator"));
        addItem(new Item(UNDERLINE_PAGEINDICATOR, "Underline PageIndicator"));
    }

    private static void addItem(Item item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class Item {
        public int id;
        public String content;

        public Item(int id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
