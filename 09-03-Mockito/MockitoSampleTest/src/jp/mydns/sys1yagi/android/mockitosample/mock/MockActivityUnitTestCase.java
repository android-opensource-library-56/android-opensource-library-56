package jp.mydns.sys1yagi.android.mockitosample.mock;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.test.ActivityUnitTestCase;
import android.test.mock.MockApplication;
import android.view.Window;

abstract public class MockActivityUnitTestCase<T extends Activity> extends
        ActivityUnitTestCase<T> {

    private Context mActivityContext;
    private Class<T> mActivityClass;
    private boolean mCreated = false;
    private boolean mAttached = false;
    private Application mApplication = null;
    private MockParent mMockParent;

    public MockActivityUnitTestCase(Class<T> activityClass) {
        super(activityClass);
        mActivityClass = activityClass;
    }

    public void setActivityContext(Context activityContext) {
        super.setActivityContext(activityContext);
        mActivityContext = activityContext;
    }

    @Override
    protected T startActivity(Intent intent, Bundle savedInstanceState,
            Object lastNonConfigurationInstance) {
        assertFalse("Activity already created", mCreated);
        if (!mAttached) {
            assertNotNull(mActivityClass);
            setActivity(null);
            T newActivity = null;
            try {
                IBinder token = null;
                if (mApplication == null) {
                    mApplication = new MockApplication();
                    setApplication(mApplication);

                }
                ComponentName cn = new ComponentName(mActivityClass
                        .getPackage().getName(), mActivityClass.getName());
                intent.setComponent(cn);
                ActivityInfo info = new ActivityInfo();
                CharSequence title = mActivityClass.getName();
                mMockParent = new MockParent();
                String id = null;

                newActivity = (T) getInstrumentation().newActivity(
                        mActivityClass, mActivityContext, token, mApplication,
                        intent, info, title, mMockParent, id,
                        lastNonConfigurationInstance);
            } catch (Exception e) {
                e.printStackTrace();
                assertNotNull(newActivity);
            }

            assertNotNull(newActivity);
            setActivity(newActivity);
            mAttached = true;
        }

        T result = getActivity();
        if (result != null) {
            // MockitoAnnotations.initMocks(this);
            getInstrumentation().callActivityOnCreate(getActivity(),
                    savedInstanceState);
            mCreated = true;
        }
        return result;
    }

    private static class MockParent extends Activity {

        public int mRequestedOrientation = 0;
        public Intent mStartedActivityIntent = null;
        public int mStartedActivityRequest = -1;
        public boolean mFinished = false;
        public int mFinishedActivityRequest = -1;

        /**
         * Implementing in the parent allows the user to call this function on
         * the tested activity.
         */
        @Override
        public void setRequestedOrientation(int requestedOrientation) {
            mRequestedOrientation = requestedOrientation;
        }

        /**
         * Implementing in the parent allows the user to call this function on
         * the tested activity.
         */
        @Override
        public int getRequestedOrientation() {
            return mRequestedOrientation;
        }

        /**
         * By returning null here, we inhibit the creation of any "container"
         * for the window.
         */
        @Override
        public Window getWindow() {
            return null;
        }

        /**
         * By defining this in the parent, we allow the tested activity to call
         * <ul>
         * <li>{@link android.app.Activity#startActivity(Intent)}</li>
         * <li>{@link android.app.Activity#startActivityForResult(Intent, int)}</li>
         * </ul>
         */
        @Override
        public void startActivityFromChild(Activity child, Intent intent,
                int requestCode) {
            mStartedActivityIntent = intent;
            mStartedActivityRequest = requestCode;
        }

        /**
         * By defining this in the parent, we allow the tested activity to call
         * <ul>
         * <li>{@link android.app.Activity#finish()}</li>
         * <li>{@link android.app.Activity#finishFromChild(Activity child)}</li>
         * </ul>
         */
        @Override
        public void finishFromChild(Activity child) {
            mFinished = true;
        }

        /**
         * By defining this in the parent, we allow the tested activity to call
         * <ul>
         * <li>{@link android.app.Activity#finishActivity(int requestCode)}</li>
         * </ul>
         */
        @Override
        public void finishActivityFromChild(Activity child, int requestCode) {
            mFinished = true;
            mFinishedActivityRequest = requestCode;
        }
    }
}
