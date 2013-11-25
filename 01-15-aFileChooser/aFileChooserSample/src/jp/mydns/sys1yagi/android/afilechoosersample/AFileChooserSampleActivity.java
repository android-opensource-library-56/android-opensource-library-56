package jp.mydns.sys1yagi.android.afilechoosersample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ipaulpro.afilechooser.FileChooserActivity;

public class AFileChooserSampleActivity extends Activity {
    private final static String TAG = AFileChooserSampleActivity.class
            .getSimpleName();
    private final static int FILE_CHOOSE = 0x0111;

    public AFileChooserSampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afile_chooser_sample);
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This(), FileChooserActivity.class);
                // 汎用的なファイル選択を呼び出す場合
                // Intent getContentIntent = FileUtils.createGetContentIntent();
                // getContentIntent.setType("image/*");
                // Intent intent = Intent.createChooser(getContentIntent,
                // "Select Image File.");
                startActivityForResult(intent, FILE_CHOOSE);
            }
        });
    }

    private boolean isImageFile(String path) {
        if (path == null) {
            return false;
        }
        path = path.toLowerCase();
        if (path.endsWith(".jpg")) {
            return true;
        }
        if (path.endsWith(".png")) {
            return true;
        }
        if (path.endsWith(".gif")) {
            return true;
        }
        return false;
    }

    private void fileChoosed(int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (resultCode == RESULT_OK) {
            String path = data.getDataString();
            Log.d(TAG, "path:" + path);
            if (isImageFile(path)) {
                ((ImageView) findViewById(R.id.image)).setImageURI(Uri
                        .parse(path));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case FILE_CHOOSE:
            fileChoosed(resultCode, data);
            break;
        }
    }
}
