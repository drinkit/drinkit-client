package
{
    import controllers.CocktailBuilderController;

    import models.CocktailBuilderModel;
    import models.supportClasses.CocktailTypes;
    import models.supportClasses.OptionParameters;

    public class CocktailBuilderControllerTest
    {

        [BeforeClass]
        public static function setUpBeforeClass():void
        {
        }

        [AfterClass]
        public static function tearDownAfterClass():void
        {
        }
        private var _controller:CocktailBuilderController;
        private var _model:CocktailBuilderModel;

        [Before]
        public function setUp():void
        {
            _model = new CocktailBuilderModel();
            _controller = new CocktailBuilderController(_model);
        }

        [After]
        public function tearDown():void
        {
        }

        [Test]
        public function testAddIngredientToQuery():void
        {

        }

//		[Test]
//		public function testPerformSearch():void
//		{
//			Assert.fail("Test method Not yet implemented");
//		}

        [Test]
        public function testToggleCocktailType():void
        {
            Assert.assertEquals(_model.selectedCocktailTypes.length, 0);
            // add right
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, true);
            Assert.assertEquals(1, _model.selectedCocktailTypes.length);
            Assert.assertEquals(CocktailTypes.LONG_DRINK, _model.selectedCocktailTypes[0]);
            // remove right
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, false);
            Assert.assertEquals(0, _model.selectedCocktailTypes.length);
            // add wrong
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, true);
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, true);
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, true);
            Assert.assertEquals(1, _model.selectedCocktailTypes.length);
            Assert.assertEquals(CocktailTypes.LONG_DRINK, _model.selectedCocktailTypes[0]);
            // remove wrong
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, false);
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, false);
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, false);
            _controller.toggleCocktailType(CocktailTypes.LONG_DRINK, false);
            _controller.toggleCocktailType(CocktailTypes.SHORT_DRINK, false);
            Assert.assertEquals(0, _model.selectedCocktailTypes.length);
        }

        [Test]
        public function testToggleOption():void
        {
            Assert.assertEquals(_model.selectedOptions.length, 0);
            // add right
            _controller.toggleOption(OptionParameters.BURNING, true);
            Assert.assertEquals(1, _model.selectedOptions.length);
            Assert.assertEquals(CocktailTypes.LONG_DRINK, _model.selectedOptions[0]);
            // remove right
            _controller.toggleOption(OptionParameters.BURNING, false);
            Assert.assertEquals(0, _model.selectedOptions.length);
            // add wrong
            _controller.toggleOption(OptionParameters.BURNING, true);
            _controller.toggleOption(OptionParameters.BURNING, true);
            _controller.toggleOption(OptionParameters.BURNING, true);
            Assert.assertEquals(1, _model.selectedOptions.length);
            Assert.assertEquals(OptionParameters.BURNING, _model.selectedOptions[0]);
            // remove wrong
            _controller.toggleOption(OptionParameters.BURNING, false);
            _controller.toggleOption(OptionParameters.BURNING, false);
            _controller.toggleOption(OptionParameters.BURNING, false);
            _controller.toggleOption(OptionParameters.BURNING, false);
            _controller.toggleOption(OptionParameters.FLACKY, false);
            Assert.assertEquals(0, _model.selectedOptions.length);
        }
    }
}