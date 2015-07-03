/**
 * Created by Crabar on 29.06.2015.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import flash.events.Event;
    import flash.net.URLRequestMethod;

    import models.MyBarModel;
    import models.UserInfoModel;
    import models.supportClasses.BarItem;
    import models.supportClasses.BarItem;
    import models.supportClasses.Ingredient;

    import utils.JSONInstantiator;

    import utils.ServiceUtil;
    import utils.StringUtil;
    import utils.supportClasses.JSRequest;

    public class MyBarController
    {
        public function MyBarController(model:MyBarModel)
        {
            _model = model;
        }

        private var _model:MyBarModel;

        public function requestUserIngredients():void
        {
            var request:JSRequest = new JSRequest();
            var requestURL:String = StringUtil.insertParameters(Services.USER_BAR, {"id": UserInfoModel.instance.id});
            ServiceUtil.instance.sendRequest(requestURL, request, onGetUserBar);
        }

        private function onGetUserBar(response:String):void
        {

        }

        public function addIngredientToBar(ingredient:Ingredient):void
        {
            var request:JSRequest = new JSRequest(URLRequestMethod.POST);
            request.expectedStatus = 201;
            request.bodyParams = JSON.stringify(new BarItem(ingredient.id, true));
            var requestURL:String = StringUtil.insertParameters(Services.USER_BAR, {"id": UserInfoModel.instance.id});
            ServiceUtil.instance.sendRequest(requestURL, request, onAddIngredient);
        }

        private function onAddIngredient(response:String):void {
            //
        }

        public function deactivateIngredient(ingredient:Ingredient):void
        {
            //
        }

        public function activateIngredient(ingredient:Ingredient):void
        {
            //
        }

        public function removeIngredientFromBar(ingredient:Ingredient):void
        {
            //
        }
    }
}
