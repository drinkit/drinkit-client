package models.supportClasses
{
	import flash.display.Bitmap;
	import flash.display.Loader;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.utils.ByteArray;
	
	import mx.utils.Base64Decoder;
	
	import utils.ParserUtil;

	public class CocktailMini extends EventDispatcher
	{
		public var id:Number;
		public var name:String;
		public var cocktailTypeId:Number;
		public var ingredientIds:Array;
		public var optionIds:Array;
		
		private var _image:Bitmap;
		
		[Bindable("imageDecoded")]
		public function get image():Bitmap
		{
			return _image;
		}

		public function set thumbnail(value:String):void
		{
			if (value)
			{
				var decoder:Base64Decoder = new Base64Decoder();
				decoder.decode(value);
				var loader:Loader = new Loader();
				loader.contentLoaderInfo.addEventListener(Event.COMPLETE, onImageLoad);
				loader.loadBytes(decoder.toByteArray());
			}
		}

		public function CocktailMini(aId:Number = -1, aName:String = "", aThumbnail:String = null, aIngredients:Array = null)
		{
			id = aId;
			name = aName;
			thumbnail = aThumbnail;
			ingredientIds = aIngredients;
		}

		private function onImageLoad(event:Event):void
		{
			_image = event.target.content;
			dispatchEvent(new Event("imageDecoded"));
		}


	}
}