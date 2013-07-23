package models
{
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.events.CollectionEvent;
	
	import utils.MockData;

	public class SearchViewModel extends EventDispatcher
	{		
		public function SearchViewModel()
		{
			ingredientsList = MockData.fakeIngredients();
			ingredientsQueryList = new ArrayList();
			//
			ingredientsQueryList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
		}
		
		[Bindable]
		public var ingredientsList:ArrayCollection;
		
		[Bindable]
		public var ingredientsQueryList:ArrayList;
		
		private function onIngredientsQueryListChange(event:CollectionEvent):void
		{
			ingredientsList.refresh();
		}
	}
}