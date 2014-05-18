package
{
	import flexunit.framework.Assert;
	
	import models.CocktailModel;
	import models.TagsModel;
	import models.supportClasses.CocktailTypes;
	import models.supportClasses.OptionParameters;
	
	public class TagsModelTest
	{		
		[Before]
		public function setUp():void
		{
		}
		
		[After]
		public function tearDown():void
		{
		}
		
		[BeforeClass]
		public static function setUpBeforeClass():void
		{
		}
		
		[AfterClass]
		public static function tearDownAfterClass():void
		{
		}
		
		[Test]
		public function testGet_instance():void
		{
			Assert.assertNotNull(TagsModel.instance);
		}
		
		[Test]
		public function testGetTagByIdAndType():void
		{
			// TRUE TESTS
			Assert.assertNotNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.LONG_DRINK, TagsModel.COCKTAIL_TYPE_TAG, 24));
			Assert.assertNotNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.LONG_DRINK, TagsModel.COCKTAIL_TYPE_TAG, 32));
			//
			Assert.assertNotNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.SHORT_DRINK, TagsModel.COCKTAIL_TYPE_TAG, 24));
			Assert.assertNotNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.SHORT_DRINK, TagsModel.COCKTAIL_TYPE_TAG, 32));
			//
			Assert.assertNotNull(TagsModel.instance.getTagByIdAndType(OptionParameters.BURNING, TagsModel.COCKTAIL_OPTION_TAG, 24));
			Assert.assertNotNull(TagsModel.instance.getTagByIdAndType(OptionParameters.WITH_ICE, TagsModel.COCKTAIL_OPTION_TAG, 64));
			// FALSE TESTS
			Assert.assertNull(TagsModel.instance.getTagByIdAndType(10, TagsModel.COCKTAIL_OPTION_TAG, 24));
			Assert.assertNull(TagsModel.instance.getTagByIdAndType(-1, TagsModel.COCKTAIL_OPTION_TAG, 64));
			//
			Assert.assertNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.SHORT_DRINK, "dsfdsf", 24));
			Assert.assertNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.SHORT_DRINK, "dsfdsdsf", 64));
			//
			Assert.assertNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.SHORT_DRINK, TagsModel.COCKTAIL_TYPE_TAG, 0));
			Assert.assertNull(TagsModel.instance.getTagByIdAndType(CocktailTypes.SHORT_DRINK, TagsModel.COCKTAIL_TYPE_TAG, -10));
		}		

	}
}