package controllers.supportClasses
{
	public class SearchParameters
	{
		public var cocktailTypes:Array;
		public var ingredients:Array;
		public var optionals:Array;
		
		public function SearchParameters()
		{
		}
		
		public function toString():String
		{
			return JSON.stringify(this);
		}
	}
}