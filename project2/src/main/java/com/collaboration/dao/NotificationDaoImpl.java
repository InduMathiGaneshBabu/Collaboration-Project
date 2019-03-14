package com.collaboration.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.models.Notification;
@Repository
@Transactional
public class NotificationDaoImpl implements NotificationDao 
{
     @Autowired
     SessionFactory sessionfactory;
	@Override
	public void addNotifiction(Notification notification) 
	{
		Session session=sessionfactory.getCurrentSession();
		session.save(notification);
		
	}

	@Override
	public List<Notification> GetAllNotificationNotViewed(String email)
	{
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Notification where viewed=:viewed and usertobenotified.email=:email");
	    query.setString("email", email);
	    query.setBoolean("viewed",false);
	    List<Notification> ListofNotification=query.list();
	    return ListofNotification;
	
	}

	@Override
	public Notification getNotification(int notificationid) {
		Session session=sessionfactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class,notificationid);
		return notification;
	}

	@Override
	public void UpdateViewedStatus(int notificationid) {
		Session session=sessionfactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class,notificationid);
		notification.setViewed(true);
		session.update(notification);
		}

}
