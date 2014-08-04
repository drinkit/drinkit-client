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

    import utils.ServiceUtil;
    import utils.URLContentTypes;
    import utils.supportClasses.JSRequest;

    public class SignUpController extends UserController
    {
        public function SignUpController()
        {
        }

        private var _email:String;
        private var _password:String;

        private var _isSilentMode:Boolean;

        public function registerUser(email:String, password:String, displayName:String, isSilentMode:Boolean = false):void
        {
            _email = email;
            _password = password;
            _isSilentMode = isSilentMode;
            //
            var variables:URLVariables = new URLVariables();
            variables["email"] = email;
            variables["password"] = encryptPassword(password);
            variables["displayName"] = displayName;
            var request:JSRequest = new JSRequest(URLRequestMethod.POST);
            request.bodyParams = variables.toString();
            request.contentType = URLContentTypes.FORM_URLENCODED;
            request.expectedStatus = 201;
            request.expectedErrorStatus = 403;

            ServiceUtil.instance.sendRequest(Services.REGISTER_USER, request, onRegister, onRegisterError);
        }

        private function onRegister(response:String):void
        {
            SignupWindow.close();
            var authController:AuthController = new AuthController();
            authController.login(_email, _password);

            if (!_isSilentMode)
                Alert.show("Вы успешно зарегестрированы!");
        }

        private function onRegisterError(response:String):void
        {
            SignupWindow.close();
            Alert.show("Пользователь с таким именем уже существует!");
        }
    }
}
