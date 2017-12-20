package com.app.rakez.winnersprit.FirebaseHandler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.app.rakez.winnersprit.EntryPointActivity;
import com.app.rakez.winnersprit.R;
import com.app.rakez.winnersprit.quiz.MainContainer;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by RAKEZ on 12/14/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static String TAG = "Data From Firebase Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Data Message Body: " + remoteMessage.getData().toString());
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData()!=null){
            sendNotification(remoteMessage.getData().get("content"), remoteMessage.getData().get("title"));
        }
        if(!remoteMessage.getData().toString().equals("{}")){
            if(remoteMessage.getData().get("reset").equals("1")){
                resetScore();
            }
        }
    }

    private void resetScore() {
        Log.d(TAG, "Data is resetting");
    }

    private void sendNotification(String messageBody, String messageTitle) {
        Intent resultIntent = new Intent(this, EntryPointActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(this)
                .setSound(soundUri)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(resultPendingIntent)
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(123, notification);
    }

}
