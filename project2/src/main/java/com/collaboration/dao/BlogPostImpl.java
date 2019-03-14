package com.collaboration.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.models.BlogPost;
@Repository
@Transactional
public class BlogPostImpl implements BlogPostDao {
	
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public void addblogpost(BlogPost blogpost) {
		
		Session session=sessionfactory.getCurrentSession();
		session.save(blogpost);
		}

	@Override
	public List<BlogPost> getBlogsApproved() {
	
		Session session = sessionfactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved=true");
		List<BlogPost> approvedBlogs=query.list();
		return approvedBlogs;
	}

	@Override
	public List<BlogPost> getBlogsWaitingForApproval() {
		
		Session session = sessionfactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approved=false");
		List<BlogPost> waitingBlogs=query.list();
		return waitingBlogs;
		
	}

	@Override
	public BlogPost getblog(int blogpostid) {
		Session session=sessionfactory.getCurrentSession();
		BlogPost blogpost=(BlogPost)session.get(BlogPost.class,blogpostid);
		return blogpost;
	}

	@Override
	public void approve(BlogPost blogpost) {
    Session session=sessionfactory.getCurrentSession();
    session.update(blogpost);
		
	}

	@Override
	public void reject(BlogPost blogpost) {
		Session session=sessionfactory.getCurrentSession();
		session.delete(blogpost);
		
	}

}
