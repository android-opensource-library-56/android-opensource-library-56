package jp.mydns.sys1yagi.android.evernotesdkandroidsample;

import android.content.Context;

import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteSession.EvernoteService;

public class EvernoteSessionManager {

    private static final String CONSUMER_KEY = "YOUR_EVERNOTE=CONSUMER_KEY";
    private static final String CONSUMER_SECRET = "YOUR_EVERNOTE=CONSUMER_SECRET";
    private static final EvernoteService mEvernoteService = EvernoteService.SANDBOX;
    private static EvernoteSession sEvernoteSession = null;

    public static synchronized EvernoteSession getInstance(Context context) {
        if (sEvernoteSession == null) {
            sEvernoteSession = EvernoteSession.getInstance(context,
                    CONSUMER_KEY, CONSUMER_SECRET, mEvernoteService);
        }
        return sEvernoteSession;
    }
}
