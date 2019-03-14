package com.collaboration.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.models.Job;
@Repository
@Transactional
public class JobDaoImpl implements JobDao 
{

	@Autowired
	private SessionFactory sessionfactory;
	@Override
	public void addJob(Job job) {
		Session session=sessionfactory.getCurrentSession();
		session.save(job);
		
	}
	@Override
	public List<Job> getalljobs() {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Job");
		List<Job> job=query.list();
		return job;
	}

}
