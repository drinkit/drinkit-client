package models.supportClasses
{
	public class Ingredient
	{
		public var id:Number;
		public var name:String;
		public var vol:uint;
		
		public function Ingredient(aId:Number = -1, aName:String = "", aVol:uint = 0)
		{
			id = aId;
			name = aName;
			vol = aVol;
		}
	}
}