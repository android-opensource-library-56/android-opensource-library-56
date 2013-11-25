package jp.mydns.sys1yagi.android.styleddialogssample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import eu.inmite.android.lib.dialogs.ISimpleDialogListener;
import eu.inmite.android.lib.dialogs.ProgressDialogFragment;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

public class StyledDialogsSampleActivity extends FragmentActivity implements
        ISimpleDialogListener {

    private StyledDialogsSampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styled_dialogs_sample);

        // 簡単なダイアログ
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment
                        .createBuilder(This(), getSupportFragmentManager())
                        .setTitle("Welcome!")
                        .setMessage("Hello StyledDialogs! Enjoy!").show();
            }
        });

        // プログレス
        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogFragment dialog = ProgressDialogFragment
                        .createBuilder(This(), getSupportFragmentManager())
                        .setTitle("Initialize").setMessage("loading...")
                        .setCancelable(false).show();

                // 2秒後に消す
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 2000);
            }
        });

        // コールバック
        findViewById(R.id.button3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment
                        .createBuilder(This(), getSupportFragmentManager())
                        .setTitle("Welcome!").setMessage("How are you?")
                        .setPositiveButtonText("I'm fine.")
                        .setNegativeButtonText("Not too bad.")
                        .setRequestCode(100).show();
            }
        });

        // カスタムダイアログ
        findViewById(R.id.button4).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.show(This());
            }
        });

    }

    @Override
    public void onPositiveButtonClicked(int requestCode) {
        switch (requestCode) {
        case 100:
            Toast.makeText(This(), "Positive Button Click.", Toast.LENGTH_SHORT)
                    .show();
            break;
        }
    }

    @Override
    public void onNegativeButtonClicked(int requestCode) {
        switch (requestCode) {
        case 100:
            Toast.makeText(This(), "Negative Button Click.", Toast.LENGTH_SHORT)
                    .show();
            break;
        }
    }
}
