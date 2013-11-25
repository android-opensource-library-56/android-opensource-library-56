package jp.mydns.sys1yagi.android.robotiumsample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EditedActivity extends Activity {

    public static final String EXTRAS_TEXT = "text";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edited);
        
        String edited = getIntent().getStringExtra(EXTRAS_TEXT);
        ((TextView)findViewById(R.id.text)).setText(edited);
        
    }
}
