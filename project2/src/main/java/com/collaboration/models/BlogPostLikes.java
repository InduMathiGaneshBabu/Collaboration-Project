package com.collaboration.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BlogPostLikes
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private	int likeid;
	@ManyToOne
	private BlogPost likedblog;
	@ManyToOne
	private UserDetails likedby;
	public int getLikeid() {
		return likeid;
	}
	public void setLikeid(int likeid) {
		this.likeid = likeid;
	}
	public BlogPost getLikedblog() {
		return likedblog;
	}
	public void setLikedblog(BlogPost likedblog) {
		this.likedblog = likedblog;
	}
	public UserDetails getLikedby() {
		return likedby;
	}
	public void setLikedby(UserDetails likedby) {
		this.likedby = likedby;
	}
	
	
	
    
}
