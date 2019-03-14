package com.collaboration.dao;

import java.util.List;

import com.collaboration.models.Notification;

public interface NotificationDao 
{
 void addNotifiction(Notification notification);
 List<Notification> GetAllNotificationNotViewed(String email);
 Notification getNotification(int notificationid);
 void UpdateViewedStatus(int notificationid);
}
