package utils
{
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;

	public class MockData
	{
		public static function fakeIngredients():ArrayCollection
		{
			var source:Array = [{name:"Водка"},
				{name:"Ананасовый сок"},
				{name:"Джин"},
				{name:"Текила"},
				{name:"Кока-кола"},
				{name:"Виски"},
				{name:"Трипл сек"}];
			
			return new ArrayCollection(source);
		}
	}
}