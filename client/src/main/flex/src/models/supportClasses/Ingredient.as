package models.supportClasses
{
	public class Ingredient
	{
		public var id:Number;
		public var name:String;
		public var vol:uint;
		
		public function Ingredient(aId:Number, aName:String, aVol:uint)
		{
			id = aId;
			name = aName;
			vol = aVol;
		}
	}
}