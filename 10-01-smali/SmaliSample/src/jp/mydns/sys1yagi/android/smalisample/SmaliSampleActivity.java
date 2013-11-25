package jp.mydns.sys1yagi.android.smalisample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SmaliSampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smali_sample);

		TextView textView = (TextView) findViewById(R.id.text);
		textView.setText("value:" + square(5));
	}

	public int square(int i) {
		return i * i;
	}

}
