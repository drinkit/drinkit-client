package utils
{
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	import flash.net.URLVariables;
	
	import mx.controls.Alert;

	public class ServiceUtil
	{
		public static var serviceAddress:String = "";
		
		public static function requestData(functionName:String, params:Object, handler:Function):URLLoader
		{
			var request:URLRequest = new URLRequest(serviceAddress + functionName);
			request.method = URLRequestMethod.GET;
			request.data = params;
			var query:URLLoader = new URLLoader();
			query.addEventListener(Event.COMPLETE, handler);
			query.addEventListener(IOErrorEvent.IO_ERROR, onIOError);
			query.load(request);
			return query;
		}
		
		public static function sendData(functionName:String, params:Object, handler:Function):URLLoader
		{
			var request:URLRequest = new URLRequest(serviceAddress + functionName);
			request.method = URLRequestMethod.POST;
			request.data = params;
			var query:URLLoader = new URLLoader();
			query.addEventListener(Event.COMPLETE, handler);
			query.addEventListener(IOErrorEvent.IO_ERROR, onIOError);
			query.load(request);
			return query;
		}
		
		public static function requestDataWithDigest(functionName:String, params:Object, handler:Function):void
		{
			var request:URLRequest = new URLRequest(serviceAddress + functionName);
			request.method = URLRequestMethod.GET;
			request.data = params;
			var query:HTTPDigest = new HTTPDigest("test", "test");
			query.addEventListener(Event.COMPLETE, handler);
			query.addEventListener(IOErrorEvent.IO_ERROR, onIOError);
			query.load(request);
		}
		
		private static function onIOError(event:IOErrorEvent):void
		{
			Alert.show("Ошибка соединения с сервером!");
		}
	}
	
	
}