package jp.mydns.sys1yagi.android.androidannotationssample;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * AndroidAnnotationsを使わない場合
 * @author Toshihiro Yagi
 *
 */
public class AndroidAnnotationsNotUseActivity extends Activity {
    private AndroidAnnotationsNotUseActivity This() {
        return this;
    }

    Button mLoadButton;
    ImageView mImageView;
    HttpClient mHttpClient;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_annotations_sample);

        mLoadButton = (Button) findViewById(R.id.load_button);
        mImageView = (ImageView) findViewById(R.id.image);
        mHttpClient = new DefaultHttpClient();
        mHandler = new Handler();
        mLoadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage("http://farm1.staticflickr.com/43/86898565_563dab2319_z.jpg");
                toast("load start.");
            }
        });
    }

    public void loadImage(String url) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    HttpGet httpget = new HttpGet(params[0]);
                    HttpResponse response = mHttpClient.execute(httpget);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        Bitmap bitmap = BitmapFactory.decodeStream(response
                                .getEntity().getContent());
                        return bitmap;
                    } else {
                        toast("load failed.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    toast("load failed.");
                }
                return null;
            }

            protected void onPostExecute(Bitmap result) {
                setImage(result);
            };
        }.execute(url);
    }

    void toast(final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(This(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setImage(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }
}
