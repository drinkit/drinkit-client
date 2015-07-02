/**
 * Created by Crabar on 21.05.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import models.MyBarModel;

    import models.UserInfoModel;
    import models.UserRoles;
    import models.events.AuthEvent;
    import models.supportClasses.BarItem;

    import utils.CookieUtil;
    import utils.JSONInstantiator;
    import utils.ServiceUtil;
    import utils.supportClasses.JSRequest;

    public class AuthController extends UserController
    {
        private static const USER_COOKIE_NAME:String = "user_credentials";

        public function AuthController()
        {
        }

        public function tryLoginWithStoredUserCredentials():void
        {
            var userCredentials:Object = JSON.parse(String(CookieUtil.getCookie(USER_COOKIE_NAME)));

            if (userCredentials)
            {
                UserInfoModel.instance.setUserCredentials(userCredentials.login, userCredentials.password);
                requestUserInfo();
            }

        }

        public function login(user:String, password:String, customAuthHandler:Function = null):void
        {
            UserInfoModel.instance.setUserCredentials(user, encryptPassword(password));
            requestUserInfo(customAuthHandler);
        }

        public function logout():void
        {
            clearStoredUserCredentials();
            UserInfoModel.instance.clear();
            ServiceUtil.instance.clearDigest();
            UserInfoModel.instance.dispatchEvent(new AuthEvent(AuthEvent.LOGOUT));
        }

        public function requestUserInfo(customAuthHandler:Function = null):void
        {
            var request:JSRequest = new JSRequest();

            if (customAuthHandler)
                request.expectedErrorStatus = 401;

            ServiceUtil.instance.sendRequest(Services.GET_USER_INFO, request, onGetUserInfo, customAuthHandler);
        }

        private function getHighestRole(roles:Array):uint
        {
            var curRole:uint = UserRoles.ANONYMOUS;

            for each (var role:Object in roles)
            {
                if (curRole < UserRoles.USER && role.authority == "ROLE_USER")
                    curRole = UserRoles.USER;

                if (curRole < UserRoles.ADMIN && role.authority == "ROLE_ADMIN")
                    curRole = UserRoles.ADMIN;
            }

            return curRole;
        }

        private function onGetUserInfo(response:String):void
        {
            // parse response and save user info
            var responseJSON:Object = JSON.parse(response);
            UserInfoModel.instance.displayName = responseJSON.displayName;
            UserInfoModel.instance.id = responseJSON.id;
            UserInfoModel.instance.role = getHighestRole(responseJSON.authorities);
            MyBarModel.instance.barItems = Vector.<BarItem>(JSONInstantiator.createInstance(responseJSON.barItems, BarItem) as Array);
            //
            storeUserCredentials(UserInfoModel.instance.email, UserInfoModel.instance.password);
            //
            UserInfoModel.instance.dispatchEvent(new AuthEvent(AuthEvent.AUTH_SUCCESS));
        }

        private function clearStoredUserCredentials():void
        {
            CookieUtil.deleteCookie(USER_COOKIE_NAME);
        }

        private function storeUserCredentials(login:String, hashedPassword:String):void
        {
            CookieUtil.setCookie(USER_COOKIE_NAME, JSON.stringify({login: login, password: hashedPassword}), 7);
        }
    }
}
