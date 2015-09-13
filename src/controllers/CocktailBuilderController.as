package controllers
{
    import controllers.supportClasses.SearchParameters;
    import controllers.supportClasses.Services;

    import flash.net.URLVariables;

    import models.CocktailBuilderModel;
    import models.CocktailModel;
    import models.supportClasses.CocktailTypes;
    import models.supportClasses.Ingredient;
    import models.supportClasses.OptionParameters;

    import mx.collections.ArrayCollection;
import mx.collections.ArrayList;
import mx.collections.IList;

import utils.CocktailUrlDecorator;
    import utils.JSONInstantiator;
    import utils.PerformanceAnalyzer;
    import utils.ServiceUtil;
    import utils.supportClasses.JSRequest;

    public class CocktailBuilderController
    {
        public function CocktailBuilderController(model:CocktailBuilderModel)
        {
            _model = model;
        }

        private var _model:CocktailBuilderModel;

        public function addIngredientToQuery(ingr:Ingredient):void
        {
            if (_model.selectedIngredientsList.getItemIndex(ingr) == -1)
                _model.selectedIngredientsList.addItemAt(ingr, 0);
        }

        public function addIngredientsToQuery(ingredients:IList):void {
            var filteredIngredients:Array = [];
            var ingredient:Ingredient;

            for (var i:uint = 0; i < ingredients.length; i++) {
                ingredient = ingredients.getItemAt(i) as Ingredient;
                if (_model.selectedIngredientsList.getItemIndex(ingredient) == -1) {
                    filteredIngredients.push(ingredient);
                }
            }

            _model.selectedIngredientsList.addAllAt(new ArrayList(filteredIngredients), 0);
        }

        public function toggleCocktailType(id:uint, selected:Boolean):void
        {
            if (CocktailTypes.AVAILABLE_TYPES.indexOf(id) == -1)
                return;

            if (selected)
            {
                if (_model.selectedCocktailTypes.indexOf(id) == -1)
                    _model.selectedCocktailTypes.push(id);
            }
            else
            {
                var index:uint = _model.selectedCocktailTypes.indexOf(id);
                if (index >= 0)
                    _model.selectedCocktailTypes.splice(index, 1);
            }
        }

        public function toggleOption(id:uint, selected:Boolean):void
        {
            if (OptionParameters.AVAILABLE_OPTIONS.indexOf(id) == -1)
                return;

            if (selected)
            {
                if (_model.selectedOptions.indexOf(id) == -1)
                    _model.selectedOptions.push(id);
            }
            else
            {
                var index:uint = _model.selectedOptions.indexOf(id);
                if (index >= 0)
                    _model.selectedOptions.splice(index, 1);
            }
        }

        public function performSearch():void
        {
            var criteria:SearchParameters = new SearchParameters();
            criteria.cocktailTypes = _model.selectedCocktailTypes;
            criteria.ingredients = _model.selectedIngredients;
            criteria.options = _model.selectedOptions;
            //
            var vars:URLVariables = new URLVariables();
            vars.criteria = criteria.toString();
            //
            var request:JSRequest = new JSRequest();
            request.queryParams = vars.toString();
            ServiceUtil.instance.sendRequest(Services.SEARCH_BY_BUILDER, request, onSearchComplete);
        }

        private function onSearchComplete(response:String):void
        {
            var res:Array = JSONInstantiator.createInstance(response, CocktailModel, false) as Array;
            res.map(function (element:CocktailModel, index:uint, array:Array):void
            {
                element = CocktailUrlDecorator.decorate(element);
            });
            _model.isNoCocktailsFound = !res || res.length == 0;
            _model.cocktailsList = new ArrayCollection(res);
        }

        public function removeIngredientFromQuery(ingredientData:Ingredient):void {
            if (_model.selectedIngredientsList.getItemIndex(ingredientData) >= 0)
                _model.selectedIngredientsList.removeItem(ingredientData);
        }
    }
}