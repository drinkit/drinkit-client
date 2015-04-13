package controllers
{
    import controllers.supportClasses.Services;

    import flash.events.Event;
    import flash.events.EventDispatcher;

    import models.CocktailModel;

    import utils.CocktailUrlDecorator;

    import utils.JSONInstantiator;
    import utils.ServiceUtil;
    import utils.supportClasses.JSRequest;

    /**
     * @eventType controllers.CocktailController.COCKTAIL_DATA_LOADED
     */
    [Event(name="cocktailDataLoaded", type="flash.events.Event")]
    public class CocktailController extends EventDispatcher
    {
        public static const COCKTAIL_DATA_LOADED:String = "cocktailDataLoaded";

        public function CocktailController()
        {

        }

        [Bindable]
        public var model:CocktailModel;

        private function requestCocktailData(key:Number):void
        {
            var request:JSRequest = new JSRequest();
            ServiceUtil.instance.sendRequest(Services.RECIPES + key, request, onCocktailInfoLoad);
        }

        private function onCocktailInfoLoad(response:String):void
        {
            model = CocktailUrlDecorator.decorate(JSONInstantiator.createInstance(response, CocktailModel, false) as CocktailModel);
            MainController.instance.setTitle(model.name);
            dispatchEvent(new Event(COCKTAIL_DATA_LOADED));
        }

        public function loadNeededInfo(prototype:CocktailModel):void
        {
            if (prototype.name && prototype.cocktailTypeId)
            {
                model = prototype;
                MainController.instance.setTitle(model.name);
                dispatchEvent(new Event(COCKTAIL_DATA_LOADED));
            }
            else
            {
                requestCocktailData(prototype.id);
            }
        }
    }
}
