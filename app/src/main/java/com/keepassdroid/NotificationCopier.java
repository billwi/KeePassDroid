package com.keepassdroid;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.android.keepass.R;

public class NotificationCopier extends IntentService {
    public static final String USERACTION = "ACTION1";
    public static final String PASSACTION = "ACTION2";

    public NotificationCopier(String name) {
        super(name);
    }

    @Override
    public void onHandleIntent(Intent intent) {
        System.out.println("Service started");
        final String action = intent.getAction();
        if (USERACTION.equals(action)) {
            ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Username", EntryActivity.USERNAME);
            clipboard.setPrimaryClip(clip);

            NotificationCompat.Builder notiInfo = new  NotificationCompat.Builder(getApplicationContext());
            notiInfo.setSmallIcon(R.drawable.launcher);
            notiInfo.setContentTitle("Username copied");
            notiInfo.setContentText("Username copied");
            notiInfo.setPriority(Notification.PRIORITY_MAX);
            notiInfo.setVibrate(new long[0]);
            NotificationManager notManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            int id = (int) System.currentTimeMillis();

            notManager.notify(id, notiInfo.build());
            try {
                Thread.sleep(2600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notManager.cancel(id);

            System.out.println("Added username CB!");
        } else if (PASSACTION.equals(action)) {
            ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Password", EntryActivity.PASSWORD);
            clipboard.setPrimaryClip(clip);

            System.out.println("Added password CB!");

            NotificationCompat.Builder notiInfo = new  NotificationCompat.Builder(getApplicationContext());
            notiInfo.setSmallIcon(R.drawable.launcher);
            notiInfo.setContentTitle("Password copied");
            notiInfo.setContentText("Password copied");
            notiInfo.setPriority(Notification.PRIORITY_MAX);
            notiInfo.setVibrate(new long[0]);
            NotificationManager notManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            int id = (int) System.currentTimeMillis();

            notManager.notify(id, notiInfo.build());
            try {
                Thread.sleep(2600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notManager.cancel(id);

            NotificationManager notiCancel = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notiCancel.cancel(EntryActivity.NOTIFICATION_ID);
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }
    }

}