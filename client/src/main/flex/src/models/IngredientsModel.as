package models
{
	import models.supportClasses.Ingredient;
	import utils.MockData;
	import mx.collections.ArrayCollection;
	
	public class IngredientsModel
	{
		private static var _instance:IngredientsModel;
		
		public static function get instance():IngredientsModel
		{
			if (!_instance)
				_instance = new IngredientsModel();
			
			return _instance;
		}
		
		public function IngredientsModel()
		{
		}
		
		[Bindable]
		public var ingredientsList:ArrayCollection = MockData.fakeIngredients();
		
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
	}
}
