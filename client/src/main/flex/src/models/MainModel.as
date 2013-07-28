package models
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	public class MainModel extends EventDispatcher
	{
		public static const BUILDER_VIEW:int = 0;
		public static const COCKTAIL_VIEW:int = 2;
		
		[Bindable]
		public var currentView:int = BUILDER_VIEW; 
		
		public function MainModel()
		{
			super(null);
		}
	}
}