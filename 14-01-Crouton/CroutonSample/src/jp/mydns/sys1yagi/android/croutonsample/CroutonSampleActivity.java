package jp.mydns.sys1yagi.android.croutonsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class CroutonSampleActivity extends Activity {
    private static final Style ENDLESS = new Style.Builder().setTextSize(30)
            .setBackgroundColorValue(Style.holoGreenLight).build();
    private static final Configuration CONFIGURATION_ENDLESS = new Configuration.Builder()
            .setDuration(Configuration.DURATION_INFINITE).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crouton_sample);

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        adapter.add("Alert Style");
        adapter.add("Info Style");
        adapter.add("Confirm Style");
        adapter.add("Does not disappear until click");
        adapter.add("Custom View");

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                switch (pos) {
                case 0:
                    alertStyle();
                    break;
                case 1:
                    infoStyle();
                    break;
                case 2:
                    confirmStyle();
                    break;
                case 3:
                    doesNotDisapear();
                    break;
                case 4:
                    customView();
                    break;
                }
            }
        });
    }

    private void alertStyle() {
        Crouton.makeText(this, "Hello Crouton!!", Style.ALERT).show();
    }

    private void infoStyle() {
        Crouton.makeText(this, "Hello Crouton!!", Style.INFO).show();
    }

    private void confirmStyle() {
        Crouton.makeText(this, "Hello Crouton!!", Style.CONFIRM).show();
    }

    private void doesNotDisapear() {
        final Crouton crouton = Crouton.makeText(this,
                "Hello Crouton!!\nClick here!", ENDLESS);

        crouton.setConfiguration(CONFIGURATION_ENDLESS)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Crouton.hide(crouton);
                    }
                }).show();
    }

    private void customView() {
        View view = getLayoutInflater().inflate(R.layout.notice_crouton, null);
        ((TextView) view.findViewById(R.id.message))
                .setText("Hello Crouton!! Let's enjoy!!");

        Configuration config = new Configuration.Builder().setDuration(
                Configuration.DURATION_INFINITE).build();

        final Crouton crouton = Crouton.make(this, view);
        crouton.setConfiguration(config);

        view.findViewById(R.id.ok_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Crouton.hide(crouton);
                    }
                });
        crouton.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
