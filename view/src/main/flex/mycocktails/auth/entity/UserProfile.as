package mycocktails.auth.entity
{
	[RemoteClass(alias="mycocktails.auth.entity.UserProfile")]
	[Bindable]	
	public class UserProfile
	{
	
		private var _password:String;
        private var _loginId:String;

		
    	
		public function UserProfile()
		{
		}
		
		
		 public function set password(value:String):void
        {
            _password = value;
        }

        public function get password():String
        {
            return _password;
        }

        public function set loginId(value:String):void
        {
            _loginId = value;
        }

        public function get loginId():String
        {
            return _loginId;
        }


	}
}