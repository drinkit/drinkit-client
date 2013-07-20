package models
{
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	
	import utils.MockData;

	public class SearchViewModel extends EventDispatcher
	{
		public function SearchViewModel()
		{
		}
		
		[Bindable]
		public var ingredientsList:ArrayCollection = MockData.fakeIngredients();
		
		[Bindable]
		public var ingredientsQueryList:ArrayList = new ArrayList();
	}
}