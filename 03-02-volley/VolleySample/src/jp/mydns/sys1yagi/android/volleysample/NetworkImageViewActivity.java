package jp.mydns.sys1yagi.android.volleysample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class NetworkImageViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_image_view);
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader loader = new ImageLoader(queue, new NoImageCache());
        String url = "http://farm6.staticflickr.com/5026/5735032857_68a332481e_z.jpg";

        NetworkImageView imageView = (NetworkImageView) findViewById(R.id.image);

        imageView.setDefaultImageResId(R.drawable.ic_launcher);
        imageView.setErrorImageResId(R.drawable.ic_launcher);
        imageView.setImageUrl(url, loader);

    }

    class NoImageCache implements ImageCache {
        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
        }
    }
}
