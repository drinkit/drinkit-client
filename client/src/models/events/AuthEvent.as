package models.events
{
    import flash.events.Event;

    public class AuthEvent extends Event
    {
        public static const AUTH_SUCCESS:String = "authSuccess";
        public static const AUTH_ERROR:String = "authError";
        public static const LOGOUT:String = "logout";

        public function AuthEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
        }

        override public function clone():Event
        {
            return new AuthEvent(type, bubbles, cancelable);
        }
    }
}