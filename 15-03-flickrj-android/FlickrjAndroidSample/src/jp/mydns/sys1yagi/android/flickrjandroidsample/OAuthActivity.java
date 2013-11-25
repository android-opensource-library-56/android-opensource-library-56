package jp.mydns.sys1yagi.android.flickrjandroidsample;

import java.net.URL;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.auth.Permission;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import com.googlecode.flickrjandroid.oauth.OAuthToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class OAuthActivity extends Activity {
    private OAuthActivity This() {
        return this;
    }

    private final static String PREF_NAME = "auth_cache";
    private final static String KEY_OAUTH_TOKEN = "token";
    private final static String KEY_TOKEN_SECRET = "tokenSecret";
    private final static String KEY_USER_NAME = "userName";
    private final static String KEY_USER_ID = "userId";

    private Flickr mFlickr = null;
    private OAuthToken mOAuthToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);

        mFlickr = new Flickr(Settings.API_KEY, Settings.API_SECRET);

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startOauth();
            }
        });

        findViewById(R.id.pin_code_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText pinCodeEdit = (EditText) findViewById(R.id.pin_code_edit);
                        verifyPin(pinCodeEdit.getText().toString());
                    }
                });
    }

    private void verifyPin(String pin) {
        if (mOAuthToken == null) {
            return;
        }

        new AsyncTask<String, Void, Boolean>() {
            protected Boolean doInBackground(String[] params) {
                try {
                    OAuthInterface oauthApi = mFlickr.getOAuthInterface();
                    OAuth oauth = oauthApi.getAccessToken(
                            mOAuthToken.getOauthToken(),
                            mOAuthToken.getOauthTokenSecret(), params[0]);
                    saveOAuthToken(oauth.getUser().getUsername(), oauth
                            .getUser().getId(), oauth.getToken()
                            .getOauthToken(), oauth.getToken()
                            .getOauthTokenSecret());
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            };

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    String userName = getUserName();
                    Toast.makeText(This(), "OAuth OK!:" + userName,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(This(), "OAuth Failed!", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }.execute(pin);
    }

    private void startOauth() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                try {
                    OAuthInterface oAuthInterface = mFlickr.getOAuthInterface();
                    mOAuthToken = oAuthInterface.getRequestToken(null);
                    URL oauthUrl = oAuthInterface.buildAuthenticationUrl(
                            Permission.WRITE, mOAuthToken);
                    return oauthUrl.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(result));
                    startActivity(intent);
                }
            }
        }.execute();
    }

    public void saveOAuthToken(String userName, String userId, String token,
            String tokenSecret) {
        SharedPreferences sp = getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(KEY_OAUTH_TOKEN, token);
        editor.putString(KEY_TOKEN_SECRET, tokenSecret);
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_ID, userId);
        editor.commit();
    }

    private String getUserName() {
        SharedPreferences sp = getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(KEY_USER_NAME, "");
    }
}
