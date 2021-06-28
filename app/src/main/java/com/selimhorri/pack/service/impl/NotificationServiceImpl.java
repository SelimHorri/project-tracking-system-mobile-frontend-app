package com.selimhorri.pack.service.impl;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.selimhorri.pack.model.dto.custom.NotificationMsg;
import com.selimhorri.pack.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

    private final Context context;

    public NotificationServiceImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void notifyy(final NotificationMsg notificationMsg) {

        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this.context, NotificationMsg.CHANNEL_ID)
                        .setSmallIcon(notificationMsg.getSmallIcon())
                        .setContentTitle(notificationMsg.getContentTitle())
                        .setContentText(notificationMsg.getContentText())
                        .setPriority(notificationMsg.getPriority());

        NotificationManagerCompat.from(this.context)
                .notify(NotificationMsg.NOTIFICATION_ID, builder.build());
    }



}
