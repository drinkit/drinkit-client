package utils
{
	import flash.utils.ByteArray;
	
	public class ParserUtil
	{
		public static function stringToByteArray(value:String):ByteArray
		{
			var result:ByteArray = new ByteArray();
			var curPos:uint;
			result.writeMultiByte(value, "utf-8");
			return result;
		}
	}
}
