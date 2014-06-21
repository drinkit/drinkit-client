/**
 * Created by Crabar on 24.05.2014.
 */
package controllers
{
    import components.SignupWindow;

    import controllers.supportClasses.Services;

    import flash.net.URLRequestMethod;

    import flash.net.URLVariables;

    import mx.controls.Alert;

    import utils.supportClasses.JSRequest;

    import utils.ServiceUtil;
    import utils.URLContentTypes;

    public class SignUpController extends UserController
    {
        public function SignUpController()
        {
        }

        public function registerUser(email:String, password:String, displayName:String):void
        {
            var variables:URLVariables = new URLVariables();
            variables["email"] = email;
            variables["password"] = encryptPassword(password);
            variables["displayName"] = displayName;
            var request:JSRequest = new JSRequest(URLRequestMethod.POST);
            request.bodyParams = variables.toString();
            request.contentType = URLContentTypes.FORM_URLENCODED;
            request.expectedStatus = 201;

            ServiceUtil.instance.sendRequest(Services.REGISTER_USER, request, onRegister);
        }

        private function onRegister(response:String):void
        {
            SignupWindow.close();
            Alert.show("Вы успешно зарегестрированы!");
        }
    }
}
