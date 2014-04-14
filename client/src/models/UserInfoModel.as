package models
{
	import flash.events.EventDispatcher;
	
	import models.events.AuthEvent;
	
	import utils.ServiceUtil;
	
	[Event(name="authSuccess", type="models.events.AuthEvent")]
	[Event(name="authError", type="models.events.AuthEvent")]
	public class UserInfoModel extends EventDispatcher
	{
		private static var _instance:UserInfoModel;
		
		public function get password():String
		{
			return _password;
		}
		
		public function get email():String
		{
			return _email;
		}
		
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
		
		private var _email:String = "";
		private var _password:String = "";
		private var _userRole:uint = UserRoles.ANONYMOUS;
		//
		private var _displayName:String;
		
		public function requestUserInfo():void
		{
			ServiceUtil.instance.requestData("", null, onGetUserInfo);
		}
		
		private function onGetUserInfo(response:String):void
		{
			dispatchEvent(new AuthEvent(AuthEvent.AUTH_SUCCESS));
		}
		
		private function onAuthError(event:AuthEvent):void
		{
			dispatchEvent(event);
		}
	
	}
}
