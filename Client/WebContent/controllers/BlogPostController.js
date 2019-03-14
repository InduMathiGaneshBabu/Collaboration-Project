/**
 * 
 */
app.controller('BlogPostCtrl',function($scope,BlogPostServices,$location,$rootScope)

	{
	
	$scope.addblogpost=function(blogpost)
	{
		BlogPostServices.addblogpost(blogpost).then(function(response)
		{
			console.log(response.status)
			
			$scope.message="BlogPost Added Successfully!!!! and it is waiting for approval"
				
			$scope.blogpost={ }	
			
		},function(response)
		{
			$scope.error=response.data
			console.log(response.status)
			
		})
	}
	if($rootScope.user.role=='ADMIN')
		{
		
		BlogPostServices.getblogswaiting().then(function(response){
		
		
		$scope.blogpostwaiting=response.data
		console.log(response.status)
		
		
		
	},function(response){
		
		$scope.error=response.data
		console.log(response.status)
		
		
	})
		}
	BlogPostServices.getblogsapproved().then(function(response){
		
		$scope.blogsapproved=response.data
		console.log(response.status)
		
	},function(response){
		
		$scope.error=response.data
		console.log(response.status)
		
	})
	
	})