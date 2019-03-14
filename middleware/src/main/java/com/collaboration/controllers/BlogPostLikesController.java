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

import com.collaboration.dao.BlogPostLikesDao;
import com.collaboration.models.BlogPost;
import com.collaboration.models.BlogPostLikes;
import com.collaboration.models.ErrorClazz;

@RestController
public class BlogPostLikesController 
{
@Autowired
BlogPostLikesDao blogpostlikesdao;

@RequestMapping(value="/hasUserLikedBlog/{blogpostid}",method=RequestMethod.GET)
public ResponseEntity<?> hasuserlikedblog(HttpSession session,@PathVariable int blogpostid)
{
	String email=(String)session.getAttribute("LoginId");
	if(email==null)
	{
		ErrorClazz errorclazz=new ErrorClazz(1,"please login");
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
	}
	
	BlogPostLikes blogpostlikes=blogpostlikesdao.hasUserLikedBlogPost(blogpostid, email);
	return new ResponseEntity<BlogPostLikes>(blogpostlikes,HttpStatus.OK);
}


@RequestMapping(value="/updatelikes/{blogpostid}",method=RequestMethod.PUT)
public ResponseEntity<?> updatelikes(HttpSession session,@PathVariable int blogpostid)
{
	String email=(String)session.getAttribute("LoginId");
	if(email==null)
	{
		ErrorClazz errorclazz=new ErrorClazz(1,"please login");
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
	}
	
	BlogPost blogpost=blogpostlikesdao.UpdateLikes(blogpostid, email);
	return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
	
}

@RequestMapping(value="/getlikelist/{blogpostid}",method=RequestMethod.GET)
public ResponseEntity<?> getlikes(HttpSession session,@PathVariable int blogpostid)
{
	String email=(String)session.getAttribute("LoginId");
	if(email==null)
	{
		ErrorClazz errorclazz=new ErrorClazz(1,"please login");
		return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
	}
	
	
	List<BlogPostLikes> listlike=blogpostlikesdao.likelist(blogpostid);
	return new ResponseEntity<List<BlogPostLikes>>(listlike,HttpStatus.OK);
}

}
