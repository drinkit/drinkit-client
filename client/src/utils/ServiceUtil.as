package utils
{
    import components.LoginWindow;

    import flash.events.EventDispatcher;
    import flash.external.ExternalInterface;
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

        private var _serviceAddress:String = "";
        private var _waitingHandlers:Object = new Object();
        private var _currentAuthHeader:Object = null;

        public function init(serviceAddress:String):void
        {
            _serviceAddress = serviceAddress;
            trace(ExternalInterface.available);
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

        public function onError(errorText:String):void
        {
            Alert.show("Ошибка соединения с сервером!");
        }

        private function prepareHeaders(request:JSRequest):Array
        {
            var headers:Array = [];

            if (request.contentType)
                headers.push({"name": "Content-Type", "value": request.contentType});

            headers.push(_currentAuthHeader);
            return headers;
        }

        public function sendRequest(functionName:String, request:JSRequest, handler:Function):void
        {
            var requestID:String = prepareRequest(functionName, handler);
            var headers:Array = prepareHeaders(request);
            ExternalInterface.call("sendRequest", request.method, _serviceAddress + functionName, request.queryParams, request.bodyParams, headers, request.expectedStatus, requestID);
        }

        private function isNonceExpired(headers:String):Boolean
        {
            return headers.indexOf('stale="true"') > 0;
        }

        private function prepareRequest(functionName:String, handler:Function):String
        {
            var requestID:String = functionName + new Date().time.toString();
            _waitingHandlers[requestID] = handler;
            return requestID;
        }
    }


}
