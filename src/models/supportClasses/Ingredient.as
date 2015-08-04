package models.supportClasses
{
    [Bindable]
    public class Ingredient
    {
        public function Ingredient(aId:Number = NaN, aName:String = "", aVol:uint = 0, aDescription:String = "", aCategory:String = "")
        {
            id = aId;
            name = aName;
            vol = aVol;
            description = aDescription;
            category = aCategory;
        }

        public var id:Number;
        public var name:String;
        public var vol:uint;
        public var description:String;
        public var category:String;
    }
}