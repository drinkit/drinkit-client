package controllers
{
	import controllers.supportClasses.SearchParameters;
	import controllers.supportClasses.Services;
	
	import flash.events.Event;
	import flash.net.URLVariables;
	
	import models.CocktailBuilderModel;
	import models.supportClasses.CocktailMini;
	import models.supportClasses.CocktailTypes;
	import models.supportClasses.OptionParameters;
	
	import mx.collections.ArrayCollection;
	
	import utils.JSONInstantiator;
	import utils.ServiceUtil;

	public class CocktailBuilderController
	{
		private var _model:CocktailBuilderModel;
		
		public function CocktailBuilderController(model:CocktailBuilderModel)
		{
			_model = model;
		}
		
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
			ServiceUtil.requestData(Services.SEARCH_BY_BUILDER, vars, onSearchComplete);
		}
		
		private function onSearchComplete(event:Event):void
		{
			_model.cocktailsList = new ArrayCollection(JSONInstantiator.createInstance(event.target.data, CocktailMini, false) as Array);
		}
	}
}