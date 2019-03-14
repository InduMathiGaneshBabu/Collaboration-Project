package com.collaboration.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.NotificationDao;
import com.collaboration.models.ErrorClazz;
import com.collaboration.models.Notification;

@RestController
public class NotificationController 
{

	@Autowired
	NotificationDao notificationdao;
	
	@RequestMapping(value="/getallnotificationnotviewed",method=RequestMethod.GET)
	public ResponseEntity<?> getnotifactionnotviewed(HttpSession session)
	{
		String email=(String)session.getAttribute("LoginId");
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"Please Login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		
		
		List<Notification> getallnotification=notificationdao.GetAllNotificationNotViewed(email);
		return new ResponseEntity<List<Notification>>(getallnotification,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getnotification/{notificationid}",method=RequestMethod.GET)
	public ResponseEntity<?> getnotification(HttpSession session,@PathVariable int notificationid)
	{
		String email=(String)session.getAttribute("LoginId");
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"Please Login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		
		Notification notification=notificationdao.getNotification(notificationid);
		
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
	@RequestMapping(value="/updatenotification/{notificationid}",method=RequestMethod.PUT)
	public ResponseEntity<?> updatenotification(HttpSession session,@PathVariable int notificationid)
	{
		String email=(String)session.getAttribute("LoginId");
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"Please Login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		 
		notificationdao.UpdateViewedStatus(notificationid);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
}
