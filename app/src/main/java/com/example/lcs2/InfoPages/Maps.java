/*
 * CS-499
 * 3-2 Milestone Two: Enhancement One: Software Design and Engineering
 * Bourama Mangara
 * 19 July 2020
 */
package com.example.lcs2.InfoPages;


import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lcs2.FindItems;
import com.example.lcs2.Logout;
import com.example.lcs2.R;
        import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Maps extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map;

    // Storing latitude and longitude of nearest store location
    LatLng santaLuciaCoffee = new LatLng(46.715027, -122.954373);
    LatLng fiddlersCoffee = new LatLng(46.711633, -122.973132);
    LatLng  jimmieEspresso= new LatLng(46.723120, -122.971185);
    LatLng avenueEspresso = new LatLng(46.718688, -122.965046);

    ArrayList<LatLng> arrayList = new ArrayList<LatLng>(); // ArrayList to store the latitude and longitude data.
    ArrayList<String> storeName = new ArrayList<String>();// ArrayList tot store store names.
    Button logout;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Manually populating LongLt and store names arrayList.
        arrayList.add(santaLuciaCoffee);
        arrayList.add(fiddlersCoffee);
        arrayList.add(jimmieEspresso);
        arrayList.add(avenueEspresso);
        storeName.add("Santa Lucia Coffee");
        storeName.add("Fiddler's Coffee");
        storeName.add("Jimmie's Espresso");
        storeName.add("AVENUE Espresso");

        Intent intent = getIntent();
        intent.getAction();
        logout = (Button) findViewById(R.id.logOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Logout.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Maps.this.startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //Setting Ltlg bounds for centralia
        LatLngBounds centralia = new LatLngBounds(new LatLng(46.7162, -122.9543), new LatLng(46.7162, -122.9543));

        for (int i = 0; i<arrayList.size(); i++) {// Adding Marker and name of each store.

            map.addMarker(new MarkerOptions().position(arrayList.get(i)).title(storeName.get(i)));
            map.animateCamera(CameraUpdateFactory.zoomTo(30.0f));
            map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));

        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(centralia.getCenter(), 13));
    }


    public void navigateToHome(View view){// On click back to add product activity
        Intent myIntent = new Intent(Maps.this, FindItems.class);
        Maps.this.startActivity(myIntent);
        finish();
    }

}
