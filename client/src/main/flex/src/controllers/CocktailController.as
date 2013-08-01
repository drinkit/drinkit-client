package controllers
{
	import controllers.supportClasses.Services;
	
	import flash.events.Event;
	import flash.net.URLVariables;
	
	import models.CocktailModel;
	
	import mx.binding.utils.BindingUtils;
	
	import utils.JSONInstantiator;
	import utils.ServiceUtil;

	public class CocktailController
	{
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
			ServiceUtil.requestData(Services.GET_COCKTAIL_INFO, params, onCocktailInfoLoad);
		}
		
		private function onCocktailInfoLoad(event:Event):void
		{
			model = JSONInstantiator.createInstance(event.target.data, CocktailModel, false) as CocktailModel;
		}
	}
}