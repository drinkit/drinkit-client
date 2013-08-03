package controllers
{
	import controllers.supportClasses.Services;
	
	import flash.events.Event;
	
	import models.IngredientsModel;
	import models.MainModel;
	import models.supportClasses.Ingredient;
	
	import mx.collections.ArrayCollection;
	
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
		
		public function changeView(view:int, data:Object):void
		{
			_model.currentView = view;
			_model.viewData = data;
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
		
		public function requestIngredients():void
		{
			ServiceUtil.requestData(Services.GET_INGREDIENTS, null, onIngredientsLoad);
		}
		
		
		private function onIngredientsLoad(event:Event):void
		{
			IngredientsModel.instance.ingredientsList = new ArrayCollection(JSONInstantiator.createInstance(event.target.data, Ingredient) as Array);
		}
		
	}
}
class PrivateConstructor {}