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
		
		public function changeView(view:int):void
		{
			_model.currentView = view;
		}
		
	}
}
class PrivateConstructor {}