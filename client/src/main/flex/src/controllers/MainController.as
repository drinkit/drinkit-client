package controllers
{
	import models.MainModel;

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
		
	}
}
class PrivateConstructor {}