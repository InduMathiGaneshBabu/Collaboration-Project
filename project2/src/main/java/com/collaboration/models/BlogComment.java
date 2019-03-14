package com.collaboration.models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class BlogComment 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentid;
	@ManyToOne
	private BlogPost blogpost;
	private String commenttext;
	@ManyToOne
	private UserDetails commentedby;
    private Date commentedOn;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public BlogPost getBlogpost() {
		return blogpost;
	}
	public void setBlogpost(BlogPost blogpost) {
		this.blogpost = blogpost;
	}
	public String getCommenttext() {
		return commenttext;
	}
	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}
	public UserDetails getCommentedby() {
		return commentedby;
	}
	public void setCommentedby(UserDetails commentedby) {
		this.commentedby = commentedby;
	}
	public Date getCommentedOn() {
		return commentedOn;
	}
	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}
    
    
}
