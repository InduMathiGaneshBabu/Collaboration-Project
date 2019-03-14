/**
 * 
 */
app.controller('UserCtrl',function($scope,UserServices,$location,$rootScope,$cookieStore){
	
	$scope.register=function(user){
		
		UserServices.register(user).then(function(response){
			
			console.log(response.status);
			$location.path('/login')
			
			
		},function(response){
			console.log(response.status)
			$scope.error=response.data
		})
		
		
	}
	
	$scope.login=function(user){
		
		UserServices.login(user).then(function(response){
			console.log(response.status)
				$rootScope.user=response.data
				$cookieStore.put('userdetail',response.data)
				$location.path("views/home.html")
				
				
			},function(response){
				
				if(response.status==401)
				{
				$scope.emessage="check the email and password"
				$scope.user={ }
				
				}
			console.log(response.status)
			$scope.error=response.data
			
			
		})
	}
	$scope.updateuserprofile=function(user)
	{
		UserServices.updateuserprofile(user).then(function(response){
			
			$scope.message=response.data
			$location.path("/login")
			
			
			
		} ,function(response){
			
			$scope.error=response.data
			console.log(response.status)
		})
	}
})