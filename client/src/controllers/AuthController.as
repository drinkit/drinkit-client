/**
 * Created by Crabar on 21.05.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import models.UserInfoModel;
    import models.UserRoles;
    import models.events.AuthEvent;

    import utils.ServiceUtil;

    public class AuthController
    {
        public function AuthController()
        {
        }

        public function login(user:String, password:String):void
        {
            UserInfoModel.instance.setUserCredentials(user, password);
            requestUserInfo();
        }

        public function logout():void
        {
            UserInfoModel.instance.clear();
            ServiceUtil.instance.clearDigest();
            UserInfoModel.instance.dispatchEvent(new AuthEvent(AuthEvent.LOGOUT));
        }

        public function requestUserInfo():void
        {
            ServiceUtil.instance.requestData(Services.GET_USER_INFO, null, onGetUserInfo);
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
