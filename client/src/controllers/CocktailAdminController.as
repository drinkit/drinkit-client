/**
 * Created by Crabar on 10.07.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import models.CocktailAdminModel;
    import models.CocktailModel;
    import models.IngredientsModel;
    import models.supportClasses.Ingredient;

    import mx.collections.ArrayList;

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

        public function removeIngredientFromCocktail(ingredientId:Number):void
        {

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
            _model.name = cocktail.name;
            _model.description = cocktail.description;
            _model.cocktailTypeId = cocktail.cocktailTypeId;
            _model.selectedOptions = Vector.<Object>(cocktail.options);
            _model.selectedIngredientsList = convertIngredientsWithQuantitiesToSelectedIngredients(cocktail.cocktailIngredients);
        }

        private function convertIngredientsWithQuantitiesToSelectedIngredients(source:Array):ArrayList
        {
            var result:Array = [];
            var curIngredient:Object;
            var ingredient:Array;

            for (var i:int = 0; i < source.length; i++)
            {
                ingredient = source[i];
                curIngredient = {id: ingredient[0], name: IngredientsModel.instance.getIngredientNameById(ingredient[0]), quantity: ingredient[1]};
                result.push(curIngredient)
            }

            return new ArrayList(result);
        }
    }
}
