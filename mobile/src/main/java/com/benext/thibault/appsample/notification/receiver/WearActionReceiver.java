/*
 *
 *  Copyright (C) 2016 Thibault Fighiera
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.benext.thibault.appsample.notification.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

import com.benext.thibault.appsample.R;

/**
 * Created by Thibault Fighiera on 10/01/2016 inspired by Ivan Kocijan
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