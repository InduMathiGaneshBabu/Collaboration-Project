package com.collaboration.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.UserDao;
import com.collaboration.models.ErrorClazz;
import com.collaboration.models.UserDetails;

@RestController
public class UserController 
{
  @Autowired
  UserDao userdao;
  
  @RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> Registration(@RequestBody UserDetails user)
	{
	  if(!userdao.isEmailUnique(user.getEmail()))
	  {
		  ErrorClazz errorclazz=new ErrorClazz(2,"Email is already Exists");
		  return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  if(!userdao.isPhonenumberUnique(user.getPhonenumber()))
	  {
		  ErrorClazz errorclazz=new ErrorClazz(3,"Use Unique PhoneNumber");
		  return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  if(user.getRole()==" " || user.getRole()==null)
	  {
		  ErrorClazz errorclazz=new ErrorClazz(4,"Role is Mandatory");
		  return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  try
	  {
		userdao.Registration(user);
	  }
	  catch(Exception e)
	  {
		ErrorClazz errorclazz=new ErrorClazz(1,"Unable to Register"); 
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<Void>(HttpStatus.OK);
	}
  
  @RequestMapping(value="/login",method=RequestMethod.POST)
  public ResponseEntity<?> login(@RequestBody UserDetails user,HttpSession session)
  {
	  
		  UserDetails validuser=userdao.Login(user);
		  if(validuser==null)
		  {
			  ErrorClazz errorclazz=new ErrorClazz(5,"Email/PassWord is InCorrect please login with valid credential");
			  return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		  }
		  else
		  {
			  System.out.println("Session Id is"+session.getId());
			  System.out.println("Session Creation Time is"+session.getCreationTime());
              validuser.setOnline(true);
			  userdao.UpdateUser(validuser);
			  session.setAttribute("LoginId",validuser.getEmail());
			  System.out.println("Login Id is"+session.getAttribute("LoginId"));
			  return new ResponseEntity<UserDetails>(validuser,HttpStatus.OK);
		  }
		  
		  }
  @RequestMapping(value="/logout", method=RequestMethod.PUT)
  public ResponseEntity<?> Logout(HttpSession session)
  {
	 String email=(String)session.getAttribute("LoginId");
	 
	 if(email==null)
	 {
		 ErrorClazz errorclazz=new ErrorClazz(5,"please login first");
		 return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
	 }
	
	UserDetails user=userdao.GetUser(email);
	user.setOnline(false);
	userdao.UpdateUser(user);
	session.removeAttribute("LoginId");
	session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);
	  
  }
	@RequestMapping(value="/getuserdetails",method=RequestMethod.GET)
	public ResponseEntity<?> GetUserDetails(HttpSession session)
	{
		String email=(String)session.getAttribute("LoginId");
		
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(6,"Please login first");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		UserDetails user=userdao.GetUser(email);
	    return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/updateuserprofile",method=RequestMethod.PUT)
	public ResponseEntity<?> UpdateUserProfile(HttpSession session,@RequestBody UserDetails user)
	{
		String email=(String)session.getAttribute("LoginId");
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(7,"Please login first");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		
		if(!userdao.isUpdatedPhonenumberUnique(user.getPhonenumber(), email))
		{
			ErrorClazz errorclazz=new ErrorClazz(8,"Phone number already exist");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(user.getRole()==""||user.getRole()==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(9,"Role is mandatory");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try
		{
			userdao.UpdateUser(user);
		}
		catch(Exception e)
		{
			ErrorClazz errorclazz=new ErrorClazz(10,"Unable to Update the user");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
	
}
