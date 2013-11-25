package jp.mydns.sys1yagi.android.actionbarsherlocksample;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class DefaultStyleActivity extends SherlockActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_style);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //通常のActivityでの呼び出し方
        //MenuInflater inflator = getMenuInflater();

        MenuInflater inflator = getSupportMenuInflater();

        inflator.inflate(R.menu.default_style, menu);
        return true;
    }

}
