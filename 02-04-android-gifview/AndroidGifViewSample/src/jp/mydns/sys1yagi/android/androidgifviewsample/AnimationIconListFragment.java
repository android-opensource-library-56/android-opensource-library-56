package jp.mydns.sys1yagi.android.androidgifviewsample;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class AnimationIconListFragment extends ListFragment {
    public AnimationIconListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<AnimationIconInfo> icons = new ArrayList<AnimationIconInfo>();
        Resources resource = getResources();

        try {
            Field[] fields = R.drawable.class.getFields();
            for (Field field : fields) {
                int id = field.getInt(null);
                String name = resource.getResourceEntryName(id);
                if (name.startsWith("anim_icon")) {
                    AnimationIconInfo info = new AnimationIconInfo();
                    info.title = name;
                    info.resId = id;
                    icons.add(info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AnimationIconAdapter adapter = new AnimationIconAdapter(getActivity(),
                icons);
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
