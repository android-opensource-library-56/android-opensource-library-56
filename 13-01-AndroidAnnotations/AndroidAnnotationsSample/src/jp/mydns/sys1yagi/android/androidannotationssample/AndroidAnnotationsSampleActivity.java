package jp.mydns.sys1yagi.android.androidannotationssample;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.HttpsClient;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@NoTitle
@EActivity(R.layout.activity_android_annotations_sample)
public class AndroidAnnotationsSampleActivity extends Activity {

    @ViewById(R.id.load_button)
    Button mLoadButton;

    @ViewById(R.id.image)
    ImageView mImageView;

    @HttpsClient
    HttpClient mHttpClient;

    @AfterViews
    public void after() {
        // onCreateの後に呼び出されます。
    }

    @Click(R.id.load_button)
    public void onClick(View v) {
        loadImage("http://farm1.staticflickr.com/43/86898565_563dab2319_z.jpg");
        toast("load start.");
    }

    @Background
    public void loadImage(String url) {
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = mHttpClient.execute(httpget);
            if (response.getStatusLine().getStatusCode() == 200) {
                Bitmap bitmap = BitmapFactory.decodeStream(response.getEntity()
                        .getContent());
                setImage(bitmap);
            } else {
                toast("load failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            toast("load failed.");
        }
    }

    @UiThread
    void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    void setImage(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }
}