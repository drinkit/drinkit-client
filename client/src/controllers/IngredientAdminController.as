/**
 * Created by Crabar on 28.07.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import flash.net.URLRequestMethod;

    import models.IngredientsModel;

    import models.supportClasses.Ingredient;

    import mx.controls.Alert;

    import utils.JSONUtil;

    import utils.ServiceUtil;

    import utils.supportClasses.JSRequest;

    public class IngredientAdminController
    {
        [Bindable]
        private var _model:Ingredient;

        public function IngredientAdminController(model:Ingredient)
        {
            _model = model;
        }

        public function createNewIngredient():void
        {
            var createRequest:JSRequest = new JSRequest(URLRequestMethod.POST);
            createRequest.bodyParams = JSONUtil.escapeSpecialChars(JSON.stringify(_model));
            createRequest.contentType = "application/json;charset=UTF-8";
            ServiceUtil.instance.sendRequest(Services.INGREDIENTS, createRequest, onCreateIngredient);
        }

        private function onCreateIngredient(response:String):void
        {
            Alert.show("Ингредиент успешно создан");
            MainController.instance.requestIngredients();
        }

        public function saveCurrentIngredient():void
        {
            var saveRequest:JSRequest = new JSRequest(URLRequestMethod.PUT);
            saveRequest.bodyParams = JSONUtil.escapeSpecialChars(JSON.stringify(_model));
            saveRequest.contentType = "application/json;charset=UTF-8";
            ServiceUtil.instance.sendRequest(Services.INGREDIENTS + _model.id, saveRequest, onSaveIngredient);
        }

        private function onSaveIngredient(response:String):void
        {
            Alert.show("Ингредиент успешно обновлен");
            MainController.instance.requestIngredients();
        }

        public function loadIngredient(ingredient:Ingredient):void
        {
            _model.id = ingredient.id;
            _model.name = ingredient.name;
            _model.description = ingredient.description;
            _model.vol = ingredient.vol;
        }

        public function deleteIngredient():void
        {
            var deleteRequest:JSRequest = new JSRequest(URLRequestMethod.DELETE);
            ServiceUtil.instance.sendRequest(Services.INGREDIENTS + _model.id, deleteRequest, onDeleteIngredient);
        }

        private function onDeleteIngredient(response:String):void
        {
            Alert.show("Ингредиент успешно удален");
            MainController.instance.requestIngredients();
        }
    }
}
