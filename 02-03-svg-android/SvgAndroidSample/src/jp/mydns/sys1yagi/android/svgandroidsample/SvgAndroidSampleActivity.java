package jp.mydns.sys1yagi.android.svgandroidsample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class SvgAndroidSampleActivity extends Activity implements
        OnScaleGestureListener {

    private ImageView mImageView = null;
    private Matrix mMatrix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_svg_android_sample);

        mMatrix = new Matrix();
        mImageView = (ImageView) findViewById(R.id.image);
        checkLayer(mImageView);

        SVG svg = SVGParser.getSVGFromResource(getResources(), R.raw.cute_fox);
        mImageView.setImageDrawable(svg.createPictureDrawable());
        mImageView.setImageMatrix(mMatrix);

        final ScaleGestureDetector detector = new ScaleGestureDetector(this,
                this);
        mImageView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = detector.getScaleFactor();
        mMatrix.postScale(scale, scale);
        mImageView.setImageMatrix(mMatrix);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        // 何もしない
    }

    @TargetApi(11)
    private void switchLayer(View view) {
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * AndroidManifest.xmlのactivity要素の属性にandroid:hardwareAccelerated="false"
     * を設定するか、 SVGをレンダリングするViewをこのメソッドに渡してレイヤをソフトウェアにする必要があります。
     * 
     * @param view
     */
    private void checkLayer(View view) {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            switchLayer(view);
        }
    }
}
