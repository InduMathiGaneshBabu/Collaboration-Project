package com.collaboration.dao;

import com.collaboration.models.UserDetails;

public interface UserDao 
{
	void Registration(UserDetails user);
	
	boolean isEmailUnique(String email);
	boolean isPhonenumberUnique(String phonenumber);
	
	UserDetails Login(UserDetails user);
	
	void UpdateUser(UserDetails user);
	
	UserDetails GetUser(String email);
	
	boolean isUpdatedPhonenumberUnique(String phonenumber,String email);
	

	
	

}
