/**
 * Notificationctrl
 */
app.controller('Notificationctrl',function($scope,NotificationServices,$rootScope,$location,$routeParams)
{
	
	var NotificationId=$routeParams.notificationid
	
	NotificationServices.getnotificationnotviewed().then(function(response)
			
			{
		
		
		     $rootScope.notificationNotViewed=response.data
		     $rootScope.notificationCount=$rootScope.notificationNotViewed.length
		     console.log(response.status)
	
            },function(response){
            	
            	if(response.status==401)
            		{
            		$location.path('/login')
            		}
		
	})
	
	$rootScope.getNotification=function(NotificationId)
	{
		NotificationServices.getNotification(NotificationId).then
		(
	     function(response){
					
				$scope.notificationdetail=response.data
				console.log(response.status)
			
		},
		function(response){
			
			if(response.status==401)
    		{
    		$location.path('/login')
    		}
			
		})
	}
	
	
	
})