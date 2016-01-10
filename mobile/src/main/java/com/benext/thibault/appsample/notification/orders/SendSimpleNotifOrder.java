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

package com.benext.thibault.appsample.notification.orders;

import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.benext.thibault.appsample.R;
import com.benext.thibault.appsample.notification.builder.NotificationBuilder;

public class SendSimpleNotifOrder implements Order {
    @Override
    public void execute(Context context) {
        NotificationCompat.Builder builder = NotificationBuilder.buildNotificationSimple(context);
        NotificationBuilder.sendNotification(context, builder);
    }

    @Override
    public int getName() {
        return R.string.simple_notif_name;
    }
}
