package mycocktails.auth.viewmodel
{
	import mycocktails.auth.entity.UserProfile;
	import mycocktails.auth.event.LoginEvent;
	
	import mx.controls.Alert;
	
	public class AuthManager
	{
		
		public var loggedInUser:UserProfile = null;
		
		public function AuthManager()
		{
		}
		
		
		public function transformLoginEvent(loginEvent:LoginEvent):UserProfile
		{
			var user:UserProfile = UserProfile(loginEvent.output);			
			return user; 
		}
		
		public function validateLoginResult(result:UserProfile):void
		{
			 if(result!=null)
			 {
			 	this.loggedInUser = result;
			 	Alert.show("Login Successful");
			 	
			 } else {
			 	Alert.show("Login not Successful");
			 }			
				
		}

	}
}