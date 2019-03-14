package com.collaboration.dao;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.models.BlogPost;
import com.collaboration.models.BlogPostLikes;
import com.collaboration.models.UserDetails;
@Repository
@Transactional
public class BlogPostLikesDaoImpl implements BlogPostLikesDao {

	@Autowired
	
	SessionFactory sessionfactory;

	@Override
	public BlogPostLikes hasUserLikedBlogPost(int blogpostid, String email) 
	{
		Session session=sessionfactory.getCurrentSession();
		Query query =session.createQuery("from BlogPostLikes where likedblog.blogpostid=:blogid and likedby.email=:email");
		 query.setInteger("blogid",blogpostid);
		 query.setString("email",email);
		 BlogPostLikes blogPostLikes=(BlogPostLikes)query.uniqueResult();
		 return blogPostLikes;
		
	}
	
	

	@Override
	public BlogPost UpdateLikes(int blogpostid, String email) {
		
		Session session=sessionfactory.getCurrentSession();
		BlogPost blogpost=(BlogPost)session.get(BlogPost.class, blogpostid);
		UserDetails Likedby=(UserDetails)session.get(UserDetails.class,email);
		BlogPostLikes blogpostlikes=hasUserLikedBlogPost(blogpostid,email);//check whether like record is present or not
		if(blogpostlikes==null)
		{
			BlogPostLikes blogpostlike=new BlogPostLikes();
			blogpostlike.setLikedblog(blogpost);
			blogpostlike.setLikedby(Likedby);
			session.save(blogpostlike);
			blogpost.setLikes(blogpost.getLikes()+1);
			session.update(blogpost);
		}
		else
		{
			session.delete(blogpostlikes);
			blogpost.setLikes(blogpost.getLikes()-1);
			session.update(blogpost);
			
		}
		return blogpost;
		
	}



	@Override
	public List<BlogPostLikes> likelist(int blogpostid) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from BlogPostLikes where likedblog.blogpostid=:blogid");
		query.setInteger("blogid", blogpostid);
		List<BlogPostLikes> likelist=query.list();
		return likelist;
	}
	
	
}
