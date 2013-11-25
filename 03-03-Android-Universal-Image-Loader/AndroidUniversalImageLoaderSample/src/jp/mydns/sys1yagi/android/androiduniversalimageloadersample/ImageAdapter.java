package jp.mydns.sys1yagi.android.androiduniversalimageloadersample;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.googlecode.flickrjandroid.photos.Photo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class ImageAdapter extends ArrayAdapter<Photo> {
    private final static String TAG = ImageAdapter.class.getSimpleName();
    private LayoutInflater mInflater;

    public ImageAdapter(Context context, List<Photo> photolist) {
        super(context, -1, photolist);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.image_item, null);
        }
        Photo photo = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.setTag(photo.getMediumUrl());
        imageView.setImageBitmap(null);
        ImageLoader.getInstance().cancelDisplayTask(imageView);

        ImageLoader.getInstance().displayImage(photo.getMediumUrl(), imageView, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (view.getTag().equals(imageUri)) {
                    ((ImageView) view).setImageBitmap(loadedImage);
                }
            }
        });
        
        return convertView;
    }

}
