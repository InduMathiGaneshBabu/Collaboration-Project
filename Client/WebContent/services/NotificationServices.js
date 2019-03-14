/**
 * 
 */
app.factory('NotificationServices',function($http){
	
	var notificationServices={}
	
	
	var Url="http://localhost:9021/middleware"
	
	NotificationServices.getnotificationnotviewed=function()
	{
		return $http.get(Url+"/getallnotificationnotviewed");
	}

	NotificationServices.getNotification=function(NotificationId)
	{
		return $http.get(Url+"/getnotification/"+NotificationId);
	}
	
	
	return notificationServices;
	
	
	
	
})