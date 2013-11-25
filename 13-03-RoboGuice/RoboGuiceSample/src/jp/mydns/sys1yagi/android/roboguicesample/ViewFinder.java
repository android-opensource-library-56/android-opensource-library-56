package jp.mydns.sys1yagi.android.roboguicesample;

import com.google.inject.Inject;

import android.app.Activity;
import android.view.View;

public class ViewFinder {
	
	private Activity mActivity;
	
	@Inject
	public ViewFinder(Activity activity){
		mActivity = activity;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends View> T find(int id){
		return (T) mActivity.findViewById(id);
	}
}
