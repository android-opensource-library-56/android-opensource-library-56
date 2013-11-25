package jp.mydns.sys1yagi.android.jsoupsample;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SanitizeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanitize);

        final EditText inputText = (EditText) findViewById(R.id.input_text);
        inputText
                .setText("<p><a href='http://example.com/' onclick='doAttack()'>Link</a></p>");
        final EditText sanitizedText = (EditText) findViewById(R.id.sanitized_text);
        findViewById(R.id.sanitize_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sanitized = Jsoup.clean(inputText.getText()
                                .toString(), Whitelist.basic());
                        sanitizedText.setText(sanitized);
                    }
                });
    }
}
