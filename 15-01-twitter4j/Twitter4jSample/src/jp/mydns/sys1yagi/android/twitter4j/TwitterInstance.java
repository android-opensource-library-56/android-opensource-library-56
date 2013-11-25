package jp.mydns.sys1yagi.android.twitter4j;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.content.Context;

public class TwitterInstance {
    private final static String TAG = TwitterInstance.class.getSimpleName();
    private static Twitter INSTANCE;

    public static Twitter getTwitterInstance(Context context) {
        if (INSTANCE == null) {
            Settings settings = Settings.getInstance(context);
            ConfigurationBuilder cbuilder = new ConfigurationBuilder();
            cbuilder.setOAuthConsumerKey(Settings.CONSUMER_KEY);
            cbuilder.setOAuthConsumerSecret(Settings.CONSUMER_SECRET);
            cbuilder.setOAuthAccessToken(settings.getAccessToken());
            cbuilder.setOAuthAccessTokenSecret(settings.getAccessTokenSecret());
            Configuration c = cbuilder.build();
            TwitterFactory twitterFactory = new TwitterFactory(c);
            INSTANCE = twitterFactory.getInstance();
        }
        return INSTANCE;
    }
}
