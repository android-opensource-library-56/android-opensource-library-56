package jp.mydns.sys1yagi.android.gsonsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.Gson;

public class GsonSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_sample);
        findViewById(R.id.to_json).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toJSON();
            }
        });
        findViewById(R.id.from_json).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fromJSON();
            }
        });
        toJSON();
    }

    private void fromJSON() {
        TextView textView = (TextView) findViewById(R.id.text);
        StringBuilder newText = new StringBuilder();
        String json = textView.getText().toString();
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        if (user != null) {
            newText.append("name:" + user.name + "\n");
            newText.append("age:" + user.age + "\n");
            newText.append("address:" + user.address + "\n");
        } else {
            newText.append("object is null.");
        }
        textView.setText(newText.toString());
    }

    private void toJSON() {
        TextView textView = (TextView) findViewById(R.id.text);
        Gson gson = new Gson();
        User user = new User();
        user.name = "test";
        user.age = 25;
        user.address = "Japan";
        String json = gson.toJson(user);
        textView.setText(json);
    }
}
