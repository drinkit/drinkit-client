package models.supportClasses
{
	public class Cocktail
	{
		public var id:Number;
		public var name:String;
		public var ingredients:Array; // of ingredient's ids
		public var optionals:Array;
		
		public function Cocktail(aId:Number = -1, aName:String = "")
		{
			id = aId;
			name = aName;
		}
	}
}