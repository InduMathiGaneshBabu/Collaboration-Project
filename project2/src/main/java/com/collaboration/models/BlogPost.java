package com.collaboration.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class BlogPost 
{

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int blogpostid;
	private String blogposttitle;
	@Lob
	private String blogpostcontent;
	@ManyToOne
	private UserDetails author;
	private boolean approved; 
	private Date postedOn;
	private int likes;
	public int getBlogpostid() {
		return blogpostid;
	}
	public void setBlogpostid(int blogpostid) {
		this.blogpostid = blogpostid;
	}
	public String getBlogposttitle() {
		return blogposttitle;
	}
	public void setBlogposttitle(String blogposttitle) {
		this.blogposttitle = blogposttitle;
	}
	public String getBlogpostcontent() {
		return blogpostcontent;
	}
	public void setBlogpostcontent(String blogpostcontent) {
		this.blogpostcontent = blogpostcontent;
	}
	public UserDetails getAuthor() {
		return author;
	}
	public void setAuthor(UserDetails author) {
		this.author = author;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
}
