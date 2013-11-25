package jp.mydns.sys1yagi.android.androidasynchttpsample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ImageLoadActivity extends Activity {
    private final static String TAG = ImageLoadActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_method);
        startLoad();
    }

    private void startLoad() {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(
                "http://farm3.staticflickr.com/2004/2249945112_caa85476ef_o.jpg",
                new BinaryHttpResponseHandler() {
                    @Override
                    public void onSuccess(byte[] binaryData) {
                        Log.d(TAG, "onSuccess");
                        Bitmap bitmap = BitmapFactory.decodeByteArray(
                                binaryData, 0, binaryData.length);
                        ImageView imageView = ((ImageView) findViewById(R.id.image));
                        imageView.setImageBitmap(bitmap);
                        imageView.setVisibility(View.VISIBLE);
                        findViewById(R.id.progress).setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
                        error.printStackTrace();
                        Log.d(TAG, "onFailure");
                    }
                });

        RequestParams param = new RequestParams();
        param.put("hpge", "fuga");

    }
}
