package models.supportClasses
{
	import flash.display.Bitmap;
	import flash.display.Loader;
	import flash.events.Event;
	import flash.utils.ByteArray;
	
	import mx.utils.Base64Decoder;
	
	import utils.ParserUtil;

	public class CocktailMini
	{
		public var id:Number;
		public var name:String;
		public var ingredients:Array; // of ingredient's ids
		public var options:Array;
		
		[Bindable]
		public var image:Bitmap;

		private var _thumbnail:String;
		
		public function get thumbnail():String
		{
			return _thumbnail;
		}
		
		public function set thumbnail(value:String):void
		{
			if (value)
			{
				thumbnail = value;
				var decoder:Base64Decoder = new Base64Decoder();
				decoder.decode(value);
				var imageBA:ByteArray = decoder.toByteArray();
				var loader:Loader = new Loader();
				loader.contentLoaderInfo.
					loader.contentLoaderInfo.addEventListener(Event.COMPLETE, onImageLoad);
				loader.loadBytes(imageBA);
			}
		}

		public function CocktailMini(aId:Number = -1, aName:String = "", aThumbnail:String = null, aIngredients:Array = null)
		{
			id = aId;
			name = aName;
			thumbnail = aThumbnail;
			ingredients = aIngredients;
		}

		private function onImageLoad(event:Event):void
		{
			image = event.target.content;
		}


	}
}