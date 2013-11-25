package jp.mydns.sys1yagi.android.datetimepickersample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;

public class DateTimePickerSampleActivity extends FragmentActivity implements
        OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker_sample);

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startDatePickerDialog();
            }
        });
    }

    private void startDatePickerDialog() {
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,
                2013, 11, 16);
        datePickerDialog.setVibrate(false);
        datePickerDialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year,
            int month, int day) {
        ((TextView) findViewById(R.id.date_text)).setText(year + "/"
                + (month + 1) + "/" + day);
    }
}
