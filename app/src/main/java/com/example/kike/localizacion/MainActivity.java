package com.example.kike.localizacion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity  implements GoogleMap.OnMapClickListener {

    private final LatLng casa = new LatLng(18.343597948551178, -100.82206587307155);
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMapType((googleMap.MAP_TYPE_NORMAL));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa, 15));

        //Mostrar / ocultar tu ubicacion
        googleMap.setMyLocationEnabled(true);
        //Mostara / ocultar los controles de Zoom
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        //Mostrar / ocultar los botones de localizacion
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        //Mostrar / ocultar icon de compas
        googleMap.getUiSettings().setCompassEnabled(true);
        //Mostrar / ocultar evento de rotar
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        //Mostrar / ocultar funcionalidad de Zoom
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        googleMap.addMarker(new MarkerOptions().position(casa).title("Mi casa").snippet("Santa teresa, I. Manuel Altamirano, # 405").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        googleMap.setOnMapClickListener(this);

    }

    public void moveCamera(View view) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(18.343597948551178, -100.82206587307155)).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void animateCamera(View view) {
        if (googleMap.getMyLocation() != null)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googleMap.getMyLocation().getLatitude(),googleMap.getMyLocation().getLongitude()),15));
            }

    public void addMarker(View view) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude)));
         }

    @Override
    public void onMapClick(LatLng puntoPulsado) {
        googleMap.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.uno) {
            googleMap.setMapType((googleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.dos) {
            googleMap.setMapType((googleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.tres) {
            googleMap.setMapType((googleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.cuatro) {
            googleMap.setMapType((googleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.cinco) {
            googleMap.setMapType((googleMap.MAP_TYPE_NONE));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
