package jp.mydns.sys1yagi.android.svgandroidsample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.view.View;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class SVGRenderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SVGView view = new SVGView(this);
        view.setSVGResourceId(R.raw.cute_fox);
        setContentView(view);
    }

    private class SVGView extends View {
        SVG mSvg;
        Picture mPict;

        public SVGView(Context context) {
            super(context);
            checkLayer(this);
        }

        public void setSVGResourceId(int id) {
            mSvg = SVGParser
                    .getSVGFromResource(getContext().getResources(), id);
            mPict = mSvg.getPicture();
            mPict.endRecording();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.save();
            canvas.scale(1.5f, 1.5f);
            canvas.drawPicture(mPict);
            canvas.restore();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void switchLayer(View view) {
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void checkLayer(View view) {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            switchLayer(view);
        }
    }

}
