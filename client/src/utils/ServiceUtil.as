package utils
{
	import components.LoginWindow;
	
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.external.ExternalInterface;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	
	import mx.controls.Alert;
	
	public class ServiceUtil
	{
		public static function init(serviceAddress:String):void
		{
			_serviceAddress = serviceAddress;
			ExternalInterface.addCallback("onRequestComplete", onRequestComplete);
			ExternalInterface.addCallback("processAuth", processAuth);
			ExternalInterface.addCallback("onError", onError);
		}
		
		public static function setUserCredentials(userName:String, password:String):void
		{
			_username = userName;
			_password = password;
		}
		
		public static function onRequestComplete(response:String, address:String, params:Object, handlerName:String):void
		{
			var paramsString:String = params ? params.toString() : "";
			var handler:Function = (_waitingHandlers[address + paramsString] as Function);
			delete _waitingHandlers[address + paramsString];
			handler(response);
		}
		
		private static var _username:String = "";
		private static var _password:String = "";
		//
		private static var _serviceAddress:String = "";
		private static var _waitingHandlers:Object = new Object();
		private static var _currentAuthHeader:Object = null;
		
		/**
		 *
		 * @param functionName
		 * @param params
		 * @param handler function(responseBody:String):void
		 */
		public static function requestData(functionName:String, params:Object, handler:Function):void
		{
			var paramsString:String = params ? params.toString() : "";
			_waitingHandlers[_serviceAddress + functionName + paramsString] = handler;
			ExternalInterface.call("sendRequest", URLRequestMethod.GET, _serviceAddress + functionName, paramsString, handler, [_currentAuthHeader]);
		}
		
		public static function processAuth(authResponseHeader:String, address:String, params:Object, handlerName:String):void
		{
			if (_username == "" || _password == "")
			{
				LoginWindow.show();
				return;
			}
			
			var digest:Digest = new Digest(_username, _password, "GET");
			_currentAuthHeader = digest.generateAuthHeader(authResponseHeader);
			ExternalInterface.call("requestData", address, params, handlerName, [_currentAuthHeader]);			
		}
		
		public static function onError(errorText:String):void
		{
			Alert.show("Ошибка соединения с сервером!");
		}
		
		public static function sendData(functionName:String, params:Object, handler:Function):void
		{
			var paramsString:String = params ? params.toString() : "";
			_waitingHandlers[_serviceAddress + functionName + paramsString] = handler;
			ExternalInterface.call("sendRequest", URLRequestMethod.POST, _serviceAddress + functionName, paramsString, handler, [_currentAuthHeader]);
		}
	}


}
