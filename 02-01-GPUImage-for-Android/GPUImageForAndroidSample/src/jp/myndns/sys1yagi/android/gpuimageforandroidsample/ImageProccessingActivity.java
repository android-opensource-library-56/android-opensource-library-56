package jp.myndns.sys1yagi.android.gpuimageforandroidsample;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSobelEdgeDetection;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ImageProccessingActivity extends Activity implements
        OnClickListener, OnSeekBarChangeListener {

    private GPUImageView mImageView = null;
    private SeekBar mSeekBar = null;

    private GPUImageSepiaFilter mSepia = null;
    private GPUImageGrayscaleFilter mGray = null;
    private GPUImageSharpenFilter mSharp = null;
    private GPUImageSobelEdgeDetection mEdge = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_proccessing);

        mImageView = (GPUImageView) findViewById(R.id.gpuimage);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.image);
        mImageView.setImage(bitmap);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBar.setMax(100);
        mSeekBar.setEnabled(false);
        mSeekBar.setOnSeekBarChangeListener(this);

        // セピア
        mSepia = new GPUImageSepiaFilter();
        // 白黒
        mGray = new GPUImageGrayscaleFilter();
        // シャープ
        // -4.0 to 4.0
        mSharp = new GPUImageSharpenFilter();
        // エッジ
        // 0.0 to 5.0
        mEdge = new GPUImageSobelEdgeDetection();

        findViewById(R.id.sepia).setOnClickListener(this);
        findViewById(R.id.gray).setOnClickListener(this);
        findViewById(R.id.sharp).setOnClickListener(this);
        findViewById(R.id.edge).setOnClickListener(this);

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {/* なにもしない */
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {/* なにもしない */
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
            boolean fromUser) {
        GPUImageFilter filter = mImageView.getFilter();
        if (filter instanceof GPUImageSharpenFilter) {
            float level = (8.0f * progress / 100.0f) - 4.0f;
            mSharp.setSharpness(level);
            mImageView.setFilter(mSharp);
        } else if (filter instanceof GPUImageSobelEdgeDetection) {
            float level = (5.0f * progress / 100.0f);
            mEdge.setLineSize(level);
            mImageView.setFilter(mEdge);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.sepia:
            mImageView.setFilter(mSepia);
            mSeekBar.setEnabled(false);
            break;
        case R.id.gray:
            mImageView.setFilter(mGray);
            mSeekBar.setEnabled(false);
            break;
        case R.id.sharp:
            mImageView.setFilter(mSharp);
            mSeekBar.setEnabled(true);
            break;
        case R.id.edge:
            mImageView.setFilter(mEdge);
            mSeekBar.setEnabled(true);
            break;
        }
    }
}
