package controllers
{
    import controllers.supportClasses.SearchParameters;
    import controllers.supportClasses.Services;

    import flash.net.URLVariables;

    import models.CocktailBuilderModel;
    import models.CocktailModel;
    import models.supportClasses.CocktailTypes;
    import models.supportClasses.OptionParameters;

    import mx.collections.ArrayCollection;

    import utils.JSONInstantiator;
    import utils.ServiceUtil;
    import utils.supportClasses.JSRequest;

    public class CocktailBuilderController
    {
        public function CocktailBuilderController(model:CocktailBuilderModel)
        {
            _model = model;
        }
        private var _model:CocktailBuilderModel;

        public function addIngredientToQuery(ingr:Object):void
        {
            _model.selectedIngredientsList.addItem(ingr);
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
            res.map(function(element:CocktailModel, index:uint, array:Array):void {
                element.imageUrl = ServiceUtil.instance.serviceAddress + element.imageUrl;
                element.thumbnailUrl = ServiceUtil.instance.serviceAddress + element.thumbnailUrl;
            });
            _model.isNoCocktailsFound = !res || res.length == 0;
            _model.cocktailsList = new ArrayCollection(res);
        }
    }
}