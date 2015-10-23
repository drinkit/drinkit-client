/**
 * Created by Crabar on 26.10.2014.
 */
package controllers
{
    import controllers.supportClasses.Services;

    import flash.net.URLVariables;

    import models.CocktailModel;
    import models.MainModel;
    import models.SearchResultsModel;

    import mx.collections.ArrayCollection;

    import utils.CocktailUrlDecorator;
    import utils.JSONInstantiator;
    import utils.ServiceUtil;
    import utils.supportClasses.JSRequest;

    public class SearchController
    {
        public function SearchController(model:SearchResultsModel)
        {
            _model = model;
        }

        private var _model:SearchResultsModel;

        public function findCocktail(name:String):void
        {
            var request:JSRequest = new JSRequest();
            var vars:URLVariables = new URLVariables();
            vars.namePart = name;
            request.queryParams = vars.toString();
            ServiceUtil.instance.sendRequest(Services.SEARCH_BY_NAME, request, onSearchEnd);
        }

        private function onSearchEnd(response:String):void
        {
            var res:Array = JSONInstantiator.createInstance(response, CocktailModel, false) as Array;
            res.map(function (element:CocktailModel, index:uint, array:Array):void
            {
                CocktailUrlDecorator.decorate(element);
            });

            _model.cocktailsList = new ArrayCollection(res);
            MainController.instance.changeView(MainModel.SEARCH_BY_NAME_VIEW, _model);
        }
    }
}
