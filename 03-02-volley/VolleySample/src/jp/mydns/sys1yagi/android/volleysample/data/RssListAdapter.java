package jp.mydns.sys1yagi.android.volleysample.data;

import java.util.List;

import jp.mydns.sys1yagi.android.volleysample.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

public class RssListAdapter extends ArrayAdapter<Item> {
    private LayoutInflater mInflator = null;
    private RequestQueue mQueue = null;
    private ImageLoader mImageLoader = null;
    private List<Item> mItems;

    public RssListAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        mItems = items;
        mInflator = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mQueue, new LruImageCache());
    }

    public void remove(int position) {
        mItems.remove(position);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public TextView text;
        public ImageView entryImage;
        public TextView description;
        public ImageContainer container;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflator.inflate(R.layout.card_list, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.title);
            holder.entryImage = (ImageView) convertView
                    .findViewById(R.id.entry_image);
            holder.description = (TextView) convertView
                    .findViewById(R.id.description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Item item = getItem(position);
        holder.text.setText(item.title);
        holder.description.setText(item.description);
        if (item.imageUrl != null) {
            ImageListener listener = ImageLoader.getImageListener(
                    holder.entryImage, R.drawable.ic_launcher,
                    R.drawable.ic_launcher);
            if (holder.container != null) {
                holder.container.cancelRequest();
            }
            holder.container = mImageLoader.get(item.imageUrl, listener);
            holder.entryImage.setVisibility(View.VISIBLE);
        } else {
            holder.entryImage.setVisibility(View.GONE);
        }

        return convertView;
    }

    private static class LruImageCache implements ImageCache {
        private LruCache<String, Bitmap> mCache;

        public LruImageCache() {
            // 5MBのキャッシュ
            int cacheSize = 5 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }

}
