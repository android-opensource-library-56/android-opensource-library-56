package jp.mydns.sys1yagi.newquickactionsample;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import net.londatiga.android.QuickAction.OnActionItemClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

public class NewQuickActionSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quick_action_sample);

        WebView webView = (WebView) findViewById(R.id.webview);

        webView.loadUrl("http://nodejs.org/");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        final QuickAction actions = new QuickAction(this);
        actions.addActionItem(new ActionItem(0, "twitter", getResources()
                .getDrawable(R.drawable.twitter)));
        actions.addActionItem(new ActionItem(1, "facebook", getResources()
                .getDrawable(R.drawable.facebook)));
        actions.addActionItem(new ActionItem(2, "google", getResources()
                .getDrawable(R.drawable.google)));
        actions.addActionItem(new ActionItem(3, "share", getResources()
                .getDrawable(R.drawable.share)));
        actions.setOnActionItemClickListener(new OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                Toast.makeText(getApplicationContext(), "click item:" + pos,
                        Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actions.show(v);
            }
        });
    }
}
