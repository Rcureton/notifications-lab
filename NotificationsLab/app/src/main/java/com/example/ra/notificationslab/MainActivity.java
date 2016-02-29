package com.example.ra.notificationslab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConnectivityManager connectivityManager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 mBuilder= new NotificationCompat.Builder(MainActivity.this);
                 NotificationCompat.BigPictureStyle bigPictureStyle= new NotificationCompat.BigPictureStyle();
                mBuilder.setSmallIcon(android.R.drawable.sym_def_app_icon);


                if(networkInfo !=null && networkInfo.isConnected()){
                    Intent intent= new Intent(MainActivity.this,MainActivity.class);
                    PendingIntent pendingIntent= PendingIntent.getActivity(MainActivity.this,(int) System.currentTimeMillis(),intent,0);
                    mBuilder.setContentIntent(pendingIntent);
                    bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.connection)).build();
                    bigPictureStyle.setBigContentTitle("Connection Established!");
                    bigPictureStyle.setSummaryText("There is Connection Available!");

                    mBuilder.setAutoCancel(true);
                    mBuilder.setStyle(bigPictureStyle);
                    Notification notification= mBuilder.build();

                    NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(1, notification);
                }else{
                    Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                    PendingIntent pendingIntent= PendingIntent.getActivity(MainActivity.this,(int) System.currentTimeMillis(),intent,0);
                    mBuilder.setContentIntent(pendingIntent);
                    bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.noconnect)).build();
                    bigPictureStyle.setBigContentTitle("No Connection!");
                    bigPictureStyle.setSummaryText("There is no Connection Available!");


                    mBuilder.setStyle(bigPictureStyle);
                    Notification notification= mBuilder.build();

                    NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(2, notification);
                }


            }
        });
    }


}
