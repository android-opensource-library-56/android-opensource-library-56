package jp.mydns.sys1yagi.android.androiduniversalimageloadersample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.SearchParameters;

public class PhotoFragment extends ListFragment {
    private final static String TAG = PhotoFragment.class.getSimpleName();
    private final static String API_KEY = "YOUR_FLICKR_API_KEY";
    private final static String API_SECRET = "YOUR_FLOCKR_API_SECRET";
    private AsyncTask<Void, Void, PhotoList> mTask;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTask = new AsyncTask<Void, Void, PhotoList>() {
            protected PhotoList doInBackground(Void... params) {
                Log.d(TAG, "doInBackground");

                final Flickr f = new Flickr(API_KEY, API_SECRET);

                SearchParameters param = new SearchParameters();
                param.setText("coffee");

                try {
                    return f.getPhotosInterface().search(param, 20, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            };

            protected void onPostExecute(PhotoList photolist) {
                if (photolist == null) {
                    setEmptyText("load failed.");
                } else {
                    ImageAdapter adapter = new ImageAdapter(getActivity(), photolist);
                    setListAdapter(adapter);
                }
            };
        }.execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mTask != null) {
            mTask.cancel(true);
        }
    }
}
