package controllers
{
	import controllers.supportClasses.SearchParameters;
	
	import flash.events.Event;
	import flash.net.URLVariables;
	
	import models.SearchViewModel;
	import models.supportClasses.OptionalParameters;
	
	import mx.collections.ArrayCollection;
	
	import utils.ServiceUtil;

	public class SearchViewController
	{
		private var _model:SearchViewModel;
		
		public function SearchViewController(model:SearchViewModel)
		{
			_model = model;
		}
		
		public function addIngredientToQuery(ingr:Object):void
		{
			_model.ingredientsQueryList.addItem(ingr);
			//
			
		}
		
		public function requestIngredients():void
		{
			ServiceUtil.requestData("ingredients/findAll", null, onIngredientsLoad);
		}
		
		private function onIngredientsLoad(event:Event):void
		{
			_model.ingredientsList = new ArrayCollection((JSON.parse(event.target.data) as Array));
		}
		
		public function performSearch(cocktailTypes:Array, ingredients:Array, optionals:Array):void
		{
			var criteria:SearchParameters = new SearchParameters();
			criteria.cocktailTypes = cocktailTypes;
			criteria.ingredients = ingredients;
			//
			criteria.isBurning = optionals.indexOf(OptionalParameters.BURNING) != -1;
			criteria.isChecked = optionals.indexOf(OptionalParameters.CHECKED) != -1;
			criteria.isFlacky = optionals.indexOf(OptionalParameters.FLACKY) != -1;
			criteria.isIBA = optionals.indexOf(OptionalParameters.IBA) != -1;
			criteria.isWithIce = optionals.indexOf(OptionalParameters.WITH_ICE) != -1;
			//
			var vars:URLVariables = new URLVariables();
			vars.criteria = criteria.toString();
			//
			ServiceUtil.requestData("search", vars, onSearchComplete);
		}
		
		private function onSearchComplete(event:Event):void
		{
			trace(event.target.data);
		}
	}
}