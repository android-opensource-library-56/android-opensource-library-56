package jp.myndns.sys1yagi.android.gpuimageforandroidsample;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSobelEdgeDetection;
import android.app.Activity;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class RealtimeCameraPreviewActivity extends Activity implements
        OnClickListener, OnSeekBarChangeListener {

    private GLSurfaceView mSurfaceView;
    private GPUImage mGPUImage;
    private Camera mCamera;

    private SeekBar mSeekBar = null;

    private GPUImageSepiaFilter mSepia = null;
    private GPUImageGrayscaleFilter mGray = null;
    private GPUImageSharpenFilter mSharp = null;
    private GPUImageSobelEdgeDetection mEdge = null;

    private GPUImageFilter mCurrentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_realtime_camera_preview);

        mSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view);
        mGPUImage = new GPUImage(this);
        mGPUImage.setGLSurfaceView(mSurfaceView);

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
    protected void onResume() {
        super.onResume();
        mCamera = Camera.open();
        mGPUImage.setUpCamera(mCamera, isPortrait() ? 90 : 0, false, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.setPreviewCallback(null);
        mCamera.release();
        mCamera = null;
    }

    protected boolean isPortrait() {
        return (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
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
        // mGPUImage.
        if (mCurrentFilter instanceof GPUImageSharpenFilter) {
            float level = (8.0f * progress / 100.0f) - 4.0f;
            mSharp.setSharpness(level);
            mGPUImage.setFilter(mSharp);
        } else if (mCurrentFilter instanceof GPUImageSobelEdgeDetection) {
            float level = (5.0f * progress / 100.0f);
            mEdge.setLineSize(level);
            mGPUImage.setFilter(mEdge);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.sepia:
            mGPUImage.setFilter(mSepia);
            mCurrentFilter = mSepia;
            mSeekBar.setEnabled(false);
            break;
        case R.id.gray:
            mGPUImage.setFilter(mGray);
            mCurrentFilter = mGray;
            mSeekBar.setEnabled(false);
            break;
        case R.id.sharp:
            mGPUImage.setFilter(mSharp);
            mCurrentFilter = mSharp;
            mSeekBar.setEnabled(true);
            break;
        case R.id.edge:
            mGPUImage.setFilter(mEdge);
            mCurrentFilter = mEdge;
            mSeekBar.setEnabled(true);
            break;
        }
    }

}
