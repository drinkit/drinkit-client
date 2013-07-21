package controllers
{
	import flash.events.Event;
	
	import models.SearchViewModel;
	
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
//			_model.
		}
		
		public function requestIngredients():void
		{
			ServiceUtil.requestData("ingredients/findAll", null, onIngredientsLoad);
		}
		
		private function onIngredientsLoad(event:Event):void
		{
			_model.ingredientsList = new ArrayCollection((JSON.parse(event.target.data) as Array));
		}
	}
}