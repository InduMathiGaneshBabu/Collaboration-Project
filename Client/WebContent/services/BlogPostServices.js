/**
 * BlogPostServices
 */
app.factory('BlogPostServices',function($http){
	
	var BlogPostServices={ }
	
	var BaseUrl="http://localhost:9021/middleware"
	
	BlogPostServices.addblogpost=function(blogpost)
	{
		return $http.post(BaseUrl +"/addblog",blogpost);
	}
	
	BlogPostServices.getblogswaiting=function()
	{
		return $http.get(BaseUrl +"/blogswaitingforapproval");
	}
	
	BlogPostServices.getblogsapproved=function()
	{
		return $http.get(BaseUrl +"/blogsapproved");
	}
	
	BlogPostServices.getblogdetail=function(blogpostid)
	{
		return $http.get(BaseUrl +"/getblog/"+blogpostid)
	}
	
	BlogPostServices.approveblog=function(blogpost)
	{
		return $http.put(BaseUrl +"/approveblog",blogpost)
	}
	
	BlogPostServices.reject=function(blogpost,rejectionreason)
	{
	 return $http.put(BaseUrl +"/reject/"+rejectionreason,blogpost)
	}
	BlogPostServices.hasUserLikedBlog=function(Blogpostid)
	{
		return $http.get(BaseUrl +"/hasUserLikedBlog/"+Blogpostid);
	}
	BlogPostServices.updatelikes=function(Blogpostid)
	{
		return $http.put(BaseUrl+"/updatelikes/"+Blogpostid);
	}
	BlogPostServices.addblogcomment=function(Blogcomment)
	{
		return $http.post(BaseUrl+"/addcomment",Blogcomment)
	}
	BlogPostServices.getallcomments=function(Blogpostid)
	{
		return $http.get(BaseUrl+"/getblogcomments/"+Blogpostid)
	}
	BlogPostServices.getlikes=function(Blogpostid)
	{
		return $http.get(BaseUrl+"/getlikelist/"+Blogpostid)
	}
	return BlogPostServices;
	
})