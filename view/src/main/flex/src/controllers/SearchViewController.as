package controllers
{
	import models.SearchViewModel;

	public class SearchViewController
	{
		private var _model:SearchViewModel;
		
		public function SearchViewController(model:SearchViewModel)
		{
			_model = model;
		}
		
		public function addIngredientToQuery(ingr:Object):void
		{
			_model.ingredientsQueryList.addItem(ingr);
		}
	}
}