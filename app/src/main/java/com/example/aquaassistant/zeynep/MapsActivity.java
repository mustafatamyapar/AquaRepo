package com.example.aquaassistant.zeynep;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import com.example.aquaassistant.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_AZURE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_VIOLET;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    static SQLiteDatabase favouritesDatabase;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        mMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }
            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        //get the permission to get the location info
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            System.out.println(lastLocation);
            if ( lastLocation != null) {
                LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                //show the user's location with marker and move camera to location
                mMap.addMarker(new MarkerOptions().title("You are here!").position(userLastLocation).icon(BitmapDescriptorFactory.defaultMarker(HUE_VIOLET)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation, 15));
            }
        }

        intent = getIntent();
        //if the user enters the camera to see the favourite place
        if( intent != null && intent.getStringExtra("condition") != null)
            if (intent.getStringExtra("condition").matches("show fav")) {
                if (intent.getStringExtra("placeName") != null) {
                    String place = intent.getStringExtra("placeName");
                    //get the place info from the database
                    Cursor cursor = favouritesDatabase.rawQuery("SELECT * FROM places WHERE name = ? ", new String[]{place});
                    double latitudeDouble = 0.0;
                    double longitudeDouble = 0.0;
                    while (cursor.moveToNext()) {
                        latitudeDouble = Double.parseDouble(cursor.getString(cursor.getColumnIndex("latitude")));
                        longitudeDouble = Double.parseDouble(cursor.getString(cursor.getColumnIndex("longitude")));
                    }
                    cursor.close();
                    //add marker to fav place
                    LatLng favLatLng = new LatLng(latitudeDouble, longitudeDouble);
                    mMap.addMarker(new MarkerOptions().title("Your Favourite Place").position(favLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.heart)));
                }
            }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && requestCode == 1) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                System.out.println(lastLocation);
                if (lastLocation != null) {
                    LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                    System.out.println(lastUserLocation);
                    mMap.addMarker(new MarkerOptions().title("You are here!").position(lastUserLocation).icon(BitmapDescriptorFactory.defaultMarker(HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15));

                    intent = getIntent();
                    if( intent != null && intent.getStringExtra("condition") != null) {
                        if (intent.getStringExtra("condition").matches("show fav")) {
                            if (intent.getStringExtra("placeName") != null) {
                                String place = intent.getStringExtra("placeName");
                                Cursor cursor = favouritesDatabase.rawQuery("SELECT * FROM places WHERE name = ? ", new String[]{place});
                                double latitudeDouble = 0.0;
                                double longitudeDouble = 0.0;
                                while (cursor.moveToNext()) {
                                    latitudeDouble = Double.parseDouble(cursor.getString(cursor.getColumnIndex("latitude")));
                                    longitudeDouble = Double.parseDouble(cursor.getString(cursor.getColumnIndex("longitude")));
                                }
                                cursor.close();
                                LatLng favLatLng = new LatLng(latitudeDouble, longitudeDouble);
                                mMap.addMarker(new MarkerOptions().title("Your Favourite Place").position(favLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.heart)));
                            }
                        }
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
        intent = getIntent();
        //if user adds a favourite places lo list
        if (intent.getStringExtra("condition").matches("add fav")) {
            String address = "";
            List<Address> addressList;
            try {
                //get the location info
                addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                if (addressList != null && addressList.size() != 0) {
                    if (addressList.get(0).getThoroughfare() != null) {
                        address = addressList.get(0).getThoroughfare();
                    }
                }

                //put the location info into the database
                favouritesDatabase = MapsActivity.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
                favouritesDatabase.execSQL("CREATE TABLE IF NOT EXISTS places (id INTEGER PRIMARY KEY , name VARCHAR , latitude VARCHAR , longitude VARCHAR)");

                final String latit = String.valueOf(latLng.latitude);
                final String longit = String.valueOf(latLng.longitude);

                String sqlStatement = "INSERT INTO places (name, latitude, longitude) VALUES ( ?,?,? )";
                SQLiteStatement addStatement = favouritesDatabase.compileStatement(sqlStatement);
                addStatement.bindString(1, address);
                addStatement.bindString(2, latit);
                addStatement.bindString(3, longit);
                addStatement.execute();

                //add the favourite places to places array
                Cursor cursor = favouritesDatabase.rawQuery("SELECT * FROM places", null);
                while (cursor.moveToPosition(FavouritePlacesActivity.placeNames.size())){
                    FavouritePlacesActivity.placeNames.add(cursor.getString(cursor.getColumnIndex("name")));
                }
                cursor.close();
                FavouritePlacesActivity.arrayAdapter.notifyDataSetChanged();

                //add marker to fav place
                mMap.addMarker((new MarkerOptions().title("Your Favourite Place").position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.heart))));
                Toast.makeText(MapsActivity.this, "Added to favourites", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if ( intent.getStringExtra("condition").matches("show fav")){
            Toast.makeText(MapsActivity.this, "There is already a favourite place!",Toast.LENGTH_LONG).show();
        }
    }
}
