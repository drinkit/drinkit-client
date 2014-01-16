package models
{
	import flash.display.Stage;
	
	import io.oauth.OAuth;
	import io.oauth.OAuthEvent;
	import io.oauth.OAuthHTTPService;
	
	import mx.controls.Alert;
	import mx.rpc.events.ResultEvent;
	
	public class FlexOAuthIOModel implements IAuthModel
	{
		private static var _instance:FlexOAuthIOModel;
		
		public static function get instance():FlexOAuthIOModel
		{
			if (!_instance)
				_instance = new FlexOAuthIOModel(new PrivateConstructor());
			
			return _instance;
		}
		
		public function FlexOAuthIOModel(value:PrivateConstructor)
		{
		}
		
		private var _oauth:OAuth;
		
		public function init(stage:Stage):void
		{
			_oauth = new OAuth("w5lnaZLTxqd0EeBl99PrcUK3UBo");
			_oauth.addEventListener(OAuthEvent.TOKEN, onTokenReceive);
		}
		
		private function onTokenReceive(event:OAuthEvent):void
		{
			var request:OAuthHTTPService = event.http();
			request.url = "/oauth2/v1/userinfo";
			request.resultFormat = "json";
			request.addEventListener(ResultEvent.RESULT, onResultReceive);
			request.send();
		}
		
		private function onResultReceive(event:ResultEvent):void
		{
			Alert.show(event.result.toString());
		}
		
		public function login(provider:String, action:String):void
		{
			_oauth.popup(provider);
		}
	}
}

class PrivateConstructor {}