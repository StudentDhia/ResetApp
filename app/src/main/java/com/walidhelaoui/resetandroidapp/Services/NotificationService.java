package com.walidhelaoui.resetandroidapp.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import com.walidhelaoui.resetandroidapp.R;

import java.util.Calendar;

/**
 * Created by walid on 09/12/2017.
 */

public class NotificationService extends Service {

    //MainActivity mainActivity = new MainActivity();

    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId){
        // START YOUR TASKS

        //mainActivity.createNotification("Your health is pretty bad dude");
        Log.d("msg", "CA MARCHE !");
        Log.i("MyService", "SERVICE STARTEDDDDD !!!!!!!");
        System.out.println("AAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        //mainActivity.createNotification("NO RUN NOTIFICATION");
        createNotification("BACKGROUND NOTIF", intent);
        return super.onStartCommand(intent, flags, startId);
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if(hour == 5){

            createNotification("DOES THIS FINALLY WORK OR NOT ?!", intent);
        }

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
    }

    @Override
    public void onDestroy() {
        // STOP YOUR TASKS
        super.onDestroy();
    }

    private Runnable myTask = new Runnable() {
        public void run() {
            // Do something here

            System.out.println("CA MARCHE OMG OMG OMG !");

            stopSelf();
        }
    };

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }


    public void createNotification(String msg, Intent intent) {

        //Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        Notification noti = new Notification.Builder(this)
                .setContentTitle("NOTIFICATION")
                .setContentText(msg).setSmallIcon(R.drawable.notif_icone).build();
        //.setContentIntent(pIntent)
        //.addAction(R.drawable.notif_icone, "And more", pIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }
}
