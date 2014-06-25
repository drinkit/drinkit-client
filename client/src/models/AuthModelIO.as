package models
{
	import flash.display.Stage;
	import flash.external.ExternalInterface;
    import flash.media.scanHardware;

    import mx.controls.Alert;
	
	public class AuthModelIO implements IAuthModel
	{
		private static var _instance:AuthModelIO;
		
		public static function get instance():AuthModelIO
		{
			if (!_instance)
				_instance = new AuthModelIO(new PrivateConstructor());
			
			return _instance;
		}
		
		public function AuthModelIO(value:PrivateConstructor)
		{
		}
		
//		public static const LOGIN_URLS:Object = {"google":"/oauth2/v1/userinfo", "vk": ""};
		
		public function init(stage:Stage):void
		{
			ExternalInterface.call("initOAuthIO");
			ExternalInterface.addCallback("onSocialLogin", onSocialLogin);
		}
		
		private function onSocialLogin(result:Object):void
		{
//			var userInfo:Object = JSON.parse(result);
			checkSocialUser(result.email);
		}
		
		private function generateFakeSocialPassword():String
		{
			return "";
		}
		
		private function checkSocialUser(email:String):void
		{
			
		}
		
		public function login(provider:String):void
		{
			ExternalInterface.call("socialLogin", provider);
		}
	}
}

class PrivateConstructor
{
}
