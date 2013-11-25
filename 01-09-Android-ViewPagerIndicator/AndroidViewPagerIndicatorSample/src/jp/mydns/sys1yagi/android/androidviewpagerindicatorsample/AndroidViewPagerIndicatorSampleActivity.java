package jp.mydns.sys1yagi.android.androidviewpagerindicatorsample;

import jp.mydns.sys1yagi.android.androidviewpagerindicatorsample.ItemFragment.OnFragmentInteractionListener;
import jp.mydns.sys1yagi.android.androidviewpagerindicatorsample.dummy.Items;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AndroidViewPagerIndicatorSampleActivity extends FragmentActivity
        implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(int id) {
        Intent intent = null;
        switch (id) {
        case Items.TITLE_PAGEINDICATOR:
            intent = new Intent(this, TitlePageIndicatorActivity.class);
            break;
        case Items.CIRCLE_PAGEINDICATOR:
            intent = new Intent(this, CirclePageIndicatorActivity.class);
            break;
        case Items.ICON_PAGEINDICATOR:
            intent = new Intent(this, IconPageIndicatorActivity.class);
            break;
        case Items.TAB_PAGEINDICATOR:
            intent = new Intent(this, TabPageIndicatorActivity.class);
            break;
        case Items.LINE_PAGEINDICATOR:
            intent = new Intent(this, LinePageIndicatorActivity.class);
            break;
        case Items.UNDERLINE_PAGEINDICATOR:
            intent = new Intent(this, UnderlinePageIndicatorActivity.class);
            break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
