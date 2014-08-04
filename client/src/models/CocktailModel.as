package models
{
    import flash.display.Bitmap;
    import flash.display.Loader;
    import flash.events.Event;
    import flash.events.EventDispatcher;

    import mx.collections.ArrayCollection;
    import mx.utils.Base64Decoder;

    public class CocktailModel extends EventDispatcher
    {
        public static const BIG_IMAGE_WIDTH:Number = 384;
        public static const BIG_IMAGE_HEIGHT:Number = 512;
        public static const SMALL_IMAGE_WIDTH:Number = 132;
        public static const SMALL_IMAGE_HEIGHT:Number = 176;

        public function CocktailModel()
        {
        }
        public var id:Number;
        public var name:String;
        public var description:String;
        public var cocktailTypeId:int;
        public var cocktailIngredients:Array;
        public var options:Array;

        public function get ingredientWithQuantitiesProvider():ArrayCollection
        {
            return new ArrayCollection(cocktailIngredients);
        }

        private var _image:Bitmap;

        public function set image(value:String):void
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

        [Bindable("imageLoaded")]
        public function get bigImage():Bitmap
        {
            return _image;
        }

        private function onImageLoad(event:Event):void
        {
            _image = event.target.content;
            dispatchEvent(new Event("imageLoaded"));
        }

    }
}