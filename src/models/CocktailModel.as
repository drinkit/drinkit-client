package models
{

    import flash.events.EventDispatcher;

    import mx.collections.ArrayCollection;
import mx.collections.ArrayList;

    [Bindable]
    public class CocktailModel extends EventDispatcher
    {
        public static const BIG_IMAGE_WIDTH:Number = 384;
        public static const BIG_IMAGE_HEIGHT:Number = 512;
        public static const SMALL_IMAGE_WIDTH:Number = 132;
        public static const SMALL_IMAGE_HEIGHT:Number = 176;

        public function CocktailModel(id:Number = NaN)
        {
            this.id = id;
        }

        public var id:Number;
        public var name:String;
        public var description:String;
        public var cocktailTypeId:int;
        private var _ingredientsWithQuantities:Array;
        public var options:Array;
        public var imageUrl:String;
        public var thumbnailUrl:String;
        public var published:Boolean;

        public function get ingredientsWithQuantitiesProvider():ArrayList
        {
            return new ArrayList(_ingredientsWithQuantities);
        }

        public function get ingredientsWithQuantities():Array {
            return _ingredientsWithQuantities;
        }

        public function set ingredientsWithQuantities(value:Array):void {
            if (value) {
                _ingredientsWithQuantities = value.sortOn("quantity", Array.NUMERIC | Array.DESCENDING);
            }
        }
    }
}