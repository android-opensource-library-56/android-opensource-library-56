package jp.mydns.sys1yagi.android.scribejavasample;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ScribeJavaSampleActivity extends Activity {

    private ScribeJavaSampleActivity This() {
        return this;
    }

    private final static String CONSUMER_KEY = "YOUR_TWITTER_COMSUMER_KEY";
    private final static String CONSUMER_SECRET = "YOUR_TWITTER_COMSUMER_SECRET";

    private OAuthService mService;
    private Token mRequestToken;
    private Token mAccessToken;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scribe_java_sample);
        mHandler = new Handler();
        findViewById(R.id.oauth_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread() {
                            @Override
                            public void run() {
                                mService = new ServiceBuilder()
                                        .provider(TwitterApi.class)
                                        .apiKey(CONSUMER_KEY)
                                        .apiSecret(CONSUMER_SECRET)
                                        .callback("oob").build();
                                mRequestToken = mService.getRequestToken();
                                String authURL = mService
                                        .getAuthorizationUrl(mRequestToken);
                                Intent intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(authURL));
                                startActivity(intent);
                            }
                        }.start();
                    }
                });
        findViewById(R.id.pin_code_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mService == null) {
                            return;
                        }
                        final String pin = ((EditText) findViewById(R.id.pin_code))
                                .getText().toString();
                        new Thread() {
                            public void run() {
                                mAccessToken = mService.getAccessToken(
                                        mRequestToken, new Verifier(pin));
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mAccessToken != null) {
                                            Toast.makeText(This(),
                                                    "OAuth success!",
                                                    Toast.LENGTH_SHORT).show();
                                            findViewById(R.id.tweet_button)
                                                    .setEnabled(true);
                                        }
                                    }
                                });
                            }
                        }.start();
                    }
                });

        findViewById(R.id.tweet_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tweet();
                    }
                });
    }

    private void tweet() {
        final Button button = (Button) findViewById(R.id.tweet_button);
        button.setEnabled(false);
        new Thread() {
            public void run() {
                OAuthRequest request = new OAuthRequest(Verb.POST,
                        "https://api.twitter.com/1.1/statuses/update.json");
                request.addBodyParameter("status", "hello scribe java!");
                mService.signRequest(mAccessToken, request);
                final Response response = request.send();
                final int status = response.getCode();
                final String body = response.getBody();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (status == 200) {
                            Toast.makeText(This(), "tweet success!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(This(), "tweet failed!:" + body,
                                    Toast.LENGTH_SHORT).show();
                        }
                        button.setEnabled(true);
                    }
                });
            };
        }.start();
    }
}
