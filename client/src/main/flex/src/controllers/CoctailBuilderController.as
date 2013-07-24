package controllers
{
	import controllers.supportClasses.SearchParameters;
	import controllers.supportClasses.Services;
	
	import flash.events.Event;
	import flash.net.URLVariables;
	
	import models.CocktailBuilderModel;
	import models.supportClasses.OptionalParameters;
	
	import mx.collections.ArrayCollection;
	
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
		
		public function toggleOptional(id:uint, selected:Boolean):void
		{
			if (selected)
				_model.selectedOptionals.push(id);
			else
			{
				var index:uint = _model.selectedOptionals.indexOf(id);
				_model.selectedOptionals.splice(index, 1);
			}
		}
		
		public function requestIngredients():void
		{
			ServiceUtil.requestData(Services.GET_INGREDIENTS, null, onIngredientsLoad);
		}
		
		private function onIngredientsLoad(event:Event):void
		{
			_model.ingredientsList = new ArrayCollection((JSON.parse(event.target.data) as Array));
		}
		
		public function performSearch():void
		{
			var criteria:SearchParameters = new SearchParameters();
			criteria.cocktailTypes = _model.selectedCocktailTypes;
			criteria.ingredients = _model.selectedIngredients;
			//
			criteria.isBurning = _model.selectedOptionals.indexOf(OptionalParameters.BURNING) != -1;
			criteria.isChecked = _model.selectedOptionals.indexOf(OptionalParameters.CHECKED) != -1;
			criteria.isFlacky = _model.selectedOptionals.indexOf(OptionalParameters.FLACKY) != -1;
			criteria.isIBA = _model.selectedOptionals.indexOf(OptionalParameters.IBA) != -1;
			criteria.isWithIce = _model.selectedOptionals.indexOf(OptionalParameters.WITH_ICE) != -1;
			//
			var vars:URLVariables = new URLVariables();
			vars.criteria = criteria.toString();
			//
			ServiceUtil.requestData(Services.SEARCH_BY_BUILDER, vars, onSearchComplete);
		}
		
		private function onSearchComplete(event:Event):void
		{
			trace(event.target.data);
		}
	}
}