package models
{
	import flash.display.Bitmap;
	import models.supportClasses.CocktailTypes;
	import models.supportClasses.OptionParameters;
	import spark.components.Image;
	
	public class TagsModel
	{
		public static const COCKTAIL_OPTION_TAG:String = "cocktailOptionTag";
		public static const COCKTAIL_TYPE_TAG:String = "cocktailTypeTag";
		
		private static var _instance:TagsModel;
		
		public static function get instance():TagsModel
		{
			if (!_instance)
				_instance = new TagsModel;
			
			return _instance;
		}
		
		public function TagsModel()
		{
			tagCache = {};
			//
			addTagToCache(new tagExampleBitmap(), "Long drink", CocktailTypes.LONG_DRINK, COCKTAIL_TYPE_TAG);
			addTagToCache(new tagExampleBitmap(), "Short drink", CocktailTypes.SHORT_DRINK, COCKTAIL_TYPE_TAG);
			addTagToCache(new tagExampleBitmap(), "Shooter", CocktailTypes.SHOOTER, COCKTAIL_TYPE_TAG);
			addTagToCache(new tagExampleBitmap(), "Горящий", OptionParameters.BURNING, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Со льдом", OptionParameters.WITH_ICE, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Проверен администрацией", OptionParameters.CHECKED, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Утвержден IBA", OptionParameters.IBA, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Слоеный", OptionParameters.FLACKY, COCKTAIL_OPTION_TAG);
		}
		
		private var tagCache:Object;
		
		[Embed(source="/../assets/tag_example.png")]
		private var tagExampleBitmap:Class;
		
		public function getTagByIdAndType(id:Number, type:String, size:Number):Image
		{
			var tag:Image;
			
			if (size <= 0)
				return tag;
			
			var cachedTag:Image = tagCache[type + id];
			
			if (cachedTag)
			{
				tag = new Image();
				tag.toolTip = cachedTag.toolTip;
				tag.source = cachedTag.source;
				tag.smooth = true;
				tag.cacheAsBitmap = true;
				tag.width = size;
				tag.height = size;
			}
			
			return tag;
		}
		
		private function addTagToCache(image:Bitmap, title:String, id:Number, type:String):Image
		{
			var newTag:Image = new Image();
			newTag.source = image;
			newTag.toolTip = title;
			tagCache[type + id] = newTag;
			return newTag;
		}
	}
}
