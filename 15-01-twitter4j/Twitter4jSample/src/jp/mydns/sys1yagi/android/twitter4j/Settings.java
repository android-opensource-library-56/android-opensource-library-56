package jp.mydns.sys1yagi.android.twitter4j;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    public static final String CONSUMER_KEY = "YOUR_TWITTER_CONSUMER_KEY";
    public static final String CONSUMER_SECRET = "YOUR_TWITTER_CONSUMER_SECRET";
    
    private final static String PREFERENCE_NAME = "settings";
    private final static String KEY_INITIALIZED = "initialized";

    private final static String KEY_ACCESS_TOKEN = "access_token";
    private final static String KEY_ACCESS_TOKEN_SECRET = "access_token_secret";

    private static Settings INSTANCE = null;

    private SharedPreferences mPreference = null;

    private Settings(Context context) {
        mPreference = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public static Settings getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Settings(context);
        }
        return INSTANCE;
    }

    public boolean isInitialized() {
        return mPreference.getBoolean(KEY_INITIALIZED, false);
    }

    public void setInitialized(boolean is) {
        mPreference.edit().putBoolean(KEY_INITIALIZED, is).commit();
    }

    public String getAccessToken() {
        return mPreference.getString(KEY_ACCESS_TOKEN, null);
    }

    public String getAccessTokenSecret() {
        return mPreference.getString(KEY_ACCESS_TOKEN_SECRET, null);
    }

    public void setAccessToken(String accessToken, String accessTokenSecret) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_ACCESS_TOKEN_SECRET, accessTokenSecret);
        editor.commit();
    }
}
