package com.collaboration.dao;

import java.util.List;

import com.collaboration.models.BlogComment;

public interface BlogCommentDao 
{
	void addblogcomment(BlogComment blogcomment);
	List<BlogComment> getallblogcomment(int blogpostid);

}
