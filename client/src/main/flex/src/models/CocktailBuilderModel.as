package models
{
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.events.CollectionEvent;
	
	import utils.MockData;

	public class CocktailBuilderModel extends EventDispatcher
	{		
		public function CocktailBuilderModel()
		{
			ingredientsList = MockData.fakeIngredients();
			selectedIngredientsList = new ArrayList();
			//
			selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
		}
		
		[Bindable]
		public var ingredientsList:ArrayCollection;
		
		[Bindable]
		public var selectedIngredientsList:ArrayList;
		
		private function onIngredientsQueryListChange(event:CollectionEvent):void
		{
			ingredientsList.refresh();
			selectedIngredients = selectedIngredientsList.source.map(itemToId);
		}
		
		private function itemToId(item:*, index:int, array:Array):uint
		{
			return item.id;
		}
		
		public var selectedIngredients:Array = [];
		public var selectedCocktailTypes:Array = [];
		public var selectedOptionals:Array = [];
	}
}