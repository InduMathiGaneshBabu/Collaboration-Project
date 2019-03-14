package com.collaboration.dao;

import java.util.List;

import com.collaboration.models.BlogPost;
import com.collaboration.models.BlogPostLikes;

public interface BlogPostLikesDao 
{

	BlogPostLikes hasUserLikedBlogPost(int blogpostid,String email);
	BlogPost UpdateLikes(int blogpostid,String email);
	List<BlogPostLikes> likelist(int blogpostid);
}
