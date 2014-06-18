package controllers
{
    import controllers.supportClasses.Services;

    import flash.events.Event;
    import flash.events.EventDispatcher;
    import flash.net.URLVariables;

    import models.CocktailModel;

    import utils.JSONInstantiator;
    import utils.ServiceUtil;

    /**
     * @eventType controllers.CocktailController.COCKTAIL_DATA_LOADED
     */
    [Event(name="cocktailDataLoaded", type="flash.events.Event")]
    public class CocktailController extends EventDispatcher
    {
        public static const COCKTAIL_DATA_LOADED:String = "cocktailDataLoaded";

        [Bindable]
        public var model:CocktailModel;

        public function CocktailController(key:Number)
        {
            requestCocktailData(key);
        }

        private function requestCocktailData(key:Number):void
        {
            var params:URLVariables = new URLVariables();
            params.id = key;
            ServiceUtil.instance.getData(Services.GET_COCKTAIL_INFO, params, onCocktailInfoLoad);
        }

        private function onCocktailInfoLoad(response:String):void
        {
            model = JSONInstantiator.createInstance(response, CocktailModel, false) as CocktailModel;
            MainController.instance.setTitle(model.name);
            dispatchEvent(new Event(COCKTAIL_DATA_LOADED));
        }
    }
}
