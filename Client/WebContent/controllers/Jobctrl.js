/**
 * 
 */
	alert("enters into the addjob $scope function in jobctrl");
app.controller('jobctrl',function($scope,jobservices){
	$scope.show="false";
	$scope.addJob=function(){
		jobservices.addjob($scope.job).then(function(response)
		{
			console.log(response.data)
			console.log(response.status)
		},
		function(response)
		{
			if(response.status==400)
				{
				$scope.message="Client Error";
				}
			console.log(response.status)
			$scope.error=response.data
		})
		
	}
	jobservices.getalljobs().then(function(response)
			{
		$scope.jobs=response.data;
		console.log(response.status)
		console.log(response.data)
		
		$scope.showcontent=function(jobid){
			
			$scope.show=!$scope.show
			$scope.jobid=jobid
		}
			},
			function(response){
				
				console.log(response.status)
				
			})
	
})