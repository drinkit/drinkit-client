/**
 * Created with IntelliJ IDEA.
 * User: Crabar
 * Date: 27.07.13
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
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
