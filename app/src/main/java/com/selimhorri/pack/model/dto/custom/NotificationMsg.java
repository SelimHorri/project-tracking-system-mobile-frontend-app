package com.selimhorri.pack.model.dto.custom;

import java.util.UUID;

public final class NotificationMsg {

    public static final int NOTIFICATION_ID = UUID.randomUUID().variant();
    public static final String CHANNEL_ID = UUID.randomUUID().toString();
    private int smallIcon;
    private String contentTitle;
    private String contentText;
    private int priority;

    public NotificationMsg(int smallIcon, String contentTitle, String contentText, int priority) {
        this.smallIcon = smallIcon;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.priority = priority;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public int getPriority() {
        return priority;
    }
}
