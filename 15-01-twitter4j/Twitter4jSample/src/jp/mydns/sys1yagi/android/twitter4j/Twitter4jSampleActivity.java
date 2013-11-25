package jp.mydns.sys1yagi.android.twitter4j;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Twitter4jSampleActivity extends Activity {
    private final static String TAG = Twitter4jSampleActivity.class
            .getSimpleName();

    private Twitter4jSampleActivity This() {
        return this;
    }

    private OAuthAuthorization mOauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter4j_sample);
        init();
    }

    private void init() {
        Settings settings = Settings.getInstance(this);
        if (!settings.isInitialized()) {
            Log.d(TAG, "startOAuth");
            startOAuth();
        } else {
            Twitter twitter = TwitterInstance.getTwitterInstance(this);
            if (twitter != null) {
                Log.d(TAG, "startMain");
                startMain();
            } else {
                Log.d(TAG, "expired token?");
                settings.setInitialized(false);
                startOAuth();
            }
        }
    }

    private void startOAuth() {
        new Thread() {
            public void run() {
                ConfigurationBuilder cbuilder = new ConfigurationBuilder();
                cbuilder.setOAuthConsumerKey(Settings.CONSUMER_KEY);
                cbuilder.setOAuthConsumerSecret(Settings.CONSUMER_SECRET);
                Configuration conf = cbuilder.build();
                mOauth = new OAuthAuthorization(conf);
                String authUrl = null;
                try {
                    RequestToken requestToken = mOauth
                            .getOAuthRequestToken(null);
                    authUrl = requestToken.getAuthorizationURL();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(authUrl));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            };
        }.start();
        setContentView(R.layout.login);

        findViewById(R.id.login).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ピンコード処理を書く
                EditText editText = (EditText) findViewById(R.id.pincode);
                getOAuthAccessToken(editText.getText().toString());
            }
        });
    }

    private void getOAuthAccessToken(final String pincode) {
        new Thread() {
            @Override
            public void run() {
                try {
                    AccessToken token = mOauth.getOAuthAccessToken(pincode);
                    Settings.getInstance(This()).setAccessToken(
                            token.getToken(), token.getTokenSecret());
                    Settings.getInstance(This()).setInitialized(true);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startMain();
                        }
                    });
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void startMain() {
        setContentView(R.layout.activity_twitter4j_sample);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.twitter4j_sample, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_logout:
            Settings.getInstance(This()).setInitialized(false);
            break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
