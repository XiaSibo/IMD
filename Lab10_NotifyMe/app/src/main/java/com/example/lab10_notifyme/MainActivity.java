package com.example.lab10_notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String ACTION_UPDATE_NOTIFICATION = "com.example.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CLOSE_NOTIFICATION = "com.example.notifyme.ACTION_CLOSE_NOTIFICATION";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    private Button button_notify;
    private Button button_update;
    private Button button_cancel;
    private NotificationReceiver mReceiver = new NotificationReceiver();
    private NotificationCloser mCloser = new NotificationCloser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        registerReceiver(mCloser, new IntentFilter(ACTION_CLOSE_NOTIFICATION));

        button_notify = findViewById(R.id.notify);
        button_update = findViewById(R.id.update);
        button_cancel = findViewById(R.id.cancel);

        setNotificationButtonState(true, false, false);
    }

    public void sendNotification(View view) {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent closeIntent = new Intent(ACTION_CLOSE_NOTIFICATION);
        PendingIntent closePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, closeIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.addAction(R.drawable.ic_update, "LEARN MORE", updatePendingIntent);
        notifyBuilder.setDeleteIntent(closePendingIntent);
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        setNotificationButtonState(false, true, true);
    }

    @Override
    protected void onDestroy(){
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Title")
                .setSmallIcon(R.drawable.ic_android)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Here is the first line")
                        .addLine("This is the second line")
                        .addLine("Yay last line")
                        .setSummaryText("+1 more"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return notifyBuilder;
    }

    public void updateNotification(View view) {
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        notifyBuilder.setLargeIcon(androidImage);
        notifyBuilder.setStyle(new NotificationCompat.InboxStyle()
                .addLine("Here is the first line")
                .addLine("This is the second line")
                .addLine("Yay last line")
                .addLine("Updated this notification")
                .setSummaryText("+0 more"));
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        setNotificationButtonState(false, false, true);
    }

    public void cancelNotification(View view) {
        mNotifyManager.cancel(NOTIFICATION_ID);

        setNotificationButtonState(true, false, false);
    }

    void setNotificationButtonState(Boolean isNotifyEnabled, Boolean isUpdateEnabled, Boolean isCancelEnabled){
        button_notify.setEnabled(isNotifyEnabled);
        button_update.setEnabled(isUpdateEnabled);
        button_cancel.setEnabled(isCancelEnabled);
    }

    public class NotificationReceiver extends BroadcastReceiver{
        public NotificationReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent){
            updateNotification(getCurrentFocus());
        }
    }

    public class NotificationCloser extends BroadcastReceiver{
        public NotificationCloser(){}

        @Override
        public void onReceive(Context context, Intent intent){
            cancelNotification(getCurrentFocus());
        }
    }
}