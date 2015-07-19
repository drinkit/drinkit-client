package utils
{
    import com.adobe.crypto.MD5;

    import components.LoginWindow;

    import flash.events.EventDispatcher;
    import flash.external.ExternalInterface;

    import models.UserInfoModel;
    import models.events.AuthEvent;

    import mx.controls.Alert;

    import utils.supportClasses.JSRequest;
    import utils.supportClasses.WaitingRequest;

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
        private var _waitingRequests:Object = {};
        private var _digests:Object = {};

        private var _serviceAddress:String = "";

        public function get serviceAddress():String
        {
            return _serviceAddress;
        }

        public function init(serviceAddress:String):void
        {
            _serviceAddress = serviceAddress;

            ExternalInterface.addCallback("onRequestComplete", onRequestComplete);
            ExternalInterface.addCallback("processAuth", processAuth);
            ExternalInterface.addCallback("onExpectedError", onExpectedError);
            ExternalInterface.addCallback("onError", onError);
        }

        public function clearDigest():void
        {
            _digests = {};
        }

        public function onRequestComplete(response:String, requestID:String):void
        {
            var handler:Function = (_waitingRequests[requestID] as WaitingRequest).handler;
            delete _waitingRequests[requestID];

            if (handler != null)
                handler(response);
        }

        public function onExpectedError(response:String, requestID:String):void
        {
            var errorHandler:Function = (_waitingRequests[requestID] as WaitingRequest).errorHandler;
            delete _waitingRequests[requestID];

            if (errorHandler != null)
                errorHandler(response);
        }

        public function processAuth(response:String, authResponseHeader:String, address:String, requestID:String):void
        {
            if (UserInfoModel.instance.email == "" || UserInfoModel.instance.password == "")
            {
                LoginWindow.show();
                return;
            }

            var curRequest:JSRequest = (_waitingRequests[requestID] as WaitingRequest).request;
            var digestHeader:Object = _digests[curRequest.method];

            if (!digestHeader || isNonceExpired(authResponseHeader))
            {
                var digest:Digest = new Digest(UserInfoModel.instance.email, UserInfoModel.instance.password, curRequest.method);
                _digests[curRequest.method] = digest.generateAuthHeader(authResponseHeader);
                var headers:Array = prepareHeaders(curRequest);
                ExternalInterface.call("sendRequest", curRequest.method, address, curRequest.queryParams, curRequest.bodyParams, headers, curRequest.expectedStatus, curRequest.expectedErrorStatus, requestID);
            }
            else if (curRequest.expectedErrorStatus == 401)
            {
                clearDigest();
                onExpectedError(response, requestID);
            }
            else
            {
                LoginWindow.show();
                clearDigest();
                dispatchEvent(new AuthEvent(AuthEvent.AUTH_ERROR));
            }
        }

        public function onError(errorText:String):void
        {
            Alert.show("Ошибка соединения с сервером!");
        }

        public function sendRequest(functionName:String, request:JSRequest, handler:Function, errorHandler:Function = null):void
        {
            var requestID:String = prepareRequest(functionName, request, handler, errorHandler);
            var headers:Array = prepareHeaders(request);
            ExternalInterface.call("sendRequest", request.method, _serviceAddress + functionName, request.queryParams, request.bodyParams, headers, request.expectedStatus, request.expectedErrorStatus, requestID);
        }

        private function prepareHeaders(request:JSRequest):Array
        {
            var headers:Array = [];

            if (request.contentType)
                headers.push({"name": "Content-Type", "value": request.contentType});

            headers.push(_digests[request.method]);
            return headers;
        }

        private function isNonceExpired(headers:String):Boolean
        {
            return headers.indexOf('stale="true"') > 0;
        }

        private function prepareRequest(functionName:String, request:JSRequest, handler:Function, errorHandler:Function):String
        {
            var requestID:String =  MD5.hash(functionName + new Date().time.toString() + request.bodyParams + request.queryParams);
            _waitingRequests[requestID] = new WaitingRequest(request, handler, errorHandler);
            return requestID;
        }
    }


}
