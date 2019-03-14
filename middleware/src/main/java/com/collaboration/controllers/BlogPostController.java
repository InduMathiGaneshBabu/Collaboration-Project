package com.collaboration.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.BlogPostDao;
import com.collaboration.dao.NotificationDao;
import com.collaboration.dao.UserDao;
import com.collaboration.models.BlogPost;
import com.collaboration.models.ErrorClazz;
import com.collaboration.models.Notification;
import com.collaboration.models.UserDetails;



@RestController
public class BlogPostController
{
	
	@Autowired
	private BlogPostDao blogpostdao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private NotificationDao notificationdao;
	
	@RequestMapping(value="/addblog",method=RequestMethod.POST)
	public ResponseEntity<?> AddBlog(HttpSession session,@RequestBody BlogPost blogpost)
	{
		String email=(String)session.getAttribute("LoginId");
		
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"please login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		
		blogpost.setPostedOn(new Date());
		UserDetails author=userdao.GetUser(email);
		blogpost.setAuthor(author);
		try
		{
			blogpostdao.addblogpost(blogpost);
		}
		catch(Exception e)
		{
			ErrorClazz errorclazz=new ErrorClazz(2,"Unable to Add the blog"+e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
		
	}

	@RequestMapping(value="/blogswaitingforapproval",method=RequestMethod.GET)
	public ResponseEntity<?> BlogWaitingForApproval(HttpSession session)
	{
        String email=(String)session.getAttribute("LoginId");
		
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"please login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		
		UserDetails user=userdao.GetUser(email);
		if(!user.getRole().equals("ADMIN"))
		{
			ErrorClazz errorclazz=new ErrorClazz(2,"You are Unauthorized to view this page");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogPost> blogsforapproval=blogpostdao.getBlogsWaitingForApproval();
		return new ResponseEntity<List<BlogPost>>(blogsforapproval,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/blogsapproved",method=RequestMethod.GET)
	public ResponseEntity<?> BlogsApproved(HttpSession session)
	{
        String email=(String)session.getAttribute("LoginId");
		
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"please login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogPost> blogsapproved=blogpostdao.getBlogsApproved();
		return new ResponseEntity<List<BlogPost>>(blogsapproved,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getblog/{blogpostid}",method=RequestMethod.GET)
	public ResponseEntity<?> GetBlog(HttpSession session,@PathVariable int blogpostid)
	{
		 String email=(String)session.getAttribute("LoginId");
			
			if(email==null)
			{
				ErrorClazz errorclazz=new ErrorClazz(1,"please login");
				return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
			}
			
			
			
			BlogPost blogpost=blogpostdao.getblog(blogpostid);
			return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
			}
	
	
	@RequestMapping(value="/approveblog",method=RequestMethod.PUT)
	public ResponseEntity<?> approveblog(@RequestBody BlogPost blogpost,HttpSession session)
	{
		 String email=(String)session.getAttribute("LoginId");
			
			if(email==null)
			{
				ErrorClazz errorclazz=new ErrorClazz(1,"please login");
				return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
			}
			
			UserDetails user=(UserDetails)userdao.GetUser(email);
			if(!user.getRole().equals("ADMIN"))
			{
				ErrorClazz errorclazz=new ErrorClazz(9,"You are Unauthorized to view this page");
				return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
			}
		     
			blogpost.setApproved(true);
			Notification notification=new Notification();
			notification.setApprovedorrejected("Approved");
			notification.setUsertobenotified(blogpost.getAuthor());
			notification.setBlogtitle(blogpost.getBlogposttitle());
			notificationdao.addNotifiction(notification);
			blogpostdao.approve(blogpost);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/reject/{rejectionreason}",method=RequestMethod.PUT)
	public ResponseEntity<?> Reject(@RequestBody BlogPost blogpost,@PathVariable String rejectionreason,HttpSession session)
	{
		
		 String email=(String)session.getAttribute("LoginId");
			
			if(email==null)
			{
				ErrorClazz errorclazz=new ErrorClazz(1,"please login");
				return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
			}
			
			UserDetails user=(UserDetails)userdao.GetUser(email);
			if(!user.getRole().equals("ADMIN"))
			{
				ErrorClazz errorclazz=new ErrorClazz(9,"You are Unauthorized to view this page");
				return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
			}
			
			Notification notification=new Notification();
			notification.setApprovedorrejected("Rejected");
			notification.setBlogtitle(blogpost.getBlogposttitle());
			notification.setUsertobenotified(blogpost.getAuthor());
			notification.setRejectionreason(rejectionreason);
			notificationdao.addNotifiction(notification);
	        blogpostdao.reject(blogpost);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
			}
}
