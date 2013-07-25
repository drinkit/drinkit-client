package utils
{
	import models.supportClasses.Ingredient;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;

	public class MockData
	{
		public static function fakeIngredients():ArrayCollection
		{
			var source:Array = [new Ingredient(1, "Ананасовый сок", 40),
				new Ingredient(2, "Джин", 40),
				new Ingredient(3, "Текила", 40),
				new Ingredient(4, "Кока-кола", 40),
				new Ingredient(5, "Виски", 40),
				new Ingredient(6, "Водка", 40),
				new Ingredient(7, "Трипл сек", 40)];
			
			return new ArrayCollection(source);
		}
	}
}