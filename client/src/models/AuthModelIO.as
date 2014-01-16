package models
{
	import flash.display.Stage;
	import flash.external.ExternalInterface;
	
	import io.oauth.OAuth;
	import io.oauth.OAuthEvent;
	
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
		
		public static const LOGIN_URLS:Object = {"google":"/oauth2/v1/userinfo"};
		
		public function init(stage:Stage):void
		{
			ExternalInterface.call("initOAuthIO");
			ExternalInterface.addCallback("onSocialLogin", onSocialLogin);
		}
		
		private function onSocialLogin(error:String, result:String):void
		{
			Alert.show(result);
		}
		
		public function login(provider:String, action:String):void
		{
			ExternalInterface.call("socialLogin", provider, action);
		}
	}
}

class PrivateConstructor
{
}
