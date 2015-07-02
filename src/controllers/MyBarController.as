/**
 * Created by Crabar on 29.06.2015.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import flash.events.Event;

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
            _model.barItems = Vector.<BarItem>(JSONInstantiator.createInstance(response, BarItem) as Array);
            _model.dispatchEvent(new Event(MyBarModel.MODEL_CHANGED));
        }

        public function addIngredientToBar(ingredient:Ingredient):void
        {
            //
        }

        public function deactivateIngredient(ingredient:Ingredient):void
        {
            //
        }

        public function removeIngredientFromBar(ingredient:Ingredient):void
        {
            //
        }
    }
}
