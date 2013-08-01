package controllers
{
	import models.CocktailModel;

	public class CocktailController
	{
		private var _model:CocktailModel;
		
		public function CocktailController(model:CocktailModel)
		{
			_model = model;
		}
	}
}