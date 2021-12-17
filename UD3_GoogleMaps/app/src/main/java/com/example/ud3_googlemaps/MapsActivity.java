package com.example.ud3_googlemaps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ud3_googlemaps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LatLng actual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
        while ((ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)){

        }
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager locat = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locat.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        actual = new LatLng(location.getAltitude(), location.getLatitude());
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

        // Add a marker in Huesca and move the camera
        LatLng huesca = new LatLng(42.132042201654706, -0.40689173576569454);
        mMap.addMarker(new MarkerOptions().position(huesca).title("Marker in Huesca"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(huesca, 15));
        //añadimos marcador en Zaragoza
        LatLng zaragoza = new LatLng(41.64847922722495, -0.892578295818866);
        mMap.addMarker(new MarkerOptions().position(zaragoza).title("Marker in Zaragoza"));

        LatLng lerida = new LatLng(41.617700624999216, 0.619996924141952);
        mMap.addMarker(new MarkerOptions().position(lerida).title("Marker in Lérida"));

        //Establecer zoom minimo y maximo
        mMap.setMinZoomPreference(5);
        mMap.setMaxZoomPreference(20);

        //Añadir circulo
        CircleOptions opciones = new CircleOptions();
        opciones.fillColor(Color.CYAN);
        opciones.strokeColor(R.color.teal_200);
        opciones.center(huesca);
        opciones.radius(4000);
        mMap.addCircle(opciones);

        //Añadir polígono
        LatLng[] array = {huesca, zaragoza, lerida};
        PolygonOptions opc = new PolygonOptions();
        opc.add(array);
        opc.strokeColor(Color.GREEN);
        opc.fillColor(Color.TRANSPARENT);
        mMap.addPolygon(opc);

        mMap.addMarker(new MarkerOptions().position(actual).title("Posición actual"));


    }
}