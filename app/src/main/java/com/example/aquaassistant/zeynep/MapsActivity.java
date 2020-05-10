package com.example.aquaassistant.zeynep;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_AZURE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_BLUE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    static SQLiteDatabase favouritesDatabase;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favourites_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == R.id.favourites_menu){
            Intent intent = new Intent( MapsActivity.this , FavouritePlacesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        System.out.println("Size" + FavouritePlacesActivity.placeNames.size());
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                SharedPreferences sharedPreferences = MapsActivity.this.getSharedPreferences("com.example.aquaassistant.zeynep",MODE_PRIVATE);
                boolean firstTimeCheck = sharedPreferences.getBoolean("notFirstTime",false);

                if (!firstTimeCheck) {
                    LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
                    sharedPreferences.edit().putBoolean("notFirstTime",true).apply();
                }
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if ( lastLocation != null) {
                LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().title("You are here!").position(userLastLocation).icon(BitmapDescriptorFactory.defaultMarker(HUE_AZURE)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation, 15));
            }
        }


        Intent intent = getIntent();
        if( intent != null && intent.getStringExtra("condition") != null) {
            if (intent.getStringExtra("condition").matches("show fav")) {
                if (intent.getStringExtra("placeName") != null) {
                    String place = intent.getStringExtra("placeName");
                    Cursor cursor = favouritesDatabase.rawQuery("SELECT * FROM places WHERE name = ? ", new String[]{place});
                    Double latitudeDouble = 0.0;
                    Double longitudeDouble = 0.0;
                    while (cursor.moveToNext()) {
                        latitudeDouble = Double.valueOf(cursor.getString(cursor.getColumnIndex("latitude")));
                        longitudeDouble = Double.valueOf(cursor.getString(cursor.getColumnIndex("longitude")));
                    }
                    LatLng favLatLng = new LatLng(latitudeDouble, longitudeDouble);
                    mMap.addMarker(new MarkerOptions().title("Your Favourite Place").position(favLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.heart)));
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && requestCode == 1) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (lastLocation != null) {
                    LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15));

                    Intent intent = getIntent();
                    if( intent != null && intent.getStringExtra("condition") != null) {
                        if (intent.getStringExtra("condition").matches("show fav")) {
                            if (intent.getStringExtra("placeName") != null) {
                                String place = intent.getStringExtra("placeName");
                                Cursor cursor = favouritesDatabase.rawQuery("SELECT * FROM places WHERE name = ? ", new String[]{place});
                                Double latitudeDouble = 0.0;
                                Double longitudeDouble = 0.0;
                                while (cursor.moveToNext()) {
                                    latitudeDouble = Double.valueOf(cursor.getString(cursor.getColumnIndex("latitude")));
                                    longitudeDouble = Double.valueOf(cursor.getString(cursor.getColumnIndex("longitude")));
                                }
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

        Intent intent = getIntent();
        if (intent.getStringExtra("condition").matches("add fav")) {
            String address = "";

            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

                if (addressList != null && addressList.size() != 0) {
                    if (addressList.get(0).getThoroughfare() != null) {
                        address = address + addressList.get(0).getThoroughfare();
                        if (addressList.get(0).getSubThoroughfare() != null) {
                            address = address + addressList.get(0).getSubThoroughfare();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                favouritesDatabase = MapsActivity.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
                favouritesDatabase.execSQL("CREATE TABLE IF NOT EXISTS places (id INTEGER PRIMARY KEY , name VARCHAR , latitude VARCHAR , longitude VARCHAR)");
            } catch (Exception e) {
                e.printStackTrace();
            }
            final String latit = String.valueOf(latLng.latitude);
            final String longit = String.valueOf(latLng.longitude);

            AlertDialog.Builder setName = new AlertDialog.Builder(MapsActivity.this);
            setName.setTitle("Name of Place");
            setName.setMessage("Would you like to name the place?");
            final EditText giveName = new EditText(MapsActivity.this);
            giveName.setInputType(InputType.TYPE_CLASS_TEXT);
            setName.setView(giveName);
            setName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String givenName = giveName.getText().toString();
                    String sqlStatement = "INSERT INTO places VALUES ( ?,?,? )";
                    SQLiteStatement addStatement = favouritesDatabase.compileStatement(sqlStatement);
                    addStatement.bindString(1, givenName);
                    addStatement.bindString(2, latit);
                    addStatement.bindString(3, longit);
                    addStatement.execute();
                    FavouritePlacesActivity.placeNames.add(givenName);
                }
            });
            final String finalAddress = address;
            setName.setNegativeButton("Add with street name", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String sqlStatement = "INSERT INTO places VALUES ( ?, ? ,?)";
                    SQLiteStatement addStatement = favouritesDatabase.compileStatement(sqlStatement);
                    addStatement.bindString(1, finalAddress);
                    addStatement.bindString(2, latit);
                    addStatement.bindString(3, longit);
                    FavouritePlacesActivity.placeNames.add(finalAddress);
                }
            });
            mMap.addMarker((new MarkerOptions().title("Your Favourite Place").position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.heart))));
            Toast.makeText(MapsActivity.this, "Added to favourites", Toast.LENGTH_LONG);
        }
        else if ( intent.getStringExtra("condition").matches("show fav")){
            Toast.makeText(MapsActivity.this, "There is already a favourite place!",Toast.LENGTH_LONG);
        }
    }
}
