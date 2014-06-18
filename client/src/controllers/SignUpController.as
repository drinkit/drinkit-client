/**
 * Created by Crabar on 24.05.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import flash.net.URLVariables;

    import utils.ServiceUtil;

    public class SignUpController
    {
        public function SignUpController()
        {
        }

        public function registerUser(email:String, password:String, displayName:String):void
        {
            var variables:URLVariables = new URLVariables();
            variables["email"] = email;
            variables["password"] = password;
            variables["displayName"] = displayName;
            ServiceUtil.instance.postData(Services.REGISTER_USER, variables.toString(), onRegister);
        }

        private function onRegister(response:String):void
        {
            //
        }
    }
}
