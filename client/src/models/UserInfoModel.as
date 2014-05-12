package models
{	
	import controllers.supportClasses.Services;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import models.events.AuthEvent;
	
	import mx.messaging.config.ServerConfig;
	
	import utils.ServiceUtil;
	
	[Event(name="authSuccess", type="models.events.AuthEvent")]
	[Event(name="authError", type="models.events.AuthEvent")]
	[Event(name="logout", type="models.events.AuthEvent")]
	public class UserInfoModel extends EventDispatcher
	{
		private static var _instance:UserInfoModel;
		
		[Bindable(event="logout")]
		[Bindable(event="authSuccess")]
		public function get displayName():String
		{
			return _displayName;
		}
		
		public function get password():String
		{
			return _password;
		}
		
		public function get email():String
		{
			return _email;
		}
		
		[Bindable(event="logout")]
		[Bindable(event="authSuccess")]
		public function get role():uint
		{
			return _userRole;
		}
		
		public static function get instance():UserInfoModel
		{
			if (!_instance)
				_instance = new UserInfoModel();
			
			return _instance;
		}
		
		public function UserInfoModel()
		{
			ServiceUtil.instance.addEventListener(AuthEvent.AUTH_ERROR, onAuthError);
		}
		
		public function setUserCredentials(email:String, password:String):void
		{
			_email = email;
			_password = password;
			//
			requestUserInfo();
		}
		
		public function logOut():void
		{
			_email = "";
			_password = "";
			_displayName = "";
			ServiceUtil.instance.clearDigest();
			_userRole = UserRoles.ANONYMOUS;
			//
			dispatchEvent(new AuthEvent(AuthEvent.LOGOUT));
		}
		
		private var _email:String = "";
		private var _password:String = "";
		private var _userRole:uint = UserRoles.ANONYMOUS;
		//
		private var _displayName:String;
		
		public function requestUserInfo():void
		{
			ServiceUtil.instance.requestData(Services.GET_USER_INFO, null, onGetUserInfo);
		}
		
		private function getHighestRole(roles:Array):uint
		{
			var curRole:uint = UserRoles.ANONYMOUS;
			
			for each (var role:Object in roles)
			{
				if (curRole < UserRoles.USER && role.authority == "ROLE_USER")
					curRole = UserRoles.USER;
				
				if (curRole < UserRoles.ADMIN && role.authority == "ROLE_ADMIN")
					curRole = UserRoles.ADMIN;
			}
			
			return curRole;
		}
		
		private function onGetUserInfo(response:String):void
		{
			// parse response and save user info
			var responseJSON:Object = JSON.parse(response);
			_displayName = responseJSON.displayName;
			_userRole = getHighestRole(responseJSON.authorities);
			//
			dispatchEvent(new AuthEvent(AuthEvent.AUTH_SUCCESS));
		}
		
		private function onAuthError(event:AuthEvent):void
		{
			dispatchEvent(event);
		}
	
	}
}
