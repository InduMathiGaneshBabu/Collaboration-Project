/**
 * 
 */

app.factory('UserServices',function($http){
	
	var UserServices={}
	
	UserServices.register=function(user){
		
return $http.post("http://localhost:9021/middleware/register",user);
		
	}
	
	UserServices.login=function(user){
		
		return $http.post("http://localhost:9021/middleware/login",user);
	}

	UserServices.logout=function(){
		
		return $http.put("http://localhost:9021/middleware/logout");
	}
	
	UserServices.updateuserprofile=function(user){
		
		return $http.put("http://localhost:9021/middleware/updateuserprofile",user);
	}
	
	
	return UserServices;
	
})