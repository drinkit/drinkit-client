package controllers
{
	import controllers.supportClasses.Services;
	
	import flash.events.Event;
	
	import models.IngredientsModel;
	import models.MainModel;
	import models.supportClasses.Ingredient;
	
	import mx.collections.ArrayCollection;
	import mx.events.BrowserChangeEvent;
	import mx.managers.BrowserManager;
	import mx.utils.URLUtil;
	
	import utils.JSONInstantiator;
	import utils.ServiceUtil;

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
		
		private var _model:MainModel;
		
		public function MainController(value:PrivateConstructor)
		{
		}
		
		public function set model(value:MainModel):void
		{
			_model = value;
		}
		
		public function changeView(view:int, data:Object, manual:Boolean = true):void
		{
			if (manual)
			{
				_model.currentView = view;
				_model.viewData = data;
			}
			
			var fragments:String = "panel=" + view;
			
			if (view == MainModel.COCKTAIL_VIEW)
				fragments += "&id=" + data;
			
			BrowserManager.getInstance().setFragment(fragments);
		}
		
		public function get viewData():Object
		{
			if (_model)
			{
				var data:Object = _model.viewData;
				_model.viewData = null;
				return data;
			}
			
			return null;
			
		}
		
		/**
		 *Request and load ingredients. 
		 * 
		 */
		public function requestIngredients():void
		{
			ServiceUtil.requestData(Services.GET_INGREDIENTS, null, onIngredientsLoad);
		}		
		
		private function onIngredientsLoad(event:Event):void
		{
			IngredientsModel.instance.ingredientsList = new ArrayCollection(JSONInstantiator.createInstance(event.target.data, Ingredient) as Array);
		}
		
		private function checkFragments():void
		{
			var fragments:Object = URLUtil.stringToObject(BrowserManager.getInstance().fragment);
			
			if (fragments.hasOwnProperty("panel") && fragments.panel == MainModel.COCKTAIL_VIEW)
				MainController.instance.changeView(MainModel.COCKTAIL_VIEW, fragments.id);
		}
		
		public function initBrowserEngine():void
		{
			BrowserManager.getInstance().addEventListener(BrowserChangeEvent.BROWSER_URL_CHANGE, onURLChange);
			BrowserManager.getInstance().init("", "drinkit");
			checkFragments();			
		}
		
		private function onURLChange(event:BrowserChangeEvent):void
		{
			checkFragments();
		}
		
	}
}
class PrivateConstructor {}