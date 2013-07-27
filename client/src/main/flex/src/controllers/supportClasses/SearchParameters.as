package controllers.supportClasses
{
	public class SearchParameters
	{
		public var cocktailTypes:Array;
		public var ingredients:Array;
		public var optionals:Array;
		//
		public var isBurning:Boolean;
		public var isWithIce:Boolean;
		public var isChecked:Boolean;
		public var isIBA:Boolean;
		public var isFlacky:Boolean;
		
		public function SearchParameters()
		{
		}
		
		public function toString():String
		{
			return JSON.stringify(this);
		}
	}
}