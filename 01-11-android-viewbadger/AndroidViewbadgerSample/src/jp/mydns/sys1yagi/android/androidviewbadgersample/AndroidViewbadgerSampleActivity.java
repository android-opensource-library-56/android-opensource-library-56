package jp.mydns.sys1yagi.android.androidviewbadgersample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;

public class AndroidViewbadgerSampleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_viewbadger_sample);

        defaultStyle();
        toggle();
        potision();
        size();
        color();
        clickable();
        incremental();
    }

    private void defaultStyle() {
        Button button = (Button) findViewById(R.id.button);
        BadgeView badge = new BadgeView(this, button);
        badge.setText("6");
        badge.show();
    }

    private void toggle() {
        Button button = (Button) findViewById(R.id.toggle);
        final BadgeView badge = new BadgeView(this, button);
        badge.setText("8");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // badge.show();
                // badge.hide();

                badge.toggle();
            }
        });
    }

    private void potision() {
        Button button = (Button) findViewById(R.id.position);
        BadgeView badge = new BadgeView(this, button);
        badge.setText("10");
        badge.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
        badge.show();
    }

    private void size() {
        Button button = (Button) findViewById(R.id.size);
        BadgeView badge = new BadgeView(this, button);
        badge.setText("update");
        badge.setTextSize(20);
        badge.show();
    }

    private void color() {
        Button button = (Button) findViewById(R.id.color);
        BadgeView badge = new BadgeView(this, button);
        badge.setText("12");
        badge.setTextColor(Color.WHITE);
        badge.setBackgroundColor(Color.BLUE);
        badge.show();
    }

    private void clickable() {
        Button button = (Button) findViewById(R.id.clickable);
        BadgeView badge = new BadgeView(this, button);
        badge.setText("click");
        badge.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "badget clicked.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "button clicked.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        badge.show();
    }

    private void incremental() {
        Button button = (Button) findViewById(R.id.incremental);
        final BadgeView badge = new BadgeView(this, button);
        badge.setText("0");
        badge.show();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                badge.increment(1);
            }
        });
    }

}
