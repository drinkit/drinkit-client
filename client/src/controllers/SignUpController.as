/**
 * Created by Crabar on 24.05.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import utils.ServiceUtil;

    public class SignUpController
    {
        public function SignUpController()
        {
        }

        public function registerUser(email:String, password:String):void
        {
            ServiceUtil.instance.sendData(Services.REGISTER_USER, {"email": email, "password": password}, onRegister);
        }

        private function onRegister(response:String):void
        {
            //
        }
    }
}
