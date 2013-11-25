package jp.mydns.sys1yagi.android.polaris2sample;

import java.util.Random;

import com.cyrilmottier.polaris2.maps.CameraUpdate;
import com.cyrilmottier.polaris2.maps.CameraUpdateFactory;
import com.cyrilmottier.polaris2.maps.GoogleMap;
import com.cyrilmottier.polaris2.maps.SupportMapFragment;
import com.cyrilmottier.polaris2.maps.model.CameraPosition;
import com.cyrilmottier.polaris2.maps.model.LatLng;
import com.cyrilmottier.polaris2.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

public class Polaris2SampleActivity extends FragmentActivity {

    private GoogleMap mMap;
    private MarkerOptions mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polaris2_sample);

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                randomPin();
            }
        });
    }

    private void randomPin() {
        if (mMarker == null) {
            LatLng latLng = new LatLng(35.681382, 139.766084);
            mMarker = new MarkerOptions().position(latLng).title("東京駅");
            CameraPosition position = CameraPosition.builder().target(latLng)
                    .zoom(14.0f).build();
            CameraUpdate update = CameraUpdateFactory
                    .newCameraPosition(position);
            getMap().addMarker(mMarker);
            getMap().moveCamera(update);
        } else {
            Random random = new Random(System.currentTimeMillis());
            double lat = random.nextDouble() * 0.3f;
            double lon = random.nextDouble() * 0.3f;

            lat = random.nextBoolean() ? lat : -lat;
            lon = random.nextBoolean() ? lon : -lon;

            // マーカの更新
            LatLng latLng = new LatLng(mMarker.getPosition().latitude + lat,
                    mMarker.getPosition().longitude + lon);
            mMarker.reset();
            mMarker.position(latLng).title(
                    latLng.latitude + "," + latLng.longitude);

            // カメラの移動
            CameraPosition position = CameraPosition.builder().target(latLng)
                    .zoom(14.0f).build();
            CameraUpdate update = CameraUpdateFactory
                    .newCameraPosition(position);

            // マップを更新
            getMap().clear();
            getMap().addMarker(mMarker);
            getMap().animateCamera(update);
        }
    }

    private GoogleMap getMap() {
        if (mMap == null) {
            FragmentManager fm = getSupportFragmentManager();
            SupportMapFragment smf = ((SupportMapFragment) fm
                    .findFragmentById(R.id.map));
            mMap = smf.getPolarisMap();
        }
        return mMap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        randomPin();
    }
}
