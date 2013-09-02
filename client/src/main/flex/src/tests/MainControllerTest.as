package tests
{
	import controllers.MainController;
	
	import flexunit.framework.Assert;
	
	import mx.managers.BrowserManager;
	
	public class MainControllerTest
	{
		
		[Before]
		public function setUp():void
		{
			MainController.instance.initBrowserEngine();
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
		public function testChangeView():void
		{
			Assert.fail("Test method Not yet implemented");
		}
		
		[Test]
		public function testSetTitle():void
		{
			MainController.instance.setTitle("Cocktail Test");
			Assert.assertEquals("drinkIt - Cocktail Test", BrowserManager.getInstance().title);
		}
	}
}
