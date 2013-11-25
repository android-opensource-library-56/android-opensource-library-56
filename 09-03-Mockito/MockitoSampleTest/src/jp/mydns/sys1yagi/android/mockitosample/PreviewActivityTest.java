package jp.mydns.sys1yagi.android.mockitosample;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.ImageView;

public class PreviewActivityTest extends ActivityUnitTestCase<PreviewActivity> {
    @Spy
    private ImageLoader mImageLoader = new ImageLoader();

    @InjectMocks
    private PreviewActivity mPreviewActivity;

    public PreviewActivityTest() {
        super(PreviewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getInstrumentation()
                .getTargetContext().getCacheDir().getPath());
    }

    @Override
    protected void setActivity(Activity testActivity) {
        super.setActivity(testActivity);
        mPreviewActivity = (PreviewActivity) testActivity;
        MockitoAnnotations.initMocks(this);
    }

    public void testActivity() {
        Intent intent = new Intent();
        String testString = "test";
        intent.putExtra(PreviewActivity.EXTRA_URL, testString);
        startActivity(intent, null, null);

        doNothing().when(mImageLoader)
                .loadImage((ImageView) any(), anyString());

        getInstrumentation().callActivityOnResume(getActivity());

        verify(mImageLoader).loadImage((ImageView) any(), eq(testString));
    }
}
