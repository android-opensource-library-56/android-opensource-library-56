package jp.mydns.sys1yagi.android.androidgifviewsample;

import java.util.List;

import jp.tomorrowkey.android.gifplayer.GifView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AnimationIconAdapter extends ArrayAdapter<AnimationIconInfo> {

    private LayoutInflater mInflator = null;
    private List<AnimationIconInfo> mItems;

    public AnimationIconAdapter(Context context, List<AnimationIconInfo> items) {
        super(context, 0, items);
        mItems = items;
        mInflator = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflator
                .inflate(R.layout.animation_icon_list_item, null);
        TextView text = (TextView) convertView.findViewById(R.id.title);
        GifView gifView = (GifView) convertView.findViewById(R.id.gif_view);

        AnimationIconInfo info = getItem(position);
        text.setText(info.title);
        gifView.setGif(info.resId);
        gifView.play();
        return convertView;
    }
}
