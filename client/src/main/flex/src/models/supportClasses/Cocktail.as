package models.supportClasses
{
	import flash.display.Bitmap;
	import flash.display.Loader;
	import flash.events.Event;
	import flash.utils.ByteArray;
	
	import utils.ParserUtil;

	public class Cocktail
	{
		public var id:Number;
		public var name:String;
		public var ingredients:Array; // of ingredient's ids
		public var optionals:Array;
		public var image:Bitmap;

		private var _image64:String;

		public function Cocktail(aId:Number = -1, aName:String = "", aImage64:String = null, aIngredients:Array = null)
		{
			id = aId;
			name = aName;
			image64 = aImage64;
			ingredients = aIngredients;
		}

		public function get image64():String
		{
			return _image64;
		}

		public function set image64(value:String):void
		{
			if (value)
			{
				_image64 = value;
				var imageBA:ByteArray = ParserUtil.stringToByteArray(value);
//				imageBA.position = 0;
//				trace(imageBA.readByte());
				var loader:Loader = new Loader();
				loader.contentLoaderInfo.addEventListener(Event.COMPLETE, onImageLoad);
//				loader.loadBytes(imageBA);
			}
		}

		private function onImageLoad(event:Event):void
		{
//			image = event.target.data;
		}


	}
}