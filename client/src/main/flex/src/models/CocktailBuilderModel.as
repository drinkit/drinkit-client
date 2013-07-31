package models
{
	import flash.events.EventDispatcher;
	
	import models.supportClasses.Ingredient;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.events.CollectionEvent;
	
	import utils.MockData;

	public class CocktailBuilderModel extends EventDispatcher
	{		
		public function CocktailBuilderModel()
		{
			ingredientsList = MockData.fakeIngredients();
			cocktailsList = MockData.fakeCocktails();
			selectedIngredientsList = new ArrayList();
			//
			selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
		}
		
		[Bindable]
		public var cocktailsList:ArrayCollection;
		
		[Bindable]
		public var ingredientsList:ArrayCollection;
		
		[Bindable]
		public var selectedIngredientsList:ArrayList;
		
		public var selectedIngredients:Array = [];
		public var selectedCocktailTypes:Array = [];
		public var selectedoptions:Array = [];
		
		private function onIngredientsQueryListChange(event:CollectionEvent):void
		{
			ingredientsList.refresh();
			selectedIngredients = selectedIngredientsList.source.map(itemToId);
		}
		
		private function itemToId(item:*, index:int, array:Array):uint
		{
			return item.id;
		}
		
		public function getIngredientNameById(id:Number):String
		{
			if (ingredientsList)
			{
				for (var i:uint = 0; i < ingredientsList.source.length; i++)
				{
					if (Ingredient(ingredientsList.source[i]).id == id)
						return Ingredient(ingredientsList.source[i]).name;
				}
			}
			
			return null;
		}
		
		public function isIngredientSelected(id:Number):Boolean
		{
			for (var i:uint = 0; i < selectedIngredients.length; i++)
			{
				if (selectedIngredients[i] == id)
					return true;
			}
			
			return false;
		}

	}
}