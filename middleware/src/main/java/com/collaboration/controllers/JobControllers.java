package com.collaboration.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.JobDao;
import com.collaboration.models.ErrorClazz;
import com.collaboration.models.Job;

@RestController
public class JobControllers 
{
@Autowired
private JobDao jobdao;

@RequestMapping(value="/addjob",method=RequestMethod.POST)
public ResponseEntity<?> addjob(@RequestBody Job job,HttpSession session)
{
	
	String email=(String)session.getAttribute("LoginId");
	if(email==null)
	{
		ErrorClazz errorclazz=new ErrorClazz(7,"Please login first");
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
	}
	try
	{
		job.setPostedOn(new Date());
		jobdao.addJob(job);
	}
	catch(Exception e)
	{
		ErrorClazz errorclazz=new ErrorClazz(1,"job details not inserted...something went wrong.." +e.getMessage());
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<Job>(job,HttpStatus.OK);
}
@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
public ResponseEntity<?> getalljobs(HttpSession session)
{
	String email=(String)session.getAttribute("LoginId");
	if(email==null)
	{
		ErrorClazz errorclazz=new ErrorClazz(7,"Please login first");
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
	}
	
List<Job> job=jobdao.getalljobs();
return new ResponseEntity<List<Job>>(job,HttpStatus.OK);
}
}
