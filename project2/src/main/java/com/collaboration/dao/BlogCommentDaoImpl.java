package com.collaboration.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.models.BlogComment;
@Repository
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao {
	
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public void addblogcomment(BlogComment blogcomment)
	{
		Session session=sessionfactory.getCurrentSession();
		session.save(blogcomment);
		

	}

	@Override
	public List<BlogComment> getallblogcomment(int blogpostid) {
		
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogpost.blogpostid=?");
		query.setInteger(0,blogpostid);
		List<BlogComment> listofblogcomment=query.list();
		return listofblogcomment;
		
	}

}
