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

import com.collaboration.dao.BlogCommentDao;
import com.collaboration.dao.UserDao;
import com.collaboration.models.BlogComment;
import com.collaboration.models.ErrorClazz;
import com.collaboration.models.UserDetails;

@RestController
public class BlogPostCommentController
{
	@Autowired
	BlogCommentDao blogcommentdao;
	@Autowired
	UserDao userdao;
	@RequestMapping(value="/addcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addcomment(HttpSession session,@RequestBody BlogComment blogcomment)
	{
		String email=(String)session.getAttribute("LoginId");
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"Please login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
	    UserDetails commentedby=userdao.GetUser(email);
	    blogcomment.setCommentedby(commentedby);
	    blogcomment.setCommentedOn(new Date());
		blogcommentdao.addblogcomment(blogcomment);
		return new ResponseEntity<BlogComment>(blogcomment,HttpStatus.OK);//here we are return blogcomment becz there we show immediately the comment which was commented by the loggedin user
	}
   
	@RequestMapping(value="/getblogcomments/{blogpostid}",method=RequestMethod.GET)
	public ResponseEntity<?> getallcomments(HttpSession session,@PathVariable int blogpostid)
	{
		String email=(String)session.getAttribute("LoginId");
		if(email==null)
		{
			ErrorClazz errorclazz=new ErrorClazz(1,"Please login");
			return new ResponseEntity<ErrorClazz>(errorclazz,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogcomments=blogcommentdao.getallblogcomment(blogpostid);
		return new ResponseEntity<List<BlogComment>>(blogcomments,HttpStatus.OK);
	}
}
