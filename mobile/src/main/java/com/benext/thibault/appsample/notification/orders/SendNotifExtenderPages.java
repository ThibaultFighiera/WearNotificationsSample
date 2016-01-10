package com.benext.thibault.appsample.notification.orders;

import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.benext.thibault.appsample.R;
import com.benext.thibault.appsample.notification.builder.NotificationBuilder;

/**
 * @author Thibault Fighiera
 * @date 10/01/2016.
 */
public class SendNotifExtenderPages implements Order {
    @Override
    public void execute(Context context) {
        NotificationCompat.Builder builder = NotificationBuilder.buildNotificationExtenderPages(context);
        NotificationBuilder.sendNotification(context, builder);
    }

    @Override
    public int getName() {
        return R.string.pages_notifications;
    }
}