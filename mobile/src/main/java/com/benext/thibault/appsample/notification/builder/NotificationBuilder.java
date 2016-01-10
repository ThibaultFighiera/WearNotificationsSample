/*
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
 *
 */

package com.benext.thibault.appsample.notification.builder;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import com.benext.thibault.appsample.R;
import com.benext.thibault.appsample.notification.receiver.WearActionReceiver;

import java.util.ArrayList;

public class NotificationBuilder {

    private static final int WEAR_REQUEST_CODE = 1986;
    private static int NOTIFICATION_ID = 0;

    private NotificationBuilder() {
    }

    public static void sendNotification(Context context, NotificationCompat.Builder builder) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID++, builder.build());
    }

    public static NotificationCompat.Builder buildNotificationSimple(Context context) {
        Intent viewIntent = new Intent(context, NotificationBuilder.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent, 0);

        return (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.resto_icn)
                .setContentTitle(context.getString(R.string.new_resto))
                .setContentText(context.getString(R.string.new_resto_desc))
                .setContentIntent(viewPendingIntent);
    }

    public static NotificationCompat.Builder buildNotificationSimpleNBackground(Context context) {
        Bitmap restoImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.resto);

        return (NotificationCompat.Builder) buildNotificationSimple(context)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(restoImg)
                        .setBigContentTitle(context.getString(R.string.new_resto))
                        .setSummaryText(context.getString(R.string.new_resto_desc)));
    }

    public static NotificationCompat.Builder buildNotificationExtender(Context context) {

        Bitmap tableImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.table_restaurant);

        NotificationCompat.Builder builder = buildNotificationSimpleNBackground(context);
        builder.setSmallIcon(R.drawable.resto_icn);

        // Create a WearableExtender to add functionality for wearables
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(false)
                        .setBackground(tableImg);

        return (NotificationCompat.Builder) builder.extend(wearableExtender);
    }

    public static NotificationCompat.Builder buildNotificationExtenderAction(Context context) {

        Bitmap tableImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.table_restaurant);

        NotificationCompat.Builder builder = buildNotificationSimpleNBackground(context);
        builder.setSmallIcon(R.drawable.resto_icn);

        // Create a WearableExtender to add functionality for wearables
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(false)
                        .setBackground(tableImg)
                        .addActions(getActionsSample(context));

        return (NotificationCompat.Builder) builder.extend(wearableExtender);
    }

    private static ArrayList<android.support.v4.app.NotificationCompat.Action> getActionsSample(Context context) {
        ArrayList<NotificationCompat.Action> actions = new ArrayList<>();

        //User click on action triggers broadcast which is received by WearActionReceiver.class
        //Put notification id and flag WEAR_ACTION.
        //Using these parameters WearActionReceiver.class will know which action was clicked
        Intent notifyNextTimeIntent = new Intent(context, WearActionReceiver.class);
        notifyNextTimeIntent.putExtra(WearActionReceiver.NOTIFICATION_ID_STRING, NOTIFICATION_ID);
        notifyNextTimeIntent.putExtra(WearActionReceiver.WEAR_ACTION, WearActionReceiver.SNOOZE_NOTIFICATION);

        PendingIntent pendingIntentNotify = PendingIntent.getBroadcast(context, WEAR_REQUEST_CODE, notifyNextTimeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //text shown in notification
        String notifyAgainText = context.getString(R.string.notification_next_in_line);

        //Wear action - this action will be shown only on Android Wear devices
        //Set action icon, text and pending intent which will be executed on click
        //When user clicks on this icon he will "snooze" notification
        actions.add(new NotificationCompat.Action
                .Builder(R.drawable.resto_icn, notifyAgainText, pendingIntentNotify).build());

        return actions;
    }

    public static NotificationCompat.Builder buildNotificationExtenderPages(Context context) {

        ArrayList<Notification> pages = new ArrayList<>();

        // Create first page notification
        Bitmap tableImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.resto);
        Notification page1 = new NotificationCompat.Builder(context)
                .extend(new NotificationCompat.WearableExtender()
                        .setHintShowBackgroundOnly(true)
                        .setBackground(tableImg)).build();

        pages.add(page1);

        // Create second page notification
        Spannable meatTitle = new SpannableString(context.getString(R.string.menu_meats));
        meatTitle.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, meatTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        NotificationCompat.InboxStyle secondPageStyle = new NotificationCompat.InboxStyle();
        secondPageStyle.setBigContentTitle("Page 2")
                .setSummaryText("")
                .addLine(getSpannableString(context, R.string.menu_starters, Typeface.BOLD))
                .addLine(context.getString(R.string.menu_starters_content1))
                .addLine(context.getString(R.string.menu_starters_content2))
                .addLine(getSpannableString(context, R.string.menu_meats, Typeface.BOLD))
                .addLine(context.getString(R.string.menu_meats_content1))
                .addLine(context.getString(R.string.menu_meats_content2));


        Notification page2 = new NotificationCompat.Builder(context)
                .extend(new NotificationCompat.WearableExtender()
                        .setBackground(BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.table_restaurant)))
                .setStyle(secondPageStyle).build();


        pages.add(page2);

        // Create builder for the main notification
        Bitmap restoImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.resto);
        return (NotificationCompat.Builder) buildNotificationSimpleNBackground(context)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(restoImg))
                .extend(new NotificationCompat.WearableExtender()
                        .addPages(pages));
    }

    private static Spannable getSpannableString(Context context, int strId, int typeface) {

        Spannable startersTitle = new SpannableString(context.getString(strId));
        startersTitle.setSpan(new StyleSpan(typeface), 0, startersTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return startersTitle;
    }
}
