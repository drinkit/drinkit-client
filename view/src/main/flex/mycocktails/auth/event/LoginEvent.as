package mycocktails.auth.event
{
	
    import flash.events.Event;

    public class LoginEvent extends Event
    {
        public static const LOGIN: String = "onLogin";

        public var username : String;
        public var password : String;
        public var output:Object;

        public function LoginEvent(type:String, bubbles:Boolean=true, cancelable:Boolean=false)
        {
            super(type, bubbles, cancelable);
        }
    }

}