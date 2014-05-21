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

        [Bindable]
        public var displayName:String;

        [Bindable]
        public var role:uint = UserRoles.ANONYMOUS;

		public function get password():String
		{
			return _password;
		}
		
		public function get email():String
		{
			return _email;
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
		}
		
		public function clear():void
		{
			_email = "";
			_password = "";
			displayName = "";
			role = UserRoles.ANONYMOUS;
		}
		
		private var _email:String = "";
		private var _password:String = "";
		
		private function onAuthError(event:AuthEvent):void
		{
			dispatchEvent(event);
		}
	
	}
}
