package jp.mydns.sys1yagi.android.androiduniversalimageloadersample;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * ImageAdapterクラスでUniversal Image Loader for Androidを利用しています。
 * 
 * @author yagitoshihiro
 * 
 */
public class AndroidUniversalImageLoaderSampleActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_universal_image_loader_sample);

        String uri = "image uri";
        ImageLoader.getInstance().loadImage(uri, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view,
                    FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view,
                    Bitmap loadedImage) {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

    }

}
