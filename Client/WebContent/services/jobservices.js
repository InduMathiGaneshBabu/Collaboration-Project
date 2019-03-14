/**
 * 
 */
app.factory('jobservices',function($http){
	var jobservice={}//created the object of the services
	alert("enters into the  function into the services");
	jobservice.addjob=function(job){
	
		return $http.post("http://localhost:9021/middleware/addjob",job);
		
	}
	jobservice.getalljobs=function(){
		var url="http://localhost:9021/middleware/getalljobs"
			return $http.get(url);
	}
	return jobservice;//return the object to the controller
})