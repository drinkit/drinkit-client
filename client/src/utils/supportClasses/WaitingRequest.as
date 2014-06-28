/**
 * Created by Crabar on 21.06.2014.
 */
package utils.supportClasses
{
    public class WaitingRequest
    {
        public function WaitingRequest(request:JSRequest, handler:Function, errorHandler:Function)
        {
            _request = request;
            _handler = handler;
            _errorHandler = errorHandler;
        }

        private var _request:JSRequest;

        public function get request():JSRequest
        {
            return _request;
        }

        private var _handler:Function;

        public function get handler():Function
        {
            return _handler;
        }

        private var _errorHandler:Function;

        public function get errorHandler():Function
        {
            return _errorHandler;
        }
    }
}
