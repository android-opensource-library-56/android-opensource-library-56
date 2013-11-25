package jp.mydns.sys1yagi.android.robotiumsample.test;

import jp.mydns.sys1yagi.android.robotiumsample.EditedActivity;
import jp.mydns.sys1yagi.android.robotiumsample.R;
import jp.mydns.sys1yagi.android.robotiumsample.RobotiumSampleActivity;
import jp.mydns.sys1yagi.android.robotiumsample.SettingsActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

public class RobotiumSampleActivityTest extends
        ActivityInstrumentationTestCase2<RobotiumSampleActivity> {
    private final static String TAG = RobotiumSampleActivityTest.class
            .getSimpleName();
    private Solo solo;

    public RobotiumSampleActivityTest() {
        super(RobotiumSampleActivity.class);
    }

    public void setUp() {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testEditTextに未入力の状態でOKボタンを押下する() {
        solo.clickOnButton(0);
        solo.assertCurrentActivity("アクティビティが起動されました",
                RobotiumSampleActivity.class);
    }

    public void testEditTextに値を入力しOKボタンを押下する() {
        String inputStr = "test input";
        solo.enterText(0, inputStr);
        solo.clickOnButton(0);
        solo.assertCurrentActivity("アクティビティが起動されませんでした", EditedActivity.class);
        TextView textView = (TextView) solo.getCurrentActivity().findViewById(
                R.id.text);
        solo.takeScreenshot();
        assertNotNull(textView);
        assertEquals(inputStr, textView.getText());
    }

    public void testMenuからSettingsを開く() {
        solo.clickOnMenuItem("Settings");
        solo.assertCurrentActivity("アクティビティが起動されませんでした", SettingsActivity.class);
    }

    public void testEditTextに値を入力する() {
        solo.clickOnMenuItem("Settings");
        solo.assertCurrentActivity("アクティビティが起動されませんでした", SettingsActivity.class);
    }

}
