/**
 * Created by Crabar on 28.06.2014.
 */
package controllers
{
    import flash.external.ExternalInterface;

    import models.supportClasses.SocialNetParser;

    import mx.controls.Alert;

    public class OAuthController implements IOAuthController
    {
        public function OAuthController(authController:AuthController, signUpController:SignUpController)
        {
            _authController = authController;
            _signUpController = signUpController;
        }

        private var _authController:AuthController;
        private var _signUpController:SignUpController;

        public function init():void
        {
            ExternalInterface.call("initOAuthIO");
            ExternalInterface.addCallback("onSocialLogin", onSocialLogin);
        }

        public function login(provider:String):void
        {
            _lastLoggedProvider = provider;
            ExternalInterface.call("socialLogin", provider);
        }

        private var _lastLoggedProvider:String;
        private var _lastLoggedUser:Object;

        private function onSocialLogin(result:Object):void
        {
            var userID:Number = SocialNetParser.getSocialNetUserId(_lastLoggedProvider, result.raw);

            if (isNaN(userID))
            {
                Alert.show("Ошибка связи с социальной сетью!");
                return;
            }

            var userLogin:String = generateLogin(_lastLoggedProvider, userID);
            var userPassword:String = generatePassword(_lastLoggedProvider, userID);

            _lastLoggedUser = {login: userLogin, password: userPassword, displayName: result.name};

            _authController.login(userLogin, userPassword, onUserNotExists);
        }

        private function generateLogin(provider:String, id:Number):String
        {
            return provider + id.toString();
        }

        private function generatePassword(provider:String, id:Number):String
        {
            return provider + id.toString();
        }

        private function onUserNotExists(response:String):void
        {
            _signUpController.registerUser(_lastLoggedUser.login, _lastLoggedUser.password, _lastLoggedUser.displayName, true);
            _lastLoggedUser = null;
        }
    }
}
