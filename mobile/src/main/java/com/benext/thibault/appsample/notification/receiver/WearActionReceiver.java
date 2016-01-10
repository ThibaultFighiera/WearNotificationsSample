package com.benext.thibault.appsample.notification.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

import com.benext.thibault.appsample.R;

/**
 * Ivan Kocijan
 * https://infinum.co/the-capsized-eight/articles/make-your-app-work-with-android-wear-in-4-easy-steps
 */
public class WearActionReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_ID_STRING = "NotificationId";
    public static final String WEAR_ACTION = "WearAction";
    public static final int SNOOZE_NOTIFICATION = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {

            int notificationId = intent.getIntExtra(NOTIFICATION_ID_STRING, 0);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(notificationId);

            int action = intent.getIntExtra(WEAR_ACTION, 0);

            switch (action) {
                case SNOOZE_NOTIFICATION:
                    Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(1000);
                    Toast.makeText(context, R.string.notif_click, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }
}