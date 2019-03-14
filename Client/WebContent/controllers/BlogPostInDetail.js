/**
 * BlogInDetailctrl
 */
app.controller('BlogInDetailctrl',function($scope,$location,BlogPostServices,$routeParams,$sce)
		{
	
	var Blogpostid=$routeParams.blogpostid
	
     $scope.show=false;
	$scope.shows=false;
	$scope.isRejected=false;
	
	BlogPostServices.getblogdetail(Blogpostid).then(
			
			function(response){
				
				alert("successfully got the detailed blogpost")
				$scope.blogpost=response.data
				$scope.content=$sce.trustAsHtml($scope.blogpost.blogpostcontent)
				
			},function(response){
				if(response.status==401)
					$location.path('/login')
				
			})
			
			$scope.approve=function(blogpost){
			BlogPostServices.approveblog(blogpost).then(function(response)
					{
				
			$location.path('/blogswaitingforapproval')	
				
				
			        },function(response){
			        	
			        	if(response.status==401)
			        		$location.path('/login')
			       })
	}
	
	        $scope.Reject=function()
	        {
	        	$scope.isRejected=!$scope.isRejected
	        }
	
	       
	        
	       $scope.reject=function(blogpost,rejectionreason){
			
	         BlogPostServices.reject(blogpost,rejectionreason).then(function(response){
	    	   
	    	      $location.path('/blogswaitingforapproval')
	    	   
	    	   
	       },function(response){
	    	   
	    	   if(response.status==401)
	        		$location.path('/login')
	    	   
	    	   
	       })
	}
	        $scope.hasUserLikedBlog=function(Blogpostid)
	        {
	        BlogPostServices.hasUserLikedBlog(Blogpostid).then(function(response)
	        {
	        	
	        	if(response.data=='')
	        		{
	        		$scope.isLiked=false//make thumbsup color in black
	        		}
	        	else
	        		{
	        		$scope.isLiked=true//make thumbsup color in blue
	        		}
	        	
	        },function(response)
	        {
	        	if(response.status==401)
	        		{
	        		$location.path("/login")
	        		}
	        })
	        }
	        
	        $scope.updatelikes=function(Blogpostid)
	        {
	        	BlogPostServices.updatelikes(Blogpostid).then(function(response)
	        	{
	        		$scope.isLiked=!$scope.isLiked
	        		$scope.blogpost=response.data
	        	},
	        	function(response)
	        	{
	        		if(response.status==401)
	        		{
	        		$location.path("/login")
	        		}
	        	})
	        }
	          
	        $scope.addblogcomment=function(commenttxt,blogpost)
	        {
	        	$scope.Blogcomment={}
	        	
	        	$scope.Blogcomment.commenttext=commenttxt
	        	
	        	$scope.Blogcomment.blogpost=blogpost
	        	
	        	console.log($scope.Blogcomment)
	        	
	        	BlogPostServices.addblogcomment($scope.Blogcomment).then(function(response){
	        		
	        		$scope.commenttxt=''
	        		
	        		$scope.BlogComment=response.data
	        		
	        	},function(response){
	        		
	        		if(response.status==401)
	        			{
	        			$location.path('/login')
	        			}
	        		
	        	})
	         }
	        
	        $scope.getallcomments=function(Blogpostid)
	        {
	        	BlogPostServices.getallcomments(Blogpostid).then(
	        			function(response)
	        			{
	        				$scope.blogcommentlist=response.data
	        				console.log(response.status)
	        				$scope.show=!$scope.show
	        				
	        				
	        			},
	        			function(response)
	        			{
	        				if(response.status==401)
	        					{
	        				console.log('/login')
	        					}
	        			})
	        }
	        
	        $scope.getlikes=function(Blogpostid)
	        {
	        	BlogPostServices.getlikes(Blogpostid).then(function(response)
	        			
	        	{
	        		$scope.listlikes=response.data
	        		console.log(response.status)
	        		$scope.shows=!$scope.shows
	        		
	        	},function(response){
	        		if(response.status==401)
	        			{
	        			$location.path('/login')
	        			}
	        		
	        	})
	        	
	        }
	        
	        
	        
})
