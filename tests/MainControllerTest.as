package
{
    import controllers.MainController;

    import models.MainModel;

    public class MainControllerTest
    {

        [BeforeClass]
        public static function setUpBeforeClass():void
        {
        }

        [AfterClass]
        public static function tearDownAfterClass():void
        {
        }

        [Before]
        public function setUp():void
        {
            var model:MainModel = new MainModel();
            MainController.instance.model = model;
            MainController.instance.initBrowserEngine();
        }

        [After]
        public function tearDown():void
        {
        }

        [Test]
        public function testChangeView():void
        {
//			Assert.fail("Test method Not yet implemented");
        }

        [Test]
        public function testSetTitle():void
        {
//			// does not work in standalone FP
//			if (BrowserManager.getInstance())
//			{
//				MainController.instance.setTitle("Cocktail Test");
//				Assert.assertEquals("drinkIt - Cocktail Test", BrowserManager.getInstance().title);
//			}

        }
    }
}
