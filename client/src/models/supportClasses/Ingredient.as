package models.supportClasses
{
    [Bindable]
    public class Ingredient
    {
        public function Ingredient(aId:Number = NaN, aName:String = "", aVol:uint = 0, aDescription:String = "")
        {
            id = aId;
            name = aName;
            vol = aVol;
            description = aDescription;
        }
        public var id:Number;
        public var name:String;
        public var vol:uint;
        public var description:String;
    }
}