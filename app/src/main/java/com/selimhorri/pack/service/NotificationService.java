package com.selimhorri.pack.service;

import com.selimhorri.pack.model.dto.custom.NotificationMsg;

@FunctionalInterface
public interface NotificationService {
    void notifyy(final NotificationMsg notificationMsg);
}
