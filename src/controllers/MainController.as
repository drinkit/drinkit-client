package controllers
{
    import controllers.supportClasses.Services;

    import models.CocktailModel;
    import models.IngredientsModel;
    import models.MainModel;
    import models.events.ViewEvent;
    import models.supportClasses.Ingredient;
    import models.supportClasses.ViewInformation;

    import mx.collections.ArrayCollection;
    import mx.events.BrowserChangeEvent;
    import mx.managers.BrowserManager;
    import mx.utils.URLUtil;

    import utils.JSONInstantiator;
    import utils.ServiceUtil;
    import utils.supportClasses.JSRequest;

    public class MainController
    {
        private static var _instance:MainController;

        public static function get instance():MainController
        {
            if (!_instance)
                _instance = new MainController(new PrivateConstructor());

            return _instance;
        }


        /////////////

        public function MainController(value:PrivateConstructor)
        {
        }

        private var _model:MainModel;

        public function set model(value:MainModel):void
        {
            _model = value;
        }

        public function changeView(view:ViewInformation, data:Object, changedByUser:Boolean = false):void
        {
            _model.currentView = view;

            var fragments:String = "panel=" + view.id;

            if (view == MainModel.COCKTAIL_VIEW)
                fragments += ";id=" + data.id;

            BrowserManager.getInstance().setFragment(fragments);
            setTitle(view.title);

            if (!changedByUser)
                _model.dispatchEvent(new ViewEvent(ViewEvent.VIEW_CHANGED, view.id, data));
        }

        public function setTitle(value:String):void
        {
            BrowserManager.getInstance().setTitle("drinkIt - " + value);
        }

        /**
         * Request and load ingredients.
         *
         */
        public function requestIngredients():void
        {
            var request:JSRequest = new JSRequest();
            ServiceUtil.instance.sendRequest(Services.INGREDIENTS, request, onIngredientsLoad);
        }

        public function initBrowserEngine():void
        {
            BrowserManager.getInstance().addEventListener(BrowserChangeEvent.BROWSER_URL_CHANGE, onURLChange);
            BrowserManager.getInstance().init("", "drinkIt - " + MainModel.BUILDER_VIEW.title);
//            checkFragments();
        }

        private function onIngredientsLoad(response:String):void
        {
            IngredientsModel.instance.ingredientsList = new ArrayCollection(JSONInstantiator.createInstance(response, Ingredient, false) as Array);
        }

        private function checkFragments():void
        {
            var fragments:Object = URLUtil.stringToObject(BrowserManager.getInstance().fragment);

            if (!fragments.hasOwnProperty("panel"))
            {
                changeView(MainModel.BUILDER_VIEW, null);
                return;
            }

            switch (fragments.panel)
            {
                case MainModel.BUILDER_VIEW.id:
                {
                    changeView(MainModel.BUILDER_VIEW, null);
                    break;
                }
                case MainModel.COCKTAIL_VIEW.id:
                {
                    try
                    {
                        var id:Number = Number(fragments.id);
                        changeView(MainModel.COCKTAIL_VIEW, new CocktailModel(id));
                    }
                    catch (error:Error)
                    {
                        changeView(MainModel.BUILDER_VIEW, null);
                    }

                    break;
                }
                case MainModel.ADMIN_VIEW.id:
                {
                    try
                    {
                        var id:Number = Number(fragments.id);
                        changeView(MainModel.ADMIN_VIEW, id);
                    }
                    catch (error:Error)
                    {
                        changeView(MainModel.ADMIN_VIEW, null);
                    }

                    break;
                }
            }
        }

        private function onURLChange(event:BrowserChangeEvent):void
        {
            checkFragments();
        }

    }
}

class PrivateConstructor
{
}
