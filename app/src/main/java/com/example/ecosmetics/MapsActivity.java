package com.example.ecosmetics;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {}
        } catch (Resources.NotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(14);
//        // Add a marker in Sydney and move the camera
//        LatLng softwarica = new LatLng(27.706253, 85.330643);
//        mMap.addMarker(new MarkerOptions().position(softwarica).title("Softwarica"));
//        float zoomLevel = 17.0f;
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(softwarica));

        LatLng dillibazar = new LatLng(27.704839,85.329228);
        MarkerOptions markerOptions1 = new MarkerOptions().position(dillibazar).title("My Skincare in dillibazar pipolbot").snippet("").icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store)));
        mMap.addMarker(markerOptions1);

        LatLng pipalbot = new LatLng(25.566170,86.898041);
        MarkerOptions markerOptions2 = new MarkerOptions().position(pipalbot).title("My Skincare in pipalbot").snippet("").icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store)));
        mMap.addMarker(markerOptions2);

        LatLng putalisadak = new LatLng(27.703691,85.322548);
        MarkerOptions markerOptions3 = new MarkerOptions().position(putalisadak).title("My Skincare in anamnagar").snippet("").icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.store)));
        mMap.addMarker(markerOptions3);






    }
}
