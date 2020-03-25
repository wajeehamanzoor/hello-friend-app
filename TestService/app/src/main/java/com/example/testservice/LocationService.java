package com.example.testservice;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LocationService extends Service {

    public String lon;
    public String lat;
    private static final String TAG = "LocationService";

    //number variable

    public static String numberr;
    public static String name;


    private FusedLocationProviderClient mFusedLocationClient;
    private final static long UPDATE_INTERVAL = 5 * 1000;  /* 4 secs */
    private final static long FASTEST_INTERVAL = 2000; /* 2 sec */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Toast.makeText(getApplicationContext(),"Helooooo",Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(1, notification);

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d(TAG, "onStartCommand: called.");


        //number receive from main activity
        numberr=intent.getStringExtra("phone");
        name=intent.getStringExtra("name");

        //toast number
        Toast.makeText(getApplicationContext(),"numToast "+numberr,Toast.LENGTH_LONG).show();
        getLocation();
        return START_NOT_STICKY;
    }

    private void getLocation() {
        LocationRequest mLocationRequestHighAccuracy = new LocationRequest();
        mLocationRequestHighAccuracy.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequestHighAccuracy.setInterval(UPDATE_INTERVAL);
        mLocationRequestHighAccuracy.setFastestInterval(FASTEST_INTERVAL);


        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           // Log.d(TAG, "getLocation: stopping the location service.");
            stopSelf();
            Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequestHighAccuracy, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {

                      //  Log.d(TAG, "onLocationResult: got location result.");

                        Location location = locationResult.getLastLocation();
                        if (location != null) {
                           lon= new StringBuilder(""+location.getLongitude()).toString();
                           lat= new StringBuilder(""+location.getLatitude()).toString();


                            //toast number
                             Toast.makeText(getApplicationContext(),"numToast1 "+numberr,Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),lon,Toast.LENGTH_SHORT).show();
                            //saveUserLocation();




                            //database code to store Latitude and longitude with number key
                            DatabaseReference reff;
                            reff= FirebaseDatabase.getInstance().getReference().child("Model");
                            Modell m=new Modell();
                            m.lat=lat;
                            m.lon=lon;
                            m.name=name;

                            reff.child(numberr).setValue(m);



                        }
                    }
                },
                Looper.myLooper()); // Looper.myLooper tells this to repeat forever until thread is destroyed
    }

   /* private void saveUserLocation(){


    }*/
}