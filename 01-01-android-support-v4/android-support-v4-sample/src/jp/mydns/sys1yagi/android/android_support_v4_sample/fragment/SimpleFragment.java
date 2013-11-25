/**
 * 
 */
package jp.mydns.sys1yagi.android.android_support_v4_sample.fragment;

import jp.mydns.sys1agi.android.android_support_v4_sample.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author yagitoshihiro
 */
public class SimpleFragment extends Fragment {

    public static final String PAGE_NUMBER = "page_number";

    public static SimpleFragment newInstance(){
        return new SimpleFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Page" + getArguments().getInt(PAGE_NUMBER));
        return view;
    }
}
