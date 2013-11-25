package jp.mydns.sys1yagi.android.festandroidsample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FestAndroidSampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fest_android_sample);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.fest_android_sample, menu);
		return true;
	}

}
