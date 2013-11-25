package jp.mydns.sys1yagi.android.festandroidsample.test;

import static jp.mydns.sys1yagi.android.festandroidsample.test.MyANDROID.assertThat;
import jp.mydns.sys1yagi.android.festandroidsample.CustomClass;
import jp.mydns.sys1yagi.android.festandroidsample.FestAndroidSampleActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class FestAndroidSampleActivityTest extends
        ActivityInstrumentationTestCase2<FestAndroidSampleActivity> {

    public FestAndroidSampleActivityTest() {

        super(FestAndroidSampleActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFest() {
        TextView view = (TextView) getActivity().findViewById(
                jp.mydns.sys1yagi.android.festandroidsample.R.id.text);
        String text = getInstrumentation()
                .getTargetContext()
                .getResources()
                .getString(
                        jp.mydns.sys1yagi.android.festandroidsample.R.string.hello_world);

        assertThat(view).isNotNull().isVisible().isNotClickable().hasText(text);
    }

    public void testCustomClass() {
        CustomClass custom = new CustomClass(10, 5);
        assertThat(custom).isNotNull().sumResult(15);
    }
}
