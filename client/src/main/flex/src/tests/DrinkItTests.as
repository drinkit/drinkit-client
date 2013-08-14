package tests
{
	import tests.TagsModelTest;
	
	[Suite]
	[RunWith("org.flexunit.runners.Suite")]
	public class DrinkItTests
	{
		public var test1:tests.TagsModelTest;
		public var test2:CocktailBuilderControllerTest;
	}
}