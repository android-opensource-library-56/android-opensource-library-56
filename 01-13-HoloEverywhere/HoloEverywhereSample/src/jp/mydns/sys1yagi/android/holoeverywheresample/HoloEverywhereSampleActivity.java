package jp.mydns.sys1yagi.android.holoeverywheresample;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.datetimepicker.time.RadialPickerLayout;
import org.holoeverywhere.widget.datetimepicker.time.TimePickerDialog;
import org.holoeverywhere.widget.datetimepicker.time.TimePickerDialog.OnTimeSetListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class HoloEverywhereSampleActivity extends Activity {

    private HoloEverywhereSampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holo_everywhere_sample);
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.newInstance(new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view,
                            int hourOfDay, int minute) {
                    }
                }, 12, 34, true).show(This());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.holo_everywhere_sample, menu);

        return true;
    }
}
