/**
 * Created by Crabar on 10.07.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import models.CocktailAdminModel;
    import models.CocktailModel;
    import models.supportClasses.Ingredient;

    import utils.JSONInstantiator;

    import utils.ServiceUtil;

    import utils.supportClasses.JSRequest;

    public class CocktailAdminController
    {
        public function CocktailAdminController(model:CocktailAdminModel)
        {
            _model = model;
        }

        private var _model:CocktailAdminModel;

        public function addIngredientToCocktail(ingredient:Ingredient):void
        {
            _model.selectedIngredientsList.addItem({id: ingredient.id, name: ingredient.name, quantity: ""});
        }

        public function updateOptions(selectedOptions:Vector.<Object>):void
        {
            _model.selectedOptions = selectedOptions.map(itemToId);
        }

        private function itemToId(item:*, index:int, array:Vector.<Object>):uint
        {
            return item.value;
        }

        public function setCocktailType(selectedType:Object):void
        {
            _model.cocktailTypeId = selectedType.value;
        }

        public function loadCocktailInfo(id:Number):void
        {
            var request:JSRequest = new JSRequest();
            ServiceUtil.instance.sendRequest(Services.GET_COCKTAIL_INFO + id, request, onCocktailInfoLoad);
        }

        private function onCocktailInfoLoad(response:String):void
        {
            var cocktail:CocktailModel = JSONInstantiator.createInstance(response, CocktailModel, false) as CocktailModel;
            _model.cocktailTypeId = cocktail.cocktailTypeId;
            _model.selectedOptions = Vector.<Object>(cocktail.optionIds);
        }
    }
}
