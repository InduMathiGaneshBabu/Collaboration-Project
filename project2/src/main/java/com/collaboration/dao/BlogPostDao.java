package com.collaboration.dao;

import java.util.List;

import com.collaboration.models.BlogPost;

public interface BlogPostDao 
{
	void addblogpost(BlogPost blogpost);
	List<BlogPost> getBlogsApproved();
	List<BlogPost> getBlogsWaitingForApproval();
	BlogPost getblog(int blogpostid);
	void approve(BlogPost blogpost);
	void reject(BlogPost blogpost);

}
