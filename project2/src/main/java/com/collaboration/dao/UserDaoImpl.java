package com.collaboration.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.models.UserDetails;

@Repository
@Transactional

public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public void Registration(UserDetails user) 
	{
	    Session session=sessionfactory.getCurrentSession();   
	    session.save(user);
		
	}

	@Override
	public boolean isEmailUnique(String email)
	{
		Session session=sessionfactory.getCurrentSession();
		UserDetails user=(UserDetails)session.get(UserDetails.class,email);
		if(user==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isPhonenumberUnique(String phonenumber) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from UserDetails where phonenumber=:pnumber");
		query.setString("pnumber",phonenumber);
		UserDetails user=(UserDetails)query.uniqueResult();
		if(user==null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public void UpdateUser(UserDetails user) {
		Session session=sessionfactory.getCurrentSession();
		session.update(user);
		
		
		
	}

	@Override
	public UserDetails GetUser(String email) {
		Session session=sessionfactory.getCurrentSession();
		UserDetails user=(UserDetails)session.get(UserDetails.class,email);
		return user;
		
		
		
	}

	@Override
	public UserDetails Login(UserDetails user) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from UserDetails where email=:email and password=:pwd");
		query.setString("email",user.getEmail());
		query.setString("pwd",user.getPassword());
		UserDetails validuser=(UserDetails)query.uniqueResult();
		return validuser;
		
	}

	@Override
	public boolean isUpdatedPhonenumberUnique(String phonenumber, String email) {
		
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from UserDetails where email!=:email and phonenumber=:pnumber");
		query.setString("pnumber",phonenumber);
		query.setString("email",email);
		UserDetails user=(UserDetails)query.uniqueResult();
		if(user==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}



}
