package models
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	public class MainModel extends EventDispatcher
	{
		public static const SEARCH_VIEW:String = "searchView";
		public static const COCKTAIL_VIEW:String = "cocktailView";
		
		public function MainModel()
		{
			super(null);
		}
	}
}