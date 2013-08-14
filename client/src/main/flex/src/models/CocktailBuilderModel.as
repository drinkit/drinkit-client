package models
{
	import flash.events.EventDispatcher;
	
	import models.supportClasses.Ingredient;
	
	import mx.binding.utils.BindingUtils;
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.events.CollectionEvent;
	
	import utils.MockData;

	public class CocktailBuilderModel extends EventDispatcher
	{		
		public function CocktailBuilderModel()
		{
			BindingUtils.bindProperty(this, "ingredientsList", IngredientsModel.instance, "ingredientsList");
			cocktailsList = MockData.fakeCocktails();
			selectedIngredientsList = new ArrayList();
			//
			selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
		}
		
		[Bindable]
		public var cocktailsList:ArrayCollection;
		
		[Bindable]
		public var ingredientsList:ArrayCollection = IngredientsModel.instance.ingredientsList;
		
		[Bindable]
		public var selectedIngredientsList:ArrayList;
		
		public var selectedIngredients:Array = [];
		public var selectedCocktailTypes:Array = [];
		public var selectedOptions:Array = [];
		
		private function onIngredientsQueryListChange(event:CollectionEvent):void
		{
			ingredientsList.refresh();
			selectedIngredients = selectedIngredientsList.source.map(itemToId);
		}
		
		private function itemToId(item:*, index:int, array:Array):uint
		{
			return item.id;
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