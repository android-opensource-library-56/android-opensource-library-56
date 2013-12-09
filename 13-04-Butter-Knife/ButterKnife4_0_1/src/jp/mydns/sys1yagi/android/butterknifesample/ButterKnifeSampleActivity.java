package jp.mydns.sys1yagi.android.butterknifesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChanged;
import butterknife.OnLongClick;

public class ButterKnifeSampleActivity extends Activity {
    private ButterKnifeSampleActivity This() {
        return this;
    }

    @InjectView(R.id.text)
    TextView mText;

    @InjectView(R.id.button)
    Button mButton;

    @OnClick(R.id.button)
    void onClick(Button v) {
        Toast.makeText(This(), "button clicked", Toast.LENGTH_SHORT).show();
        startActivity(ListViewActivity.createIntent(this));
    }

    @OnLongClick(R.id.button)
    boolean onLongClick(View v) {
        Toast.makeText(This(), "long clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @OnCheckedChanged(R.id.check_box)
    void checkedChanged(boolean isChecked) {
        Toast.makeText(This(), "check box " + isChecked, Toast.LENGTH_SHORT)
                .show();
    }

    @OnEditorAction(R.id.edit_text)
    boolean editorAction(EditText v, int actionId, KeyEvent event) {
        mText.setText("onEditorAction actionId=" + actionId + " keycode="
                + event.getKeyCode());
        return false;
    }

    @OnFocusChanged(R.id.edit_text)
    void focusChanged(boolean isFocused) {
        Toast.makeText(This(), "focus " + isFocused, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_sample);
        ButterKnife.inject(this);

        mText.setText("Hello Butter Knife!");
    }
}
