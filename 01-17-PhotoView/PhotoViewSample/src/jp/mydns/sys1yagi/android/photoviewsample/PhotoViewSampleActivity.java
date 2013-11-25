package jp.mydns.sys1yagi.android.photoviewsample;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;
import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

public class PhotoViewSampleActivity extends Activity {
    private final static String TAG = PhotoViewSampleActivity.class
            .getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_sample);
        // ImageViewを取り出す
        ImageView imageView = (ImageView) findViewById(R.id.photo);
        // ImageViewに画像をセットする
        imageView.setImageResource(R.drawable.photo01);
        // PhotoViewAttacherにImageViewをセットする
        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);

        attacher.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "onLonkClick");
                return false;
            }
        });
        attacher.setOnMatrixChangeListener(new OnMatrixChangedListener() {
            @Override
            public void onMatrixChanged(RectF rect) {
                Log.d(TAG, "onMatrixChanged:" + rect);
            }
        });
        attacher.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                Log.d(TAG, "onPhotoTap");
            }
        });
        attacher.setOnViewTapListener(new OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                Log.d(TAG, "onViewTap");
            }
        });
    }
}
