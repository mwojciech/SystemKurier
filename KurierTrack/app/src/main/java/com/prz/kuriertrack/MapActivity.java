package com.prz.kuriertrack;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.prz.kuriertrack.Database.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Aktywnosc glowna
 * */
public class MapActivity extends ActionBarActivity {

    private GoogleMap googleMap;
    private DatabaseAdapter dbAdapter;
    /**
     * onCreate
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        dbAdapter = new DatabaseAdapter(getApplicationContext());
        initilizeMap(); // wczytanie mapy

        zadanie1();     //zad1
        zadanie2();     //zad2
    }

    /**
     * onResume
     * */
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    /**
     * metoda odpowiedzialna za ladowanie mapy.
     */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    private void zadanie1() {
        // zmiana typu mapy
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //ZADANIE 2
    ////////////////////////////////////////////////////////////////////////////////////////////////


    class Data {
        //szerokosc i dlugosc geograficzna
        float lat;
        float lng;
        String title;   //tytul
        String snippet; //opis

        public Data(float lng, float lat, String title, String snippet) {
            super();
            this.lat = (float) lat;
            this.lng = (float) lng;
            this.title = title;
            this.snippet = snippet;
        }
    }

    final LatLng RZESZOW = new LatLng(50.0217757, 21.98317051);


    Data[] data = {
            /*new Data(21.98372304f, 50.02587011f, "Politechnika Rzeszowska budynek D",
                    "Katedra Automatyki i Informatyki"),
            new Data(21.98539674f, 50.02687987f, "A",
                    "Politechnika Rzeszowska budynek A"),
            new Data(21.98902845f, 50.01911484f, "V",
                    "Politechnika Rzeszowska budynek V"),
            new Data(21.98263407f, 50.02016955f, "Miasteczko akademickie",
                    "Politechnika Rzeszowska"),*/
    };



    private void zadanie2() {
        // ustawienie kamery
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(RZESZOW)
                .zoom(15)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        ArrayList<String> list = new ArrayList<String>();
        list = getIntent().getExtras().getStringArrayList("lista");
        Log.d("Wojciech", "  " + getLocationFromAddress(getApplicationContext(), list.get(0)));

        /*//dodanie znacznikow
        for (Data d : data) {
            LatLng location = new LatLng(d.lat, d.lng);
            googleMap.addMarker(new MarkerOptions().position(location)
                    .title(d.title)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))        //ustawienie ikony
                    .snippet(d.snippet));                                               //ustawienie opisu
        }*/
        for(int i=0; i < list.size(); i++){
            LatLng location = getLocationFromAddress(getApplicationContext(), list.get(i));
            googleMap.addMarker(new MarkerOptions().position(location)
                    .title(list.get(i)));       //ustawienie ikony

        }
    }




    private void openSearch() {
        Uri gmmIntentUri = Uri.parse("geo:50.03853024, 21.99795485?z=10&q=restaurants");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }


    private void openNavi() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=politechnika+rzeszowska,+rzeszow");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }


}
