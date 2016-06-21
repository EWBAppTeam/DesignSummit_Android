package test.com.myapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    ProgressBar mProgressBar;
    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_location);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        SupportMapFragment mapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mapFragment, "map-fragment")
                .commit();

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap pGoogleMap) {
        mGoogleMap = pGoogleMap;
        new LoadMarker().execute();
    }

    public class LoadMarker extends AsyncTask<Void, Void, List<String[]>> {
        @Override
        protected List<String[]> doInBackground(Void... params) {
            try {
                URL url = new URL("https://docs.google.com/spreadsheets/d/15vp9jdLYI7RI7oes5LyF6FFfQ4NPC0VdKzwAfWyheJ0/pub?gid=0&single=true&output=csv");

                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                List<String[]> data = new ArrayList<>();
                String str;
                int i = 0;
                while ((str = in.readLine()) != null) {
                    if (i != 0) {
                        data.add(str.split(","));
                    }
                    i++;
                }
                in.close();
                return data;
            } catch (MalformedURLException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<String[]> data) {
            super.onPostExecute(data);
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    LatLng latLng = new LatLng(Double.valueOf(data.get(i)[2]), Double.valueOf
                            (data.get(i)[3]));
                    if (i == 0) {
                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
                    }
                    mGoogleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .snippet(data.get(i)[1])
                            .title(data.get(i)[0]));
                }
            }
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
