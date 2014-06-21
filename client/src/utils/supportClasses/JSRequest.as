package utils.supportClasses
{
    import flash.net.URLRequestMethod;

    public class JSRequest
    {
        public function JSRequest(method:String = URLRequestMethod.GET)
        {
            _method = method;
        }

        private var _method:String;

        public function get method():String
        {
            return _method;
        }

        public var queryParams:String;
        public var bodyParams:String;
        public var contentType:String;
        public var expectedStatus:uint = 200;

    }
}
