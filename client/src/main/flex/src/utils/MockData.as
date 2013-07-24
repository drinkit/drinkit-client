package utils
{
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;

	public class MockData
	{
		public static function fakeIngredients():ArrayCollection
		{
			var source:Array = [{id:"1", name:"Водка"},
				{id:"2", name:"Ананасовый сок"},
				{id:"3", name:"Джин"},
				{id:"4", name:"Текила"},
				{id:"5", name:"Кока-кола"},
				{id:"6", name:"Виски"},
				{id:"7", name:"Трипл сек"}];
			
			return new ArrayCollection(source);
		}
	}
}