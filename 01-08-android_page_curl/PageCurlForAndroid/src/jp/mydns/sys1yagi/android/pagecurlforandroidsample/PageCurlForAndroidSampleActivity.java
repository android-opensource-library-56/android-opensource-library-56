package jp.mydns.sys1yagi.android.pagecurlforandroidsample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import fi.harism.curl.CurlPage;
import fi.harism.curl.CurlView;
import fi.harism.curl.CurlView.SizeChangedObserver;

public class PageCurlForAndroidSampleActivity extends Activity {
    private CurlView mCurlView;
    private int mViewMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_curl_for_android_sample);
        mViewMode = CurlView.SHOW_ONE_PAGE;
        mCurlView = (CurlView) findViewById(R.id.curl);
        mCurlView.setPageProvider(new PageProvider());
        mCurlView.setSizeChangedObserver(new SizeChangedObserver() {
            @Override
            public void onSizeChanged(int w, int h) {
                if (w > h) {
                    mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
                    mCurlView.setMargins(0f, 0f, 0f, 0f);
                    mViewMode = CurlView.SHOW_TWO_PAGES;
                } else {
                    mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
                    mCurlView.setMargins(0f, 0f, 0f, 0f);
                    mViewMode = CurlView.SHOW_ONE_PAGE;
                }
            }
        });
        mCurlView.setCurrentIndex(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCurlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurlView.onResume();
    }

    /**
     * Bitmap provider.
     */
    private class PageProvider implements CurlView.PageProvider {
        private int[] mBitmapIds = { R.drawable.page01, R.drawable.page02,
                R.drawable.page03 };
        private int mPages = 3;

        @Override
        public int getPageCount() {
            if (mViewMode == CurlView.SHOW_ONE_PAGE) {
                return mPages;
            } else {
                return (mPages / 2) + 1;
            }
        }

        private Bitmap loadBitmap(int width, int height, int index) {
            Bitmap b = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            b.eraseColor(0xFFFFFFFF);
            Canvas c = new Canvas(b);
            Drawable d = getResources().getDrawable(mBitmapIds[index]);

            int margin = 7;
            int border = 3;
            Rect r = new Rect(margin, margin, width - margin, height - margin);

            int imageWidth = r.width() - (border * 2);
            int imageHeight = imageWidth * d.getIntrinsicHeight()
                    / d.getIntrinsicWidth();
            if (imageHeight > r.height() - (border * 2)) {
                imageHeight = r.height() - (border * 2);
                imageWidth = imageHeight * d.getIntrinsicWidth()
                        / d.getIntrinsicHeight();
            }

            r.left += ((r.width() - imageWidth) / 2) - border;
            r.right = r.left + imageWidth + border + border;
            r.top += ((r.height() - imageHeight) / 2) - border;
            r.bottom = r.top + imageHeight + border + border;

            Paint p = new Paint();
            p.setColor(0xFFC0C0C0);
            c.drawRect(r, p);
            r.left += border;
            r.right -= border;
            r.top += border;
            r.bottom -= border;

            d.setBounds(r);
            d.draw(c);

            return b;
        }

        @Override
        public void updatePage(CurlPage page, int width, int height, int index) {
            if (mViewMode == CurlView.SHOW_ONE_PAGE) {
                Bitmap bitmap = loadBitmap(width, height, index);
                page.setTexture(bitmap, CurlPage.SIDE_FRONT);
                page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
            } else {
                int i = index * 2;
                if (i < mPages) {
                    Bitmap bitmap = loadBitmap(width, height, i);
                    page.setTexture(bitmap, CurlPage.SIDE_FRONT);
                }
                if (i + 1 < mPages) {
                    Bitmap bitmap = loadBitmap(width, height, i + 1);
                    Matrix matrix = new Matrix();
                    matrix.preScale(-1, 1);
                    Bitmap invertedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                            bitmap.getWidth(), bitmap.getHeight(), matrix,
                            false);
                    page.setTexture(invertedBitmap, CurlPage.SIDE_BACK);
                    bitmap.recycle();
                }
            }
        }
    }
}
