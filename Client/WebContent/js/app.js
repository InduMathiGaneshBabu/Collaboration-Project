/**
 * 
 */

var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/addjob',{controller:'jobctrl',templateUrl:'views/jobform.html'})
	.when('/getalljobs',{controller:'jobctrl',templateUrl:'views/Joblist.html'})
	.when('/register',{controller:'UserCtrl',templateUrl:'views/registerform.html'})
	.when('/login',{controller:'UserCtrl',templateUrl:'views/loginpage.html'})
	.when('/updateuserprofile',{controller:'UserCtrl',templateUrl:'views/UpdateUserProfile.html'})
	.when('/addblog',{controller:'BlogPostCtrl',templateUrl:'views/AddBlogForm.html'})
	.when('/blogswaitingforapproval',{controller:'BlogPostCtrl',templateUrl:'views/BlogsWaitingForApproval.html'})
	.when('/blogsapproved',{controller:'BlogPostCtrl',templateUrl:'views/BlogsApproved.html'})
	.when('/getblog/:blogpostid',{controller:'BlogInDetailctrl',templateUrl:'views/BlogApproveForm.html'})
	.when('/getblogapproved/:blogpostid',{controller:'BlogInDetailctrl',templateUrl:'views/BlogInDetail.html'})
	.when('/updatelikes/:blogpostid',{controller:'BlogInDetailctrl',templateUrl:'views/BlogInDetail.html'})
	.when('/addcomment',{controller:'BlogInDetailctrl',templateUrl:'views/BlogInDetail.html'})
	.when('/getblogcomments/:blogpostid',{controller:'BlogInDetailctrl',templateUrl:'views/BlogInDetail.html'})
	.when('/getlikelist/:blogpostid',{controller:'BlogInDetailctrl',templateUrl:'views/BlogInDetail.html'})
	.when('/approveblog',{controller:'BlogInDetailctrl',templateUrl:'views/BlogInDetail.html'})
	.when('/home',{controller:'Notificationctrl',templateUrl:'views/home.html'})
	.when('/getallnotificationnotviewed',{controller:'Notificationctrl',templateUrl:'views/home.html'})
	.when('/getnotification/:notificationid',{controller:'Notificationctrl',templateUrl:'views/NotificationDetail.html'})
	.otherwise({templateUrl:'views/home.html'})
	
})

app.run(function($rootScope,UserServices,$location,$cookieStore){
	
	alert("$rootScope get newly instantiated");
	
if($rootScope.user==undefined)
	{
	$rootScope.user=$cookieStore.get('userdetail')
	alert("User Details are retrived");
	}
$rootScope.logout=function()
{
	UserServices.logout().then(function(response){
		
		delete $rootScope.user
		$cookieStore.remove('userdetail')
		$location.path('/login')
		$scope.user={ }
		
	} ,function(response){
		if(response.status==401)
			$location.path('/login')
	})
	
	}


	
})
