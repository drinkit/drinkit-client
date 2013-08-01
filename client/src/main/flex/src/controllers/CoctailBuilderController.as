package controllers
{
	import controllers.supportClasses.SearchParameters;
	import controllers.supportClasses.Services;
	
	import flash.events.Event;
	import flash.net.URLVariables;
	
	import models.CocktailBuilderModel;
	import models.supportClasses.CocktailMini;
	import models.supportClasses.Ingredient;
	import models.supportClasses.OptionParameters;
	
	import mx.collections.ArrayCollection;
	
	import utils.JSONInstantiator;
	import utils.ServiceUtil;

	public class CoctailBuilderController
	{
		private var _model:CocktailBuilderModel;
		
		public function CoctailBuilderController(model:CocktailBuilderModel)
		{
			_model = model;
		}
		
		public function addIngredientToQuery(ingr:Object):void
		{
			_model.selectedIngredientsList.addItem(ingr);			
		}
		
		public function toggleCocktailType(id:uint, selected:Boolean):void
		{
			if (selected)
				_model.selectedCocktailTypes.push(id);
			else
			{
				var index:uint = _model.selectedCocktailTypes.indexOf(id);
				_model.selectedCocktailTypes.splice(index, 1);
			}
		}
		
		public function toggleOption(id:uint, selected:Boolean):void
		{
			if (selected)
				_model.selectedoptions.push(id);
			else
			{
				var index:uint = _model.selectedoptions.indexOf(id);
				_model.selectedoptions.splice(index, 1);
			}
		}
		
		public function requestIngredients():void
		{
			ServiceUtil.requestData(Services.GET_INGREDIENTS, null, onIngredientsLoad);
		}
		
		private function onIngredientsLoad(event:Event):void
		{
			_model.ingredientsList = new ArrayCollection(JSONInstantiator.createInstance(event.target.data, Ingredient) as Array);
		}
		
		public function performSearch():void
		{
			var criteria:SearchParameters = new SearchParameters();
			criteria.cocktailTypes = _model.selectedCocktailTypes;
			criteria.ingredients = _model.selectedIngredients;
			criteria.options = _model.selectedoptions;
			//
			var vars:URLVariables = new URLVariables();
			vars.criteria = criteria.toString();
			//
			ServiceUtil.requestData(Services.SEARCH_BY_BUILDER, vars, onSearchComplete);
		}
		
		private function onSearchComplete(event:Event):void
		{
			_model.cocktailsList = new ArrayCollection(JSONInstantiator.createInstance(event.target.data, CocktailMini, false) as Array);
		}
	}
}