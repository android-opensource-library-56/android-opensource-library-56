package jp.mydns.sys1yagi.android.flickrjandroidsample;

import java.io.IOException;

import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

public class PhotoSearchActivity extends Activity {
    private final static String TAG = PhotoSearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_search);

        new AsyncTask<Void, Void, PhotoList>() {
            @Override
            protected PhotoList doInBackground(Void... p) {
                try {
                    final Flickr flickr = new Flickr(Settings.API_KEY,
                            Settings.API_SECRET);

                    SearchParameters params = new SearchParameters();
                    params.setText("android");

                    PhotosInterface photosInterface = flickr
                            .getPhotosInterface();
                    final PhotoList photos = photosInterface.search(params, 20,
                            1);

                    return photos;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FlickrException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(PhotoList result) {
                showTextView(result);
            }
        }.execute();
    }

    private void showTextView(PhotoList photos) {
        TextView textView = (TextView) findViewById(R.id.text);
        StringBuilder sb = new StringBuilder();
        for (Photo p : photos) {
            Log.d(TAG, "p:" + p.getSmallUrl());
            sb.append(p.getSmallUrl()).append("\n");
        }
        textView.setText(sb.toString());
    }
}
