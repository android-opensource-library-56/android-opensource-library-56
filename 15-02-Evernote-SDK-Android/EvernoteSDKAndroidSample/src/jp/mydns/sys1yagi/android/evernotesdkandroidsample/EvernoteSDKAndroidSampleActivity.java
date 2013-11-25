package jp.mydns.sys1yagi.android.evernotesdkandroidsample;

import jp.mydns.sys1yagi.android.evernotesdkandroidsample.MainFragment.SessionProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.evernote.client.android.EvernoteSession;

public class EvernoteSDKAndroidSampleActivity extends FragmentActivity
        implements SessionProvider {

    private EvernoteSDKAndroidSampleActivity This() {
        return this;
    }

    private static final String TAG_MAIN_FRAGMENT = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evernote_sdkandroid_sample);

        findViewById(R.id.login_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EvernoteSessionManager.getInstance(This())
                                .authenticate(This());
                    }
                });

        // TODO
        // switchUI();

    }

    private void switchUI() {
        findViewById(R.id.login_screen).setVisibility(View.GONE);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.add(R.id.fragment_container, MainFragment.newInstance(),
                TAG_MAIN_FRAGMENT);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
        case EvernoteSession.REQUEST_CODE_OAUTH:
            if (resultCode == RESULT_OK) {
                switchUI();
            } else {
                Toast.makeText(this, "OAuth失敗", Toast.LENGTH_LONG).show();
            }
            break;
        }

    }

    @Override
    public EvernoteSession getEvernoteSession() {
        return EvernoteSessionManager.getInstance(this);
    }
}
