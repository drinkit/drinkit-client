package utils
{
	import models.supportClasses.Cocktail;
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
		
		public static function fakeCocktails():ArrayCollection
		{
			var source:Array = [new Cocktail(1, "Куба Либре", "8905004E04700D00A01A00A00000000000D049048044052"  +
				"000000000200000000000200080020000000000FC0180ED"  +
				"A30000000000010730520470420000AE0CE01C0E9000000"  +
				"0000406704104D0410000000B108F00B0FC061005000000"  +
				"0000907004805907300000000E0C300000000E0C30010C7"  +
				"6F0A806400000000001A07404505807405306F066074077"  +
				"6107206500005006106906E07402E04E045054020076033"  +
				"2E03502E0310300300F40720A100000000003F049044041"  +
				"5404804B0ED0CD03100D00002000C0000C10AA0C000B052"  +
				"500C30CA08008F0AA04005B0910F00E90FE0C90ED0170B5"  +
				"4E0CF09802D0060C80000190200030640800E20D506E0B9"  +
				"5902D0060C80000190200030640000B203E0D001A09B06A"  +
				"BA04902205800000000000004904504E0440AE042060082", [1, 2, 3]),
				new Cocktail(2, "Лонг Айленд"),
				new Cocktail(3, "Пина Колада")];
			
			return new ArrayCollection(source);
		}
	}
}