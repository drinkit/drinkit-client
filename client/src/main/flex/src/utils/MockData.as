package utils
{
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;

	public class MockData
	{
		public static function fakeIngredients():ArrayCollection
		{
			var source:Array = [{cname:"Водка"},
				{cname:"Ананасовый сок"},
				{cname:"Джин"},
				{cname:"Текила"},
				{cname:"Кока-кола"},
				{cname:"Виски"},
				{cname:"Трипл сек"}];
			
			return new ArrayCollection(source);
		}
	}
}