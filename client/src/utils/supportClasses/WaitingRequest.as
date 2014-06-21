/**
 * Created by Crabar on 21.06.2014.
 */
package utils.supportClasses
{
    public class WaitingRequest
    {
        public function WaitingRequest(request:JSRequest, handler:Function)
        {
            _request = request;
            _handler = handler;
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
    }
}
