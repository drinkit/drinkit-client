package models
{
	import com.gigya.socialize.GSAPI;
	import com.gigya.socialize.GSResponse;
	
	import controllers.supportClasses.Services;
	
	import flash.display.Stage;
	import flash.events.Event;
	
	import utils.ServiceUtil;

	public class AuthModel
	{
		private static const API_KEY:String = "3_yXNSDzZrmilfB2yLAa4e-OeZw_99JcdxU0tYMMYP4ZhYWobv8SvHLg1-faJoyaWl";
		
		private static var _instance:AuthModel;
		
		public static function get instance():AuthModel
		{
			if (!_instance)
				_instance = new AuthModel(new PrivateConstructor());
			
			return _instance;
		}
		
		public function AuthModel(value:PrivateConstructor)
		{
		}
		
		private var gsAPI:GSAPI;
		
		public function initGigyaAPI(stage:Stage):void
		{
			gsAPI = new GSAPI(API_KEY, stage);
		}
		
		private function loginCallback(method:String, response:GSResponse = null, context:* = null):void
		{
			if (response.getErrorCode() == 0)
			{
				ServiceUtil.sendData(Services.AUTH_GIGYA, response.getResponseText(), onServerLogin);
			}
		}
		
		private function onServerLogin(event:Event):void
		{
			trace(event.target.data);
		}
		
		public function socialLogin(provider:String):void
		{
			var loginParams:Object = {provider: provider};
			gsAPI.login(loginParams, loginCallback);
		}
	}
}
class PrivateConstructor {}