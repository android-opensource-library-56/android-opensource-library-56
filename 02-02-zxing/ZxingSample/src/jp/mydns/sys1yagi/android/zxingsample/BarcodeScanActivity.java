package jp.mydns.sys1yagi.android.zxingsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BarcodeScanActivity extends Activity {

    private final static int REQUEST_SCAN = 1;

    public BarcodeScanActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scan);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(),
                        BarcodeScanCameraActivity.class);
                startActivityForResult(intent, REQUEST_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case REQUEST_SCAN:
            if (resultCode == RESULT_OK) {
                TextView textView = ((TextView) findViewById(R.id.text));
                textView.setText(data.getDataString());
            }
            break;
        }
    }

}
