package models.supportClasses
{
    [Bindable]
    public class Ingredient
    {
        public function Ingredient(id:Number = NaN, name:String = "", vol:uint = 0, description:String = "", category:String = "", alias:Array = null)
        {
            this.id = id;
            this.name = name;
            this.vol = vol;
            this.description = description;
            this.category = category;
            this.alias = alias ? alias : [];
        }

        public var id:Number;
        public var name:String;
        public var vol:uint;
        public var description:String;
        public var category:String;
        public var alias:Array;
    }
}