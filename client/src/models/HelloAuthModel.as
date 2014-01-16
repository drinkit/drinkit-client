package models
{
	import flash.display.Stage;
	import flash.external.ExternalInterface;
	
	import mx.controls.Alert;

	public class HelloAuthModel implements IAuthModel
	{
		private static var _instance:HelloAuthModel;
		
		public static function get instance():HelloAuthModel
		{
			if (!_instance)
				_instance = new HelloAuthModel(new PrivateConstructor());
			
			return _instance;
		}
		
		public function HelloAuthModel(value:PrivateConstructor)
		{
		}

		
		public function init(stage:Stage):void
		{
			ExternalInterface.addCallback("onSocialLogin", onSocialLogin);
		}
		
		public function login(provider:String, action:String):void
		{
			ExternalInterface.call("socialLogin", provider, action);
		}
		
		public function onSocialLogin(event:String):void
		{
			Alert.show(event);
		}
	}
}

class PrivateConstructor {}