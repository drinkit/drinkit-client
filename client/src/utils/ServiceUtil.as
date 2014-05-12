package utils
{
	import components.LoginWindow;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.external.ExternalInterface;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	
	import models.UserInfoModel;
	import models.events.AuthEvent;
	
	import mx.controls.Alert;
	
	[Event(name="authError", type="models.events.AuthEvent")]
	public class ServiceUtil extends EventDispatcher
	{
		private static var _instance:ServiceUtil;
		
		public static function get instance():ServiceUtil
		{
			if (!_instance)
				_instance = new ServiceUtil();
			
			return _instance;
		}
		
		public function init(serviceAddress:String):void
		{
			_serviceAddress = serviceAddress;
			ExternalInterface.addCallback("onRequestComplete", onRequestComplete);
			ExternalInterface.addCallback("processAuth", processAuth);
			ExternalInterface.addCallback("onError", onError);
		}
		
		public function clearDigest():void
		{
			_currentAuthHeader = null;
		}
		
		public function onRequestComplete(response:String, requestID:String):void
		{
			var handler:Function = (_waitingHandlers[requestID] as Function);
			delete _waitingHandlers[requestID];
			handler(response);
		}
		
		private var _serviceAddress:String = "";
		private var _waitingHandlers:Object = new Object();
		private var _currentAuthHeader:Object = null;
		
		/**
		 *
		 * @param functionName
		 * @param params
		 * @param handler function(responseBody:String):void
		 */
		public function requestData(functionName:String, params:Object, handler:Function):void
		{
			var requestID:String = functionName + new Date().time.toString();
			var paramsString:String = params ? params.toString() : "";
			_waitingHandlers[requestID] = handler;
			ExternalInterface.call("sendRequest", URLRequestMethod.GET, _serviceAddress + functionName, paramsString, [_currentAuthHeader], requestID);
		}
		
		public function processAuth(authResponseHeader:String, method:String, address:String, paramsString:String, requestID:String):void
		{
			if (UserInfoModel.instance.email == "" || UserInfoModel.instance.password == "")
			{
				LoginWindow.show();
				return;
			}
			
			if (!_currentAuthHeader || isNonceExpired(authResponseHeader))
			{
				var digest:Digest = new Digest(UserInfoModel.instance.email, UserInfoModel.instance.password, method);
				_currentAuthHeader = digest.generateAuthHeader(authResponseHeader);
				ExternalInterface.call("sendRequest", method, address, paramsString, [_currentAuthHeader], requestID);
			}
			else
			{
				LoginWindow.show();
				_currentAuthHeader = null;
				dispatchEvent(new AuthEvent(AuthEvent.AUTH_ERROR));
			}
				
		}
		
		private function isNonceExpired(headers:String):Boolean
		{
			return headers.indexOf('stale="true"') > 0;
		}
		
		public function onError(errorText:String):void
		{
			Alert.show("Ошибка соединения с сервером!");
		}
		
		public function sendData(functionName:String, params:Object, handler:Function):void
		{
			var requestID:String = functionName + new Date().time.toString();
			var paramsString:String = params ? params.toString() : "";
			_waitingHandlers[requestID] = handler;
			ExternalInterface.call("sendRequest", URLRequestMethod.POST, _serviceAddress + functionName, paramsString, handler, [_currentAuthHeader]);
		}
	}


}
