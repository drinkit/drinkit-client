/**
 * Created by Crabar on 21.05.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import models.UserInfoModel;
    import models.UserRoles;
    import models.events.AuthEvent;

    import utils.supportClasses.JSRequest;

    import utils.ServiceUtil;

    public class AuthController extends UserController
    {
        public function AuthController()
        {
        }

        public function login(user:String, password:String, customAuthHandler:Function = null):void
        {
            UserInfoModel.instance.setUserCredentials(user, encryptPassword(password));
            requestUserInfo(customAuthHandler);
        }

        public function logout():void
        {
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
            UserInfoModel.instance.role = getHighestRole(responseJSON.authorities);
            //
            UserInfoModel.instance.dispatchEvent(new AuthEvent(AuthEvent.AUTH_SUCCESS));
        }
    }
}
