package jp.mydns.sys1yagi.android.mockitosample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

public class ImageLoader {
    @SuppressWarnings("rawtypes")
    public void loadImage(final ImageView imageView, String imageUrl) {
        Object maybeTask = imageView.getTag();
        if (maybeTask != null) {
            if (maybeTask instanceof AsyncTask) {
                ((AsyncTask) maybeTask).cancel(true);
            }
        }
        AsyncTask<String, Void, Bitmap> task = new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    return BitmapFactory.decodeStream(new URL(params[0])
                            .openStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Bitmap result) {
                imageView.setTag(null);
                if (imageView != null && !isCancelled()) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(result);
                }
            };
        }.execute(imageUrl);
        imageView.setTag(task);
    }

    @SuppressWarnings("rawtypes")
    public void cancel(ImageView imageView) {
        Object maybeTask = imageView.getTag();
        if (maybeTask != null) {
            if (maybeTask instanceof AsyncTask) {
                ((AsyncTask) maybeTask).cancel(true);
            }
        }
    }
}
